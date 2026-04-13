package org.example;

import org.example.model.Book;
import org.example.model.Loan;
import org.example.model.Member;
import org.example.repository.BookRepository;
import org.example.repository.LoanRepository;
import org.example.repository.MemberRepository;
import org.example.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoanServiceTest {

    private BookRepository bookRepository;
    private MemberRepository memberRepository;
    private LoanRepository loanRepository;
    private LoanService service;

    // Test fixtures
    private Book book;
    private Member member;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
        memberRepository = new MemberRepository();
        loanRepository = new LoanRepository();
        service = new LoanService(loanRepository, bookRepository, memberRepository);

        // Pre-populate one book (2 copies) and one member
        book = new Book("B1", "Clean Code", "Robert Martin", "isbn1", 2);
        bookRepository.save(book);

        member = new Member("M1", "Alice Dupont", "alice@example.com");
        memberRepository.save(member);
    }

    // -------------------------------------------------------------------------
    // borrowBook
    // -------------------------------------------------------------------------

    @Test
    void borrowBook_shouldCreateLoanAndDecrementCopies() {
        Loan loan = service.borrowBook("L1", "B1", "M1");

        assertNotNull(loan);
        assertEquals("L1", loan.getId());
        assertEquals("B1", loan.getBookId());
        assertEquals("M1", loan.getMemberId());
        assertFalse(loan.isReturned());
        assertNotNull(loan.getLoanDate());
        assertNotNull(loan.getDueDate());

        assertEquals(1, bookRepository.findById("B1").get().getAvailableCopies());
    }

    @Test
    void borrowBook_shouldPersistLoanInRepository() {
        service.borrowBook("L1", "B1", "M1");

        assertTrue(loanRepository.findById("L1").isPresent());
    }

    @Test
    void borrowBook_shouldThrowIfBookNotFound() {
        assertThrows(IllegalArgumentException.class,
                () -> service.borrowBook("L1", "NONEXISTENT", "M1"));
    }

    @Test
    void borrowBook_shouldThrowIfMemberNotFound() {
        assertThrows(IllegalArgumentException.class,
                () -> service.borrowBook("L1", "B1", "NONEXISTENT"));
    }

    @Test
    void borrowBook_shouldThrowIfNoAvailableCopies() {
        service.borrowBook("L1", "B1", "M1");
        service.borrowBook("L2", "B1", "M1"); // last copy

        assertThrows(IllegalArgumentException.class,
                () -> service.borrowBook("L3", "B1", "M1")); // none left
    }

    // -------------------------------------------------------------------------
    // returnBook
    // -------------------------------------------------------------------------

    @Test
    void returnBook_shouldMarkReturnedAndIncrementCopies() {
        service.borrowBook("L1", "B1", "M1");
        assertEquals(1, book.getAvailableCopies());

        Loan returned = service.returnBook("L1");

        assertTrue(returned.isReturned());
        assertNotNull(returned.getReturnDate());
        assertEquals(2, bookRepository.findById("B1").get().getAvailableCopies());
    }

    @Test
    void returnBook_shouldThrowIfLoanNotFound() {
        assertThrows(IllegalArgumentException.class,
                () -> service.returnBook("NONEXISTENT"));
    }

    @Test
    void returnBook_shouldThrowIfAlreadyReturned() {
        service.borrowBook("L1", "B1", "M1");
        service.returnBook("L1");

        assertThrows(IllegalArgumentException.class,
                () -> service.returnBook("L1"));
    }

    // -------------------------------------------------------------------------
    // getActiveLoans
    // -------------------------------------------------------------------------

    @Test
    void getActiveLoans_shouldReturnOnlyUnreturnedLoans() {
        service.borrowBook("L1", "B1", "M1");
        service.borrowBook("L2", "B1", "M1");
        service.returnBook("L1");

        List<Loan> active = service.getActiveLoans();

        assertEquals(1, active.size());
        assertEquals("L2", active.get(0).getId());
    }

    @Test
    void getActiveLoans_shouldReturnEmptyListWhenNoneActive() {
        assertTrue(service.getActiveLoans().isEmpty());
    }

    // -------------------------------------------------------------------------
    // getLoansByMember
    // -------------------------------------------------------------------------

    @Test
    void getLoansByMember_shouldReturnAllLoansForMember() {
        Member member2 = new Member("M2", "Bob", "bob@example.com");
        memberRepository.save(member2);

        service.borrowBook("L1", "B1", "M1");
        service.borrowBook("L2", "B1", "M2");

        List<Loan> loans = service.getLoansByMember("M1");

        assertEquals(1, loans.size());
        assertEquals("L1", loans.get(0).getId());
    }

    @Test
    void getLoansByMember_shouldIncludeReturnedLoans() {
        service.borrowBook("L1", "B1", "M1");
        service.returnBook("L1");

        List<Loan> loans = service.getLoansByMember("M1");

        assertEquals(1, loans.size());
        assertTrue(loans.get(0).isReturned());
    }

    @Test
    void getLoansByMember_shouldReturnEmptyListForMemberWithNoLoans() {
        assertTrue(service.getLoansByMember("M1").isEmpty());
    }

    // -------------------------------------------------------------------------
    // getOverdueLoans
    // -------------------------------------------------------------------------

    @Test
    void getOverdueLoans_shouldReturnEmptyWhenNoneOverdue() {
        service.borrowBook("L1", "B1", "M1");

        // A freshly created loan is due in 14 days — not overdue yet
        assertTrue(service.getOverdueLoans().isEmpty());
    }

    @Test
    void getOverdueLoans_shouldNotIncludeReturnedLoans() {
        Loan loan = service.borrowBook("L1", "B1", "M1");
        // Simulate overdue by directly manipulating the loan (white-box test)
        loan.markReturned();

        // Even if somehow the dates were past due, returned loans should not appear
        assertTrue(service.getOverdueLoans().isEmpty());
    }
}

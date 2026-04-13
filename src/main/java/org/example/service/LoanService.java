package org.example.service;

import org.example.model.Loan;
import org.example.repository.BookRepository;
import org.example.repository.LoanRepository;
import org.example.repository.MemberRepository;

import java.util.List;

/**
 * ============================================================
 *  GROUP 3 — Loan Management
 * ============================================================
 *
 * Your task: implement every method that throws UnsupportedOperationException.
 * Do NOT modify the models, repositories, or tests.
 *
 * This service depends on all three repositories.
 * Read the Javadoc carefully — each method has specific rules.
 *
 * Git workflow:
 *   1. Create your branch:
 *        git checkout -b feature/loan-management
 *   2. Implement the TODOs below
 *   3. Run the tests:
 *        ./gradlew test --tests "org.example.LoanServiceTest"
 *   4. Commit and push when all tests pass:
 *        git add .
 *        git commit -m "feat: implement LoanService"
 *        git push origin feature/loan-management
 *   5. Open a Pull Request targeting the main branch
 * ============================================================
 */
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public LoanService(LoanRepository loanRepository,
                       BookRepository bookRepository,
                       MemberRepository memberRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    /**
     * Borrow a book for a member. Creates and saves a Loan, then decrements the
     * book's available copy count.
     *
     * Rules (throw IllegalArgumentException if violated):
     *   - The book with bookId must exist
     *   - The member with memberId must exist
     *   - The book must have at least one available copy (availableCopies >= 1)
     *
     * @param loanId   unique id for this loan
     * @param bookId   id of the book to borrow
     * @param memberId id of the borrowing member
     * @return the created Loan
     */
    public Loan borrowBook(String loanId, String bookId, String memberId) {
        // TODO: validate that book, member exist and book has copies available
        // TODO: create and save the Loan
        // TODO: decrement the book's availableCopies by 1 (then save the book again)
        // TODO: return the created loan
        throw new UnsupportedOperationException("Not implemented yet — Group 3");
    }

    /**
     * Mark a loan as returned and increment the book's available copy count.
     *
     * Rules (throw IllegalArgumentException if violated):
     *   - The loan with loanId must exist
     *   - The loan must not already be returned
     *
     * @return the updated Loan
     */
    public Loan returnBook(String loanId) {
        // TODO: find the loan (throw IllegalArgumentException if not found)
        // TODO: throw IllegalArgumentException if already returned
        // TODO: call loan.markReturned()
        // TODO: find the book and increment its availableCopies by 1
        // TODO: save both and return the updated loan
        throw new UnsupportedOperationException("Not implemented yet — Group 3");
    }

    /**
     * Return all loans that have not yet been returned.
     */
    public List<Loan> getActiveLoans() {
        // TODO: filter all loans where isReturned() == false
        throw new UnsupportedOperationException("Not implemented yet — Group 3");
    }

    /**
     * Return the complete loan history (active and returned) for a specific member.
     * Return an empty list if the member has no loans.
     */
    public List<Loan> getLoansByMember(String memberId) {
        // TODO: filter all loans where memberId matches
        throw new UnsupportedOperationException("Not implemented yet — Group 3");
    }

    /**
     * Return all loans that are overdue (not returned and past their due date).
     * Hint: use loan.isOverdue()
     */
    public List<Loan> getOverdueLoans() {
        // TODO: filter all loans where isOverdue() == true
        throw new UnsupportedOperationException("Not implemented yet — Group 3");
    }
}

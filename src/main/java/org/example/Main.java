package org.example;

import org.example.model.Book;
import org.example.model.Loan;
import org.example.model.Member;
import org.example.repository.BookRepository;
import org.example.repository.LoanRepository;
import org.example.repository.MemberRepository;
import org.example.service.BookService;
import org.example.service.LoanService;
import org.example.service.MemberService;

/**
 * Entry point — demonstrates the three services working together.
 *
 * This file will throw UnsupportedOperationException until all groups
 * have implemented their service. Run it after all Pull Requests are merged.
 */
public class Main {

    public static void main(String[] args) {
        // --- Repositories (shared in-memory storage) ---
        BookRepository bookRepo     = new BookRepository();
        MemberRepository memberRepo = new MemberRepository();
        LoanRepository loanRepo     = new LoanRepository();

        // --- Services (implemented by each group) ---
        BookService   bookService   = new BookService(bookRepo);
        MemberService memberService = new MemberService(memberRepo);
        LoanService   loanService   = new LoanService(loanRepo, bookRepo, memberRepo);
        toto
        // --- Group 1: add some books ---
        Book cleanCode = bookService.addBook("B1", "Clean Code", "Robert Martin", "978-0132350884", 2);
        Book pragProg  = bookService.addBook("B2", "The Pragmatic Programmer", "Hunt & Thomas", "978-0135957059", 1);
        System.out.println("Books added: " + bookService.listAll().size());

        // --- Group 2: register some members ---
        Member alice = memberService.registerMember("M1", "Alice Dupont", "alice@example.com");
        Member bob   = memberService.registerMember("M2", "Bob Martin",   "bob@example.com");
        System.out.println("Members registered: " + memberService.listAll().size());

        // --- Group 3: borrow and return ---
        Loan loan1 = loanService.borrowBook("L1", "B1", "M1");
        Loan loan2 = loanService.borrowBook("L2", "B2", "M2");
        System.out.println("Active loans: " + loanService.getActiveLoans().size());

        loanService.returnBook("L1");
        System.out.println("Active loans after return: " + loanService.getActiveLoans().size());

        System.out.println("Alice's loan history: " + loanService.getLoansByMember("M1").size());

        System.out.println("\nAll done! Library system is working.");
    }
}

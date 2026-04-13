package org.example.model;

import java.time.LocalDate;

public class Loan {

    private String id;
    private String bookId;
    private String memberId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean returned;

    public Loan(String id, String bookId, String memberId) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.loanDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(14);
        this.returned = false;
    }

    public String getId()               { return id; }
    public String getBookId()           { return bookId; }
    public String getMemberId()         { return memberId; }
    public LocalDate getLoanDate()      { return loanDate; }
    public LocalDate getDueDate()       { return dueDate; }
    public LocalDate getReturnDate()    { return returnDate; }
    public boolean isReturned()         { return returned; }

    public void markReturned() {
        this.returned = true;
        this.returnDate = LocalDate.now();
    }

    public boolean isOverdue() {
        return !returned && LocalDate.now().isAfter(dueDate);
    }

    @Override
    public String toString() {
        return String.format("Loan{id='%s', bookId='%s', memberId='%s', due=%s, returned=%b}",
                id, bookId, memberId, dueDate, returned);
    }
}

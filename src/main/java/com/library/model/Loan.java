package com.library.model;

import com.library.model.enums.LoanStatus;
import java.time.LocalDate;

public class Loan {
    private String loanId;
    private String bookIsbn;
    private String memberId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private LoanStatus status;

    public Loan() {}

    public Loan(String loanId, String bookIsbn, String memberId, LocalDate issueDate, int loanPeriodDays) {
        this.loanId = loanId;
        this.bookIsbn = bookIsbn;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.dueDate = issueDate.plusDays(loanPeriodDays);
        this.status = LoanStatus.ACTIVE;
    }

    public String getLoanId() { return loanId; }
    public void setLoanId(String loanId) { this.loanId = loanId; }

    public String getBookIsbn() { return bookIsbn; }
    public void setBookIsbn(String bookIsbn) { this.bookIsbn = bookIsbn; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public LoanStatus getStatus() { return status; }
    public void setStatus(LoanStatus status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Loan [LoanID=%s, Book=%s, Member=%s, Issued=%s, Due=%s, Returned=%s, Status=%s]",
                loanId, bookIsbn, memberId, issueDate, dueDate, returnDate, status);
    }
}
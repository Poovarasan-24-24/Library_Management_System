package com.library.service;

import com.library.dto.IssueBookRequest;
import com.library.dto.ReturnBookRequest;
import com.library.model.Loan;
import java.util.List;

public interface LoanService {
    Loan issueBook(IssueBookRequest request);
    Loan returnBook(ReturnBookRequest request);
    List<Loan> getAllLoans();
}
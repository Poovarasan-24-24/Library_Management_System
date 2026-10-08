package com.library.repository;

import com.library.model.Loan;
import java.util.List;
import java.util.Optional;

public interface LoanRepository {
    void save(Loan loan);
    Optional<Loan> findById(String loanId);
    Optional<Loan> findActiveLoanByIsbn(String isbn);
    List<Loan> findActiveLoansByMember(String memberId);
    List<Loan> findAll();
    void update(Loan loan);
}
package com.library.repository.impl;

import com.library.model.Loan;
import com.library.model.enums.LoanStatus;
import com.library.repository.LoanRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryLoanRepository implements LoanRepository {
    private final Map<String, Loan> storage = new ConcurrentHashMap<>();

    @Override
    public void save(Loan loan) {
        storage.put(loan.getLoanId(), loan);
    }

    @Override
    public Optional<Loan> findById(String loanId) {
        return Optional.ofNullable(storage.get(loanId));
    }

    @Override
    public Optional<Loan> findActiveLoanByIsbn(String isbn) {
        return storage.values().stream()
                .filter(l -> l.getBookIsbn().equals(isbn) && l.getStatus() == LoanStatus.ACTIVE)
                .findFirst();
    }

    @Override
    public List<Loan> findActiveLoansByMember(String memberId) {
        return storage.values().stream()
                .filter(l -> l.getMemberId().equals(memberId) && l.getStatus() == LoanStatus.ACTIVE)
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void update(Loan loan) {
        storage.put(loan.getLoanId(), loan);
    }
}
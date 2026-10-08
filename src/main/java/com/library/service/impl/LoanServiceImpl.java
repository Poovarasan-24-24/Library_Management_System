package com.library.service.impl;

import com.library.dto.IssueBookRequest;
import com.library.dto.ReturnBookRequest;
import com.library.exception.BookAlreadyIssuedException;
import com.library.exception.BookNotFoundException;
import com.library.exception.MemberNotFoundException;
import com.library.model.Book;
import com.library.model.Loan;
import com.library.model.Member;
import com.library.model.enums.BookStatus;
import com.library.model.enums.LoanStatus;
import com.library.repository.BookRepository;
import com.library.repository.LoanRepository;
import com.library.repository.MemberRepository;
import com.library.service.FineService;
import com.library.service.LoanService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class LoanServiceImpl implements LoanService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;
    private final FineService fineService;

    public LoanServiceImpl(BookRepository bookRepository, MemberRepository memberRepository,
                           LoanRepository loanRepository, FineService fineService) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
        this.fineService = fineService;
    }

    @Override
    public Loan issueBook(IssueBookRequest request) {
        Book book = bookRepository.findByIsbn(request.getIsbn())
                .orElseThrow(() -> new BookNotFoundException("Book not found: " + request.getIsbn()));

        if (book.getStatus() == BookStatus.ISSUED) {
            throw new BookAlreadyIssuedException("Book is already issued.");
        }

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException("Member not found: " + request.getMemberId()));

        book.setStatus(BookStatus.ISSUED);
        bookRepository.update(book);

        String loanId = "LOAN-" + UUID.randomUUID().toString().substring(0, 8);
        Loan loan = new Loan(loanId, book.getIsbn(), member.getMemberId(), LocalDate.now(), 14);
        loanRepository.save(loan);

        return loan;
    }

    @Override
    public Loan returnBook(ReturnBookRequest request) {
        Loan loan = loanRepository.findActiveLoanByIsbn(request.getIsbn())
                .orElseThrow(() -> new IllegalArgumentException("Active loan not found for ISBN: " + request.getIsbn()));

        Book book = bookRepository.findByIsbn(request.getIsbn())
                .orElseThrow(() -> new BookNotFoundException("Book not found: " + request.getIsbn()));

        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);

        if (loan.getReturnDate().isAfter(loan.getDueDate())) {
            fineService.calculateAndCreateFine(loan);
        }

        loanRepository.update(loan);

        book.setStatus(BookStatus.AVAILABLE);
        bookRepository.update(book);

        return loan;
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}
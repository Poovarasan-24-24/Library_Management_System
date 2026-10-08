package com.library.controller;

import com.library.dto.IssueBookRequest;
import com.library.dto.ReturnBookRequest;
import com.library.model.Loan;
import com.library.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/issue")
    public ResponseEntity<Loan> issueBook(@RequestBody IssueBookRequest request) {
        return ResponseEntity.ok(loanService.issueBook(request));
    }

    @PostMapping("/return")
    public ResponseEntity<Loan> returnBook(@RequestBody ReturnBookRequest request) {
        return ResponseEntity.ok(loanService.returnBook(request));
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }
}
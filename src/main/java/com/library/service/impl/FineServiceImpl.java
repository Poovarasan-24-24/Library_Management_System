package com.library.service.impl;

import com.library.model.Fine;
import com.library.model.Loan;
import com.library.service.FineService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class FineServiceImpl implements FineService {

    private static final BigDecimal DAILY_FINE_RATE = new BigDecimal("2.00");

    @Override
    public Fine calculateAndCreateFine(Loan loan) {
        long overdueDays = ChronoUnit.DAYS.between(loan.getDueDate(), loan.getReturnDate());
        if (overdueDays <= 0) {
            return null;
        }

        BigDecimal fineAmount = DAILY_FINE_RATE.multiply(BigDecimal.valueOf(overdueDays));
        String fineId = "FINE-" + UUID.randomUUID().toString().substring(0, 8);

        return new Fine(fineId, loan.getLoanId(), loan.getMemberId(), fineAmount);
    }

    @Override
    public void payFine(String fineId) {
        // Business logic to settle unpaid fine balances
    }
}
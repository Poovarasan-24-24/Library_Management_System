package com.library.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Fine {
    private String fineId;
    private String loanId;
    private String memberId;
    private BigDecimal amount;
    private boolean paid;
    private LocalDate generatedDate;

    public Fine() {}

    public Fine(String fineId, String loanId, String memberId, BigDecimal amount) {
        this.fineId = fineId;
        this.loanId = loanId;
        this.memberId = memberId;
        this.amount = amount;
        this.paid = false;
        this.generatedDate = LocalDate.now();
    }

    public String getFineId() { return fineId; }
    public void setFineId(String fineId) { this.fineId = fineId; }

    public String getLoanId() { return loanId; }
    public void setLoanId(String loanId) { this.loanId = loanId; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }

    public LocalDate getGeneratedDate() { return generatedDate; }
    public void setGeneratedDate(LocalDate generatedDate) { this.generatedDate = generatedDate; }
}
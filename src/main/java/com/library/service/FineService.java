package com.library.service;

import com.library.model.Fine;
import com.library.model.Loan;

public interface FineService {
    Fine calculateAndCreateFine(Loan loan);
    void payFine(String fineId);
}
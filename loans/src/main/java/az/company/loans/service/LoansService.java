package az.company.loans.service;

import az.company.loans.model.request.LoansRequest;
import az.company.loans.model.response.LoansResponse;

import java.math.BigDecimal;
import java.util.List;

public interface LoansService {

    List<LoansResponse> getLoans();
    LoansResponse getLoan(Long id);
    LoansResponse getLoanByCustomerId(Long customerId);
    void saveLoan(LoansRequest request);
    void updateLoan(LoansRequest request, Long loansId);
    void updateOutstandingAmount(Long id, BigDecimal outstandingAmount);
    void deleteLoan(Long id);

}

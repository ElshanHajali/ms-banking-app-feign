package az.company.loans.controller;

import az.company.loans.model.request.LoansRequest;
import az.company.loans.model.response.LoansResponse;
import az.company.loans.service.LoansService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/loans")
@RequiredArgsConstructor
public class LoansController {
    private final LoansService loansService;

    ///////// Fetch /////////
    @GetMapping
    public List<LoansResponse> getLoans() {
        return loansService.getLoans();
    }

    @GetMapping("{id}")
    public LoansResponse getLoanById(@PathVariable Long id){
        return loansService.getLoan(id);
    }

    @GetMapping("/customers/{customerId}")
    public LoansResponse getLoansByCustomerId(@PathVariable long customerId) {
        return loansService.getLoanByCustomerId(customerId);
    }

    /////// Management ///////
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveLoan(@RequestBody LoansRequest request) {
        loansService.saveLoan(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLoan(@PathVariable Long id,
                           @RequestBody LoansRequest request) {
        loansService.updateLoan(request, id);
    }

    @PatchMapping("/{id}/outstanding-amount")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTotalLoan(@PathVariable Long id,
                                @RequestParam("value") BigDecimal outstandingAmount) {
        loansService.updateOutstandingAmount(id, outstandingAmount);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLoan(@PathVariable Long id) {
        loansService.deleteLoan(id);
    }
}

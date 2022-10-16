package az.company.accounts.client;

import az.company.accounts.model.client.response.LoansResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "loans", url = "${client.loans.endpoint}/v1/loans")
public interface LoansClient {

    @GetMapping("customers/{customerId}")
    LoansResponseDto getLoanByCustomerId(@PathVariable Long customerId);
}

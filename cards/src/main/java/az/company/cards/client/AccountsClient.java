package az.company.cards.client;

import az.company.cards.model.client.AccountsResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "accounts", url = "${client.accounts.endpoint}/v1/accounts")
public interface AccountsClient {

    @GetMapping("customers/{customerId}")
    AccountsResponseDto getAccountByCustomerId(@PathVariable Long customerId);

}

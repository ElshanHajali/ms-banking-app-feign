package az.company.customers.controller;

import az.company.customers.model.request.CustomersRequest;
import az.company.customers.model.response.CustomersResponse;
import az.company.customers.service.CustomersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/customers")
@RequiredArgsConstructor
public class CustomersController {
    private final CustomersService customersService;

    ////// Fetch ///////
    @GetMapping
    public List<CustomersResponse> getCustomers(){
        return customersService.getCustomers();
    }

    @GetMapping("{id}")
    public CustomersResponse getCustomer(@PathVariable Long id){
        return customersService.getCustomer(id);
    }

    ////// Management //////
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody CustomersRequest request) {
        customersService.saveCustomer(request);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable Long id,
                               @RequestBody CustomersRequest request) {
        customersService.updateCustomer(id, request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id){
        customersService.deleteCustomer(id);
    }
}

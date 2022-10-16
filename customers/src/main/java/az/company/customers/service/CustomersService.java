package az.company.customers.service;

import az.company.customers.model.request.CustomersRequest;
import az.company.customers.model.response.CustomersResponse;

import java.util.List;

public interface CustomersService {

    List<CustomersResponse> getCustomers();
    CustomersResponse getCustomer(Long id);

    void saveCustomer(CustomersRequest request);
    void updateCustomer(Long id, CustomersRequest request);
    void deleteCustomer(Long id);

}

package az.company.customers.service;

import az.company.customers.dao.entity.CustomersEntity;
import az.company.customers.dao.repository.CustomersRepository;
import az.company.customers.model.request.CustomersRequest;
import az.company.customers.model.response.CustomersResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static az.company.customers.mapper.CustomersMapper.MAP;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomersServiceImpl implements CustomersService {
    private final CustomersRepository customersRepository;

    @Override
    public List<CustomersResponse> getCustomers() {
        log.info("ActionLog.getCustomers.start");
        return customersRepository.findAll().stream()
                .map(MAP::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomersResponse getCustomer(Long id) {
        log.info("ActionLog.getCustomer.start");
        return MAP.entityToResponse(fetchCustomerById(id));
    }

    @Override
    public void saveCustomer(CustomersRequest request) {
        log.info("ActionLog.saveCustomer.start");
        customersRepository.save(MAP.requestToEntity(request));
        log.info("ActionLog.saveCustomer.success");
    }

    @Override
    public void updateCustomer(Long id, CustomersRequest request) {
        log.info("ActionLog.updateCustomer.start");
        var customer = fetchCustomerById(id);

        MAP.updateDesiredCustomerField(customer, request);

        customersRepository.save(customer);
        log.info("ActionLog.updateCustomer.success");
    }

    @Override
    public void deleteCustomer(Long id) {
        log.info("ActionLog.deleteCustomer.start");

        customersRepository.delete(fetchCustomerById(id));

        log.info("ActionLog.deleteCustomer.success");
    }

    private CustomersEntity fetchCustomerById(Long id) {
        return customersRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ActionLog.getCustomerById.error id: {}", id);
                    return new RuntimeException();
                });
    }
}

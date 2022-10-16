package az.company.customers.mapper;

import az.company.customers.dao.entity.CustomersEntity;
import az.company.customers.model.request.CustomersRequest;
import az.company.customers.model.response.CustomersResponse;
import org.apache.logging.log4j.util.Strings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomersMapper {
    CustomersMapper MAP = Mappers.getMapper(CustomersMapper.class);

    default CustomersResponse entityToResponse(CustomersEntity customer) {
        return CustomersResponse
                .builder()
                .birthDate(customer.getBirthDate())
                .email(customer.getEmail())
                .fullName(customer.getFullName())
                .registeredAt(customer.getRegisteredAt())
                .build();
    }

    default CustomersEntity requestToEntity(CustomersRequest request) {
        return CustomersEntity
                .builder()
                .birthDate(request.getBirthDate())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .build();
    }

    default void updateDesiredCustomerField(CustomersEntity customer, CustomersRequest request) {
        if (!(request.getBirthDate() == null) &&
            !(request.getBirthDate().isEqual(customer.getBirthDate()))) {
            customer.setBirthDate(request.getBirthDate());
        }
        if (Strings.isNotEmpty(request.getFullName()) &&
            Strings.isNotBlank(request.getFullName())) {
            customer.setFullName(request.getFullName());
        }
        if (Strings.isNotEmpty(request.getEmail()) &&
                Strings.isNotBlank(request.getEmail())) {
            customer.setEmail(request.getEmail());
        }
    }
}

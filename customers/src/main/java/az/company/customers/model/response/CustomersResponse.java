package az.company.customers.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomersResponse {

    private String fullName;
    private String email;
    private LocalDate birthDate;
    private LocalDateTime registeredAt;

}

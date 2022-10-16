package az.company.customers.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomersRequest {

    private String fullName;
    private String email;
    private LocalDate birthDate;

//{
//    "fullName" : ""
//    "email" : ""
//    "birthDate" : "yyyy-MM-dd"
//}
}

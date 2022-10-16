package az.company.loans.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoansRequest {

    private Long customerId;
    private LocalDate startDt; //yyyy-MM-dd
    private String type;
    private BigDecimal total;
    private BigDecimal amountPaid;

//{
//    "customerId" : ,
//    "startDt" : "yyyy-MM-dd",
//    "type" : "",
//    "total" : ,
//    "amountPaid" : ,
//}
}

package az.company.cards.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsRequest {

    private Long customerId;
    private String number;
    private String type;
    private BigDecimal totalLimit;
    private BigDecimal amountUsed;
    private BigDecimal availableAmount;

//{
//    "customerId" : ,
//    "number" : "",
//    "type" : "",
//    "totalLimit" : ,
//    "amountUsed" : ,
//    "availableAmount" :
//}
}

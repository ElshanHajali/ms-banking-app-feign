package az.company.loans.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoansResponse {

    private long customerId;
    private LocalDate startDt;
    private String type;
    private BigDecimal total;
    private BigDecimal amountPaid;
    private BigDecimal outstandingAmount;
    private LocalDateTime createdAt;

}

package az.company.accounts.model.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoansResponseDto {

    private long customerId;
    private LocalDate startDt;
    private String type;
    private BigDecimal total;
    private BigDecimal amountPaid;
    private BigDecimal outstandingAmount;
    private LocalDateTime createdAt;

}

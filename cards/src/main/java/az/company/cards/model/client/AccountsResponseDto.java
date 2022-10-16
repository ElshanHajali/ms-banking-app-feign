package az.company.cards.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountsResponseDto {

    private Long customerId;
    private String accountType;
    private String branchAddress;
    private LocalDateTime createdAt;
    private BigDecimal outstandingLoanAmount;

}

package az.company.loans.dao.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loans")
public class LoansEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long customerId;

    @NotNull
    private LocalDate startDt; //yyyy-MM-dd

    @NotNull
    private String type;

    private BigDecimal total;
    private BigDecimal amountPaid;
    private BigDecimal outstandingAmount;

    @CreationTimestamp
    private LocalDateTime createdAt; // yyyy-MM-dd-hhhh-mm-ss
    // NOTE: yyyy-MM-dd-hhhh-mm-ss.zzz year-month-day-hour-minute-second milli-second

//    "customerId" : ,
//    "startDt" : "",
//    "loanType" : "",
//    "totalLoan" :  ,
//    "amountPaid" :  ,
//    "outstandingAmount" :  ,
}

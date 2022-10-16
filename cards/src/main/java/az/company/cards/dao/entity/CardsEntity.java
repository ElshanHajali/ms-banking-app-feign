package az.company.cards.dao.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
public class CardsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long customerId;
    private String number;
    private String type;
    private BigDecimal totalLimit;
    private BigDecimal amountUsed;
    private BigDecimal availableAmount;

    @CreationTimestamp
    private LocalDateTime createdAt; //yyyy-MM-dd-HH-mm-ss.zzz
}

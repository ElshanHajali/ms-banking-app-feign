package az.company.customers.dao.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class CustomersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String fullName;
    private String email;

    private LocalDate birthDate;

    @CreationTimestamp
    private LocalDateTime registeredAt;

}

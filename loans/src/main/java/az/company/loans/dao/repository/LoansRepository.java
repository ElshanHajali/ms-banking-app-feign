package az.company.loans.dao.repository;

import az.company.loans.dao.entity.LoansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoansRepository extends JpaRepository<LoansEntity, Long> {

    @Query(value = """
            select *
            from loans l
            where l.customer_id=:customerId
            """, nativeQuery = true)
    List<LoansEntity> findByCustomerIdOrderByStartDtDesc(@Param("customerId") long customerId);

}

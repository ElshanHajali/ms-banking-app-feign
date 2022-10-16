package az.company.accounts.dao.repository;

import az.company.accounts.dao.entity.AccountsEntity;
import az.company.accounts.model.response.AccountsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface AccountsRepository extends JpaRepository<AccountsEntity, Long> {

    @Query(
            value = """
                    select *
                    from accounts a
                    where a.customer_id=:customerId
                    """, nativeQuery = true
    )
    AccountsEntity findByCustomerIdAndAccountType(@Param("customerId") long id);

}

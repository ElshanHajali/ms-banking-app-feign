package az.company.customers.dao.repository;

import az.company.customers.dao.entity.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<CustomersEntity, Long> {
}

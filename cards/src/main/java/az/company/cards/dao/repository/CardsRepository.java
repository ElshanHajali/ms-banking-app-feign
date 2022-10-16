package az.company.cards.dao.repository;

import az.company.cards.dao.entity.CardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CardsRepository extends JpaRepository<CardsEntity, Long> {

    List<CardsEntity> findByCustomerId(long customerId);

}




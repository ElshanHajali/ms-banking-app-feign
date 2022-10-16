package az.company.cards.service;

import az.company.cards.model.request.CardsRequest;
import az.company.cards.model.response.CardsResponse;

import java.math.BigDecimal;
import java.util.List;

public interface CardsService {

    List<CardsResponse> getCards();
    CardsResponse getCard(Long id);
    List<CardsResponse> getCardByCustomerId(Long customerId);
    void saveCard(CardsRequest cards);
    void updateCard(CardsRequest request, Long loansId);

    void updateAvailableAmount(Long id, BigDecimal amountLimit);
    void deleteCard(Long id);

}

package az.company.cards.service;

import az.company.cards.client.AccountsClient;
import az.company.cards.dao.entity.CardsEntity;
import az.company.cards.dao.repository.CardsRepository;
import az.company.cards.model.request.CardsRequest;
import az.company.cards.model.response.CardsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static az.company.cards.mapper.CardsMapper.MAP;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardsServiceImpl implements CardsService {
    private final CardsRepository cardsRepository;
    private final AccountsClient accountsClient;

    ////// Fetch //////
    @Override
    public List<CardsResponse> getCards() {
        log.info("ActionLog.getCards.start");
        return cardsRepository
                .findAll().stream()
                .map(MAP::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CardsResponse getCard(Long id) {
        log.error("ActionLog.getCard.start");
        var card = fetchCardById(id);

        var cardsResponse = MAP.entityToResponse(card);

        log.info("ActionLog.getCard.success card: {}", card);
        return cardsResponse;
    }

    @Override
    public List<CardsResponse> getCardByCustomerId(Long customerId) {
        log.info("ActionLog.getCardByCustomerId.start");

        var response = cardsRepository
                .findByCustomerId(customerId)
                .stream()
                .map(MAP::entityToResponse).toList();

        log.info("ActionLog.getCardByCustomerId.success cardId: {}", response);
        return response;
    }

    ////// Management //////
    @Override
    public void saveCard(CardsRequest request) {
        log.info("ActionLog.saveCard.start");

        var card = MAP.requestToEntity(request, accountsClient);


        cardsRepository.save(card);
        log.info("ActionLog.saveCard.success card: {}", card);
    }

    @Override
    public void updateCard(CardsRequest request, Long cardId) {
        log.info("ActionLog.updateCard.start");
        var card = fetchCardById(cardId);

        MAP.updateDesiredEntityField(card, request);

        cardsRepository.save(card);
        log.info("ActionLog.updateCard.success card: {}", card);
    }

    @Override
    public void updateAvailableAmount(Long id, BigDecimal amountLimit) {
        log.info("ActionLog.updateAvailableAmount.start");
        var card = fetchCardById(id);

        card.setAvailableAmount(amountLimit);

        cardsRepository.save(card);
        log.info("ActionLog.updateAvailableAmount.success card: {}", card);
    }

    @Override
    public void deleteCard(Long id) {
        log.info("ActionLog.deleteCard.start");
        var card = fetchCardById(id);

        cardsRepository.deleteById(id);
        log.info("ActionLog.deleteCard.success card: {}", card);
    }

    public CardsEntity fetchCardById(Long id) {
        return cardsRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ActionLog.getCardById.error cardId: {}", id);
                    return new RuntimeException();
                });
    }
}


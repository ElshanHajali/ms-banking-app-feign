package az.company.cards.mapper;

import az.company.cards.client.AccountsClient;
import az.company.cards.dao.entity.CardsEntity;
import az.company.cards.model.request.CardsRequest;
import az.company.cards.model.response.CardsResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper
public class CardsMapper {
    public static CardsMapper MAP = Mappers.getMapper(CardsMapper.class);

    public CardsEntity requestToEntity(CardsRequest request, AccountsClient accountsClient) {
        var outstandingLoanAmount =
                accountsClient
                        .getAccountByCustomerId(request.getCustomerId())
                        .getOutstandingLoanAmount();

        // if outstanding loan amount is less than 500, then save
        if (outstandingLoanAmount.compareTo(BigDecimal.valueOf(500)) < 0) {
            return CardsEntity
                    .builder()
                    .totalLimit(request.getTotalLimit())
                    .type(request.getType())
                    .number(request.getNumber())
                    .customerId(request.getCustomerId())
                    .availableAmount(request.getAvailableAmount())
                    .amountUsed(request.getAmountUsed())
                    .build();
        } else {
            throw new RuntimeException("""
                           Please pay loans,
                           loan should be less than 500 to create card
                           in this account
                    """);
        }
    }

    public CardsResponse entityToResponse(CardsEntity card) {
        return CardsResponse
                .builder()
                .amountUsed(card.getAmountUsed())
                .createdAt(card.getCreatedAt())
                .availableAmount(card.getAvailableAmount())
                .cardNumber(card.getNumber())
                .cardType(card.getType())
                .customerId(card.getCustomerId())
                .totalLimit(card.getTotalLimit())
                .build();
    }

    public void updateDesiredEntityField(CardsEntity card, CardsRequest request) {
        var cardNumber = request.getNumber();
        var cardType = request.getType();
        var amountUsed = request.getAmountUsed();
        var availableAmount = request.getAvailableAmount();
        var totalLimit = request.getTotalLimit();
        var customerId = request.getCustomerId();

        if (Strings.isNotEmpty(cardNumber) &&
                Strings.isNotBlank(cardNumber)) {
            card.setNumber(cardNumber);
        }
        if (Strings.isNotEmpty(cardType) &&
                Strings.isNotBlank(cardType)) {
            card.setType(cardType);
        }
        if (amountUsed != null) {
            card.setAmountUsed(amountUsed);
        }
        if (availableAmount != null) {
            card.setAvailableAmount(availableAmount);
        }
        if (totalLimit != null) {
            card.setTotalLimit(totalLimit);
        }
        if (customerId != null) {
            card.setCustomerId(customerId);
        }
    }
}

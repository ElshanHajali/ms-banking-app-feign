package az.company.cards.controller;

import az.company.cards.model.request.CardsRequest;
import az.company.cards.model.response.CardsResponse;
import az.company.cards.service.CardsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/cards")
@AllArgsConstructor
public class CardsController {
    private final CardsService cardsService;

    /////////Fetch/////////
    @GetMapping
    public List<CardsResponse> getCards() {
        return cardsService.getCards();
    }

    @GetMapping("{cardId}")
    public CardsResponse findById(@PathVariable Long cardId) {
        return cardsService.getCard(cardId);
    }

    @GetMapping("/customers/{customerId}")
    public List<CardsResponse> getCardsByCustomerId(@PathVariable Long customerId) {
        return cardsService.getCardByCustomerId(customerId);
    }

    ////////////Management/////////////
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCard(@RequestBody CardsRequest request) {
        cardsService.saveCard(request);
    }

    @PutMapping("/{cardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCard(@PathVariable Long cardId, @RequestBody CardsRequest request) {
        cardsService.updateCard(request, cardId);
    }

    @PatchMapping("/{id}/available-amount")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAvailableAmount(@PathVariable Long id,
                                      @RequestParam("value")BigDecimal availableAmount) {
        cardsService.updateAvailableAmount(id, availableAmount);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCard(@PathVariable Long id) {
        cardsService.deleteCard(id);
    }
}

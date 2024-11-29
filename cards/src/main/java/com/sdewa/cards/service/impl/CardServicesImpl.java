package com.sdewa.cards.service.impl;

import com.sdewa.cards.constants.CardConstants;
import com.sdewa.cards.dto.CardsDto;
import com.sdewa.cards.entity.Cards;
import com.sdewa.cards.exception.CardAlreadyExistsException;
import com.sdewa.cards.exception.ResourcesNotFoundException;
import com.sdewa.cards.mapper.CardMapper;
import com.sdewa.cards.repository.CardRepository;
import com.sdewa.cards.service.ICardsServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class CardServicesImpl implements ICardsServices {

    private CardRepository cardRepository;

    @Override
    public void createCard(String mobileNumber) {
       var optCard= cardRepository.findByMobileNumber(mobileNumber);
        if (optCard.isPresent()){
            throw  new CardAlreadyExistsException(
                    "Card already registered with given mobile number "
                    + mobileNumber
            );
        }
        cardRepository.save(createNewCard(mobileNumber));
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        return cardRepository.findByMobileNumber(mobileNumber)
                .map(cards-> CardMapper.mapToCardsDto(cards, new CardsDto()))
                .orElseThrow(()-> new ResourcesNotFoundException(
                        "Card",
                        "mobileNumber",
                        mobileNumber
                ));
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        return cardRepository.findByCardNumber(cardsDto.getCardNumber())
                .map((card)-> {
                    CardMapper.mapToCards(cardsDto, card);
                    cardRepository.save(card);
                    return  true;
                }).orElseThrow(()-> new ResourcesNotFoundException(
                        "Card",
                        "CardNumber",
                        cardsDto.getCardNumber()
                ));
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        return cardRepository.findByMobileNumber(mobileNumber)
                .map(card-> {
                    cardRepository.deleteById(card.getCardId());
                    return  true;
                })
                .orElseThrow(()-> new ResourcesNotFoundException(
                        "Card","mobileNumber", mobileNumber
                ));
    }

    private Cards createNewCard(String mobileNumber){
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        return newCard;
    }
}

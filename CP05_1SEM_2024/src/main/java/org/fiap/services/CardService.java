package org.fiap.services;

import org.fiap.entities.Card;
import org.fiap.repositories.CardRepository;

public class CardService {
    private CardRepository cardRepository = new CardRepository();

    public CardService(){
        CardRepository cardRepository = new CardRepository();
    }

    public boolean Create(Card card){
        var validation = card.validate();
        try {
            if(validation.containsKey(false)) {
                throw new IllegalArgumentException(validation.get(false).toString());
            } else if (cardRepository.VerificarApenasUmSuperTrunfo(card)) {
                throw new IllegalArgumentException("Já existe um super trunfo cadastrado");
            }
            else {
                cardRepository.Create(card);
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void UpdateByCodCard(Card card, String cod_card){
        var validation = card.validate();
        try {
            if(validation.containsKey(false)) {
                throw new IllegalArgumentException(validation.get(false).toString());
            } else if (cardRepository.VerificarApenasUmSuperTrunfo(card)) {
                throw new IllegalArgumentException("Já existe um super trunfo cadastrado");
            } else {
                cardRepository.UpdateByCodCard(card, cod_card);
            }
        } catch (Exception e) {
            throw e;
        }

    }


}

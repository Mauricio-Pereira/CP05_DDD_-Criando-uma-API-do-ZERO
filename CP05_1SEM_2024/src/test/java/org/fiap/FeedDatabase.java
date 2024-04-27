package org.fiap;


import org.fiap.entities.Card;
import org.fiap.entities.Colecoes;
import org.fiap.repositories.CardRepository;
import org.fiap.services.CardService;
import org.fiap.services.ColecoesService;

import java.sql.SQLException;
import java.util.ArrayList;

public class FeedDatabase {
    public static void main(String[] args) throws SQLException{
        CardService cardService = new CardService();
        ColecoesService colecoesService = new ColecoesService();
        CardRepository cardRepository = new CardRepository();

        Colecoes colecao1 = new Colecoes("Cavaleiros do zodíaco", new ArrayList<>());
        Colecoes colecao2 = new Colecoes("Cavaleiros do zodíaco 2", new ArrayList<>());
        Card card1 = new Card(
                true,
                "A1",
                1,
                "Shiryu",
                "Shiryu de Dragão",
                "Bronze",
                100,
                100,
                100,
                100,
                100
        );
        Card card2= new Card(
                false,
                "A2",
                1,
                "Seiya",
                "Seiya de Pégaso",
                "Bronze",
                100,
                100,
                500,
                500,
                800
        );
        Card card3= new Card(
                false,
                "A3",
                2,
                "asdasdadfffasd",
                "Seiya de Pégaso",
                "Bronze",
                100,
                100,
                500,
                500,
                800
        );
        colecoesService.Create(colecao1);
        colecoesService.Create(colecao2);
        cardService.Create(card1);
        cardService.Create(card2);
        cardService.Create(card3);
        System.out.println(cardRepository.ReadByCodCard("a4"));




    }
}

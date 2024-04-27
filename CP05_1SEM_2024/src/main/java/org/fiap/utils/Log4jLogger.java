package org.fiap.utils;


import org.apache.logging.log4j.LogManager;
import org.fiap.entities.Card;
import org.fiap.entities.Colecoes;

import java.util.List;

public class Log4jLogger{


    private final org.apache.logging.log4j.Logger cardLogger;
    private final org.apache.logging.log4j.Logger colecoesLogger;
    public Log4jLogger() {
        this.cardLogger = LogManager.getLogger(Card.class);
        this.colecoesLogger = LogManager.getLogger(Colecoes.class);
    }


    public void logCreateCard(Card entity){
        cardLogger.info("Create: "+entity);
    }

    public void logReadCardByCodCard(Card entity){
        cardLogger.info("Read: " + entity );
    }

    public void logReadAllCards(List<Card> entity) {
        cardLogger.info("ReadAll: " + entity);
    }

    public void logUpdateCardByCodCard(Card entity){
        cardLogger.info("Update: "+entity);
    }

    public void logDeleteCardByCodCard(Card entity){
        cardLogger.info("Delete: "+entity);
    }

    public void logCreateColecao(Colecoes entity){
        colecoesLogger.info("Create: "+entity);
    }

    public void logReadCartasDaColecaoByIdDaColecao(List<Card> entity){
        colecoesLogger.info("Read: " + entity);
    }

    public void logReadAllColecoes(List<Colecoes> entity) {
        colecoesLogger.info("ReadAll: " + entity);
    }
    public void logReadColecaoByID(Colecoes entity){
        colecoesLogger.info("Read: "+entity);
    }

    public void logUpdateColecaoById(Colecoes entity){
        colecoesLogger.info("Update: "+entity);
    }

    public void logDeleteColecaoById(Colecoes entity){
        colecoesLogger.info("Delete: "+entity);
    }
}

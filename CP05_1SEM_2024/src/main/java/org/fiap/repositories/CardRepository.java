package org.fiap.repositories;

import org.fiap.connections.DatabaseConnection;
import org.fiap.entities.Card;
import org.fiap.utils.Log4jLogger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardRepository {
        Log4jLogger logger = new Log4jLogger();
        DatabaseConnection connection = new DatabaseConnection();

    public static final String TB_NAME = "CP_CARD";
    public static final HashMap<String, String> TB_COLUMNS = new HashMap<>() {{
        put("COD_CARD", "COD_CARD");
        put("COLLECTIONSID", "COLLECTIONSID");
        put("SUPERTRUNFO", "SUPERTRUNFO");
        put("NOME", "NOME");
        put("NOME_DE_CAVALEIRO", "NOME_DE_CAVALEIRO");
        put("GRUPO_DE_CAVALEIROS", "GRUPO_DE_CAVALEIROS");
        put("SOCO", "SOCO");
        put("CHUTE", "CHUTE");
        put("TECNICA", "TECNICA");
        put("CONHECIMENTO", "CONHECIMENTO");
        put("FORCA", "FORCA");
    }};

    public CardRepository() {
        this.logger = new Log4jLogger();
    }

    public void Create(Card card) {
        String sql = "INSERT INTO " + TB_NAME + " (" +
                TB_COLUMNS.get("COD_CARD") + ", " +
                TB_COLUMNS.get("COLLECTIONSID") + ", " +
                TB_COLUMNS.get("SUPERTRUNFO") + ", " +
                TB_COLUMNS.get("NOME") + ", " +
                TB_COLUMNS.get("NOME_DE_CAVALEIRO") + ", " +
                TB_COLUMNS.get("GRUPO_DE_CAVALEIROS") + ", " +
                TB_COLUMNS.get("SOCO") + ", " +
                TB_COLUMNS.get("CHUTE") + ", " +
                TB_COLUMNS.get("TECNICA") + ", " +
                TB_COLUMNS.get("CONHECIMENTO") + ", " +
                TB_COLUMNS.get("FORCA") + ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (var conn = connection.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, card.getCod_card().toUpperCase());
            stmt.setInt(2, card.getCollectionsId());
            stmt.setString(3, card.getSuperTrunfo() ? "1" : "0");
            stmt.setString(4, card.getNome());
            stmt.setString(5, card.getNomeDeCavaleiro());
            stmt.setString(6, card.getGrupoDeCavaleiros());
            stmt.setInt(7, card.getSoco());
            stmt.setInt(8, card.getChute());
            stmt.setInt(9, card.getTecnica());
            stmt.setInt(10, card.getConhecimento());
            stmt.setInt(11, card.getForca());
            var result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.logCreateCard(card);

        System.out.println("Carta criada com sucesso!");
    }

    public List<Card> ReadAllCards(){
        List<Card> cards = new ArrayList<>();
        try (var conn = connection.getConnection()) {
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " ORDER BY " + TB_COLUMNS.get("COD_CARD") + " ASC");
            var rs = stmt.executeQuery();
            while (rs.next()) {
                Card card = new Card(
                        rs.getBoolean(TB_COLUMNS.get("SUPERTRUNFO")),
                        rs.getString(TB_COLUMNS.get("COD_CARD")),
                        rs.getInt(TB_COLUMNS.get("COLLECTIONSID")),
                        rs.getString(TB_COLUMNS.get("NOME")),
                        rs.getString(TB_COLUMNS.get("NOME_DE_CAVALEIRO")),
                        rs.getString(TB_COLUMNS.get("GRUPO_DE_CAVALEIROS")),
                        rs.getInt(TB_COLUMNS.get("SOCO")),
                        rs.getInt(TB_COLUMNS.get("CHUTE")),
                        rs.getInt(TB_COLUMNS.get("TECNICA")),
                        rs.getInt(TB_COLUMNS.get("CONHECIMENTO")),
                        rs.getInt(TB_COLUMNS.get("FORCA"))
                );
                cards.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.logReadAllCards(cards);
        return cards;


    }

    public boolean UpdateByCodCard(Card card, String cod_card) {
        String sql = "UPDATE " + TB_NAME + " SET " +
                TB_COLUMNS.get("COD_CARD") + " = ?, " +
                TB_COLUMNS.get("COLLECTIONSID") + " = ?, " +
                TB_COLUMNS.get("SUPERTRUNFO") + " = ?, " +
                TB_COLUMNS.get("NOME") + " = ?, " +
                TB_COLUMNS.get("NOME_DE_CAVALEIRO") + " = ?, " +
                TB_COLUMNS.get("GRUPO_DE_CAVALEIROS") + " = ?, " +
                TB_COLUMNS.get("SOCO") + " = ?, " +
                TB_COLUMNS.get("CHUTE") + " = ?, " +
                TB_COLUMNS.get("TECNICA") + " = ?, " +
                TB_COLUMNS.get("CONHECIMENTO") + " = ?, " +
                TB_COLUMNS.get("FORCA") + " = ? " +
                "WHERE " + TB_COLUMNS.get("COD_CARD") + " = ?";

        try (var conn = connection.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, card.getCod_card().toUpperCase());
            stmt.setInt(2, card.getCollectionsId());
            stmt.setString(3, card.getSuperTrunfo() ? "1" : "0");
            stmt.setString(4, card.getNome());
            stmt.setString(5, card.getNomeDeCavaleiro());
            stmt.setString(6, card.getGrupoDeCavaleiros());
            stmt.setInt(7, card.getSoco());
            stmt.setInt(8, card.getChute());
            stmt.setInt(9, card.getTecnica());
            stmt.setInt(10, card.getConhecimento());
            stmt.setInt(11, card.getForca());
            stmt.setString(12, cod_card.toUpperCase());
            var result = stmt.executeUpdate();
            logger.logUpdateCardByCodCard(card);
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Card ReadByCodCard(String cod_card) {
        Card card = null;
        try (var conn = connection.getConnection()) {
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE " + TB_COLUMNS.get("COD_CARD") + " = ?");
            stmt.setString(1, cod_card.toUpperCase());
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    card = new Card(
                            rs.getBoolean(TB_COLUMNS.get("SUPERTRUNFO")),
                            rs.getString(TB_COLUMNS.get("COD_CARD")),
                            rs.getInt(TB_COLUMNS.get("COLLECTIONSID")),
                            rs.getString(TB_COLUMNS.get("NOME")),
                            rs.getString(TB_COLUMNS.get("NOME_DE_CAVALEIRO")),
                            rs.getString(TB_COLUMNS.get("GRUPO_DE_CAVALEIROS")),
                            rs.getInt(TB_COLUMNS.get("SOCO")),
                            rs.getInt(TB_COLUMNS.get("CHUTE")),
                            rs.getInt(TB_COLUMNS.get("TECNICA")),
                            rs.getInt(TB_COLUMNS.get("CONHECIMENTO")),
                            rs.getInt(TB_COLUMNS.get("FORCA"))
                    );
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        logger.logReadCardByCodCard(card);
        return card;
    }

    public boolean DeleteByCodCard(String cod_card){
        try (var conn = connection.getConnection()) {
            Card deletedCard = ReadByCodCard(cod_card);
            var stmt = conn.prepareStatement("DELETE FROM " + TB_NAME + " WHERE " + TB_COLUMNS.get("COD_CARD") + " = ?");
            stmt.setString(1, cod_card.toUpperCase());
            var result = stmt.executeUpdate();
            logger.logDeleteCardByCodCard(deletedCard);
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean VerificarApenasUmSuperTrunfo(Card card) {
        try (var conn = connection.getConnection()) {
            var stmt = conn.prepareStatement("SELECT COUNT(*) FROM " + TB_NAME + " WHERE " + TB_COLUMNS.get("SUPERTRUNFO") + " = 1 AND " + TB_COLUMNS.get("COLLECTIONSID") + " = "+ card.getCollectionsId());
            var rs = stmt.executeQuery();
            if (rs.next() && card.getSuperTrunfo() == true) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

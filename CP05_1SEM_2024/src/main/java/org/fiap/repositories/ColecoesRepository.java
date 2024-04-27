package org.fiap.repositories;

import org.fiap.connections.DatabaseConnection;
import org.fiap.entities.Card;
import org.fiap.entities.Colecoes;
import org.fiap.utils.Log4jLogger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ColecoesRepository {
    Log4jLogger logger = new Log4jLogger();
    DatabaseConnection connection = new DatabaseConnection();
    public static final String TB_NAME = "CP_COLLECTIONS";
    public static final HashMap<String, String> TB_COLUMNS = new HashMap<>() {
        {
            put("ID", "ID");
            put("NAME", "NAME");
        }
    };

    public ColecoesRepository() {
        this.logger = logger;
    }

    public void Create(Colecoes colecoes) {
        String sql = "INSERT INTO " + TB_NAME + " (" +
                TB_COLUMNS.get("NAME") + ") " +
                "VALUES (?)";
        try (var conn = connection.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, colecoes.getNome());
            stmt.executeUpdate();
            logger.logCreateColecao(colecoes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Colecoes> ReadAll() {
        List<Colecoes> colecoes = new ArrayList<>();
        try (var conn = connection.getConnection()) {
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " ORDER BY " + TB_COLUMNS.get("ID") + " ASC");
            var rs = stmt.executeQuery();
            while (rs.next()) {
                Colecoes colecao = new Colecoes(
                        rs.getInt(TB_COLUMNS.get("ID")),
                        rs.getString(TB_COLUMNS.get("NAME"))
                );
                colecoes.add(colecao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.logReadAllColecoes(colecoes);
        return colecoes;
    }

    public boolean UpdateById(Colecoes colecoes, int id) {
        String sql = "UPDATE " + TB_NAME + " SET " +
                TB_COLUMNS.get("NAME") + " = ? " +
                "WHERE " + TB_COLUMNS.get("ID") + " = ?";
        try (var conn = connection.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, colecoes.getNome());
            stmt.setInt(2, id);
            var result = stmt.executeUpdate();
            logger.logUpdateColecaoById(colecoes);
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean DeleteById(int id) {
        String sql = "DELETE FROM " + TB_NAME + " WHERE " + TB_COLUMNS.get("ID") + " = ?";
        Colecoes colecao = ReadById(id);
        try (var conn = connection.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            var result = stmt.executeUpdate();
            logger.logDeleteColecaoById(colecao);
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Colecoes ReadById(int id) {
        Colecoes colecao = null;
        try (var conn = connection.getConnection()) {
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE " + TB_COLUMNS.get("ID") + " = ?");
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    colecao = new Colecoes(
                            rs.getInt(TB_COLUMNS.get("ID")),
                            rs.getString(TB_COLUMNS.get("NAME"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.logReadColecaoByID(colecao);
        return colecao;
    }

    public List<Card> getCardsByCollectionId(int collectionId) {
        List<Card> cards = new ArrayList<>();
        try (var conn = connection.getConnection()) {
            var stmt = conn.prepareStatement("SELECT * FROM CP_CARD WHERE COLLECTIONSID = ?");
            stmt.setInt(1, collectionId);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                Card card = new Card(
                        rs.getBoolean("SUPERTRUNFO"),
                        rs.getString("COD_CARD"),
                        rs.getInt("COLLECTIONSID"),
                        rs.getString("NOME"),
                        rs.getString("NOME_DE_CAVALEIRO"),
                        rs.getString("GRUPO_DE_CAVALEIROS"),
                        rs.getInt("SOCO"),
                        rs.getInt("CHUTE"),
                        rs.getInt("TECNICA"),
                        rs.getInt("CONHECIMENTO"),
                        rs.getInt("FORCA")
                );
                cards.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.logReadCartasDaColecaoByIdDaColecao(cards);
        return cards;
    }

}

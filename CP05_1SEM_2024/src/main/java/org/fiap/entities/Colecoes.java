package org.fiap.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Colecoes {
    private int id;
    private String nome;
    private List<Card> cards;

    public Colecoes() {
    }

    public Colecoes(String nome, List<Card> cards) {
        this.nome = nome;
        this.cards = cards;
    }

    public Colecoes(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Colecoes{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cards=" + cards +
                '}';
    }
    public Map<Boolean, String> validate(){
        Map<Boolean, String> validation = new HashMap<>();
        if (this.nome == null || this.nome.isEmpty()) {
            validation.put(false, "Nome da coleção não pode ser vazio");
        }
        return validation;
    }
}

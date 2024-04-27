package org.fiap.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Card {
    private Boolean superTrunfo = false;
    private String cod_card;
    private int collectionsId;
    private String nome;
    private String nomeDeCavaleiro;
    private String grupoDeCavaleiros;
    private int soco;
    private int chute;
    private int tecnica;
    private int conhecimento;
    private int forca;

    public Card() {
    }

    public Card(Boolean superTrunfo, String cod_card, int collectionsId, String nome, String nomeDeCavaleiro, String grupoDeCavaleiros, int soco, int chute, int tecnica, int conhecimento, int forca) {
        this.superTrunfo = superTrunfo;
        this.cod_card = cod_card;
        this.collectionsId = collectionsId;
        this.nome = nome;
        this.nomeDeCavaleiro = nomeDeCavaleiro;
        this.grupoDeCavaleiros = grupoDeCavaleiros;
        this.soco = soco;
        this.chute = chute;
        this.tecnica = tecnica;
        this.conhecimento = conhecimento;
        this.forca = forca;
    }

    public Boolean getSuperTrunfo() {
        return superTrunfo;
    }

    public void setSuperTrunfo(Boolean superTrunfo) {
        this.superTrunfo = superTrunfo;
    }

    public String getCod_card() {
        return cod_card;
    }

    public void setCod_card(String cod_card) {
        this.cod_card = cod_card;
    }

    public int getCollectionsId() {
        return collectionsId;
    }

    public void setCollectionsId(int collectionsId) {
        this.collectionsId = collectionsId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeDeCavaleiro() {
        return nomeDeCavaleiro;
    }

    public void setNomeDeCavaleiro(String nomeDeCavaleiro) {
        this.nomeDeCavaleiro = nomeDeCavaleiro;
    }

    public String getGrupoDeCavaleiros() {
        return grupoDeCavaleiros;
    }

    public void setGrupoDeCavaleiros(String grupoDeCavaleiros) {
        this.grupoDeCavaleiros = grupoDeCavaleiros;
    }

    public int getSoco() {
        return soco;
    }

    public void setSoco(int soco) {
        this.soco = soco;
    }

    public int getChute() {
        return chute;
    }

    public void setChute(int chute) {
        this.chute = chute;
    }

    public int getTecnica() {
        return tecnica;
    }

    public void setTecnica(int tecnica) {
        this.tecnica = tecnica;
    }

    public int getConhecimento() {
        return conhecimento;
    }

    public void setConhecimento(int conhecimento) {
        this.conhecimento = conhecimento;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    @Override
    public String toString() {
        return "Card{" +
                "superTrunfo=" + superTrunfo +
                ", cod_card='" + cod_card + '\'' +
                ", collectionsId=" + collectionsId +
                ", nome='" + nome + '\'' +
                ", nomeDeCavaleiro='" + nomeDeCavaleiro + '\'' +
                ", grupoDeCavaleiros='" + grupoDeCavaleiros + '\'' +
                ", soco=" + soco +
                ", chute=" + chute +
                ", tecnica=" + tecnica +
                ", conhecimento=" + conhecimento +
                ", forca=" + forca +
                '}';
    }

    public Map<Boolean, String> validate(){
        Map<Boolean, String> validation = new HashMap<>();
        if(this.cod_card == null || this.cod_card.isEmpty())
            validation.put(false, "O código do card não pode ser vazio");
        if(!isValidCodCard(this.cod_card))
            validation.put(false, "O código do card deve ser no formato A1, com a letra de A a E e o número de 1 a 9");
        if(this.nome == null || this.nome.isEmpty())
            validation.put(false, "O nome do card não pode ser vazio");
        if(this.nomeDeCavaleiro == null || this.nomeDeCavaleiro.isEmpty())
            validation.put(false, "O nome do cavaleiro não pode ser vazio");
        if(this.grupoDeCavaleiros == null || this.grupoDeCavaleiros.isEmpty())
            validation.put(false, "O grupo de cavaleiros não pode ser vazio");
        if(this.soco < 100 || this.soco > 1000)
            validation.put(false, "O soco deve ser um valor entre 100 e 1000");
        if(this.chute < 100 || this.chute > 1000)
            validation.put(false, "O chute deve ser um valor entre 100 e 1000");
        if(this.tecnica < 100 || this.tecnica > 1000)
            validation.put(false, "A técnica deve ser um valor entre 100 e 1000");
        if(this.conhecimento < 100 || this.conhecimento > 1000)
            validation.put(false, "O conhecimento deve ser um valor entre 100 e 1000");
        if(this.forca < 100 || this.forca > 1000)
            validation.put(false, "A força deve ser um valor entre 100 e 1000");
        return validation;

    }

    public boolean isValidCodCard(String cod_card) {
        Pattern pattern = Pattern.compile("[A-E][1-9]");
        Matcher matcher = pattern.matcher(cod_card);
        return matcher.matches();
    }
}

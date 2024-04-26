package com.palazzo.logisticareversa;

import java.io.Serializable;

public class Item implements Serializable {
    private String dataDevolucao;
    private String dataEntrega;
    private String id;
    private int numeroPedido;
    private int quantidade;
    private String status;

    private String garrafasQuebradas;
    private String garrafasReciclagem;
    private String garrafasReclamacoes;

    public Item(int numeroPedido2, int quantidade2, String dataEntrega2, String dataDevolucao2, String status2) {
        this.numeroPedido = numeroPedido2;
        this.quantidade = quantidade2;
        this.dataEntrega = dataEntrega2;
        this.dataDevolucao = dataDevolucao2;
        this.status = status2;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id2) {
        this.id = id2;
    }

    public Item() {
    }

    public int getNumeroPedido() {
        return this.numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido2) {
        this.numeroPedido = numeroPedido2;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade2) {
        this.quantidade = quantidade2;
    }

    public String getDataEntrega() {
        return this.dataEntrega;
    }

    public void setDataEntrega(String dataEntrega2) {
        this.dataEntrega = dataEntrega2;
    }

    public String getDataDevolucao() {
        return this.dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao2) {
        this.dataDevolucao = dataDevolucao2;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status2) {
        this.status = status2;
    }

    public String getGarrafasQuebradas() {
        return garrafasQuebradas;
    }

    public void setGarrafasQuebradas(String garrafasQuebradas) {
        this.garrafasQuebradas = garrafasQuebradas;
    }

    public String getGarrafasReciclagem() {
        return garrafasReciclagem;
    }

    public void setGarrafasReciclagem(String garrafasReciclagem) {
        this.garrafasReciclagem = garrafasReciclagem;
    }

    public String getGarrafasReclamacoes() {
        return garrafasReclamacoes;
    }

    public void setGarrafasReclamacoes(String garrafasReclamacoes) {
        this.garrafasReclamacoes = garrafasReclamacoes;
    }
}

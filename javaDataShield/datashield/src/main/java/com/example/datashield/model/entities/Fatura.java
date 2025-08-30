package com.example.datashield.model.entities;

import java.sql.Date;

public class Fatura {
    private int idFatura;
    private int idContrato;
    private double valorTotal;
    private Date dataEmissao;
    private String statusPagamento;

    public int getIdFatura() {
        return idFatura;
    }
    public void setIdFatura(int idFatura) {
        this.idFatura = idFatura;
    }
    public int getIdContrato() {
        return idContrato;
    }
    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public Date getDataEmissao() {
        return dataEmissao;
    }
    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
    public String getStatusPagamento() {
        return statusPagamento;
    }
    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}

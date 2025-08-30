package com.example.datashield.model.entities;

import java.sql.Date;

public class ContratoServico {
    private int idContrato;
    private int idServico;
    private double valorNegociado;
    private Date prazoEntrega;

    public int getIdContrato() {
        return idContrato;
    }
    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }
    public int getIdServico() {
        return idServico;
    }
    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }
    public double getValorNegociado() {
        return valorNegociado;
    }
    public void setValorNegociado(double valorNegociado) {
        this.valorNegociado = valorNegociado;
    }
    public Date getPrazoEntrega() {
        return prazoEntrega;
    }
    public void setPrazoEntrega(Date prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }
}

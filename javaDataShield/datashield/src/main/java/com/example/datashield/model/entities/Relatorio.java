package com.example.datashield.model.entities;

import java.sql.Date;

public class Relatorio {
    private int idRelatorio;
    private int idContrato;
    private Date dataEntrega;
    private String tipo;
    private String descricaoResultado;

    public int getIdRelatorio() {
        return idRelatorio;
    }
    public void setIdRelatorio(int idRelatorio) {
        this.idRelatorio = idRelatorio;
    }
    public int getIdContrato() {
        return idContrato;
    }
    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }
    public Date getDataEntrega() {
        return dataEntrega;
    }
    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getDescricaoResultado() {
        return descricaoResultado;
    }
    public void setDescricaoResultado(String descricaoResultado) {
        this.descricaoResultado = descricaoResultado;
    }
}

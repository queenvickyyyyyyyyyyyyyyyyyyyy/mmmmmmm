package com.example.datashield.model.entities;

public class EquipeProjeto {
    private int idContrato;
    private int idFuncionario;
    private String funcaoNoProjeto;

    public int getIdContrato() {
        return idContrato;
    }
    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }
    public int getIdFuncionario() {
        return idFuncionario;
    }
    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
    public String getFuncaoNoProjeto() {
        return funcaoNoProjeto;
    }
    public void setFuncaoNoProjeto(String funcaoNoProjeto) {
        this.funcaoNoProjeto = funcaoNoProjeto;
    }
}

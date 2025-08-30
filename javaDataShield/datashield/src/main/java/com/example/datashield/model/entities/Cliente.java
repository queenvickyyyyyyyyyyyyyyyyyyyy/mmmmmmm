package com.example.datashield.model.entities;

import java.io.Serializable;

public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
    private int idCliente;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String cnpjOuCpf;

    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getCnpjOuCpf() {
        return cnpjOuCpf;
    }
    public void setCnpjOuCpf(String cnpjOuCpf) {
        this.cnpjOuCpf = cnpjOuCpf;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
        result = prime * result + idCliente;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
        Cliente other = (Cliente) obj;
        return idCliente == other.idCliente;
    }

	@Override
	public String toString() {
		return "Cliente [id=" + idCliente + ", Nome=" + nome + ", E-mail=" + email + ", Telefone=" + telefone + ", Endereço="
				+ endereco + ", CNPJ ou CPF=" + cnpjOuCpf + "]";
	}
}

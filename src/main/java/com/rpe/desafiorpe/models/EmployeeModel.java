package com.rpe.desafiorpe.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("funcionario")
public class EmployeeModel extends PersonModel {
    private String funcao;
    private String status;
    private String dataDeContratacao;

    public EmployeeModel() {
    }

    public EmployeeModel(String cpf, String nome, String endereco, String telefone, String funcao, String status, String dataDeContratacao) {
        super(cpf, nome, endereco, telefone);
        this.funcao = funcao;
        this.status = status;
        this.dataDeContratacao = dataDeContratacao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataDeContratacao() {
        return dataDeContratacao;
    }

    public void setDataDeContratacao(String dataDeContratacao) {
        this.dataDeContratacao = dataDeContratacao;
    }
}

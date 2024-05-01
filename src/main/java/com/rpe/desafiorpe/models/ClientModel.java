package com.rpe.desafiorpe.models;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("cliente")
public class ClientModel extends PersonModel {
    private String dataDoUltimoServico;

    public ClientModel() {
    }

    public ClientModel(String cpf, String nome, String endereco, String telefone, String dataDoUltimoServico) {
        super(cpf, nome, endereco, telefone);
        this.dataDoUltimoServico = dataDoUltimoServico;
    }

    public String getdataDoUltimoServico() {
        return dataDoUltimoServico;
    }

    public void setdataDoUltimoServico(String dataDoUltimoServico) {
        this.dataDoUltimoServico = dataDoUltimoServico;
    }
}

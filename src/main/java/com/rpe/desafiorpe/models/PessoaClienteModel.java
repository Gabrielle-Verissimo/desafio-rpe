package com.rpe.desafiorpe.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
//@Table(name = "cliente")
@DiscriminatorValue("cliente")
public class PessoaClienteModel extends PessoaModel{
    private String dataDoUltimoServico;

    public PessoaClienteModel() {
    }

    public PessoaClienteModel(String cpf, String nome, String endereco, String telefone, String dataDoUltimoServico) {
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

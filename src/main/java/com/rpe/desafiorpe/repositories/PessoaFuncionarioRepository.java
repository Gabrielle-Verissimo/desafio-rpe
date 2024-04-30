package com.rpe.desafiorpe.repositories;

import com.rpe.desafiorpe.models.PessoaClienteModel;
import com.rpe.desafiorpe.models.PessoaFuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PessoaFuncionarioRepository extends JpaRepository<PessoaFuncionarioModel, UUID> {
    Optional<PessoaFuncionarioModel> findByCpf(String cpf);
}
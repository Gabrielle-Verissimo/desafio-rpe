package com.rpe.desafiorpe.repositories;

import com.rpe.desafiorpe.models.PessoaClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PessoaClienteRepository extends JpaRepository<PessoaClienteModel, UUID> {
    Optional<PessoaClienteModel> findByCpf(String cpf);
}

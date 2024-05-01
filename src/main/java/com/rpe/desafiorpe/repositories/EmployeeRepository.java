package com.rpe.desafiorpe.repositories;

import com.rpe.desafiorpe.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, UUID> {
    Optional<EmployeeModel> findByCpf(String cpf);
}
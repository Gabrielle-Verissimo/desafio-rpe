package com.rpe.desafiorpe.services;

import com.rpe.desafiorpe.dtos.PessoaFuncionarioRecordDto;
import com.rpe.desafiorpe.models.PessoaFuncionarioModel;
import com.rpe.desafiorpe.repositories.PessoaFuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaFuncionarioService {
    @Autowired
    PessoaFuncionarioRepository funcionarioRepository;

    public List<PessoaFuncionarioModel> findAll(){
        return funcionarioRepository.findAll();
    }
    public Optional<PessoaFuncionarioModel> findById(UUID id){
        return funcionarioRepository.findById(id);
    }
    public Optional<PessoaFuncionarioModel> findByCpf(String cpf){
        return funcionarioRepository.findByCpf(cpf);
    }
    @Transactional
    public PessoaFuncionarioModel save(PessoaFuncionarioRecordDto funcionarioDto){
        var funcionario = new PessoaFuncionarioModel();
        BeanUtils.copyProperties(funcionarioDto, funcionario);
        return funcionarioRepository.save(funcionario);
    }
    @Transactional
    public PessoaFuncionarioModel update(PessoaFuncionarioRecordDto funcionarioDto, Optional<PessoaFuncionarioModel> funcionarioModel){
        var funcionario = funcionarioModel.get();
        BeanUtils.copyProperties(funcionarioDto, funcionario);
        return  funcionarioRepository.save(funcionario);
    }
    @Transactional
    public void delete(PessoaFuncionarioModel funcionario){
        funcionarioRepository.delete(funcionario);
    }
}
package com.rpe.desafiorpe.services;

import com.rpe.desafiorpe.dtos.PessoaClienteRecordDto;
import com.rpe.desafiorpe.models.PessoaClienteModel;
import com.rpe.desafiorpe.repositories.PessoaClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaClienteService {
    @Autowired
    PessoaClienteRepository clienteRepository;

    public List<PessoaClienteModel> findAll(){
        return clienteRepository.findAll();
    }
    public Optional<PessoaClienteModel> findById(UUID id){
        return clienteRepository.findById(id);
    }
    public Optional<PessoaClienteModel> findByCpf(String cpf){
        return clienteRepository.findByCpf(cpf);
    }
    @Transactional
    public PessoaClienteModel save(PessoaClienteRecordDto clienteDto){
        var cliente = new PessoaClienteModel();
        BeanUtils.copyProperties(clienteDto, cliente);
        return clienteRepository.save(cliente);
    }
    @Transactional
    public PessoaClienteModel update(PessoaClienteRecordDto clienteDto, Optional<PessoaClienteModel> clienteModel){
        var cliente = clienteModel.get();
        BeanUtils.copyProperties(clienteDto, cliente);
        return  clienteRepository.save(cliente);
    }
    @Transactional
    public void delete(PessoaClienteModel cliente){
        clienteRepository.delete(cliente);
    }
}

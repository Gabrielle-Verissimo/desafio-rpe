package com.rpe.desafiorpe.services;

import com.rpe.desafiorpe.dtos.ClientRecordDto;
//import com.rpe.desafiorpe.exceptions.InvalidCpfException;
import com.rpe.desafiorpe.exceptions.InvalidCpfException;
import com.rpe.desafiorpe.exceptions.InvalidPhoneNumberException;
import com.rpe.desafiorpe.models.ClientModel;
import com.rpe.desafiorpe.repositories.ClientRepository;
import com.rpe.desafiorpe.utils.DataValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public List<ClientModel> findAll(){
        return clientRepository.findAll();
    }
    public Optional<ClientModel> findById(UUID id){
        return clientRepository.findById(id);
    }
    public Optional<ClientModel> findByCpf(String cpf){
        return clientRepository.findByCpf(cpf);
    }
    @Transactional
    public ClientModel save(ClientRecordDto clientDto){
        if(!DataValidator.validateCpf(clientDto.cpf())){
            throw new InvalidCpfException();
        }
        if(!DataValidator.validatePhone(clientDto.telefone())) {
            throw new InvalidPhoneNumberException();
        }
        var client = new ClientModel();
        BeanUtils.copyProperties(clientDto, client);
        return clientRepository.save(client);
    }
    @Transactional
    public ClientModel update(ClientRecordDto clientDto, Optional<ClientModel> clientModel){
        if(!DataValidator.validateCpf(clientDto.cpf())){
            throw new InvalidCpfException();
        }
        if(!DataValidator.validatePhone(clientDto.telefone())) {
            throw new InvalidPhoneNumberException();
        }
        var client = clientModel.get();
        BeanUtils.copyProperties(clientDto, client);
        return  clientRepository.save(client);
    }
    @Transactional
    public void delete(ClientModel client){
        clientRepository.delete(client);
    }
}

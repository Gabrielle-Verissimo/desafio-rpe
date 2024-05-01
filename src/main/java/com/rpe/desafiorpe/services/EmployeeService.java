package com.rpe.desafiorpe.services;

import com.rpe.desafiorpe.dtos.EmployeeRecordDto;
import com.rpe.desafiorpe.exceptions.InvalidCpfException;
import com.rpe.desafiorpe.exceptions.InvalidPhoneNumberException;
import com.rpe.desafiorpe.models.EmployeeModel;
import com.rpe.desafiorpe.repositories.EmployeeRepository;
import com.rpe.desafiorpe.utils.DataValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<EmployeeModel> findAll(){
        return employeeRepository.findAll();
    }
    public Optional<EmployeeModel> findById(UUID id){
        return employeeRepository.findById(id);
    }
    public Optional<EmployeeModel> findByCpf(String cpf){
        return employeeRepository.findByCpf(cpf);
    }
    @Transactional
    public EmployeeModel save(EmployeeRecordDto employeeDto){
        if(!DataValidator.validateCpf(employeeDto.cpf())){
            throw new InvalidCpfException();
        }
        if(!DataValidator.validatePhone(employeeDto.telefone())) {
            throw new InvalidPhoneNumberException();
        }
        var employee = new EmployeeModel();
        BeanUtils.copyProperties(employeeDto, employee);
        return employeeRepository.save(employee);
    }
    @Transactional
    public EmployeeModel update(EmployeeRecordDto employeeDto, Optional<EmployeeModel> employeeModel){
        if(!DataValidator.validateCpf(employeeDto.cpf())){
            throw new InvalidCpfException();
        }
        if(!DataValidator.validatePhone(employeeDto.telefone())) {
            throw new InvalidPhoneNumberException();
        }
        var employee = employeeModel.get();
        BeanUtils.copyProperties(employeeDto, employee);
        return  employeeRepository.save(employee);
    }
    @Transactional
    public void delete(EmployeeModel employee){
        employeeRepository.delete(employee);
    }
}
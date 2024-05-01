package com.rpe.desafiorpe.exceptions;

public class InvalidCpfException extends RuntimeException{
    public InvalidCpfException() { super("CPF inválido. Verifique se você digitou apenas números ou um cpf válido.");
    }

    public InvalidCpfException(String message) {
        super(message);
    }
}

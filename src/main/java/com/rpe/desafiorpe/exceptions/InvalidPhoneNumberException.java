package com.rpe.desafiorpe.exceptions;

public class InvalidPhoneNumberException extends RuntimeException{
    public InvalidPhoneNumberException () { super("Número de telefone inválido. Verifique se você digitou os 11 números incluindo com o DDD.");
    }

    public InvalidPhoneNumberException (String message) {
        super(message);
    }
}

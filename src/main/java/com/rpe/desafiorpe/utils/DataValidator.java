package com.rpe.desafiorpe.utils;

public abstract class DataValidator {
    public static boolean validateCpf(String cpf){
        boolean valid = cpf.matches("\\d{11}");

        if(!valid) return valid;

        int sum1 = 0, sum2 = 0, num = 1;
        for(int i = 0; i < 9; i++){
            sum1 += Character.getNumericValue(cpf.charAt(i)) * num;
            sum2 += Character.getNumericValue(cpf.charAt(i)) * i;
            num++;
        }
        int remainder1 = sum1 % 11;
        if(remainder1 == 10) remainder1 = 0;
        sum2 += remainder1 * 9;
        int remainder2 = sum2 % 11;
        if(remainder2 == 10) remainder2 = 0;
        if(Character.getNumericValue(cpf.charAt(9)) != remainder1  || Character.getNumericValue(cpf.charAt(10)) != remainder2) return false;
        return valid;
    }
    public static boolean validatePhone(String phone){
        boolean valid = phone.matches("\\d{11}");

        return valid;
    }
}

//Regra de validação de cpf:
//No caso do CPF, o DV módulo 11 corresponde ao resto da divisão por 11 do somatório da multiplicação de cada algarismo da base respectivamente por 9, 8, 7, 6, 5, 4, 3, 2, 1 e 0, a partir da unidade.
//O resto 10 é considerado 0. Veja, abaixo, exemplo de cálculo de DV módulo 11 para o CPF nº 280012389:
//
//2    8   0   0   1    2    3    8    9
//x    x   x   x   x    x    x    x    x
//1    2   3   4   5    6    7    8    9
//-   --   -   -   -   --   --   --   --
//2 + 16 + 0 + 0 + 5 + 12 + 21 + 64 + 81 = 201 ÷ 11 = 18, com resto 3
//
//2   8   0   0   1    2    3    8    9    3
//x   x   x   x   x    x    x    x    x    x
//0   1   2   3   4    5    6    7    8    9
//-   -   -   -   -   --   --   --   --   --
//0 + 8 + 0 + 0 + 4 + 10 + 18 + 56 + 72 + 27 = 195 ÷ 11 = 17, com resto 8
//
//Portanto, CPF+DV = 280.012.389-38

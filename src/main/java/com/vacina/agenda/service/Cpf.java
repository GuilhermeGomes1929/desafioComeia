package com.vacina.agenda.service;

import org.springframework.stereotype.Service;

@Service
public class Cpf implements Identificacao {

    private static String[] CPFS_INVALIDOS = {"00000000000", "11111111111", "22222222222",
                                            "33333333333", "44444444444", "55555555555",
                                            "66666666666", "77777777777", "88888888888", "99999999999"};


    @Override
    public String obterComMascara(Long identificador) {
        String cpf = String.valueOf(identificador);
        String cpfComMascara = cpf.substring(0,3).concat(".")
                .concat(cpf.substring(3, 6)).concat(".")
                .concat(cpf.substring(6,9)).concat("-")
                .concat(cpf.substring(9,11));
        return cpfComMascara;
    }

    @Override
    public Boolean validar(Long identificador) {

        String cpfString = String.valueOf(identificador);

        if (cpfString.length() != 11){
            return false;
        }
        for (int i = 0; i < CPFS_INVALIDOS.length; i++){
            if (cpfString.equals(CPFS_INVALIDOS[i])){
                return false;
            }
        }
        //Verifica se os digitos verificadores correspondem
        String primeiroDigitoVerificador = obterDigitoVerificador(cpfString.substring(0, 9));
        String segundoDigitoVerificador = obterDigitoVerificador(cpfString.substring(0, 10));


        if (primeiroDigitoVerificador.equals(String.valueOf(cpfString.charAt(9)))
        && segundoDigitoVerificador.equals(String.valueOf(cpfString.charAt(10)))){
            return true;
        }else return false;
    }

    @Override
    public String obterDigitoVerificador(String identificador){
        int soma = 0;
        int multiplicador = identificador.length() + 1;
        int digito;

        for (int i = 0; i < identificador.length(); i++){
            soma = soma + Character.getNumericValue(identificador.charAt(i)) * multiplicador;
            multiplicador -= 1;
        }

        digito = 11 - (soma % 11);

        if(digito > 9){
            return String.valueOf(0);
        }
        return String.valueOf(digito);
    }




}

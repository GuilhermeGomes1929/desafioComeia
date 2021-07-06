package com.vacina.agenda.service;

public interface Identificacao {
    Boolean validar(Long identificador);
    String obterComMascara(Long identificador);
    String obterDigitoVerificador(String identificador);

}

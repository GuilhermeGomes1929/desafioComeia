package com.vacina.agenda.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Agendamento {

    @Id
    private Long cpf;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Date dataNascimento;
    @Column(nullable = false)
    private String profissao;
    @Column(nullable = false)
    private Boolean possuiComorbidade;
    @Column
    private String nomeComorbidade;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp dataHoraAgendamento;

    public Agendamento(Long cpf, String nome, Date dataNascimento, String profissao, Boolean possuiComorbidade, String nomeComorbidade, Timestamp dataHoraAgendamento) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.profissao = profissao;
        this.possuiComorbidade = possuiComorbidade;
        this.nomeComorbidade = nomeComorbidade;
        this.dataHoraAgendamento = dataHoraAgendamento;
    }

    public Agendamento(){}

    public Long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getProfissao() {
        return profissao;
    }

    public Boolean getPossuiComorbidade() {
        return possuiComorbidade;
    }

    public String getNomeComorbidade() {
        return nomeComorbidade;
    }

    public Timestamp getDataHoraAgendamento() {
        return dataHoraAgendamento;
    }
}

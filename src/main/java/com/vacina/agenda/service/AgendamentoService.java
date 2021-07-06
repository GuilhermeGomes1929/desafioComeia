package com.vacina.agenda.service;

import com.vacina.agenda.model.Agendamento;
import com.vacina.agenda.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AgendamentoService {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    public Page<Agendamento> listarAgendamentos(Pageable pageable){
        Page<Agendamento> page = agendamentoRepository.findAll(pageable);
        if (page == null || page.isEmpty()){
            throw new RuntimeException("Não existe agendamentos no banco de dados.");
        }
        return page;
    }

    public Agendamento consultarAgendamentoPorCpf(Long cpf){
        Optional<Agendamento> agendamento = agendamentoRepository.findByCpf(cpf);
        if (agendamento == null || agendamento.isEmpty()){
            throw new RuntimeException("Agendamento com cpf "+cpf+" não encontrado.");
        }
        return agendamento.get();
    }

    public List<Agendamento> consultarAgendamentoPorNome(String nome){
        List<Agendamento> agendamentos = agendamentoRepository.findByNome(nome);
        if (agendamentos == null || agendamentos.isEmpty()){
            throw new RuntimeException("Agendamentos com nome "+nome+" não encontrados.");
        }
        return agendamentos;
    }

    public Agendamento agendar(Agendamento agendamento){
        Optional<Agendamento> agendamentoTemp = agendamentoRepository.findByCpf(agendamento.getCpf());
        if (agendamentoTemp != null || !agendamentoTemp.isEmpty()){
            throw new RuntimeException("Pessoa com o cpf "+agendamento.getCpf()+" já realizou o agendamento.");
        }
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento editarAgendamento(Agendamento agendamento){
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento cancelarAgendamentoPorCpf(Long cpf){
        Optional<Agendamento> agendamento = agendamentoRepository.findByCpf(cpf);
        if (agendamento == null || agendamento.isEmpty()){
            throw new RuntimeException("Agendamento com cpf "+cpf+" não encontrado.");
        }
        agendamentoRepository.delete(agendamento.get());
        return agendamento.get();
    }

}

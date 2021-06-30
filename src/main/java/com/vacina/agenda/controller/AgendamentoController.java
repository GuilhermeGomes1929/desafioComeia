package com.vacina.agenda.controller;

import com.vacina.agenda.model.Agendamento;
import com.vacina.agenda.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AgendamentoController {

    @Autowired
    AgendamentoRepository repository;

    @GetMapping("/")
    public List<Agendamento> listarAgendamentos(){
        return repository.findAll();
    }

    @GetMapping("/consultar")
    public List<Agendamento> consultarAgendamentoCpf(@RequestParam(value = "cpf", required = false) Long cpf,
                                                     @RequestParam(value = "nome", required = false) String nome){
        if (cpf != null && nome != null){
            return repository.findByCpfAndNome(cpf, nome);
        }else if (cpf == null){
            return repository.findByNome(nome);
        }else{
            return repository.findByCpf(cpf);
        }
    }

    @PostMapping("/registrar")
    public Agendamento registrarAgendamento(@RequestBody Agendamento agendamento){
        return repository.save(agendamento);
    }

    @DeleteMapping("/cancelar")
    public Agendamento cancelarAgendamento(@RequestParam("cpf") Long cpf){
        Agendamento agendamento = repository.findById(cpf).get();
        repository.delete(agendamento);
        return agendamento;
    }

    @PutMapping("/editar")
    public Agendamento editarAgendamento(@RequestBody Agendamento agendamento){
        return repository.save(agendamento);
    }



}

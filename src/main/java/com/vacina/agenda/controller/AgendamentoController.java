package com.vacina.agenda.controller;

import com.vacina.agenda.model.Agendamento;
import com.vacina.agenda.repository.AgendamentoRepository;
import com.vacina.agenda.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/")
public class AgendamentoController {

    @Autowired
    AgendamentoService service;
    @GetMapping("/")
    public Page<Agendamento> listarAgendamentos(Pageable pageable){
        try {
            return service.listarAgendamentos(pageable);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/consultar/nome")
    public List<Agendamento> consultarAgendamentosPorNome(@RequestParam(value = "nome") String nome){
        try {
            return service.consultarAgendamentoPorNome(nome);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/consultar/cpf")
    public Agendamento consultarAgendamentoPorCpf(@RequestParam(value = "cpf" ) Long cpf){
        try {
            return service.consultarAgendamentoPorCpf(cpf);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }



    @PostMapping("/registrar")
    public Agendamento registrarAgendamento(@RequestBody Agendamento agendamento){
        try {
            return service.agendar(agendamento);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/cancelar")
    public Agendamento cancelarAgendamento(@RequestParam("cpf") Long cpf){
        try {
            return service.cancelarAgendamentoPorCpf(cpf);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/editar")
    public Agendamento editarAgendamento(@RequestBody Agendamento agendamento){
        try {
            return service.editarAgendamento(agendamento);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }



}

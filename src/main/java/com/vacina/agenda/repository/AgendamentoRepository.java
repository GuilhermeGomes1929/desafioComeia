package com.vacina.agenda.repository;

import com.vacina.agenda.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByCpfAndNome(Long cpf, String nome);
    List<Agendamento> findByNome(String nome);
    Optional<Agendamento> findByCpf(Long cpf);
}

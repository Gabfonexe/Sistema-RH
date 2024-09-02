package com.gabrielf.sistema.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrielf.sistema.enumModels.CandidatoEnum;
import com.gabrielf.sistema.model.CandidatoModel;

public interface CandidatoRepository extends JpaRepository<CandidatoModel, Long>{

  List<CandidatoModel> findByNome(String nome);

  Optional<CandidatoModel> findByCpf(String cpf);

  List<CandidatoModel> findByExperiencia(int experiencia);

  List<CandidatoModel> findByIdade(int idade); 
  
  List<CandidatoModel> findByAtuacao(CandidatoEnum atuacao);

  boolean existsByCpf(String cpf);
}

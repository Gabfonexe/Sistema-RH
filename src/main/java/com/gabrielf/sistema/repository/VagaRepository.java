package com.gabrielf.sistema.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrielf.sistema.enumModels.VagaEnum;
import com.gabrielf.sistema.model.VagaModel;

public interface VagaRepository extends JpaRepository<VagaModel, Long>{

  List<VagaModel> findByAtuacao(VagaEnum atuacaoVaga);
  List<VagaModel> findByExperiencia(int experiencia);
  List<VagaModel> findBySalario(BigDecimal salario); 
  
}




package com.gabrielf.sistema.service;

import java.math.BigDecimal;
import java.util.List;

import com.gabrielf.sistema.enumModels.VagaEnum;
import com.gabrielf.sistema.model.CandidatoModel;
import com.gabrielf.sistema.model.VagaModel;

public interface VagaService {

  VagaModel procurarPorId(Long id);

  List<VagaModel> procurarPorAtuacao(VagaEnum atuacaoVaga);

  List<VagaModel> procurarPorExperiencia(int experienciaVaga);

  List<VagaModel> procurarPorSalario(BigDecimal salarioVaga);

  VagaModel criar (VagaModel criarVaga);
  
  List<VagaModel> listarVagas();

  VagaModel alterar(VagaModel alterarVaga);

  VagaModel deletar(Long id);

  List<VagaModel> adicionarCandidatos(VagaModel vaga);

  //List<VagaModel> adicionarCandidatos2(VagaModel vaga);

  //List<VagaModel> contratar(VagaModel vaga);

  List<VagaModel> contratar2(VagaModel vaga);

  List<CandidatoModel> listaProfissoes(CandidatoModel candidato);

  //CandidatoModel adicionarCandidato(CandidatoModel candidato);
}

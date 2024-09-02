package com.gabrielf.sistema.service;

import java.util.List;

import com.gabrielf.sistema.enumModels.CandidatoEnum;
import com.gabrielf.sistema.model.CandidatoModel;
import com.gabrielf.sistema.model.VagaModel;

public interface CandidatoService {

  CandidatoModel procurarPorId(Long id);

  List<CandidatoModel> procurarPorNome(String nomeCandidato);

  List<CandidatoModel> procurarPorExperiencia(int experienciaCandidato);

  List<CandidatoModel> procurarPorIdade(int idadeCandidato);

  CandidatoModel procurarPorCpf(String cpfCandidato);

  CandidatoModel criar(CandidatoModel candidatoCriar);

  List<CandidatoModel> procurarPorAtuacao(CandidatoEnum atuacao);

  List<CandidatoModel> listarCandidatos();

  CandidatoModel alterarCandidato(CandidatoModel alterarCandidato);

  CandidatoModel deletarCandidato(Long id);

  List<CandidatoModel> aplicarVaga(CandidatoModel candidato);

  List<VagaModel> listaVagas(VagaModel vaga);

}

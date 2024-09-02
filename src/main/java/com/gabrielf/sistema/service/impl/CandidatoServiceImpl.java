package com.gabrielf.sistema.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.gabrielf.sistema.controller.exception.Exception.NoSuchElementExceptionCandidato;
import com.gabrielf.sistema.enumModels.CandidatoEnum;
import com.gabrielf.sistema.model.CandidatoModel;
import com.gabrielf.sistema.model.VagaModel;
import com.gabrielf.sistema.repository.CandidatoRepository;
import com.gabrielf.sistema.repository.VagaRepository;
import com.gabrielf.sistema.service.CandidatoService;

@Service
public class CandidatoServiceImpl implements CandidatoService{

  private final CandidatoRepository candidatoRepository;
  private final VagaRepository vagaRepository;

  public CandidatoServiceImpl(CandidatoRepository candidatoRepository, VagaRepository vagaRepository ){
    this.candidatoRepository = candidatoRepository;
    this.vagaRepository = vagaRepository;
  } // Através do "constructor injection" fica mais dinâmico a manutenção do código, diferente do @Autowired, que injeta automaticamente as dependências

  @Override
  public CandidatoModel procurarPorId(Long id) {

    try {
      CandidatoModel candidato = candidatoRepository.findById(id).orElseThrow(NoSuchElementException::new);
      if(candidato == null ){
        throw new NoSuchElementExceptionCandidato("Não foi possível localizar o candidato");
      }
      return candidato;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  @Override
  public List<CandidatoModel> procurarPorNome(String nome) {
    try {
      List<CandidatoModel> listaCandidato = candidatoRepository.findByNome(nome);
      if(listaCandidato.isEmpty()){ 
        throw new NoSuchElementExceptionCandidato("Não foi possível localizar o candidato");                           
    }
    return listaCandidato;
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
    
  }

  @Override
  public List<CandidatoModel> procurarPorExperiencia(int experiencia) {

    try {
      List<CandidatoModel> listaCandidato = candidatoRepository.findByExperiencia(experiencia);
      if(listaCandidato.isEmpty()){
        throw new NoSuchElementExceptionCandidato("Não foi possível localizar o candidato"); 
    }
    return listaCandidato;
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
    
  }

  @Override
  public List<CandidatoModel> procurarPorIdade(int idade) {
    try {
      List<CandidatoModel> listaCandidato = candidatoRepository.findByIdade(idade);
      if(listaCandidato.isEmpty()){
        throw new NoSuchElementExceptionCandidato("Não foi possível localizar o candidato");
    }
    return listaCandidato;
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  @Override
  public CandidatoModel procurarPorCpf(String cpf) {
    try {
      Optional<CandidatoModel> candOptional = candidatoRepository.findByCpf(cpf);
      CandidatoModel candModel = candOptional.get();
      if(candOptional.isEmpty()){
        throw new NoSuchElementExceptionCandidato("Não foi possível localizar o candidato");
    }
    return candModel;
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    } 
  }

  @Override
  public CandidatoModel criar(CandidatoModel candidatoModel) {
    try {
      if (candidatoModel.getCpf() != null && candidatoRepository.existsByCpf(candidatoModel.getCpf())){
        throw new NoSuchElementExceptionCandidato("Esse Candidato já existe, tente outro CPF");
      }
      return candidatoRepository.save(candidatoModel);
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  @Override
  public List<CandidatoModel> procurarPorAtuacao(CandidatoEnum atuacao) {
    try {
      List<CandidatoModel> candidatoModel = candidatoRepository.findByAtuacao(atuacao);
      if(candidatoModel.isEmpty()){
        throw new NoSuchElementExceptionCandidato("Não foi possível localizar o candidato");   
    }
    return candidatoModel;
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  @Override
  public List<CandidatoModel> listarCandidatos() {
    try {
      List<CandidatoModel> candidatos = candidatoRepository.findAll();
      if(candidatos.isEmpty()){
        throw new NoSuchElementExceptionCandidato("Não há candidatos");   
      }
      return candidatos;
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  @Override
  public CandidatoModel alterarCandidato(CandidatoModel alterarCandidato) {
    Optional<CandidatoModel> candidato = candidatoRepository.findById(alterarCandidato.getId());
    candidato.get().setAtuacao(alterarCandidato.getAtuacao());
    candidato.get().setExperiencia(alterarCandidato.getExperiencia());
    candidato.get().setCpf(alterarCandidato.getCpf());
    candidato.get().setIdade(alterarCandidato.getIdade());
    candidato.get().setNome(alterarCandidato.getNome());
    candidato.get().setSalario(alterarCandidato.getSalario());
    var candidatoAlterado = candidato.get();
    return candidatoRepository.save(candidatoAlterado);
  }

  @Override
  public CandidatoModel deletarCandidato(Long id) {
   CandidatoModel candidato = candidatoRepository.findById(id).get();  // o get() transforma o optional "findById()" em Objeto
   candidatoRepository.delete(candidato);
   return candidato;
  }

  @Override
  public List<CandidatoModel> aplicarVaga(CandidatoModel candidatoObj) {
    List<CandidatoModel> candidatos = candidatoRepository.findAll();
    List<VagaModel> vagas = vagaRepository.findAll();

    List<VagaModel> engenheiros = new ArrayList<>();
    List<VagaModel> professores = new ArrayList<>();
    List<VagaModel> musicos = new ArrayList<>();
    List<VagaModel> desenvolvedores = new ArrayList<>();
    List<VagaModel> motoristas = new ArrayList<>();
    List<VagaModel> medicos = new ArrayList<>();
    List<VagaModel> dentistas = new ArrayList<>();
    List<VagaModel> pedreiros = new ArrayList<>();
    List<VagaModel> porteiros = new ArrayList<>();
    List<VagaModel> diretores = new ArrayList<>();
    List<VagaModel> gerentes = new ArrayList<>();
    List<VagaModel> empresarios = new ArrayList<>();
    List<VagaModel> auxiliares = new ArrayList<>();
    List<VagaModel> advogados = new ArrayList<>();

    for(CandidatoModel candidato : candidatos){
      if(candidato.getVagas().isEmpty()){
        for(VagaModel vagaAtual : vagas){
          if(candidato.getAtuacao().toString().equals(vagaAtual.getAtuacao().toString())){
            if(vagaAtual.getAtuacao().toString().equals("ENGENHEIRO")){
              var profissoes = listaVagas(vagaAtual);
              engenheiros.addAll(profissoes);
              candidato.setVagas(engenheiros);
            }else if(candidato.getAtuacao().toString().equals("PROFESSOR")){
              var profissoes = listaVagas(vagaAtual);
              professores.addAll(profissoes);
              candidato.setVagas(professores); 
            }else if(candidato.getAtuacao().toString().equals("MÚSICO")){
              var profissoes = listaVagas(vagaAtual);
              musicos.addAll(profissoes);
              candidato.setVagas(musicos); 
            }else if(candidato.getAtuacao().toString().equals("DESENVOLVEDOR")){
              var profissoes = listaVagas(vagaAtual);
              desenvolvedores.addAll(profissoes);
              candidato.setVagas(desenvolvedores); 
            }else if(candidato.getAtuacao().toString().equals("MOTORISTA")){
              var profissoes = listaVagas(vagaAtual);
              motoristas.addAll(profissoes);
              candidato.setVagas(motoristas); 
            }else if(candidato.getAtuacao().toString().equals("MÉDICO")){
              var profissoes = listaVagas(vagaAtual);
              medicos.addAll(profissoes);
              candidato.setVagas(medicos); 
            }else if(candidato.getAtuacao().toString().equals("DENTISTA")){
              var profissoes = listaVagas(vagaAtual);
              dentistas.addAll(profissoes);
              candidato.setVagas(dentistas);
            }else if(candidato.getAtuacao().toString().equals("PEDREIRO")){
              var profissoes = listaVagas(vagaAtual);
              pedreiros.addAll(profissoes);
              candidato.setVagas(pedreiros);
            }else if(candidato.getAtuacao().toString().equals("PORTEIRO")){
              var profissoes = listaVagas(vagaAtual);
              porteiros.addAll(profissoes);
              candidato.setVagas(porteiros);
            }else if(candidato.getAtuacao().toString().equals("DIRETOR")){
              var profissoes = listaVagas(vagaAtual);
              diretores.addAll(profissoes);
              candidato.setVagas(diretores);
            }else if(candidato.getAtuacao().toString().equals("GERENTE")){
              var profissoes = listaVagas(vagaAtual);
              gerentes.addAll(profissoes);
              candidato.setVagas(gerentes);
            }else if(candidato.getAtuacao().toString().equals("EMPRESÁRIO")){
              var profissoes = listaVagas(vagaAtual);
              empresarios.addAll(profissoes);
              candidato.setVagas(empresarios);
            }else if(candidato.getAtuacao().toString().equals("AUXILIAR")){
              var profissoes = listaVagas(vagaAtual);
              auxiliares.addAll(profissoes);
              candidato.setVagas(auxiliares);
            }else if(candidato.getAtuacao().toString().equals("ADVOGADO")){
              var profissoes = listaVagas(vagaAtual);
              advogados.addAll(profissoes);
              candidato.setVagas(advogados);
            }
          }
        }
      }
    }
    return candidatos;
  }

  @Override
  public List<VagaModel> listaVagas(VagaModel vaga) {
    if(vaga.getAtuacao().toString().equals("ENGENHEIRO")){
      List<VagaModel> engenheiros = new ArrayList<>();
      engenheiros.add(vaga);
      return engenheiros;
    }else if(vaga.getAtuacao().toString().equals("PROFESSOR")){
      List<VagaModel> professores = new ArrayList<>();
      professores.add(vaga);
      return professores;
    }else if(vaga.getAtuacao().toString().equals("MÚSICO")){
      List<VagaModel> musicos = new ArrayList<>();
      musicos.add(vaga);
      return musicos;
    }else if(vaga.getAtuacao().toString().equals("DESENVOLVEDOR")){
      List<VagaModel> desenvolvedores = new ArrayList<>();
      desenvolvedores.add(vaga);
      return desenvolvedores;
    }else if(vaga.getAtuacao().toString().equals("MOTORISTA")){
      List<VagaModel> motoristas = new ArrayList<>();
      motoristas.add(vaga);
      return motoristas;
    }else if(vaga.getAtuacao().toString().equals("MÉDICO")){
      List<VagaModel> medicos = new ArrayList<>();
      medicos.add(vaga);
      return medicos;
    }else if(vaga.getAtuacao().toString().equals("DENTISTA")){
      List<VagaModel> dentistas = new ArrayList<>();
      dentistas.add(vaga);
      return dentistas;
    }else if(vaga.getAtuacao().toString().equals("PEDREIRO")){
      List<VagaModel> pedreiros = new ArrayList<>();
      pedreiros.add(vaga);
      return pedreiros;
    }else if(vaga.getAtuacao().toString().equals("PORTEIRO")){
      List<VagaModel> porteiros = new ArrayList<>();
      porteiros.add(vaga);
      return porteiros;
    }else if(vaga.getAtuacao().toString().equals("DIRETOR")){
      List<VagaModel> diretores = new ArrayList<>();
      diretores.add(vaga);
      return diretores;
    }else if(vaga.getAtuacao().toString().equals("GERENTE")){
      List<VagaModel> gerentes = new ArrayList<>();
      gerentes.add(vaga);
      return gerentes;
    }else if(vaga.getAtuacao().toString().equals("EMPRESÁRIO")){
      List<VagaModel> empresarios = new ArrayList<>();
      empresarios.add(vaga);
      return empresarios;
    }else if(vaga.getAtuacao().toString().equals("AUXILIAR")){
      List<VagaModel> auxiliares = new ArrayList<>();
      auxiliares.add(vaga);
      return auxiliares;
    }else if(vaga.getAtuacao().toString().equals("ADVOGADO")){
      List<VagaModel> advogados = new ArrayList<>();
      advogados.add(vaga);
      return advogados;
    }else{
      return null;
    }

    
  }

 
}

package com.gabrielf.sistema.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.gabrielf.sistema.controller.exception.Exception.NoSuchElementExceptionVaga;
import com.gabrielf.sistema.enumModels.VagaEnum;
import com.gabrielf.sistema.model.CandidatoModel;
import com.gabrielf.sistema.model.VagaModel;
import com.gabrielf.sistema.repository.CandidatoRepository;
import com.gabrielf.sistema.repository.VagaRepository;
import com.gabrielf.sistema.service.VagaService;


@Service
public class VagaServiceImpl implements VagaService{

  private final VagaRepository vagaRepository;
  private final CandidatoRepository candidatoRepository;

  public VagaServiceImpl(VagaRepository vagaRepository, CandidatoRepository candidatoRepository){
    this.vagaRepository = vagaRepository;
    this.candidatoRepository = candidatoRepository;
  }


  @Override
  public VagaModel procurarPorId(Long id) {
    try {
      var vaga = vagaRepository.findById(id).orElseThrow(NoSuchElementException::new);
      if( vaga == null){
        throw new NoSuchElementExceptionVaga("Vaga não encontrada");
      }
      //VagaModel vagas = vaga.get();
      return vaga;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
      
    }
  }

  @Override
  public List<VagaModel> procurarPorAtuacao(VagaEnum atuacaoVaga) {
    try {
      List<VagaModel> vagaModel = vagaRepository.findByAtuacao(atuacaoVaga);
      if(vagaModel.isEmpty()){
        throw new NoSuchElementExceptionVaga("Candidato não encontrado");
      }
      return vagaModel;
      
    } catch (Exception e) {

      System.out.println(e.getMessage());
      return null;
    }

    
  }

  @Override
  public List<VagaModel> procurarPorExperiencia(int experienciaVaga) {
    try {
      List<VagaModel> vagaModel = vagaRepository.findByExperiencia(experienciaVaga);
      if(vagaModel.isEmpty()){
        throw new NoSuchElementExceptionVaga("Candidato não encontrado");
      }
      return vagaModel;
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    } 
  }

  @Override
  public List<VagaModel> procurarPorSalario(BigDecimal salarioVaga) {
    try {
      List<VagaModel> vagaModel =  vagaRepository.findBySalario(salarioVaga);
      if(vagaModel.isEmpty()){
        throw new NoSuchElementExceptionVaga("Candidato não encontrado");
    }
    return vagaModel;
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    } 
  }

  @Override
  public VagaModel criar(VagaModel vagaModel) {
    adicionarCandidatos(vagaModel);
    contratar2(vagaModel);
    return vagaRepository.save(vagaModel);
  }

  
  @Override
  public List<VagaModel> listarVagas() {
    try {
      List<VagaModel> vagas = vagaRepository.findAll();
      if(vagas.isEmpty()){
        throw new NoSuchElementExceptionVaga("Não há vagas");
      }
      return vagas;
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  @Override
  public VagaModel alterar(VagaModel alterarVaga) {
    Optional<VagaModel> vaga = vagaRepository.findById(alterarVaga.getId());
    vaga.get().setAtuacao(alterarVaga.getAtuacao());
    vaga.get().setExperiencia(alterarVaga.getExperiencia());
    vaga.get().setSalario(alterarVaga.getSalario());
    var vagaAlterada = vaga.get();
    return vagaRepository.save(vagaAlterada);
  }


  @Override
  public VagaModel deletar(Long id) {
    VagaModel vaga = vagaRepository.findById(id).get(); // o get() transforma o optional "findById()" em Objeto
    vagaRepository.delete(vaga);
    return vaga;
  }

  @Override
  public List<VagaModel> adicionarCandidatos(VagaModel vaga) {
    List<VagaModel> vagas = vagaRepository.findAll();
    List<CandidatoModel> candidatos = candidatoRepository.findAll();

    List<CandidatoModel> engenheiros = new ArrayList<>();
    List<CandidatoModel> professores = new ArrayList<>();
    List<CandidatoModel> musicos = new ArrayList<>();
    List<CandidatoModel> desenvolvedores = new ArrayList<>();
    List<CandidatoModel> motoristas = new ArrayList<>();
    List<CandidatoModel> medicos = new ArrayList<>();
    List<CandidatoModel> dentistas = new ArrayList<>();
    List<CandidatoModel> pedreiros = new ArrayList<>();
    List<CandidatoModel> porteiros = new ArrayList<>();
    List<CandidatoModel> diretores = new ArrayList<>();
    List<CandidatoModel> gerentes = new ArrayList<>();
    List<CandidatoModel> empresarios = new ArrayList<>();
    List<CandidatoModel> auxiliares = new ArrayList<>();
    List<CandidatoModel> advogados = new ArrayList<>();


    for(VagaModel vagaAtual : vagas){
      if(vagaAtual.getCandidatos().isEmpty()){
        for(CandidatoModel candidato : candidatos){
          if(vagaAtual.getAtuacao().toString().equals(candidato.getAtuacao().toString())){
            if(candidato.getAtuacao().toString().equals("ENGENHEIRO")){
              var profissoes = listaProfissoes(candidato);
              engenheiros.addAll(profissoes);
              vagaAtual.setCandidatos(engenheiros);
            }else if(candidato.getAtuacao().toString().equals("PROFESSOR")){
              var profissoes = listaProfissoes(candidato);
              professores.addAll(profissoes);
              vagaAtual.setCandidatos(professores); 
            }else if(candidato.getAtuacao().toString().equals("MÚSICO")){
              var profissoes = listaProfissoes(candidato);
              musicos.addAll(profissoes);
              vagaAtual.setCandidatos(musicos); 
            }else if(candidato.getAtuacao().toString().equals("DESENVOLVEDOR")){
              var profissoes = listaProfissoes(candidato);
              desenvolvedores.addAll(profissoes);
              vagaAtual.setCandidatos(desenvolvedores); 
            }else if(candidato.getAtuacao().toString().equals("MOTORISTA")){
              var profissoes = listaProfissoes(candidato);
              motoristas.addAll(profissoes);
              vagaAtual.setCandidatos(motoristas); 
            }else if(candidato.getAtuacao().toString().equals("MÉDICO")){
              var profissoes = listaProfissoes(candidato);
              medicos.addAll(profissoes);
              vagaAtual.setCandidatos(medicos); 
            }else if(candidato.getAtuacao().toString().equals("DENTISTA")){
              var profissoes = listaProfissoes(candidato);
              dentistas.addAll(profissoes);
              vagaAtual.setCandidatos(dentistas);
            }else if(candidato.getAtuacao().toString().equals("PEDREIRO")){
              var profissoes = listaProfissoes(candidato);
              pedreiros.addAll(profissoes);
              vagaAtual.setCandidatos(pedreiros);
            }else if(candidato.getAtuacao().toString().equals("PORTEIRO")){
              var profissoes = listaProfissoes(candidato);
              porteiros.addAll(profissoes);
              vagaAtual.setCandidatos(porteiros);
            }else if(candidato.getAtuacao().toString().equals("DIRETOR")){
              var profissoes = listaProfissoes(candidato);
              diretores.addAll(profissoes);
              vagaAtual.setCandidatos(diretores);
            }else if(candidato.getAtuacao().toString().equals("GERENTE")){
              var profissoes = listaProfissoes(candidato);
              gerentes.addAll(profissoes);
              vagaAtual.setCandidatos(gerentes);
            }else if(candidato.getAtuacao().toString().equals("EMPRESÁRIO")){
              var profissoes = listaProfissoes(candidato);
              empresarios.addAll(profissoes);
              vagaAtual.setCandidatos(empresarios);
            }else if(candidato.getAtuacao().toString().equals("AUXILIAR")){
              var profissoes = listaProfissoes(candidato);
              auxiliares.addAll(profissoes);
              vagaAtual.setCandidatos(auxiliares);
            }else if(candidato.getAtuacao().toString().equals("ADVOGADO")){
              var profissoes = listaProfissoes(candidato);
              advogados.addAll(profissoes);
              vagaAtual.setCandidatos(advogados);
            }
          }
        }
      }
    }
    return vagas;
  }
  


  

  @Override
  public List<VagaModel> contratar2(VagaModel vaga) {
    List<VagaModel> vagas = vagaRepository.findAll();
    //List<VagaModel> vagass = vagaRepository.findByAtuacao(VagaEnum.ENGENHEIRO);
    List<CandidatoModel> candidatos = candidatoRepository.findAll();
    for(VagaModel vagaAtual : vagas){
      if(!vagaAtual.getCandidatos().isEmpty()){
        for(CandidatoModel candidato : candidatos){
          if(vagaAtual.getAtuacao().toString().equals(candidato.getAtuacao().toString())){
            var listaProfissoes = listaProfissoes(candidato);
            vagaAtual.setCandidatos(listaProfissoes);
          }
        }
      }
    }
    return vagas;
  }


  @Override
  public List<CandidatoModel> listaProfissoes(CandidatoModel candidato) {
    if(candidato.getAtuacao().toString().equals("ENGENHEIRO")){
      List<CandidatoModel> engenheiros = new ArrayList<>();
      engenheiros.add(candidato);
      return engenheiros;
    }else if(candidato.getAtuacao().toString().equals("PROFESSOR")){
      List<CandidatoModel> professores = new ArrayList<>();
      professores.add(candidato);
      return professores;
    }else if(candidato.getAtuacao().toString().equals("MÚSICO")){
      List<CandidatoModel> musicos = new ArrayList<>();
      musicos.add(candidato);
      return musicos;
    }else if(candidato.getAtuacao().toString().equals("DESENVOLVEDOR")){
      List<CandidatoModel> desenvolvedores = new ArrayList<>();
      desenvolvedores.add(candidato);
      return desenvolvedores;
    }else if(candidato.getAtuacao().toString().equals("MOTORISTA")){
      List<CandidatoModel> motoristas = new ArrayList<>();
      motoristas.add(candidato);
      return motoristas;
    }else if(candidato.getAtuacao().toString().equals("MÉDICO")){
      List<CandidatoModel> medicos = new ArrayList<>();
      medicos.add(candidato);
      return medicos;
    }else if(candidato.getAtuacao().toString().equals("DENTISTA")){
      List<CandidatoModel> dentistas = new ArrayList<>();
      dentistas.add(candidato);
      return dentistas;
    }else if(candidato.getAtuacao().toString().equals("PEDREIRO")){
      List<CandidatoModel> pedreiros = new ArrayList<>();
      pedreiros.add(candidato);
      return pedreiros;
    }else if(candidato.getAtuacao().toString().equals("PORTEIRO")){
      List<CandidatoModel> porteiros = new ArrayList<>();
      porteiros.add(candidato);
      return porteiros;
    }else if(candidato.getAtuacao().toString().equals("DIRETOR")){
      List<CandidatoModel> diretores = new ArrayList<>();
      diretores.add(candidato);
      return diretores;
    }else if(candidato.getAtuacao().toString().equals("GERENTE")){
      List<CandidatoModel> gerentes = new ArrayList<>();
      gerentes.add(candidato);
      return gerentes;
    }else if(candidato.getAtuacao().toString().equals("EMPRESÁRIO")){
      List<CandidatoModel> empresarios = new ArrayList<>();
      empresarios.add(candidato);
      return empresarios;
    }else if(candidato.getAtuacao().toString().equals("AUXILIAR")){
      List<CandidatoModel> auxiliares = new ArrayList<>();
      auxiliares.add(candidato);
      return auxiliares;
    }else if(candidato.getAtuacao().toString().equals("ADVOGADO")){
      List<CandidatoModel> advogados = new ArrayList<>();
      advogados.add(candidato);
      return advogados;
    }else{
      return null;
    }
  }

}

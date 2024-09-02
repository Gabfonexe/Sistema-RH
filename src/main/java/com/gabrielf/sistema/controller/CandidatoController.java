package com.gabrielf.sistema.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.gabrielf.sistema.enumModels.CandidatoEnum;
import com.gabrielf.sistema.model.CandidatoModel;
import com.gabrielf.sistema.service.impl.CandidatoServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

  private final CandidatoServiceImpl candidatoServiceImpl;

  public CandidatoController(CandidatoServiceImpl candidatoServiceImpl){
    this.candidatoServiceImpl = candidatoServiceImpl;
  }

  @GetMapping("/{id}")
  public ResponseEntity <?> procurarPorId(@PathVariable Long id){
    var candidato = candidatoServiceImpl.procurarPorId(id);
    if(candidato == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
    }
    return ResponseEntity.ok(candidato);
  }
  
  @PostMapping("/cadastrar")
  public ResponseEntity <CandidatoModel> criar(@RequestBody @Valid CandidatoModel candidato){
    var candidatoCriado = candidatoServiceImpl.criar(candidato);
    
    // Em Api Rest, além de retornar o usuário criado, é importante também retornar a localização dele (localização do recurso), por isso a implementação abaixo:
    URI local = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(candidatoCriado.getId()).toUri();
    return ResponseEntity.created(local).body(candidatoCriado);
  }

  @GetMapping("/candidatos")
  public ResponseEntity <?> listarCandidatos(){
    List<CandidatoModel> candidatos = candidatoServiceImpl.listarCandidatos();
    if(candidatos.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
    }
    return ResponseEntity.ok(candidatos);
  }

  @GetMapping("buscarpornome/{nome}")
  public ResponseEntity <?> procurarPorNome(@PathVariable String nome){
    List<CandidatoModel> candidatos = candidatoServiceImpl.procurarPorNome(nome);
    if(candidatos.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
    }
    return ResponseEntity.ok(candidatos);
  }

  @GetMapping("buscarporidade/{idade}")
  public ResponseEntity <?> procurarPorIdade(@PathVariable int idade){
    List<CandidatoModel> candidatos = candidatoServiceImpl.procurarPorIdade(idade);
    if(candidatos.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
    }
    return ResponseEntity.ok(candidatos);
  }

  @GetMapping("buscarporexperiencia/{experiencia}")
  public ResponseEntity <?> procurarPorExperiencia(@PathVariable int experiencia){
    List<CandidatoModel> candidatos = candidatoServiceImpl.procurarPorExperiencia(experiencia);
    if(candidatos.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
    }
    return ResponseEntity.ok(candidatos);
  }

  @GetMapping("buscarporcpf/{cpf}")
  public ResponseEntity <?> procurarPorCpf(@PathVariable String cpf){
    CandidatoModel candidato = candidatoServiceImpl.procurarPorCpf(cpf);
    if(candidato == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga nã encontrada");
    }
    return ResponseEntity.ok(candidato);
  }

  // Método com @RequestParam - Não tem sentido usar por agora
  @GetMapping("buscarporfuncao")
  public ResponseEntity <?> procurarPorFuncao2(@RequestParam CandidatoEnum funcao){
    //CandidatoEnum funcaoFormat = funcao.toLowerCase();
    List<CandidatoModel> candidatos = candidatoServiceImpl.procurarPorAtuacao(funcao);
    if (candidatos.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
    }
    return ResponseEntity.ok(candidatos);
  }

  @PutMapping("/alterar")
  public ResponseEntity<?> alterarCandidato(@RequestBody CandidatoModel candidatoModel){
    CandidatoModel candidato = candidatoServiceImpl.alterarCandidato(candidatoModel);
    if(candidato == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato não encontrado");
    }
    return ResponseEntity.ok(candidato);
  }

  @DeleteMapping("/deletar/{id}")
  public ResponseEntity<?> deletarCandidato(@PathVariable Long id){
    CandidatoModel candidato = candidatoServiceImpl.deletarCandidato(id);
    if(candidato == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato não encontrado");
    }

    return ResponseEntity.ok(candidato);
  }

  @GetMapping("/vagas")
  public ResponseEntity<?> adicionarVaga(CandidatoModel candidato){
    List<CandidatoModel> candidatos = candidatoServiceImpl.aplicarVaga(candidato);
    if(candidatos == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum candidato inscrito");
    }
    return ResponseEntity.ok(candidatos);
  }
}

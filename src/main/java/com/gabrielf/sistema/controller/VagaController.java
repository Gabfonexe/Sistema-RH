package com.gabrielf.sistema.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.gabrielf.sistema.controller.exception.GlobalException;
import com.gabrielf.sistema.enumModels.VagaEnum;
import com.gabrielf.sistema.model.VagaModel;
import com.gabrielf.sistema.service.impl.VagaServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vagas")
public class VagaController {


  @Autowired
  public GlobalException global;

  private final VagaServiceImpl  vagaServiceImpl;

  public VagaController(VagaServiceImpl vagaServiceImpl){
    this.vagaServiceImpl = vagaServiceImpl;
  }
  
  @GetMapping("/{id}")
  public ResponseEntity <?> procurarPorId(@PathVariable Long id){
    var vaga = vagaServiceImpl.procurarPorId(id);
    if(vaga == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
    }
    return ResponseEntity.ok(vaga);
  }

 
  @PostMapping("/cadastrar") // @Valid para validar os atributos passados no Json
  public ResponseEntity <?> criar(@RequestBody @Valid VagaModel vagaModel){
    var vaga = vagaServiceImpl.criar(vagaModel);
 
    // Em Api Rest, além de retornar o usuário criado, é importante também retornar a localização dele (localização do recurso), por isso a implementação abaixo:
    URI local = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vaga.getId()).toUri();
    return ResponseEntity.created(local).body(vaga);
  }

 
  @GetMapping("buscarporexperiencia/{experiencia}")
  public ResponseEntity <?> procurarPorExperiencia(@PathVariable int experiencia){
    List<VagaModel> vagas = vagaServiceImpl.procurarPorExperiencia(experiencia);
    if (vagas.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
    }
    return ResponseEntity.ok(vagas);
  }

  @GetMapping("buscarporatuacao/{atuacao}")
  public ResponseEntity <List<VagaModel>> procurarPorAtuacao(@PathVariable VagaEnum atuacao){
    List<VagaModel> vagas = vagaServiceImpl.procurarPorAtuacao(atuacao);
    return ResponseEntity.ok(vagas);
  }

  @GetMapping("buscarporsalario/{salario}")
  public ResponseEntity <?> procurarPorSalario(@PathVariable BigDecimal salario){
    List<VagaModel> vagas = vagaServiceImpl.procurarPorSalario(salario);
    if(vagas.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
    }
    return ResponseEntity.ok(vagas);
  }

  @GetMapping("/listar")
  public ResponseEntity <?> listarVagas(){
    List<VagaModel> vagas = vagaServiceImpl.listarVagas();
    if(vagas.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
    }
    return ResponseEntity.ok(vagas);
  }

  @PutMapping("/alterar")
  public ResponseEntity<?> alterarVaga(@RequestBody VagaModel vagaModel){
    VagaModel vaga = vagaServiceImpl.alterar(vagaModel);
    if(vaga == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
    }
    return ResponseEntity.ok(vaga);
  }

  @DeleteMapping("/deletar/{id}")
  public ResponseEntity<?> deletarVaga(@PathVariable Long id){
    VagaModel vaga = vagaServiceImpl.deletar(id);
    if(vaga == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
    }
    return ResponseEntity.ok(vaga);
  }

  @GetMapping("/candidatos")
  public ResponseEntity<?> adicionarCandidato(VagaModel vagaModel){
    List<VagaModel> vaga = vagaServiceImpl.adicionarCandidatos(vagaModel);
    if(vaga == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum candidato inscrito");
    }
    return ResponseEntity.ok(vaga);
  }

}

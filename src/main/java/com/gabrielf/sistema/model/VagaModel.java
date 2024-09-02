package com.gabrielf.sistema.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.gabrielf.sistema.enumModels.VagaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "tb_vaga")
public class VagaModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(precision = 10, scale = 2, name = "Salario")
  private BigDecimal salario;

  @Column(name = "Experiencia")
  private int experiencia;

  @Enumerated(EnumType.STRING)
  @Column(name = "Atuacao")
  private VagaEnum atuacao;


  @ManyToMany
  @JoinTable(name = "tb_vaga_candidato", joinColumns = @JoinColumn(name="vaga_id"), inverseJoinColumns = @JoinColumn(name="candidato_id")) // gerencia o relacionamento
  private List <CandidatoModel> candidatos = new ArrayList<>();


  /*
   * 
   */
}

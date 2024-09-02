package com.gabrielf.sistema.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.gabrielf.sistema.enumModels.CandidatoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tb_candidato")
public class CandidatoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, name = "Cpf")
  private String cpf;

  @Column(precision = 10, scale = 2)
  private BigDecimal salario;

  @Column(name = "Experiencia")
  private int experiencia;

  @Column(name = "Nome")
  private String nome;

  @Column(name = "Idade")
  private int idade;

  @Enumerated(EnumType.STRING)
  @Column(name = "Atuacao")
  private CandidatoEnum atuacao;

  @ManyToMany(mappedBy = "candidatos") // não possui o relacionamento.
  private List<VagaModel> vagas = new ArrayList<>();

}

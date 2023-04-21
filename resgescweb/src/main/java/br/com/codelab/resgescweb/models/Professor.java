package br.com.codelab.resgescweb.models;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;


@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //quando for criado um novo registro de professor o próprio banco vai criar automaticamente um novo id, que será incremental e único e que será a chave primária
    private Long id;

    @Column(nullable = false)
    private String nome;

    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private StatusProfessor statusProfessor;

    public Professor(String nome, BigDecimal salario, StatusProfessor statusProfessor) {
        this.nome = nome;
        this.salario = salario;
        this.statusProfessor = statusProfessor;
    }

    public Professor() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public StatusProfessor getStatusProfessor() {
        return statusProfessor;
    }

    public void setStatusProfessor(StatusProfessor statusProfessor) {
        this.statusProfessor = statusProfessor;
    }
}

package br.com.codelab.resgescweb.dto;

import br.com.codelab.resgescweb.models.Professor;
import br.com.codelab.resgescweb.models.StatusProfessor;
import java.math.BigDecimal;

//É uma classe DTO (Date Transfer Object)
//será uma classe auxiliar que vai receber todos os valores dos campos do formulário que deseja que o usuário informe
//de modo que se algueém tentar manipular  o html e tentar modificar a mão um dado que não que essa classe não receba, não será armazenado no bdd

public class RequisicaoNovoProfessor {

    private String nome;
    private BigDecimal salario;
    private StatusProfessor statusProfessor;

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

    public Professor toProfessor(){
        Professor professor = new Professor();
        professor.setNome(this.nome);
        professor.setSalario(this.salario);
        professor.setStatusProfessor(this.statusProfessor);

        return professor;
    }

    @Override
    public String toString() {
        return "RequisicaoNovoProfessor{" +
                "nome='" + nome + '\'' +
                ", salario=" + salario +
                ", statusProfessor=" + statusProfessor +
                '}';
    }
}

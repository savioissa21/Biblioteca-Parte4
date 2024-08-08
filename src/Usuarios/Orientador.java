package Usuarios;

import java.io.Serializable;

public class Orientador extends Pessoa implements Serializable{
    private static final long serialVersionUID = 1L;

    private String disciplina;
    private String grauAcademico;
    private String email;

    
    public Orientador(String nome, String senha) {
        super(nome, senha);
    }


    public Orientador(String nome, int idade, String sexo, String telefone, String senha) {
        super(nome, idade, sexo, telefone, senha);
    }

    public Orientador(String nome, int idade, String sexo, String telefone, String disciplina, String grauAcademico, String senha, String email) {
        super(nome, idade, sexo, telefone, senha);
        this.disciplina = disciplina;
        this.grauAcademico = grauAcademico;
        this.email = email;
    }
    
    public Orientador(String nome){
        super(nome);
    }

    public String getDisciplina() {
        return disciplina;
    }
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
    public String getGrauAcademico() {
        return grauAcademico;
    }
    public void setGrauAcademico(String grauAcademico) {
        this.grauAcademico = grauAcademico;
    }


 public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


@Override
    public String getTipo() {
        return "Professor";
    }
    
    @Override
    public String toString() {
        return "Orientador [disciplina=" + disciplina + ", grauAcademico=" + grauAcademico + ", getId()=" + getId()
                + ", getNome()=" + getNome() + ", getDisciplina()=" + getDisciplina() + ", getIdade()=" + getIdade()
                + ", getGrauAcademico()=" + getGrauAcademico() + ", getSexo()=" + getSexo() + ", getTelefone()="
                + getTelefone() + ", email = " + getEmail() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

    

}

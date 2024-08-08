package Faculdade;

import java.util.Date;

import Usuarios.Aluno;
import Usuarios.Orientador;

public class Trabalho {
    private long id;
    private String titTrabalho;
    private Faculdade faculdade;
    private String dataConclusão;
    private Aluno aluno;
    private Orientador orientador;
    private Curso curso;
    private String localArquivo;
    private int score;
    private int quantidadedeVotos;

    
    public Trabalho(String titTrabalho, Faculdade faculdade, String dataConclusão, Aluno aluno,
            Orientador orientador, Curso curso, int score, int quantidadedeVotos) {
        this.titTrabalho = titTrabalho;
        this.faculdade = faculdade;
        this.dataConclusão = dataConclusão;
        this.aluno = aluno;
        this.orientador = orientador;
        this.curso = curso;
        this.score = score;
        this.quantidadedeVotos = quantidadedeVotos;
        }
    
    public Trabalho(long id, String titTrabalho, Faculdade faculdade, String dataConclusão, Aluno aluno,
            Orientador orientador, Curso curso, int score, int quantidadedeVotos) {
        this.id = id;
        this.titTrabalho = titTrabalho;
        this.faculdade = faculdade;
        this.dataConclusão = dataConclusão;
        this.aluno = aluno;
        this.orientador = orientador;
        this.curso = curso;
        this.score = score;
        this.quantidadedeVotos = quantidadedeVotos;
    }
    public Trabalho(long id, String titTrabalho, Faculdade faculdade, String dataConclusão, Aluno aluno,
            Orientador orientador, Curso curso, String localArquivo, int score, int quantidadedeVotos) {
        this.id = id;
        this.titTrabalho = titTrabalho;
        this.faculdade = faculdade;
        this.dataConclusão = dataConclusão;
        this.aluno = aluno;
        this.orientador = orientador;
        this.curso = curso;
        this.localArquivo = localArquivo;
        this.score = score;
        this.quantidadedeVotos = quantidadedeVotos;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitTrabalho() {
        return titTrabalho;
    }
    public void setTitTrabalho(String titTrabalho) {
        this.titTrabalho = titTrabalho;
    }
    public Faculdade getFaculdade() {
        return faculdade;
    }
    public void setFaculdade(Faculdade faculdade) {
        this.faculdade = faculdade;
    }
    public String getDataConclusão() {
        return dataConclusão;
    }
    public void setDataConclusão(String dataConclusão) {
        this.dataConclusão = dataConclusão;
    }
    public Aluno getAluno() {
        return aluno;
    }
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    public Orientador getOrientador() {
        return orientador;
    }
    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public String getLocalArquivo() {
        return localArquivo;
    }
    public void setLocalArquivo(String localArquivo) {
        this.localArquivo = localArquivo;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getQuantidadedeVotos() {
        return quantidadedeVotos;
    }
    public void setQuantidadedeVotos(int quantidadedeVotos) {
        this.quantidadedeVotos = quantidadedeVotos;
    }
    
}

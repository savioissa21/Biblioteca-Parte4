package Emprest;
import java.time.LocalDate;

import Obras.Livro;
import Usuarios.Pessoa;

public class Emprestimos {
    private int id;
    private String dataDoEmprestimo;
    private String horaDoEmprestimo;
    private Livro livros;
    private Pessoa usuarios;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean devolvido;
    
    
    public Emprestimos(int id, String dataDoEmprestimo, String horaDoEmprestimo, Livro livros, Pessoa usuarios) {
        if (!livros.isDisponivel()) {
            throw new IllegalArgumentException("Livro não está disponível para empréstimo.");
        }
        this.id = id;
        this.dataDoEmprestimo = dataDoEmprestimo;
        this.horaDoEmprestimo = horaDoEmprestimo;
        this.livros = livros;
        this.usuarios = usuarios;
        this.livros.setDisponivel(false);
    }
    public Emprestimos(String dataDoEmprestimo, String horaDoEmprestimo, Livro livros, Pessoa usuarios) {
        if (!livros.isDisponivel()) {
            throw new IllegalArgumentException("Livro não está disponível para empréstimo.");
        }
        this.dataDoEmprestimo = dataDoEmprestimo;
        this.horaDoEmprestimo = horaDoEmprestimo;
        this.livros = livros;
        this.usuarios = usuarios;
        this.livros.setDisponivel(false);
    }


    public Emprestimos(Pessoa usuario, Livro livro, LocalDate dataEmprestimo ) {
        this.usuarios = usuario;
        this.livros = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataEmprestimo.plusDays(14);
        this.devolvido = false;
    }

    public String getDataDoEmprestimo() {
        return dataDoEmprestimo;
    }
    public void setDataDoEmprestimo(String dataDoEmprestimo) {
        this.dataDoEmprestimo = dataDoEmprestimo;
    }
    public String getHoraDoEmprestimo() {
        return horaDoEmprestimo;
    }
    public void setHoraDoEmprestimo(String horaDoEmprestimo) {
        this.horaDoEmprestimo = horaDoEmprestimo;
    }
    public Livro getLivros() {
        return livros;
    }
    public void setLivros(Livro livros) {
        this.livros = livros;
    }
    public Pessoa getPessoa() {
        return usuarios;
    }
    public void setPessoa(Pessoa usuarios) {
        this.usuarios = usuarios;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
    public void devolverLivro(Livro livro){
        this.livros.setDisponivel(true);
    }

}

package Emprest;

import java.time.LocalDate;
import java.time.LocalTime;

import Obras.Livro;
import Usuarios.Pessoa;

public class Reserva {
    private int id;
    private Emprestimos Emprestimo;
    private Livro livros;
    private Pessoa usuarios;
    private LocalDate dataReserva;
    private LocalTime horaReserva;
    private String status;


    public Reserva(Pessoa usuario, Livro livro, LocalDate dataReserva, LocalTime horaReserva) {
        this.usuarios = usuario;
        this.livros = livro;
        this.dataReserva = dataReserva;
        this.horaReserva = horaReserva;
    }

    public Reserva(Livro livros, Pessoa usuarios) {
        if (livros.isDisponivel()) {
            throw new IllegalArgumentException("Livro está disponível, faça um empréstimo em vez de uma reserva.");
        }
        this.livros = livros;
        this.usuarios = usuarios;
        this.status = "Ativa";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Emprestimos getEmprestimo() {
        return Emprestimo;
    }

    public void setEmprestimo(Emprestimos emprestimo) {
        Emprestimo = emprestimo;
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
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void cancelar() {
        this.status = "Cancelada";
    }
    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public LocalTime getHoraReserva() {
        return horaReserva;
    }
    // Método para verificar se a reserva está ativa
    public boolean isAtiva() {
        return this.status.equals("Ativa");
    }
}

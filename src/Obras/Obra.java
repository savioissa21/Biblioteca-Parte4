package Obras;

import java.io.Serializable;

public abstract class Obra implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private String titulo;
    private String autores;
    private String area;
    private String ano;
    private boolean digital;

    public Obra(String titulo, String autores){
        this.titulo = titulo;
        this.autores = autores;
    }

    public Obra(String titulo, String autores, String area, String ano, boolean digital) {
        this.titulo = titulo;
        this.autores = autores;
        this.area = area;
        this.ano = ano;
        this.digital = digital;
    }
    
    public Obra(String titulo, String autores, String area, String ano) {
        this.titulo = titulo;
        this.autores = autores;
        this.area = area;
        this.ano = ano;
    }

    
    public Obra(int id, String titulo, String autores, String area, String ano) {
        this.id = id;
        this.titulo = titulo;
        this.autores = autores;
        this.area = area;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutores() {
        return autores;
    }
    public void setAutores(String autores) {
        this.autores = autores;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getAno() {
        return ano;
    }
    public void setAno(String ano) {
        this.ano = ano;
    }

    public void visualizarObra(Obra obra){
        System.out.println("Visualizando obra");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public boolean isDigital(){
        return digital; 
    }

    public void setDigital(boolean digital){
        this.digital = digital;
    }

}

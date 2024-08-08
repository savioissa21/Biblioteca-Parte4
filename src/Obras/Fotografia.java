package Obras;

import java.io.Serializable;

public class Fotografia extends Obra implements Serializable{
    private static final long serialVersionUID = 1L;


    public Fotografia(String titulo, String autores, String area, String ano, boolean digital) {
        super(titulo, autores, area, ano, digital);
    }

    public void visualizarObra(Fotografia fotografia){
        System.out.println("Visualizando Fotografia");
    }
}

package Obras;

import java.io.Serializable;

public class MidiaAudio extends Obra implements Serializable{
    private static final long serialVersionUID = 1L;


    public MidiaAudio(String titulo, String autores, String area, String ano, boolean digital) {
        super(titulo, autores, area, ano, digital);
    }

    public void visualizarObra(MidiaAudio midiaaudio){
        System.out.println("Consumindo MidiaAudio");
    }
}

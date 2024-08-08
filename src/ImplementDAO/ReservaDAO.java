package ImplementDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import Emprest.Reserva;
import Interface.DAO;

public class ReservaDAO implements DAO<Reserva>{
    private static final String path = "C:\\temp\\DadosBiblioteca.txt";
    private Map<Integer, Reserva> database = new HashMap<>();
    private int currentId = 0;

    public ReservaDAO() throws IOException {
        loadFromFile();
    }
    @Override
    public void gravar(Reserva obj) {
        obj.setId(currentId++);
        database.put(obj.getId(), obj);
        saveToFile();
        System.out.println("Reserva gravada: " + obj.getId());
    }

    @Override
    public void excluir(Reserva obj) {
        database.remove(obj.getId());
        saveToFile();
        System.out.println("Reserva excluida: " + obj.getId());
    }

    @Override
    public void atualizar(Reserva obj) {
        database.put(obj.getId(), obj);
        saveToFile();
        System.out.println("Obra Atualizado " + obj);
    }

    @Override
    public Reserva ler(int id) {
        return database.get(id);
    }

     private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(database);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            database = (Map<Integer, Reserva>) ois.readObject();
            currentId = database.size();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}

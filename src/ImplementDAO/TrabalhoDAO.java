package ImplementDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import Faculdade.Trabalho;
import Interface.DAO;
import Obras.Obra;


public class TrabalhoDAO implements DAO<Trabalho>{
    private static final String path = "C:\\temp\\DadosBiblioteca.txt";
    private Map<Integer, Trabalho> database = new HashMap<>();
    private int currentId = 0;

    public TrabalhoDAO() throws IOException {
        loadFromFile();
    }


    @Override
    public void gravar(Trabalho obj) {
        obj.setId(currentId++);
        database.put((int) obj.getId(), obj);
        saveToFile();
        System.out.println("Obra gravada: " + obj.getTitTrabalho());
    }

    @Override
    public void excluir(Trabalho obj) {
        database.remove((int)obj.getId());
        saveToFile();
        System.out.println("Obra excluida: " + obj.getTitTrabalho());
    }


    @Override
    public Trabalho ler(int id) {
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
            database = (HashMap<Integer, Trabalho>) ois.readObject();
            currentId = database.size();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void atualizar(Trabalho obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    
}


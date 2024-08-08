package ImplementDAO;

import Interface.DAO;
import Usuarios.Pessoa;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PessoaDAO implements DAO<Pessoa> {
    private static final String path = "C:\\temp\\DadosBiblioteca.txt";
    private Map<Integer, Pessoa> database = new HashMap<>();
    private int currentId = 0;

    public PessoaDAO() {
        loadFromFile();
    }

    @Override
    public void gravar(Pessoa obj) {
        obj.setId(currentId++);
        database.put(obj.getId(), obj);
        saveToFile();
        System.out.println("Usuário gravado: " + obj.getNome());
    }

    @Override
    public void excluir(Pessoa obj) {
        database.remove(obj.getId());
        saveToFile();
        System.out.println("Usuário excluído " + obj.getNome());
    }

    @Override
    public void atualizar(Pessoa obj) {
        database.put(obj.getId(), obj);
        saveToFile();
        System.out.println("Usuário atualizado " + obj.getNome());
    }

    @Override
    public Pessoa ler(int id) {
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
        if (!file.exists() || file.length() == 0) {
            System.out.println("Arquivo não encontrado ou vazio. Criando um novo arquivo.");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            database = (HashMap<Integer, Pessoa>) ois.readObject();
            currentId = database.size();
        } catch (EOFException e) {
            System.out.println("Fim do arquivo atingido. Nenhum dado foi lido.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

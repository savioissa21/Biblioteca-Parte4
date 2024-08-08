package Interface;

public interface DAO <T> {
    void gravar(T obj);
    void excluir(T obj);
    void atualizar(T obj);
    T ler(int id);


}


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Emprest.Emprestimos;
import Emprest.Reserva;
import Faculdade.Curso;
import Faculdade.Faculdade;
import Faculdade.Trabalho;
import Obras.Livro;
import Usuarios.Aluno;
import Usuarios.Funcionario;
import Usuarios.Orientador;
import Usuarios.Pessoa;
import Usuarios.PessoaInstanceCreator;

public class Main {
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Pessoa.class, new PessoaInstanceCreator())
            .create();

    private static final String USUARIOS_FILE = "C:\\temp\\usuarios.json";
    private static final String LIVROS_FILE = "C:\\temp\\livros.json";
    private static final String EMPRESTIMOS_FILE = "C:\\temp\\emprestimos.json";
    private static final String RESERVA_FILE = "C:\\temp\\reservas.json";
    private static final String TRABALHO_FILE = "C:\\temp\\trabalhos.json";

    private static ArrayList<Emprestimos> emprestimos;
    private static ArrayList<Livro> livros;
    private static ArrayList<Pessoa> usuarios;
    private static ArrayList<Reserva> reservas;
    private static ArrayList<Trabalho> trabalhos;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        emprestimos = loadFromFile(EMPRESTIMOS_FILE, new TypeToken<ArrayList<Emprestimos>>() {
        }.getType());
        livros = loadFromFile(LIVROS_FILE, new TypeToken<ArrayList<Livro>>() {
        }.getType());
        usuarios = loadFromFile(USUARIOS_FILE, new TypeToken<ArrayList<Pessoa>>() {
        }.getType());
        reservas = loadFromFile(RESERVA_FILE, new TypeToken<ArrayList<Reserva>>() {
        }.getType());

        Pessoa usuarioAdm = findUsuarioByName("adm");
        if (usuarioAdm == null) {
            usuarioAdm = new Funcionario("adm", "123");
            usuarios.add(usuarioAdm);
            saveToFile(USUARIOS_FILE, usuarios);
            System.out.println("Usuário padrão 'adm' criado.");
        } else {
            System.out.println("Usuário padrão 'adm' já existe.");
        }

        while (true) {
            System.out.println();
            System.out.println("Escolha o tipo de usuário:");
            System.out.println("1. Funcionário");
            System.out.println("2. Usuário Comum");
            System.out.println("3. Sair");
            int opcaoUsuario = sc.nextInt();
            sc.nextLine();

            if (opcaoUsuario == 3) {
                sc.close();
                System.exit(0);
            }

            System.out.println("Informe o nome de usuário:");
            String nome = sc.nextLine();
            System.out.println("Informe a senha:");
            String senha = sc.nextLine();

            Pessoa usuarioLogado = autenticarUsuario(nome, senha, opcaoUsuario);
            if (usuarioLogado != null) {
                if (opcaoUsuario == 1) {
                    menuFuncionario(sc);
                } else {
                    menuUsuarioComum(sc, usuarioLogado);
                }
            } else {
                System.out.println("Login ou senha inválidos.");
            }
        }
    }

    private static Pessoa autenticarUsuario(String nome, String senha, int tipoUsuario) {
        for (Pessoa usuario : usuarios) {
            if (usuario.getNome().equals(nome) && usuario.getSenha().equals(senha)) {
                if ((tipoUsuario == 1 && usuario instanceof Funcionario) ||
                        (tipoUsuario == 2 && !(usuario instanceof Funcionario))) {
                    return usuario;
                }
            }
        }
        return null;
    }

    private static void menuFuncionario(Scanner sc) {
        while (true) {
            System.out.println();
            System.out.println("Escolha uma opção:");
            System.out.println("1. Gerenciar livro");
            System.out.println("2. Gerenciar usuário");
            System.out.println("3. Gerenciar empréstimos");
            System.out.println("4. Gerenciar reservas");
            System.out.println("5. Cadastrar trabalho");

            System.out.println("6. Sair");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    gerenciarLivro(sc);
                    break;
                case 2:
                    gerenciarUsuario(sc);
                    break;
                case 3:
                    gerenciarEmprestimos(sc);
                    break;
                case 4:
                    gerenciarReservas(sc);
                    break;
                case 5:
                    cadastrarTrabalho(sc);
                    break;
                case 6:
                    return; // Volta para o menu anterior
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
                    break;
            }
        }
    }

    private static void menuUsuarioComum(Scanner sc, Pessoa usuarioLogado) {
        while (true) {
            System.out.println();
            System.out.println("Escolha uma opção:");
            System.out.println("1. Consultar acervo");
            System.out.println("2. Fazer empréstimo");
            System.out.println("3. Fazer devolução");
            System.out.println("4. Fazer reserva");
            System.out.println("5. Consultar seus empréstimos");
            System.out.println("6. Consultar suas reservas");
            System.out.println("7. Sair");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    consultarAcervo();
                    break;
                case 2:
                    realizarEmprestimo(sc);
                    break;
                case 3:
                    realizarDevolucao(sc);
                    break;
                case 4:
                    realizarReserva(sc);
                    break;
                case 5:
                    listarEmprestimosUsuario(usuarioLogado);
                    break;
                case 6:
                    listarReservasUsuario(usuarioLogado);
                    break;
                case 7:
                    System.exit(0);
                    System.out.println("Saindo");
                    return;
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
                    break;
            }
        }
    }

    private static final int LIMITE_LIVROS_ESTUDANTE = 3;
    private static final int LIMITE_LIVROS_FUNCIONARIO = 6;
    private static final int LIMITE_LIVROS_PROFESSOR = 9;

    private static final int DIAS_EMPRESTIMO_ESTUDANTE = 7;
    private static final int DIAS_EMPRESTIMO_FUNCIONARIO = 15;
    private static final int DIAS_EMPRESTIMO_PROFESSOR = 30;

    private static boolean podeEmprestar(Pessoa usuario, Livro livro) {
        if (usuario == null || livro == null) {
            System.out.println("Usuário ou livro inválido.");
            return false;
        }

        int livrosEmprestados = contarLivrosEmprestados(usuario);

        switch (usuario.getTipo()) {
            case "Estudante":
                if (livrosEmprestados >= LIMITE_LIVROS_ESTUDANTE) {
                    System.out.println("Estudante já atingiu o limite de empréstimos.");
                    return false;
                }
                break;
            case "Funcionário":
                if (livrosEmprestados >= LIMITE_LIVROS_FUNCIONARIO) {
                    System.out.println("Funcionário já atingiu o limite de empréstimos.");
                    return false;
                }
                break;
            case "Professor":
                if (livrosEmprestados >= LIMITE_LIVROS_PROFESSOR) {
                    System.out.println("Professor já atingiu o limite de empréstimos.");
                    return false;
                }
                break;
            default:
                System.out.println("Tipo de usuário desconhecido.");
                return false;
        }

        return true;
    }

    private static void realizarEmprestimo(Scanner sc) {
        System.out.println("Nome do livro: ");
        String tituloLivro = sc.nextLine();
        Livro livroSelecionado = null;
        for (Livro l : livros) {
            if (tituloLivro.equals(l.getTitulo())) {
                livroSelecionado = l;
                break;
            }
        }
        if (livroSelecionado == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        System.out.println("Nome do usuário: ");
        String nomeUsuario = sc.nextLine();
        Pessoa usuarioSelecionado = null;
        for (Pessoa u : usuarios) {
            if (nomeUsuario.equals(u.getNome())) {
                usuarioSelecionado = u;
                break;
            }
        }
        if (usuarioSelecionado == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        realizarEmprestimo(usuarioSelecionado, livroSelecionado);
    }

    private static void realizarEmprestimo(Pessoa usuario, Livro livro) {
        if (!podeEmprestar(usuario, livro)) {
            System.out.println("Não foi possível realizar o empréstimo.");
            return;
        }

        int diasEmprestimo;
        switch (usuario.getTipo()) {
            case "Estudante":
                diasEmprestimo = DIAS_EMPRESTIMO_ESTUDANTE;
                break;
            case "Funcionário":
                diasEmprestimo = DIAS_EMPRESTIMO_FUNCIONARIO;
                break;
            case "Professor":
                diasEmprestimo = DIAS_EMPRESTIMO_PROFESSOR;
                break;
            default:
                System.out.println("Tipo de usuário desconhecido.");
                return;
        }

        Emprestimos novoEmprestimo = new Emprestimos(usuario, livro, LocalDate.now());
        emprestimos.add(novoEmprestimo);
        saveToFile(EMPRESTIMOS_FILE, emprestimos);
        System.out.println("Empréstimo realizado com sucesso! Devolução em " + diasEmprestimo + " dias.");
    }

    private static int contarLivrosEmprestados(Pessoa usuario) {
        int count = 0;
        for (Emprestimos emprestimo : emprestimos) {
            if (emprestimo.getPessoa().equals(usuario) && !emprestimo.isDevolvido()) {
                count++;
            }
        }
        return count;
    }

    private static void consultarAcervo() {
        System.out.println("Livros disponíveis no acervo:");
        for (Livro livro : livros) {
            System.out.println(livro.getTitulo());
        }
    }

    private static void realizarDevolucao(Scanner sc) {
        System.out.println("Nome do livro: ");
        String tituloLivro = sc.nextLine();

        System.out.println("Nome do usuário: ");
        String nomeUsuario = sc.nextLine();

        Emprestimos emprestimoSelecionado = null;
        for (Emprestimos e : emprestimos) {
            if (e.getLivros().getTitulo().equals(tituloLivro) && e.getPessoa().getNome().equals(nomeUsuario)) {
                emprestimoSelecionado = e;
                break;
            }
        }

        if (emprestimoSelecionado == null) {
            System.out.println("Empréstimo não encontrado.");
            return;
        }

        emprestimoSelecionado.setDevolvido(true);
        saveToFile(EMPRESTIMOS_FILE, emprestimos);
        System.out.println("Devolução realizada com sucesso!");
    }

    private static void realizarReserva(Scanner sc) {
        System.out.println("Nome do livro: ");
        String tituloLivro = sc.nextLine();
        Livro livroSelecionado = null;
        for (Livro l : livros) {
            if (tituloLivro.equals(l.getTitulo())) {
                livroSelecionado = l;
                break;
            }
        }
        if (livroSelecionado == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        System.out.println("Nome do usuário: ");
        String nomeUsuario = sc.nextLine();
        Pessoa usuarioSelecionado = null;
        for (Pessoa u : usuarios) {
            if (nomeUsuario.equals(u.getNome())) {
                usuarioSelecionado = u;
                break;
            }
        }
        if (usuarioSelecionado == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        for (Emprestimos emprestimo : emprestimos) {
            if (emprestimo.getLivros().equals(livroSelecionado) && !emprestimo.isDevolvido()) {
                System.out.println("Livro está emprestado, realizando reserva.");
                Reserva novaReserva = new Reserva(usuarioSelecionado, livroSelecionado, LocalDate.now(),
                        LocalTime.now());
                reservas.add(novaReserva);
                saveToFile(RESERVA_FILE, reservas);
                return;
            }
        }

        System.out.println("Livro está disponível, realizando empréstimo.");
        realizarEmprestimo(usuarioSelecionado, livroSelecionado);
    }

    private static void listarEmprestimosUsuario(Pessoa usuario) {
        System.out.println("Empréstimos do usuário " + usuario.getNome() + ":");
        for (Emprestimos emprestimo : emprestimos) {
            if (emprestimo.getPessoa().equals(usuario) && !emprestimo.getLivros().isDisponivel()) {
                System.out.println(emprestimo.getLivros().getTitulo());
            }
        }
    }

    private static void listarReservasUsuario(Pessoa usuario) {
        System.out.println("Reservas do usuário " + usuario.getNome() + ":");
        for (Reserva reserva : reservas) {
            if (reserva.getPessoa().equals(usuario)) {
                System.out.println(reserva.getLivros().getTitulo());
            }
        }
    }

    private static void gerenciarLivro(Scanner sc) {
        while (true) {
            System.out.println();
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar livro");
            System.out.println("2. Remover livro");
            System.out.println("3. Listar livros");
            System.out.println("4. Voltar");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    adicionarLivro(sc);
                    break;
                case 2:
                    removerLivro(sc);
                    break;
                case 3:
                    listarLivros();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
                    break;
            }
        }
    }

    private static void adicionarLivro(Scanner sc) {
        System.out.println("Informe o título do livro:");
        String titulo = sc.nextLine();
        System.out.println("Informe o autor do livro:");
        String autor = sc.nextLine();

        Livro novoLivro = new Livro(titulo, autor);
        livros.add(novoLivro);
        saveToFile(LIVROS_FILE, livros);
        System.out.println("Livro adicionado com sucesso!");
    }

    private static void removerLivro(Scanner sc) {
        System.out.println("Informe o título do livro a ser removido:");
        String titulo = sc.nextLine();

        Livro livroParaRemover = null;
        for (Livro l : livros) {
            if (titulo.equals(l.getTitulo())) {
                livroParaRemover = l;
                break;
            }
        }

        if (livroParaRemover == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        livros.remove(livroParaRemover);
        saveToFile(LIVROS_FILE, livros);
        System.out.println("Livro removido com sucesso!");
    }

    private static void listarLivros() {
        System.out.println("Livros cadastrados:");
        for (Livro livro : livros) {
            System.out.println(livro.getTitulo() + " - " + livro.getAutores());
        }
    }

    private static void gerenciarUsuario(Scanner sc) {
        while (true) {
            System.out.println();
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar usuário");
            System.out.println("2. Remover usuário");
            System.out.println("3. Listar usuários");
            System.out.println("4. Voltar");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    adicionarUsuario(sc);
                    break;
                case 2:
                    removerUsuario(sc);
                    break;
                case 3:
                    listarUsuarios();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
                    break;
            }
        }
    }

    private static void adicionarUsuario(Scanner sc) {
        System.out.println("Informe o nome do usuário:");
        String nome = sc.nextLine();
        System.out.println("Informe a senha do usuário:");
        String senha = sc.nextLine();

        System.out.println("Escolha o tipo de usuário:");
        System.out.println("1. Estudante");
        System.out.println("2. Funcionário");
        System.out.println("3. Professor");

        int tipo = sc.nextInt();
        sc.nextLine();

        Pessoa novoUsuario;
        switch (tipo) {
            case 1:
                novoUsuario = new Aluno(nome, senha);
                break;
            case 2:
                novoUsuario = new Funcionario(nome, senha);
                break;
            case 3:
                novoUsuario = new Orientador(nome, senha);
                break;
            default:
                System.out.println("Tipo de usuário inválido.");
                return;
        }

        usuarios.add(novoUsuario);
        saveToFile(USUARIOS_FILE, usuarios);
        System.out.println("Usuário adicionado com sucesso!");
    }

    private static void removerUsuario(Scanner sc) {
        System.out.println("Informe o nome do usuário a ser removido:");
        String nome = sc.nextLine();

        Pessoa usuarioParaRemover = null;
        for (Pessoa u : usuarios) {
            if (nome.equals(u.getNome())) {
                usuarioParaRemover = u;
                break;
            }
        }

        if (usuarioParaRemover == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        usuarios.remove(usuarioParaRemover);
        saveToFile(USUARIOS_FILE, usuarios);
        System.out.println("Usuário removido com sucesso!");
    }

    private static void listarUsuarios() {
        System.out.println("Usuários cadastrados:");
        for (Pessoa usuario : usuarios) {
            System.out.println(usuario.getNome() + " - " + usuario.getTipo());
        }
    }

    private static void gerenciarEmprestimos(Scanner sc) {
        System.out.println("Empréstimos realizados:");
        for (Emprestimos emprestimo : emprestimos) {
            System.out.println("Livro: " + emprestimo.getLivros().getTitulo() + ", Usuário: "
                    + emprestimo.getPessoa().getNome() + ", Data de Empréstimo: " + emprestimo.getDataDoEmprestimo()
                    + ", Data de Devolução: " + emprestimo.getDataDevolucao() + ", Devolvido: "
                    + (emprestimo.isDevolvido() ? "Sim" : "Não"));
        }
    }

    private static void gerenciarReservas(Scanner sc) {
        System.out.println("Reservas realizadas:");
        for (Reserva reserva : reservas) {
            System.out.println("Livro: " + reserva.getLivros().getTitulo() + ", Usuário: "
                    + reserva.getPessoa().getNome() + ", Data da Reserva: " + reserva.getDataReserva());
        }
    }

    private static void cadastrarTrabalho(Scanner sc) {
        System.out.println("Cadastrar Trabalho: ");
        System.out.print("Título do trabalho: ");
        String titulo = sc.nextLine();

        System.out.print("Qual a faculdade: ");
        String faculdade = sc.nextLine();

        System.out.print("Qual a data de conclusão (dd/MM/yyyy): ");
        String dataConclusao = sc.nextLine();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataAtual = null;
        try {
            dataAtual = (Date) formato.parse(dataConclusao);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido! Use dd/MM/yyyy.");
            return;
        }
        String dataFormatada = formato.format(dataAtual);

        System.out.print("Aluno: ");
        String nomeAluno = sc.nextLine();

        System.out.print("Orientador: ");
        String nomeOrientador = sc.nextLine();

        System.out.print("Curso: ");
        String nomeCurso = sc.nextLine();

        System.out.print("Nota: ");
        int score = sc.nextInt();
        sc.nextLine(); // Consome a nova linha restante

        System.out.print("Quantidade de votos: ");
        int quantVotos = sc.nextInt();
        sc.nextLine(); // Consome a nova linha restante

        Trabalho trabalho = new Trabalho(
                titulo,
                new Faculdade(faculdade),
                dataFormatada,
                new Aluno(nomeAluno),
                new Orientador(nomeOrientador),
                new Curso(nomeCurso),
                score,
                quantVotos);

        trabalhos.add(trabalho);
        saveToFile(TRABALHO_FILE, trabalhos);
        System.out.println("Trabalho adicionado com sucesso!");
    }

    private static <T> ArrayList<T> loadFromFile(String filename, Type type) {
        ArrayList<T> list = new ArrayList<>();
        try (FileReader reader = new FileReader(filename)) {
            T[] data = gson.fromJson(reader, type);
            if (data != null) {
                list = new ArrayList<>(Arrays.asList(data));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado, criando um novo.");
        } catch (Exception e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
            // Aqui você pode inicializar a lista para evitar null
            list = new ArrayList<>();
        }
        return list;
    }

    private static <T> void saveToFile(String filename, ArrayList<T> list) {
        try (FileWriter writer = new FileWriter(filename, false)) { // 'false' sobrescreve o arquivo
            gson.toJson(list, writer);
            writer.flush(); // Certifique-se de que todos os dados são gravados no arquivo
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo: " + e.getMessage());
        }
    }

    private static Pessoa findUsuarioByName(String nome) {
        for (Pessoa u : usuarios) {
            if (u.getNome().equalsIgnoreCase(nome)) {
                return u;
            }
        }
        return null;
    }

    private static Livro findLivroByTitle(String titulo) {
        for (Livro l : livros) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }
}

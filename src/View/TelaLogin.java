    package View;

    import javax.swing.*;

import Usuarios.Pessoa;
import Usuarios.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

    public class TelaLogin extends JFrame {

        // Componentes da interface
        private JTextField usuarioField;
        private JPasswordField senhaField;
        private JButton loginButton;
        private Pessoa usuarioLogado;

          public Pessoa mostrar() {
        this.setVisible(true);
        return usuarioLogado;
    }

private void autenticar(String nome, String senha, ArrayList<Pessoa> usuarios) {
        for (Pessoa usuario : usuarios) {
            if (usuario.getNome().equals(nome) && usuario.getSenha().equals(senha)) {
                usuarioLogado = usuario;
                this.setVisible(false); 
                break;
            }
        }
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(this, "Login ou senha inválidos.");
        }
    }

        public TelaLogin() {
            // Configurações da janela
            setTitle("Login");
            setSize(300, 150);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);  // Centraliza a janela

            // Painel para os componentes
            JPanel panel = new JPanel();
            add(panel);
            placeComponents(panel);

            // Ação do botão de login
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String usuario = usuarioField.getText();
                    String senha = new String(senhaField.getPassword());

                    // Autenticação simples (apenas para exemplo)
                    if (usuario.equals("coordenador") && senha.equals("1234")) {
                        JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                        // Aqui você pode abrir a próxima tela
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
                    }
                }
            });
        }

        private void placeComponents(JPanel panel) {
            panel.setLayout(null);

            JLabel usuarioLabel = new JLabel("Usuário:");
            usuarioLabel.setBounds(10, 20, 80, 25);
            panel.add(usuarioLabel);

            usuarioField = new JTextField(20);
            usuarioField.setBounds(100, 20, 165, 25);
            panel.add(usuarioField);

            JLabel senhaLabel = new JLabel("Senha:");
            senhaLabel.setBounds(10, 50, 80, 25);
            panel.add(senhaLabel);

            senhaField = new JPasswordField(20);
            senhaField.setBounds(100, 50, 165, 25);
            panel.add(senhaField);

            loginButton = new JButton("Login");
            loginButton.setBounds(100, 80, 80, 25);
            panel.add(loginButton);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
            });
        }
    }

package View;  

import javax.swing.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

public class TelaVisualizarEmprestimo extends JFrame {  

    public TelaVisualizarEmprestimo() {  
        setTitle("Visualizar Empréstimo");  
        setSize(400, 300);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);  

        JPanel panel = new JPanel();  
        add(panel);  
        placeComponents(panel);  
    }  

    private void placeComponents(JPanel panel) {  
        panel.setLayout(null);  

        JLabel obraLabel = new JLabel("Obra: Nome da Obra Exemplo");  
        obraLabel.setBounds(10, 20, 300, 25);  
        panel.add(obraLabel);  

        JLabel usuarioLabel = new JLabel("Usuário: Nome do Usuário Exemplo");  
        usuarioLabel.setBounds(10, 50, 300, 25);  
        panel.add(usuarioLabel);  

        JLabel dataEmprestimoLabel = new JLabel("Data de Empréstimo: 01/01/2023");  
        dataEmprestimoLabel.setBounds(10, 80, 300, 25);  
        panel.add(dataEmprestimoLabel);  

        JLabel dataDevolucaoLabel = new JLabel("Data de Devolução: 01/02/2023");  
        dataDevolucaoLabel.setBounds(10, 110, 300, 25);  
        panel.add(dataDevolucaoLabel);  

        JButton voltarButton = new JButton("Voltar");  
        voltarButton.setBounds(10, 160, 150, 25);  
        voltarButton.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                // Volta para a tela anterior  
                new TelaListarEmprestimos().setVisible(true);  
                dispose();  
            }  
        });  
        panel.add(voltarButton);  
    }  

    public static void main(String[] args) {  
        SwingUtilities.invokeLater(() -> {  
            TelaVisualizarEmprestimo telaVisualizarEmprestimo = new TelaVisualizarEmprestimo();  
            telaVisualizarEmprestimo.setVisible(true);  
        });  
    }  
}
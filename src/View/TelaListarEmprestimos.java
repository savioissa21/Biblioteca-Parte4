package View;  

import javax.swing.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

public class TelaListarEmprestimos extends JFrame {  
    
    public TelaListarEmprestimos() {  
        setTitle("Listar Empréstimos");  
        setSize(400, 300);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        setLocationRelativeTo(null);  // Centraliza a janela  
        
        JPanel panel = new JPanel();  
        add(panel);  
        placeComponents(panel);  
    }  

    private void placeComponents(JPanel panel) {  
        panel.setLayout(null);  

        // Exemplo de lista (substitua por dados reais)  
        JLabel emprestimosLabel = new JLabel("Empréstimos:");  
        emprestimosLabel.setBounds(10, 20, 80, 25);  
        panel.add(emprestimosLabel);  

        JList<String> emprestimosList = new JList<>(new String[]{"Emprestimo 1", "Emprestimo 2"});  
        emprestimosList.setBounds(10, 50, 360, 150);  
        panel.add(emprestimosList);  

        JButton visualizarButton = new JButton("Visualizar");  
        visualizarButton.setBounds(150, 220, 100, 30);  
        visualizarButton.addActionListener(e -> {  
            String selectedValue = emprestimosList.getSelectedValue();  
            if (selectedValue != null) {  
                new TelaVisualizarEmprestimo().setVisible(true);  
            } else {  
                JOptionPane.showMessageDialog(this, "Selecione um empréstimo para visualizar.");  
            }  
        });  
        panel.add(visualizarButton);  
    }  
}
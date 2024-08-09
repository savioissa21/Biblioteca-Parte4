package View;  

import javax.swing.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

public class TelaVisualizarObra extends JFrame {  
    
    public TelaVisualizarObra(String obra) {  
        setTitle("Visualizar Obra - " + obra);  
        setSize(400, 300);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        setLocationRelativeTo(null);  // Centraliza a janela  
        
        JPanel panel = new JPanel();  
        add(panel);  
        placeComponents(panel, obra);  
    }  

    private void placeComponents(JPanel panel, String obra) {  
        panel.setLayout(null);  

        JLabel titleLabel = new JLabel("Detalhes da Obra: " + obra);  
        titleLabel.setBounds(10, 20, 200, 25);  
        panel.add(titleLabel);  

        // Aqui, adicione outros detalhes sobre a obra  
        JTextArea detalhesArea = new JTextArea("Detalhes sobre a obra...");  
        detalhesArea.setBounds(10, 60, 360, 150);  
        detalhesArea.setLineWrap(true);  
        detalhesArea.setEditable(false);  
        panel.add(detalhesArea);  

        JButton emprestarButton = new JButton("Emprestar");  
        emprestarButton.setBounds(150, 220, 100, 30);  
        emprestarButton.addActionListener(e -> {  
            // Implementar a lógica para emprestar a obra  
            JOptionPane.showMessageDialog(this, "Obra emprestada com sucesso!");  
        });  
        panel.add(emprestarButton);  
    }  
}
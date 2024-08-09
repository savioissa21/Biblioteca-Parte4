package View;  

import javax.swing.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

public class TelaListarAcervo extends JFrame {  
    
    public TelaListarAcervo() {  
        setTitle("Listar Acervo");  
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
        JLabel acervoLabel = new JLabel("Acervo:");  
        acervoLabel.setBounds(10, 20, 80, 25);  
        panel.add(acervoLabel);  

        JList<String> acervoList = new JList<>(new String[]{"Obra 1", "Obra 2", "Obra 3"});  
        acervoList.setBounds(10, 50, 360, 150);  
        panel.add(acervoList);  

        JButton visualizarButton = new JButton("Visualizar");  
        visualizarButton.setBounds(150, 220, 100, 30);  
        visualizarButton.addActionListener(e -> {  
            String selectedValue = acervoList.getSelectedValue();  
            if (selectedValue != null) {  
                new TelaVisualizarObra(selectedValue).setVisible(true);  
            } else {  
                JOptionPane.showMessageDialog(this, "Selecione uma obra para visualizar.");  
            }  
        });  
        panel.add(visualizarButton);  
    }  
}
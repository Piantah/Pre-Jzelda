package JZ_View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PannelloMenuPrincipale extends JPanel{
    private JButton gioca = new JButton("Gioca!");
    private JButton classifica = new JButton("classifica");
    private JButton chiudi = new JButton("chiudi");





    public PannelloMenuPrincipale(){
        super();
        setLayout(null);
        setBackground(Color.GREEN);
        gioca.setBounds(100, 100, 150, 50);
        classifica.setBounds(100, 300, 150, 50);
        chiudi.setBounds(100, 500, 150, 50);
        add(gioca);
        add(classifica);
        add(chiudi);
        setVisible(true);

        gioca.setFocusable(false);
        classifica.setFocusable(false);
        chiudi.setFocusable(false);
    }



    public void addGiocaListener(ActionListener listener) {
        gioca.addActionListener(listener);
    }
    public void addClassificaListener(ActionListener listener) {
        classifica.addActionListener(listener);
    }
    public void addChiudiListener(ActionListener listener) {
        chiudi.addActionListener(listener);
    }











}

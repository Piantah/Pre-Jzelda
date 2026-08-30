package JZ_View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PannelloPausa extends JPanel {
    private JButton riprendi = new JButton("riprendi");
    private JButton salva = new JButton("salva");
    private JButton chiudi = new JButton("Menu");


    public PannelloPausa(){
        super();
        setLayout(null);
        setBackground(Color.RED);
        riprendi.setBounds(100, 100, 150, 50);
        salva.setBounds(100, 300, 150, 50);
        chiudi.setBounds(100, 500, 150, 50);
        add(salva);
        add(riprendi);
        add(chiudi);

        setVisible(true);
        riprendi.setFocusable(false);
        salva.setFocusable(false);
        chiudi.setFocusable(false);
    }



    public void addRiprendiListener(ActionListener listener) {
        riprendi.addActionListener(listener);
    }
    public void addSalvaListener(ActionListener listener) {
        salva.addActionListener(listener);
    }
    public void addChiudiListener(ActionListener listener) {
        chiudi.addActionListener(listener);
    }
}

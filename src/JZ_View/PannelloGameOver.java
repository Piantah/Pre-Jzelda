package JZ_View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PannelloGameOver extends JPanel {
    private JButton chiudi = new JButton("Menu");
    private JButton continua = new JButton("Continua/Riprova");

    public PannelloGameOver(){
        super();
        setLayout(null);
        setBackground(Color.BLACK);

        continua.setBounds(200, 500, 150, 50);
        chiudi.setBounds(600, 500, 150, 50);

        add(chiudi);
        add(continua);

        continua.setFocusable(false);
        chiudi.setFocusable(false);
    }

    public void addChiudiListener(ActionListener listener) {
        chiudi.addActionListener(listener);
    }
    public void addContinuaListener(ActionListener listener) {
        continua.addActionListener(listener);
    }
}

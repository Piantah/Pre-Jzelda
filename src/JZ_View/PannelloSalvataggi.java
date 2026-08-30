package JZ_View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PannelloSalvataggi extends JPanel {
    private JButton saveUno = new JButton("Slot-1");
    private JButton saveDue = new JButton("Slot-2");
    private JButton saveTre = new JButton("Slot-3");
    private JButton chiudi = new JButton("Menu");
    private JButton resetUno = new JButton("Reset");
    private JButton resetDue = new JButton("Reset");
    private JButton resetTre = new JButton("Reset");



    public PannelloSalvataggi(){
        super();
        setLayout(null);
        setBackground(Color.YELLOW);
        saveUno.setBounds(100, 100, 150, 50);
        resetUno.setBounds(300, 100, 150, 50);
        saveDue.setBounds(100, 300, 150, 50);
        resetDue.setBounds(300, 300, 150, 50);
        saveTre.setBounds(100, 500, 150, 50);
        resetTre.setBounds(300, 500, 150, 50);
        chiudi.setBounds(100, 700, 150, 50);

        add(saveDue);
        add(saveUno);
        add(saveTre);
        add(resetDue);
        add(resetUno);
        add(resetTre);
        add(chiudi);

        setVisible(true);

        saveUno.setFocusable(false);
        saveDue.setFocusable(false);
        saveTre.setFocusable(false);
        chiudi.setFocusable(false);
        resetTre.setFocusable(false);
        resetDue.setFocusable(false);
        resetUno.setFocusable(false);
    }


    public void addSaveUnoListener(ActionListener listener) {
        saveUno.addActionListener(listener);
    }
    public void addResetUnoListener(ActionListener listener) {
        resetUno.addActionListener(listener);
    }
    public void addSaveDueListener(ActionListener listener) {
        saveDue.addActionListener(listener);
    }
    public void addResetDueListener(ActionListener listener) {
        resetDue.addActionListener(listener);
    }
    public void addSaveTreListener(ActionListener listener) {
        saveTre.addActionListener(listener);
    }
    public void addResetTreListener(ActionListener listener) {
        resetTre.addActionListener(listener);
    }
    public void addChiudiListener(ActionListener listener) {
        chiudi.addActionListener(listener);
    }
}

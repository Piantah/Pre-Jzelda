package JZ_View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class PannelloNome extends JPanel {
    private JButton chiudi = new JButton("Menu");
    private JButton inizia = new JButton("Inizia");
    private JButton skin_base = new JButton("Skin-base");
    private JButton skin_uno = new JButton("Skin-1");
    private JTextField campoNome = new JTextField("Inserisci il tuo nome Avventuriero!");
    BufferedImage skinBase_img;
    BufferedImage skinUno_img;

    public PannelloNome(){
        super();
        setLayout(null);
        campoNome.setBounds(280, 240, 400, 100); // X, Y, Larghezza, Altezza

        inizia.setBounds(200, 400, 150, 50);
        chiudi.setBounds(600, 400, 150, 50);
        skin_uno.setBounds(200, 100, 150, 50);
        skin_base.setBounds(600, 100, 150, 50);



        inizia.setFocusable(false);
        chiudi.setFocusable(false);
        skin_uno.setFocusable(false);
        skin_base.setFocusable(false);

        setBackground(Color.YELLOW);
        add(campoNome);
        add(inizia);
        add(chiudi);
        add(skin_base);
        add(skin_uno);
        setImage();

    }

    public void setImage(){
            try {
                URL urlImmagine = getClass().getResource("/JZ_Assets/Player_Front_JZ.png");
                if (urlImmagine != null) {
                    skinBase_img = ImageIO.read(urlImmagine);

                } else {
                    System.out.println("Immagine non trovata!");
                }
                URL urlImmagine1 = getClass().getResource("/JZ_Assets/Player_1_Front_JZ.png");
                if (urlImmagine1 != null) {
                    skinUno_img = ImageIO.read(urlImmagine1);

                } else {
                    System.out.println("Immagine non trovata!");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(skinBase_img,600, 150,48 , 48,null);
        g.drawImage(skinUno_img,200, 150,48 , 48,null);

    }

    public void addIniziaListener(ActionListener listener) {
        inizia.addActionListener(listener);
    }
    public void addChiudiListener(ActionListener listener) {
        chiudi.addActionListener(listener);
    }
    public void addSkinBaseListener(ActionListener listener) {
        skin_base.addActionListener(listener);
    }
    public void addSkinUnoListener(ActionListener listener) {
        skin_uno.addActionListener(listener);
    }
    public String getNomeInserito() {
        return campoNome.getText();
    }
    public void resetCampoNome() {
        campoNome.setText("");
    }



}

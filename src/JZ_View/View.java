package JZ_View;

import javax.swing.*;
import java.awt.event.KeyListener;

public class View extends JFrame {
    private PannelloDigioco p;
    private PannelloPausa pausa;
    private PannelloMenuPrincipale menu;
    private PannelloSalvataggi saves;
    private PannelloNome settagioNome;
    private PannelloGameOver gameOver;
    private PannelloClassifica classifica;
    private AudioManager audioSottofondo;


    public View(){
        super();
        setUndecorated(true);
        setLayout(null);
        audioSottofondo =AudioManager.getInstance();

        p = new PannelloDigioco();
        pausa = new PannelloPausa();
        menu=new PannelloMenuPrincipale();
        saves=new PannelloSalvataggi();
        settagioNome= new PannelloNome();
        gameOver=new PannelloGameOver();
        classifica=new PannelloClassifica();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(menu);
        add(pausa);
        add(saves);
        add(settagioNome);
        add(p);
        add(gameOver);
        add(classifica);

        pausa.setVisible(false);
        p.setVisible(false);
        menu.setVisible(false);
        saves.setVisible(false);
        settagioNome.setVisible(false);
        gameOver.setVisible(false);
        classifica.setVisible(false);



        setFocusable(true);
        setVisible(true);
        requestFocusInWindow();

        apriMenuIniziale();
        audioSottofondo.playLoop("/JZ_Assets/main_ost.wav");


    }

    public void setKeylistener(KeyListener k){
        addKeyListener(k);
    }
    public void apriMenuPausa(){
        p.setVisible(false);
        pausa.setVisible(true);
        menu.setVisible(false);
        saves.setVisible(false);
        settagioNome.setVisible(false);
        gameOver.setVisible(false);
        classifica.setVisible(false);

        setComponentZOrder(pausa, 0);
        repaint();
    }

    public void chiudiMenuPausa(){
        pausa.setVisible(false);
        menu.setVisible(false);
        saves.setVisible(false);
        settagioNome.setVisible(false);
        gameOver.setVisible(false);
        classifica.setVisible(false);

        p.setVisible(true);
        setComponentZOrder(p, 0);
        repaint();
        requestFocusInWindow();
    }

    public void apriGioco(){
        p.setVisible(true);
        menu.setVisible(false);
        pausa.setVisible(false);
        saves.setVisible(false);
        settagioNome.setVisible(false);
        gameOver.setVisible(false);
        classifica.setVisible(false);

        setComponentZOrder(p, 0);
        repaint();
        requestFocusInWindow();
    }

    public void apriMenuIniziale(){
        menu.setVisible(true);
        saves.setVisible(false);
        pausa.setVisible(false);
        p.setVisible(false);
        settagioNome.setVisible(false);
        gameOver.setVisible(false);
        classifica.setVisible(false);
        setComponentZOrder(menu, 0);
        repaint();
    }

    public void apriSalvataggi(){
        saves.setVisible(true);
        menu.setVisible(false);
        pausa.setVisible(false);
        p.setVisible(false);
        settagioNome.setVisible(false);
        gameOver.setVisible(false);
        classifica.setVisible(false);

        setComponentZOrder(saves, 0);
        repaint();
    }
    public void apriSettaggioNome(){
        saves.setVisible(false);
        menu.setVisible(false);
        pausa.setVisible(false);
        p.setVisible(false);
        gameOver.setVisible(false);
        classifica.setVisible(false);
        settagioNome.setVisible(true);
        setComponentZOrder(settagioNome, 0);
        repaint();
    }

    public void apriGameOver(){
        saves.setVisible(false);
        menu.setVisible(false);
        pausa.setVisible(false);
        p.setVisible(false);
        gameOver.setVisible(true);
        classifica.setVisible(false);
        settagioNome.setVisible(false);
        setComponentZOrder(gameOver, 0);
        repaint();
    }
    public void apriClassifica(){
        saves.setVisible(false);
        menu.setVisible(false);
        pausa.setVisible(false);
        p.setVisible(false);
        gameOver.setVisible(false);
        classifica.setVisible(true);
        settagioNome.setVisible(false);
        setComponentZOrder(classifica, 0);
        repaint();
    }





    public PannelloDigioco getP() {
        return p;
    }

    public PannelloPausa getPausa() {
        return pausa;
    }

    public PannelloMenuPrincipale getMenu() {
        return menu;
    }

    public PannelloSalvataggi getSaves() {
        return saves;
    }

    public PannelloNome getSettagioNome() {
        return settagioNome;
    }

    public PannelloGameOver getGameOver() {
        return gameOver;
    }

    public PannelloClassifica getClassifica() {
        return classifica;
    }
}

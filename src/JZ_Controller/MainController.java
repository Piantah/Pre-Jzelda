package JZ_Controller;

import JZ_Model_alpha.GameModel;
import JZ_View.AudioManager;
import JZ_View.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class MainController implements KeyListener {


        private boolean isshop;
        private boolean paused;
        private View frame;
        private GameModel modello;
        private AudioManager audio;


        Timer bullets = new Timer(200, bullet -> modello.moveBullets());
        Timer t = new Timer(1000, e->{
        modello.muoviNemici();
        bullets.start();
        if(modello.isGameOver()) {
            frame.apriGameOver();
            modello.endGame();
            Timer t1 = (Timer) e.getSource();
            t1.stop();
        }
        });






        public MainController(GameModel modello, View view){
        this.modello=modello;
        this.frame=view;
        frame.setKeylistener(this);
        audio=AudioManager.getInstance();
        modello.addObserver(frame.getP());
        assignButtons();
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        device.setFullScreenWindow(frame);
        //frame.setPreferredSize(new Dimension((modello.getCurrentLevel().getWidth()+1)*48, (modello.getCurrentLevel().getLength()+1)*48));
        modello.triggerFirstUpdate();
        Timer x = new Timer(50, e -> {
                frame.repaint();
        });

        //x.start();



    }



    private void assignButtons(){

        this.frame.getPausa().addChiudiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.apriMenuIniziale();
            }
        });
        this.frame.getPausa().addRiprendiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        });
        this.frame.getPausa().addSalvaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sono salvo");
                try {
                    modello.saveGame();
                    audio.play("/JZ_Assets/savepoint.wav");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.frame.getMenu().addChiudiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("chiuso il gioco");
                System.exit(0);
            }
        });
        this.frame.getMenu().addGiocaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.apriSalvataggi();
            }
        });
        this.frame.getMenu().addClassificaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.apriClassifica();
            }
        });
        this.frame.getSaves().addChiudiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.apriMenuIniziale();
            }
        });
        this.frame.getSaves().addResetUnoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resetSave("JZ_Saves/" +"save_uno" + ".txt");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.frame.getSaves().addResetDueListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resetSave("JZ_Saves/" +"save_due" + ".txt");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.frame.getSaves().addResetTreListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resetSave("JZ_Saves/" +"save_tre" + ".txt");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.frame.getSaves().addSaveUnoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("save uno");
                    modello.caricaSave("save_uno");
                    if(checkSave("save_uno")){
                        frame.apriGioco();
                        start();
                    }
                    else{frame.apriSettaggioNome();}
                }
                catch (IOException ex) {throw new RuntimeException(ex);}}
        });
        this.frame.getSaves().addSaveDueListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modello.caricaSave("save_due");
                    if(checkSave("save_due")){
                        frame.apriGioco();
                        start();
                    }
                    else{frame.apriSettaggioNome();}
                }
                catch (IOException ex) {throw new RuntimeException(ex);}}
        });
        this.frame.getSaves().addSaveTreListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modello.caricaSave("save_tre");
                    if(checkSave("save_tre")){
                        frame.apriGioco();
                        start();
                    }
                    else{frame.apriSettaggioNome();}
                }
                catch (IOException ex) {throw new RuntimeException(ex);}}
        });
        this.frame.getSettagioNome().addChiudiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.apriMenuIniziale();
            }
        });
        this.frame.getSettagioNome().addIniziaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s=frame.getSettagioNome().getNomeInserito();
                if(s==null){
                    s="Lonk";
                }
                modello.getPlayer().setNome(s);
                frame.apriGioco();
                start();
            }
        });
        this.frame.getSettagioNome().addSkinBaseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modello.getPlayer().setSkin(0);
            }
        });
        this.frame.getSettagioNome().addSkinUnoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modello.getPlayer().setSkin(1);
            }
        });
        this.frame.getGameOver().addChiudiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.apriMenuIniziale();
            }
        });
        this.frame.getGameOver().addContinuaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modello.playAgain();
                frame.apriGioco();
                restart();
            }
        });
        this.frame.getClassifica().addChiudiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.apriMenuIniziale();
            }
        });



    }


    private boolean checkSave(String s) throws IOException {
        File path = new File("JZ_Saves/" + s + ".txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String riga = reader.readLine();
                System.out.println(riga);
                if(riga.equals("Empty:true")) {
                    reader.close();
                    return false;
                }
                reader.close();

        }
        return true;
    }


    public void start(){
        paused=false;
        t.start();

    }
    public void pause(){
        frame.apriMenuPausa();
        paused=true;
            t.stop();
            bullets.stop();

    }
    public void restart(){
        frame.chiudiMenuPausa();
        paused=false;
            t.restart();
            bullets.restart();
    }
    private void resetSave(String s) throws IOException {
        File path= new File(s);
            ArrayList<String> text = new ArrayList<>();
            text.add("Empty:true");
            text.add("Name:"+0);
            text.add("P_X:"+8);
            text.add("P_Y:"+8);
            text.add("Xp:"+0);
            text.add("Skin:"+0);
            text.add("Morti:"+0);
            text.add("Tentativi:"+1);
            text.add("Soldi:"+0);
            text.add("Level_Id:"+9);
            text.add("Item:Spada");
            text.add("Hp:"+6);
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for(String t:text){
                writer.write(t);
                writer.newLine();
            }
            writer.close();
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(paused)return;
        int tasto = e.getKeyCode();
        if(tasto == KeyEvent.VK_ESCAPE){pause();}
        if (tasto == KeyEvent.VK_E){ if(modello.usaItem("Spada"))audio.play("/JZ_Assets/sword.wav");}
        if (tasto == KeyEvent.VK_R){ if(modello.usaItem("Fucile"))audio.play("/JZ_Assets/fucile.wav");}
        if (tasto == KeyEvent.VK_T){ if(modello.usaItem("Staff"))audio.play("/JZ_Assets/stafft.wav");}
        if (tasto == KeyEvent.VK_UP) {if(modello.muoviPlayerSu()==1)audio.play("/JZ_Assets/heal.wav");}
        if (tasto == KeyEvent.VK_DOWN ) { if(modello.muoviPlayerGiu()==1)audio.play("/JZ_Assets/heal.wav"); }
        if (tasto == KeyEvent.VK_LEFT ) {  if(modello.muoviPlayerSinistra()==1)audio.play("/JZ_Assets/heal.wav");}
        if (tasto == KeyEvent.VK_RIGHT) { if(modello.muoviPlayerDestra()==1)audio.play("/JZ_Assets/heal.wav");}

        if(tasto == KeyEvent.VK_1){
            if(modello.isShop()) if(modello.acquistaItem("Fucile"))audio.play("/JZ_Assets/shop_sound_1.wav");
        }
        if(tasto == KeyEvent.VK_2){
            if(modello.isShop()) if(modello.acquistaItem("Staff"))audio.play("/JZ_Assets/shop_sound_2.wav");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        modello.getPlayer().resetMove();
    }

}

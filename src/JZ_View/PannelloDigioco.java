package JZ_View;

import JZ_Model.Entities.*;
import JZ_Model.Entities.Proiettili.ProiettileNemico;
import JZ_Model.Entities.Proiettili.ProiettileGiocatore;
import JZ_Model.Entities.Nemici.*;
import JZ_Model.Entities.OtherEntities.*;
import JZ_Model.GameModel;
import JZ_Model.Items.Fucile;
import JZ_Model.Items.Item;
import JZ_Model.Items.Spada;
import JZ_Model.Items.Staffa;
import JZ_Model.Levels.Punto;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PannelloDigioco extends JPanel implements Observer {
    private final int Tile_Size = 48;
    private JPanel mappa = new JPanel();
    private int map_lenght;
    private int map_width;
    private Font mioFont;
    private GameModel modello;
    private HashMap<String, BufferedImage> immagini;
    private Collection<Punto> bottoni;

    public PannelloDigioco(){
        super();

        mioFont = new Font("Arial", Font.BOLD, 24);
        bottoni=new ArrayList<>();
        immagini=new HashMap<>();
        ArrayList<String> v = new ArrayList<>();
        //mappa
        v.add("grassTileJZelda");
        v.add("RockTile_JZ");
        v.add("ShopTile_JZ");
        v.add("Vulcanic_Tile_JZ");

        //ostacoli ed altre entità
        v.add("CespuglioJZelda");
        v.add("Roccia_JZ");
        v.add("PlayerTestBullet_JZelda");
        v.add("Vulcanic_Rock_JZ");
        v.add("Star_Bullet_JZ");
        v.add("Palla_Di_Fuoco_JZ");
        v.add("WaterTile_JZ");
        v.add("LavaTile_JZ");
        v.add("Shop_Wall_JZ");
        v.add("Tall_Grass_JZ");
        v.add("Tree_JZ");
        v.add("Tavolo_JZ");
        v.add("Roccia_Alt_JZ");


        //player
        v.add("Player_1_Front_JZ");
        v.add("Player_1_Front_Atk_JZ");
        v.add("Player_1_Front_ATK_Fucile_JZ");
        v.add("Player_1_Front_Move_Alpha_JZ");
        v.add("Player_1_Front_Move_Beta_JZ");

        v.add("Player_1_Left_JZ");
        v.add("Player_1_Left_ATK_JZ");
        v.add("Player_1_Left_ATK_Fucile_JZ");
        v.add("Player_1_Left_Move_Alpha_JZ");
        v.add("Player_1_Left_Move_Beta_JZ");

        v.add("Player_1_Right_JZ");
        v.add("Player_1_Right_ATK_JZ");
        v.add("Player_1_Right_ATK_Fucile_JZ");
        v.add("Player_1_Right_Move_Alpha_JZ");
        v.add("Player_1_Right_Move_Beta_JZ");

        v.add("Player_1_Up_JZ");
        v.add("Player_1_Up_ATK_JZ");
        v.add("Player_1_Up_ATK_Fucile_JZ");
        v.add("Player_1_Up_Move_Alpha_JZ");
        v.add("Player_1_Up_Move_Beta_JZ");

        //skin 1
        v.add("Player_Front_JZ");
        v.add("Player_Front_Atk_JZ");
        v.add("Player_Front_ATK_Fucile_JZ");
        v.add("Player_Front_Move_Alpha_JZ");
        v.add("Player_Front_Move_Beta_JZ");

        v.add("Player_Left_JZ");
        v.add("Player_Left_ATK_JZ");
        v.add("Player_Left_ATK_Fucile_JZ");
        v.add("Player_Left_Move_Alpha_JZ");
        v.add("Player_Left_Move_Beta_JZ");

        v.add("Player_Right_JZ");
        v.add("Player_Right_ATK_JZ");
        v.add("Player_Right_ATK_Fucile_JZ");
        v.add("Player_Right_Move_Alpha_JZ");
        v.add("Player_Right_Move_Beta_JZ");

        v.add("Player_Up_JZ");
        v.add("Player_Up_ATK_JZ");
        v.add("Player_Up_ATK_Fucile_JZ");
        v.add("Player_Up_Move_Alpha_JZ");
        v.add("Player_Up_Move_Beta_JZ");


        //nemici
        v.add("Ghost_Front_JZelda");
        v.add("Ghost_Front_Atk_JZelda");
        v.add("HuskAttack_JZ");


        v.add("Mago_Front_ATK_JZ");
        v.add("Mago_Front_Move_Alpha_JZ");
        v.add("Mago_Front_Move_Beta_JZ");

        v.add("Mago_Left_ATK_JZ");
        v.add("Mago_Left_Move_Alpha_JZ");
        v.add("Mago_Left_Move_Beta_JZ");

        v.add("Mago_Right_ATK_JZ");
        v.add("Mago_Right_Move_Alpha_JZ");
        v.add("Mago_Right_Move_Beta_JZ");

        v.add("Mago_Up_ATK_JZ");
        v.add("Mago_Up_Move_Alpha_JZ");
        v.add("Mago_Up_Move_Beta_JZ");

        v.add("Husk_Front_ATK_JZ");
        v.add("Husk_Front_Move_Alpha_JZ");
        v.add("Husk_Front_Move_Beta_JZ");

        v.add("Husk_Left_ATK_JZ");
        v.add("Husk_Left_Move_Alpha_JZ");
        v.add("Husk_Left_Move_Beta_JZ");

        v.add("Husk_Right_ATK_JZ");
        v.add("Husk_Right_Move_Alpha_JZ");
        v.add("Husk_Right_Move_Beta_JZ");

        v.add("Husk_Up_ATK_JZ");
        v.add("Husk_Up_Move_Alpha_JZ");
        v.add("Husk_Up_Move_Beta_JZ");





        //Vita
        v.add("Vita_Alpha_JZ");
        v.add("Vita_Beta_JZ");
        v.add("Vita_Charlie_JZ");

        //Item
        v.add("Cornice_Sotto_JZ");
        v.add("Cornice_Destra_JZ");
        v.add("Cornice_Sinistra_JZ");
        v.add("Cornice_Completa_JZ");


        v.add("Spada_JZ");
        v.add("Fucile_JZ");
        v.add("Heal_JZ");
        v.add("Staff_JZ");

        //Soldi
        v.add("Rupia_JZ");
        v.add("Mercante_Temp_JZ");
        v.add("triforza");

        setImmagini(v);


    }
    private void setImmagini(Collection<String> locazioni){
        BufferedImage textureTile;
        for(String s:locazioni){
            try {
                // Cerca il file all'interno del progetto (nel classpath)
                URL urlImmagine = getClass().getResource("/JZ_Assets/"+s+".png");
                if (urlImmagine != null) {
                    textureTile = ImageIO.read(urlImmagine);
                    immagini.putIfAbsent(s,textureTile);
                } else {
                    System.out.println("Immagine non trovata!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }




    public void settaggio(){
        setBackground(Color.GRAY);
        map_lenght=modello.getLivello().getLunghezza();
        map_width=modello.getLivello().getAltezza();
        setSize((Tile_Size*map_width)*2,Tile_Size*(map_lenght+3));
        setVisible(true);
    }


    private void creaInterfacciaInterazione(Graphics g){
        int xIn = Tile_Size+(Tile_Size*map_width);
        int xFin=((Tile_Size*map_width)*2)-xIn-Tile_Size;
        if(modello.getLivello().getId()==9){
            g.setColor(Color.RED);
            g.fillRect(xIn,Tile_Size,xFin,Tile_Size*map_lenght);
            g.setColor(Color.getHSBColor(270,39,130));
            g.drawImage(immagini.get("Mercante_Temp_JZ"), xIn, Tile_Size, xFin, Tile_Size*map_lenght, null);
            g.fillRect(xIn,10*Tile_Size,xFin,Tile_Size*map_lenght);


            int yHalf = (Tile_Size + ((Tile_Size*map_lenght)/2));
            int xHalf = ((xFin)/2)+xIn;
//            bottoni.add(new Punto((xHalf+((xHalf-xIn)/2)-Tile_Size),yHalf+(yHalf/2)));
//            bottoni.add(new Punto(((xHalf-xIn)/2)+xIn,yHalf+(yHalf/2)));
            Mercante m = null;
            for(Entita e: modello.getLivello().getEntita()) if(e instanceof Mercante) m=(Mercante)e;
            if(m!=null){
                int i=1;
                g.setColor(Color.getHSBColor(270,39,76));
                int xCalc = xHalf + ((xHalf - xIn) / 2) - Tile_Size;
                g.fillRect(xCalc,yHalf+(yHalf/2),Tile_Size,Tile_Size);
                g.fillRect(((xHalf-xIn)/2)+xIn,yHalf+(yHalf/2),Tile_Size,Tile_Size);
                for(Item item: m.getOggettiDisponibili()){
                    g.setFont(mioFont);
                    g.setColor(Color.WHITE);


                    if(i==1){
                        g.drawImage(immagini.get("Rupia_JZ"), (xHalf + ((xHalf - xIn) / 2) - (Tile_Size*2)), yHalf + (yHalf / 2)+Tile_Size, Tile_Size, Tile_Size, null);
                        g.drawString(String.valueOf(m.getPrezzoItem(item.getNomeItem())), xCalc, yHalf + (yHalf / 2)+Tile_Size+Tile_Size/2+12);
                        if(item instanceof Fucile) {
                            g.drawString("Tasto 1", xCalc, yHalf + (yHalf / 2)-Tile_Size/2-12);

                            g.drawImage(immagini.get("Fucile_JZ"), xCalc, yHalf + (yHalf / 2), Tile_Size, Tile_Size, null);

                        }
                        if(item instanceof Staffa) {
                            g.drawString("Tasto 2", xCalc, yHalf + (yHalf / 2)-Tile_Size/2-12);

                            g.drawImage(immagini.get("Staff_JZ"), xCalc, yHalf + (yHalf / 2), Tile_Size, Tile_Size, null);

                        }
                    }
                    if(i==2){
                        g.drawImage(immagini.get("Rupia_JZ"), ((xHalf-xIn)/2)+xIn-Tile_Size, yHalf + (yHalf / 2)+Tile_Size, Tile_Size, Tile_Size, null);
                        g.drawString(String.valueOf(m.getPrezzoItem(item.getNomeItem())), ((xHalf-xIn)/2)+xIn, yHalf + (yHalf / 2)+Tile_Size+Tile_Size/2+12);
                        if(item instanceof Fucile) {
                            g.drawString("Tasto 1", ((xHalf-xIn)/2)+xIn, yHalf + (yHalf / 2)-Tile_Size/2-12);
                            g.drawImage(immagini.get("Fucile_JZ"), ((xHalf-xIn)/2)+xIn, yHalf + (yHalf / 2), Tile_Size, Tile_Size, null);
                        }
                        if(item instanceof Staffa) {
                            g.drawString("Tasto 2", ((xHalf-xIn)/2)+xIn, yHalf + (yHalf / 2)-Tile_Size/2-12);
                            g.drawImage(immagini.get("Staff_JZ"), ((xHalf-xIn)/2)+xIn, yHalf + (yHalf / 2), Tile_Size, Tile_Size, null);
                        }
                    }
                    i+=1;
                }

                g.drawImage(immagini.get("Cornice_Completa_JZ"), xCalc, yHalf + (yHalf / 2), Tile_Size, Tile_Size, null);
                g.drawImage(immagini.get("Cornice_Completa_JZ"), ((xHalf-xIn)/2)+xIn, yHalf + (yHalf / 2), Tile_Size, Tile_Size, null);

            }

        }
        else{
            g.setColor(Color.BLACK);
            g.fillRect(xIn,Tile_Size,xFin,Tile_Size*map_lenght);
            g.drawImage(immagini.get("triforza"), xIn, Tile_Size, xFin, Tile_Size*map_lenght, null);

        }






    }

    private void creaTabellone(Graphics g) throws IOException {
        if(modello!=null) {
            switch (modello.getLivello().getId()){
                case 1, 3, 4, 6 ->creaTabelloneAux("grassTileJZelda",g);
                case 2, 7 ->creaTabelloneAux("RockTile_JZ",g);
                case 5,8->creaTabelloneAux("Vulcanic_Tile_JZ",g);
                case 9->creaTabelloneAux("ShopTile_JZ",g);

            }

        }

    }
    private void creaTabelloneAux(String s,Graphics g){
        for (int riga = 1; riga < map_lenght+1; riga++) {
            for (int colonna = 0; colonna <  map_width; colonna++) {
                // Scegliamo il colore
                // Dipingiamo il quadrato calcolando le coordinate in pixel
                g.drawImage(immagini.get(s),colonna * Tile_Size, riga * Tile_Size, Tile_Size, Tile_Size,null);

            }
        }
    }



    public void disegnaHotbar(Graphics g) {
        g.setColor(Color.getHSBColor(270,39,76));
        g.fillRect(0,0,Tile_Size*map_width,Tile_Size);




        switch (modello.getPlayer().getVita()){
            case 0:
                g.drawImage(immagini.get("Vita_Charlie_JZ"),Tile_Size,0, Tile_Size, Tile_Size,null);
                g.drawImage(immagini.get("Vita_Charlie_JZ"),2*Tile_Size,0, Tile_Size, Tile_Size,null);
                g.drawImage(immagini.get("Vita_Charlie_JZ"),3*Tile_Size,0, Tile_Size, Tile_Size,null);
                break;
            case 1:
                g.drawImage(immagini.get("Vita_Beta_JZ"),Tile_Size,0, Tile_Size, Tile_Size,null);
                g.drawImage(immagini.get("Vita_Charlie_JZ"),2*Tile_Size,0, Tile_Size, Tile_Size,null);
                g.drawImage(immagini.get("Vita_Charlie_JZ"),3*Tile_Size,0, Tile_Size, Tile_Size,null);
                break;
            case 2:
                g.drawImage(immagini.get("Vita_Alpha_JZ"),Tile_Size,0, Tile_Size, Tile_Size,null);
                g.drawImage(immagini.get("Vita_Charlie_JZ"),2*Tile_Size,0, Tile_Size, Tile_Size,null);
                g.drawImage(immagini.get("Vita_Charlie_JZ"),3*Tile_Size,0, Tile_Size, Tile_Size,null);
                break;
            case 3:
                g.drawImage(immagini.get("Vita_Alpha_JZ"),Tile_Size,0, Tile_Size, Tile_Size,null);
                g.drawImage(immagini.get("Vita_Beta_JZ"),2*Tile_Size,0, Tile_Size, Tile_Size,null);
                g.drawImage(immagini.get("Vita_Charlie_JZ"),3*Tile_Size,0, Tile_Size, Tile_Size,null);
                break;
            case 4:
                g.drawImage(immagini.get("Vita_Alpha_JZ"),Tile_Size,0, Tile_Size, Tile_Size,null);
                g.drawImage(immagini.get("Vita_Alpha_JZ"),2*Tile_Size,0, Tile_Size, Tile_Size,null);
                g.drawImage(immagini.get("Vita_Charlie_JZ"),3*Tile_Size,0, Tile_Size, Tile_Size,null);
                break;
            case 5:
                g.drawImage(immagini.get("Vita_Alpha_JZ"),Tile_Size,0, Tile_Size, Tile_Size,null);
                g.drawImage(immagini.get("Vita_Alpha_JZ"),2*Tile_Size,0, Tile_Size, Tile_Size,null);
                g.drawImage(immagini.get("Vita_Beta_JZ"),3*Tile_Size,0, Tile_Size, Tile_Size,null);
                break;
            case 6:
                g.drawImage(immagini.get("Vita_Alpha_JZ"),Tile_Size,0, Tile_Size, Tile_Size,null);
                g.drawImage(immagini.get("Vita_Alpha_JZ"),2*Tile_Size,0, Tile_Size, Tile_Size,null);
                g.drawImage(immagini.get("Vita_Alpha_JZ"),3*Tile_Size,0, Tile_Size, Tile_Size,null);
                break;
        }




        for(Item i : modello.getPlayer().getOggetti()){
            if(i instanceof Spada)g.drawImage(immagini.get("Spada_JZ"),5*Tile_Size,0, Tile_Size, Tile_Size,null);
            if(i instanceof Fucile)g.drawImage(immagini.get("Fucile_JZ"),6*Tile_Size,0, Tile_Size, Tile_Size,null);
            if(i instanceof Staffa)g.drawImage(immagini.get("Staff_JZ"),7*Tile_Size,0, Tile_Size, Tile_Size,null);

        }
        g.setFont(mioFont);
        g.setColor(Color.WHITE);
        g.drawImage(immagini.get("Rupia_JZ"),10*Tile_Size,0, Tile_Size, Tile_Size,null);
        g.drawString(String.valueOf(modello.getPlayer().getSoldi()), 11*Tile_Size, 32);



        //settaggio Cornice
        g.drawImage(immagini.get("Cornice_Destra_JZ"),3*Tile_Size,0, Tile_Size, Tile_Size,null);
        g.drawImage(immagini.get("Cornice_Sinistra_JZ"),Tile_Size,0, Tile_Size, Tile_Size,null);

        g.drawImage(immagini.get("Cornice_Destra_JZ"),5*Tile_Size,0, Tile_Size, Tile_Size,null);
        g.drawImage(immagini.get("Cornice_Destra_JZ"),6*Tile_Size,0, Tile_Size, Tile_Size,null);
        g.drawImage(immagini.get("Cornice_Sinistra_JZ"),5*Tile_Size,0, Tile_Size, Tile_Size,null);
        g.drawImage(immagini.get("Cornice_Destra_JZ"),7*Tile_Size,0, Tile_Size, Tile_Size,null);


        g.drawImage(immagini.get("Cornice_Sinistra_JZ"),10*Tile_Size,0, Tile_Size, Tile_Size,null);



        for (int i = 0; i < map_width; i++) {
            if(i==0)g.drawImage(immagini.get("Cornice_Sinistra_JZ"),i*Tile_Size,0, Tile_Size, Tile_Size,null);
            if(i==map_width-1)g.drawImage(immagini.get("Cornice_Destra_JZ"),i*Tile_Size,0, Tile_Size, Tile_Size,null);
            g.drawImage(immagini.get("Cornice_Sotto_JZ"),i*Tile_Size,0, Tile_Size, Tile_Size,null);

        }


    }







    private void disegnaEntità(Graphics g){
        for(Entita e : modello.getLivello().getEntita()){
            if(e instanceof HuskAttacco){
                g.drawImage(immagini.get("HuskAttack_JZ"),e.getxCord() * Tile_Size, (e.getyCord()+1) * Tile_Size, Tile_Size, Tile_Size,null);
            }
            else if( e instanceof Husk){
                switch(e.getDirezione()) {
                    case Direzione.GIU -> {
                        switch (e.getMossa()) {
                            case 1 -> g.drawImage(immagini.get("Husk_Front_Move_Alpha_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 2 -> g.drawImage(immagini.get("Husk_Front_Move_Beta_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 3 -> g.drawImage(immagini.get("Husk_Front_ATK_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                        }
                    }
                    case Direzione.SINISTRA -> {
                        switch (e.getMossa()) {
                            case 1 -> g.drawImage(immagini.get("Husk_Left_Move_Alpha_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 2 -> g.drawImage(immagini.get("Husk_Left_Move_Beta_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 3 -> g.drawImage(immagini.get("Husk_Left_ATK_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                        }
                    }
                    case Direzione.DESTRA -> {
                        switch (e.getMossa()) {
                            case 1 -> g.drawImage(immagini.get("Husk_Right_Move_Alpha_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 2 -> g.drawImage(immagini.get("Husk_Right_Move_Beta_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 3 -> g.drawImage(immagini.get("Husk_Right_ATK_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                        }
                    }
                    case Direzione.SU -> {
                        switch (e.getMossa()) {
                            case 1 -> g.drawImage(immagini.get("Husk_Up_Move_Alpha_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 2 -> g.drawImage(immagini.get("Husk_Up_Move_Beta_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 3 -> g.drawImage(immagini.get("Husk_Up_ATK_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                        }
                    }
                    case Direzione.NESSUNA -> {
                        g.drawImage(immagini.get("Husk_Front_Move_Alpha_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                    }
                }
            }
            else if( e instanceof Mago){
                switch(e.getDirezione()) {
                    case Direzione.GIU -> {
                        switch (e.getMossa()) {
                            case 1 -> g.drawImage(immagini.get("Mago_Front_Move_Alpha_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 2 -> g.drawImage(immagini.get("Mago_Front_Move_Beta_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 3 -> g.drawImage(immagini.get("Mago_Front_ATK_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                        }
                    }
                    case Direzione.SINISTRA -> {
                        switch (e.getMossa()) {
                            case 1 -> g.drawImage(immagini.get("Mago_Left_Move_Alpha_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 2 -> g.drawImage(immagini.get("Mago_Left_Move_Beta_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 3 -> g.drawImage(immagini.get("Mago_Left_ATK_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                        }
                    }
                    case Direzione.DESTRA -> {
                        switch (e.getMossa()) {
                            case 1 -> g.drawImage(immagini.get("Mago_Right_Move_Alpha_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 2 -> g.drawImage(immagini.get("Mago_Right_Move_Beta_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 3 -> g.drawImage(immagini.get("Mago_Right_ATK_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                        }
                    }
                    case Direzione.SU -> {
                        switch (e.getMossa()) {
                            case 1 -> g.drawImage(immagini.get("Mago_Up_Move_Alpha_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 2 -> g.drawImage(immagini.get("Mago_Up_Move_Beta_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                            case 3 -> g.drawImage(immagini.get("Mago_Up_ATK_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                        }
                    }
                    case Direzione.NESSUNA -> {
                        g.drawImage(immagini.get("Mago_Front_Move_Alpha_JZ"), e.getxCord() * Tile_Size, (e.getyCord() + 1) * Tile_Size, Tile_Size, Tile_Size, null);
                    }
                }
            }
            else if( e instanceof Drop){
                 if(((Drop) e).getTipo()== DropTypes.CURA){
                     g.drawImage(immagini.get("Heal_JZ"),e.getxCord() * Tile_Size, (e.getyCord()+1) * Tile_Size, Tile_Size, Tile_Size,null);
                 }
            }

            else if( e instanceof Mercante){
                g.drawImage(immagini.get("Mercante_Temp_JZ"), e.getxCord() * Tile_Size, (e.getyCord()+1) * Tile_Size, Tile_Size, Tile_Size, null);

            }

        }

    }
    private void disegnaElementiStatici(Graphics g){
        for(Entita e : modello.getLivello().getEntita()){
            if(e instanceof Ostacolo){
                switch (((Ostacolo)  e).getId_tipo_ostacolo()){
                    case 1:
                        g.drawImage(immagini.get("CespuglioJZelda"),e.getxCord() * Tile_Size, (e.getyCord()+1) * Tile_Size, Tile_Size, Tile_Size,null);
                        break;
                    case 2:
                        g.drawImage(immagini.get("Roccia_JZ"),e.getxCord() * Tile_Size, (e.getyCord()+1) * Tile_Size, Tile_Size, Tile_Size,null);
                        break;
                    case 3:
                        g.drawImage(immagini.get("WaterTile_JZ"),e.getxCord() * Tile_Size, (e.getyCord()+1) * Tile_Size, Tile_Size, Tile_Size,null);
                        break;
                    case 4:
                        g.drawImage(immagini.get("Shop_Wall_JZ"),e.getxCord() * Tile_Size, (e.getyCord()+1) * Tile_Size, Tile_Size, Tile_Size,null);
                        break;
                    case 5:
                        g.drawImage(immagini.get("Tall_Grass_JZ"),e.getxCord() * Tile_Size, (e.getyCord()+1) * Tile_Size, Tile_Size, Tile_Size,null);
                        break;
                    case 6:
                        g.drawImage(immagini.get("Tree_JZ"),e.getxCord() * Tile_Size, (e.getyCord()+1) * Tile_Size, Tile_Size, Tile_Size,null);
                        break;
                    case 7:
                        g.drawImage(immagini.get("Vulcanic_Rock_JZ"),e.getxCord() * Tile_Size, (e.getyCord()+1) * Tile_Size, Tile_Size, Tile_Size,null);
                        break;
                    case 8:
                        g.drawImage(immagini.get("LavaTile_JZ"),e.getxCord() * Tile_Size, (e.getyCord()+1) * Tile_Size, Tile_Size, Tile_Size,null);
                        break;
                    case 9:
                        g.drawImage(immagini.get("Tavolo_JZ"),e.getxCord() * Tile_Size, (e.getyCord()+1) * Tile_Size, Tile_Size, Tile_Size,null);
                        break;
                    case 10:
                        g.drawImage(immagini.get("Roccia_Alt_JZ"),e.getxCord() * Tile_Size, (e.getyCord()+1) * Tile_Size, Tile_Size, Tile_Size,null);
                        break;




                }

            }
        }


        }
    private void disegnaEntitàTemp(Graphics g){
        for(Entita e : modello.getTemp()){
            if(e instanceof HuskAttacco){
                g.drawImage(immagini.get("HuskAttack_JZ"),e.getxCord() * Tile_Size, (e.getyCord()+1) * Tile_Size, Tile_Size, Tile_Size,null);
            }
        }

    }
    private void disegnaBulletTemp(Graphics g){
        for(Entita e : modello.getProiettiliTemp()){
            if (e instanceof ProiettileGiocatore) {
                g.drawImage(immagini.get("PlayerTestBullet_JZelda"),e.getxCord() * Tile_Size, (e.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
            }

            if (e instanceof ProiettileNemico){
                if(((ProiettileNemico) e).getId()==1){
                    g.drawImage(immagini.get("Palla_Di_Fuoco_JZ"),e.getxCord() * Tile_Size, (e.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);

                }
                else{
                    g.drawImage(immagini.get("Star_Bullet_JZ"),e.getxCord() * Tile_Size, (e.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);

                }
            }

        }

    }
    private void disegnaPlayer(Graphics g){
            Player p =modello.getPlayer();
            switch (p.getSkin()){
                case 0->{switch(p.getDirezione()){
                    case Direzione.GIU -> {
                        switch (p.getMossa()){
                            case 0 -> g.drawImage(immagini.get("Player_Front_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 1 -> g.drawImage(immagini.get("Player_Front_Move_Alpha_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 2 -> g.drawImage(immagini.get("Player_Front_Move_Beta_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 3 -> g.drawImage(immagini.get("Player_Front_ATK_Fucile_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 4 -> g.drawImage(immagini.get("Player_Front_Atk_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                        }
                    }
                    case Direzione.SINISTRA -> {
                        switch (p.getMossa()){
                            case 0 -> g.drawImage(immagini.get("Player_Left_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 1 -> g.drawImage(immagini.get("Player_Left_Move_Alpha_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 2 -> g.drawImage(immagini.get("Player_Left_Move_Beta_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 3 -> {g.drawImage(immagini.get("Player_Left_ATK_Fucile_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);}
                            case 4 -> g.drawImage(immagini.get("Player_Left_ATK_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                        }
                    }
                    case Direzione.DESTRA -> {
                        switch (p.getMossa()){
                            case 0 -> g.drawImage(immagini.get("Player_Right_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 1 -> g.drawImage(immagini.get("Player_Right_Move_Alpha_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 2 -> g.drawImage(immagini.get("Player_Right_Move_Beta_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 3 -> g.drawImage(immagini.get("Player_Right_ATK_Fucile_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 4 -> g.drawImage(immagini.get("Player_Right_ATK_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                        }
                    }
                    case Direzione.SU -> {
                        switch (p.getMossa()){
                            case 0 -> g.drawImage(immagini.get("Player_Up_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 1 -> g.drawImage(immagini.get("Player_Up_Move_Alpha_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 2 -> g.drawImage(immagini.get("Player_Up_Move_Beta_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 3 -> g.drawImage(immagini.get("Player_Up_ATK_Fucile_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 4 -> g.drawImage(immagini.get("Player_Up_ATK_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                        }
                    }
                    case Direzione.NESSUNA ->{
                        g.drawImage(immagini.get("Player_Up_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                    }

                }}
                case 1->{switch(p.getDirezione()){
                    case Direzione.GIU -> {
                        switch (p.getMossa()){
                            case 0 -> g.drawImage(immagini.get("Player_1_Front_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 1 -> g.drawImage(immagini.get("Player_1_Front_Move_Alpha_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 2 -> g.drawImage(immagini.get("Player_1_Front_Move_Beta_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 3 -> g.drawImage(immagini.get("Player_1_Front_ATK_Fucile_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 4 -> g.drawImage(immagini.get("Player_1_Front_Atk_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                        }
                    }
                    case Direzione.SINISTRA -> {
                        switch (p.getMossa()){
                            case 0 -> g.drawImage(immagini.get("Player_1_Left_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 1 -> g.drawImage(immagini.get("Player_1_Left_Move_Alpha_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 2 -> g.drawImage(immagini.get("Player_1_Left_Move_Beta_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 3 -> g.drawImage(immagini.get("Player_1_Left_ATK_Fucile_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 4 -> g.drawImage(immagini.get("Player_1_Left_ATK_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                        }
                    }
                    case Direzione.DESTRA -> {
                        switch (p.getMossa()){
                            case 0 -> g.drawImage(immagini.get("Player_1_Right_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 1 -> g.drawImage(immagini.get("Player_1_Right_Move_Alpha_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 2 -> g.drawImage(immagini.get("Player_1_Right_Move_Beta_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 3 -> g.drawImage(immagini.get("Player_1_Right_ATK_Fucile_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 4 -> g.drawImage(immagini.get("Player_1_Right_ATK_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                        }
                    }
                    case Direzione.SU -> {
                        switch (p.getMossa()){
                            case 0 -> g.drawImage(immagini.get("Player_1_Up_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 1 -> g.drawImage(immagini.get("Player_1_Up_Move_Alpha_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 2 -> g.drawImage(immagini.get("Player_1_Up_Move_Beta_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 3 -> g.drawImage(immagini.get("Player_1_Up_ATK_Fucile_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                            case 4 -> g.drawImage(immagini.get("Player_1_Up_ATK_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                        }
                    }
                    case Direzione.NESSUNA ->{
                        g.drawImage(immagini.get("Player_1_Up_JZ"),p.getxCord() * Tile_Size, (p.getyCord() +1)* Tile_Size, Tile_Size, Tile_Size,null);
                    }

                }}

            }




    }

    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        if (modello == null || modello.getLivello() == null) return;
        try {
            creaTabellone(g);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        disegnaElementiStatici(g);
        disegnaHotbar(g);
        creaInterfacciaInterazione(g);
        disegnaEntitàTemp(g);
        disegnaBulletTemp(g);
        disegnaEntità(g);
        disegnaPlayer(g);

    }




    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof GameModel){
           if(modello == null) {
                modello = (GameModel) o;
                settaggio();
           }
            modello = (GameModel) o;
        }
        repaint();
    }


    public int getTile_Size() {
        return Tile_Size;
    }

    public Collection<Punto> getBottoni() {
        return bottoni;
    }
}

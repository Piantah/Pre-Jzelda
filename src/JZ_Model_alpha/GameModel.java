package JZ_Model_alpha;

import JZ_Eccezioni.MovimentoInvalidoEntita;
import JZ_Eccezioni.MovimentoInvalidoGiocatore;
import JZ_Model_alpha.Entities.*;
import JZ_Model_alpha.Entities.Bullets.Bullet;
import JZ_Model_alpha.Entities.Bullets.EnemyShot;
import JZ_Model_alpha.Entities.Bullets.PlayerShot;
import JZ_Model_alpha.Entities.Enemies.*;
import JZ_Model_alpha.Entities.OtherEntities.*;
import JZ_Model_alpha.Levels.*;
import JZ_Model_alpha.Items.*;

import java.io.*;
import java.util.*;


public class GameModel extends Observable {
    private static GameModel instance;
    private boolean isGameOver;
    private Player player;
    private String path;
    private int punteggio;
    private boolean isShop;
    private String f_path;

    private Collection<Level> livelli = new ArrayList<>();
    private Level currentLevel;
    private Collection<Entity> temp;
    private Collection<Bullet> tempBull;


    public static GameModel getInstance(){
        if(instance==null)instance=new GameModel();
        return instance;
    }


//forse dovrei creare un metodo notify movement?
    private GameModel(){
        this.isGameOver=false;
        this.player=new Player("");
        player.setxCord(14);
        player.setyCord(14);
        temp=new ArrayList<>();
        tempBull=new ArrayList<>();
        assignLevels();
        changeLevel(1);
        Spada s = new Spada("Spada",20);
        //Arco a = new Arco("Arco", 20,player);
        aggiungiItem(s);
        //aggiungiItem(a);

    }
    public void caricaSave(String f) throws FileNotFoundException {

            int tmpX=0;
            int tmpY=0;
            player.resetItems();
            path=null;
            path = "JZ_Saves/" + f + ".txt";
            f_path=f;
            File file = new File(path);
            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                String riga = reader.readLine();
                while(riga!=null){
                    String[] parti = riga.split(":");
                    switch(parti[0]){
                        case "Name"->player.setNome(parti[1]);
                        case "P_X"->tmpX=Integer.parseInt(parti[1]);
                        case "P_Y"->tmpY=Integer.parseInt(parti[1]);
                        case "Soldi"->player.setSoldi(Integer.parseInt(parti[1]));
                        case "Hp"->player.setVita(Integer.parseInt(parti[1]));
                        case "Skin"->player.setSkin(Integer.parseInt(parti[1]));
                        case "Item"->{
                            for(String s:parti[1].split(",")){
                                switch (s){
                                    case "Spada"->aggiungiItem(new Spada(s,20));
                                    case "Fucile"->aggiungiItem(new Fucile(s,20,player));
                                    case "Staff"->aggiungiItem(new Staff(s,player));
                                }
                            }}
                        case"Level_Id"->changeLevel(Integer.parseInt(parti[1]));
                        case"Xp"->player.setXp(Integer.parseInt(parti[1]));
                        case"Morti"->player.setMorti(Integer.parseInt(parti[1]));
                        case"Tentativi"->player.setTenatativi(Integer.parseInt(parti[1]));
                    }

                    riga= reader.readLine();
                }
                player.setxCord(tmpX);
                player.setyCord(tmpY);
                assignLevels();
            }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvaClassifica() throws FileNotFoundException {
        String classPath = "JZ_Saves/" + "classifica" + ".txt";
        File file = new File(classPath);
        HashMap<String,Integer> classifica = new HashMap<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String riga = reader.readLine();
            while(riga!=null){
                String[] parti = riga.split(":");
                if(classifica.containsKey(parti[0]))classifica.replace(parti[0], Integer.valueOf(parti[1]));
                else classifica.put(parti[0], Integer.valueOf(parti[1]));
                riga= reader.readLine();
            }
            reader.close();
            calcolaPiunteggio();
            if(classifica.containsKey(player.getNome())){
                Integer tmp = classifica.get(player.getNome());
                if(punteggio>tmp)classifica.replace(player.getNome(), punteggio);
            }
            else classifica.put(player.getNome(), punteggio);

            if(!classifica.isEmpty()){
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for(String s: classifica.keySet()){
                    writer.write(s+":"+classifica.get(s));
                    writer.newLine();
                }
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveGame() throws IOException {
        System.out.println(path);
        if(path!=null){
            File file = new File(path);
            ArrayList<String> text = new ArrayList<>();
            text.add("Empty:false");
            text.add("Name:"+player.getNome());
            text.add("P_X:"+player.getxCord());
            text.add("Skin:"+player.getSkin());
            text.add("P_Y:"+player.getyCord());
            text.add("Xp:"+player.getXp());
            text.add("Morti:"+player.getMorti());
            text.add("Tentativi:"+player.getTenatativi());
            text.add("Soldi:"+player.getSoldi());
            text.add("Level_Id:"+currentLevel.getId());
            String tmp="Item:";
            for(Item i: player.getItems())tmp=tmp+i.getNomeItem()+",";
            text.add(tmp);
            text.add("Hp:"+player.getVita());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(String t:text){
                writer.write(t);
                writer.newLine();
            }
            writer.close();
            System.out.println("sei salvo");
            salvaClassifica();

        }
    }




    //settaggi livelli
    private void assignLevels(){
        livelli.clear();
        Collection<Gate> uno = new ArrayList<>();
        uno.add(new Gate(7,0,Facing.UP,2,1));
        uno.add(new Gate(0,8,Facing.LEFT,3,1));
        uno.add(new Gate(7,15,Facing.DOWN,9,1));
        uno.add(new Gate(15,8,Facing.RIGHT,4,1));


        Collection<Gate> due = new ArrayList<>();
        due.add(new Gate(7,15,Facing.DOWN,1,1));
        due.add(new Gate(7,0,Facing.UP,5,1));
        due.add(new Gate(8,0,Facing.UP,5,2));




        Collection<Gate> tre = new ArrayList<>();
        tre.add(new Gate(15,8,Facing.RIGHT,1,1));
        tre.add(new Gate(0,8,Facing.LEFT,6,1));
        tre.add(new Gate(8,0,Facing.UP,8,1));


        Collection<Gate> shop = new ArrayList<>();
        shop.add(new Gate(7,0,Facing.UP,1,1));


        Collection<Gate> quattro = new ArrayList<>();
        quattro.add(new Gate(0,8,Facing.LEFT,1,1));

        Collection<Gate> cinque = new ArrayList<>();
        cinque.add(new Gate(7,15,Facing.DOWN,2,1));
        cinque.add(new Gate(8,15,Facing.DOWN,2,2));

        Collection<Gate> sei = new ArrayList<>();
        sei.add(new Gate(15,8,Facing.RIGHT,3,1));
        sei.add(new Gate(2,0,Facing.UP,7,1));

        Collection<Gate> sette = new ArrayList<>();
        sette.add(new Gate(2,15,Facing.DOWN,6,1));
        sette.add(new Gate(15,6,Facing.RIGHT,8,1));
        sette.add(new Gate(15,13,Facing.RIGHT,8,2));

        Collection<Gate> otto = new ArrayList<>();
        otto.add(new Gate(0,8,Facing.LEFT,7,1));
        otto.add(new Gate(0,13,Facing.LEFT,7,2));
        otto.add(new Gate(8,15,Facing.DOWN,3,1));



        this.livelli.add(new Level(1, "livello uno", uno,"JZ_Levels/levelOne.txt"));
        this.livelli.add(new Level(2, "livello due", due,"JZ_Levels/levelTwo.txt"));
        this.livelli.add(new Level(3, "livello tre", tre,"JZ_Levels/levelThree.txt"));
        this.livelli.add(new Level(4, "livello quattro", quattro,"JZ_Levels/levelFour.txt"));
        this.livelli.add(new Level(5, "livello cinque", cinque,"JZ_Levels/levelFive.txt"));
        this.livelli.add(new Level(6, "livello sei", sei,"JZ_Levels/levelSix.txt"));
        this.livelli.add(new Level(7, "livello sette", sette,"JZ_Levels/levelSeven.txt"));
        this.livelli.add(new Level(8, "livello otto", otto,"JZ_Levels/levelEight.txt"));
        this.livelli.add(new Level(9, "Negozio", shop,"JZ_Levels/shop.txt"));


    }
    public void changeLevel(int id){
        int tmp = -10;
        Gate gate=null;
        if(currentLevel!=null) {
            tmp= currentLevel.getId();
            for(Gate g:currentLevel.getGates()){
                if(player.getxCord()==g.getX()&&player.getyCord()==g.getY())gate=g;
            }
        }
        for(Level livello : livelli) if(livello.getId()==id) {
            currentLevel=livello;
            if(!currentLevel.isShop()){
                if(!currentLevel.getEntita().isEmpty()) currentLevel.removeEnt();
                currentLevel.createLevel();
            }
            for(Gate g:currentLevel.getGates()) if(gate!=null)if(g.getId_Link()==tmp&&gate.getnLink()==g.getnLink()){
                player.setyCord(g.getY());
                player.setxCord(g.getX());
            }
            temp.clear();
            resetTmpBull();
            notifica();
        }
    }

    // azioni su player
    public void danneggiaGiocatore(int danno){
        player.perdiVita(danno);
        notifica();
        if(player.getVita()<=0) triggerGameOver();
    }

    public void curaGiocatore(int hp){
        player.guadagnaVita(hp);
        notifica();
    }

    public void incrementaSoldiPlayer(int guadagno){
        player.guadagna(guadagno);
        notifica();
    }
    public void riduciSoldi(int pagamento){
        player.paga(pagamento);
        notifica();
    }

    public void aggiungiItem(Item i){
        player.aggiungiItem(i);
        notifica();
    }

    //movimento player
    public int muoviPlayerSinistra(){
        int rt=0;
        try{
            if (player.getxCord() ==0){
                player.moveLeft(0);
                throw new MovimentoInvalidoGiocatore();
            }
            else if (checkCollision(currentLevel.getEntita(), player.getxCord()-1, player.getyCord())==2) {
                player.moveLeft(0);
            }
            else {
                player.moveLeft(1);
                rt=checkPlayerCollision(currentLevel.getEntita(),player.getxCord(),player.getyCord());
                notifica();

            }
        } catch (MovimentoInvalidoGiocatore e) {
            System.out.println(e.getMessage());
        }
        enterGate();
        return rt;
    }

    public int muoviPlayerDestra(){
        int rt=0;
        try{
            if (player.getxCord() == currentLevel.getWidth()-1) {
                player.moveRight(0);
                throw new MovimentoInvalidoGiocatore();
            }
            else if (checkCollision(currentLevel.getEntita(), player.getxCord()+1, player.getyCord())==2) {
                player.moveRight(0);
            }
            else {
                player.moveRight(1);
                rt=checkPlayerCollision(currentLevel.getEntita(),player.getxCord(),player.getyCord());
                notifica();
            }
        } catch (MovimentoInvalidoGiocatore e) {
            System.out.println(e.getMessage());
        }
        enterGate();
        return rt;
    }

    public int muoviPlayerSu(){
        int rt=0;
        try{
            if (player.getyCord() == 0) {
                player.moveUp(0);
                throw new MovimentoInvalidoGiocatore();
            } else if (checkCollision(currentLevel.getEntita(), player.getxCord(), player.getyCord()-1)==2) {
                player.moveUp(0);
            } else {

                player.moveUp(1);
                rt=checkPlayerCollision(currentLevel.getEntita(),player.getxCord(),player.getyCord());
                notifica();
            }
        } catch (MovimentoInvalidoGiocatore e) {
            System.out.println(e.getMessage());
        }
        enterGate();
        return rt;
    }
    public int muoviPlayerGiu(){
        int rt=0;
        try{
            if (player.getyCord() == currentLevel.getLength()-1) {
                player.moveDown(0);
                throw new MovimentoInvalidoGiocatore();
            }
            else if (checkCollision(currentLevel.getEntita(), player.getxCord(), player.getyCord()+1)==2) {
                player.moveDown(0);
            }
            else {
                player.moveDown(1);
                rt=checkPlayerCollision(currentLevel.getEntita(),player.getxCord(),player.getyCord());
                notifica();
            }
        } catch (MovimentoInvalidoGiocatore e) {
            System.out.println(e.getMessage());

        }
        enterGate();
        return rt;
    }


    public boolean acquistaItem(String nomeItem){
        Mercante m = currentLevel.getM();
        if(m!=null){
            int c= m.getPrezzoItem(nomeItem);
            Item i = m.compraItem(nomeItem, player.getSoldi());
            if(i!=null){
                if(i instanceof Fucile){
                    ((Fucile) i).setOwner(player);
                }
                if(i instanceof Staff){
                    ((Staff) i).setOwner(player);
                }
                if(i instanceof Fucile){
                    for(Item itm :player.getItems()){
                        if(itm instanceof Fucile){
                            m.restock(i,c,1);
                            return false;
                        }
                    }
                }
                if(i instanceof Staff){
                    for(Item itm :player.getItems()){
                        if(itm instanceof Staff){
                            m.restock(i,c,1);
                            return false;
                        }
                    }
                }
                aggiungiItem(i);
                riduciSoldi(c);
                try {
                    saveGame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        }
        return false;
    }

    private void calcolaPiunteggio(){
        if(player.getMorti()==0) punteggio=(player.getXp()*2)+player.getSoldi();
        else{
            punteggio=(((player.getXp()*2)+player.getSoldi())/player.getTenatativi())- getPlayer().getMorti();
        }
    }


    //danneggia entita
    private void danneggiaEntita(Entity e,int danno){
        e.setVita(e.getVita()-danno);
        if(e.getVita()<=0){
            if(e instanceof Enemy) {
                incrementaSoldiPlayer(((Enemy) e).getValue_drop());
                player.gainXp(((Enemy) e).getXpOnKill());
                Collection <Entity> tmp =  currentLevel.getEntita();
                System.out.println(e.getNome()+" è morto");
                Random r = new Random();
                if(r.nextInt(6)>=4){
                    tmp.add(new Drop(2, DropTypes.CURA,e.getxCord(),e.getyCord()));
                }
                tmp.remove(e);
                currentLevel.setEntita(tmp);


            }

        }
    }



    //movimento passivo entità
    public void muoviNemici(){
            temp.clear();
            Collection<Entity> nemici = currentLevel.getEntita();
            Collection<Entity> tmp = new ArrayList<>();
            for (Entity e : nemici) {
                if (e instanceof Enemy) {
                    try{
                    Random random = new Random();
                    switch (random.nextInt(4) + 1) {
                        case 1:
                            if(checkCollision(nemici,e.getxCord()-1,e.getyCord())==0) muoviEnemySinistra((Enemy) e);
                            //danneggiaEntita(e,100);
                            //tmp.add(e);

                            break;
                        case 2:
                            if(checkCollision(nemici,e.getxCord(),e.getyCord()-1)==0)muoviEnemySu((Enemy) e);
                            break;
                        case 3:
                            if(checkCollision(nemici,e.getxCord(),e.getyCord()+1)==0)muoviEnemyGiu((Enemy) e);
                            break;
                        case 4:
                            if(checkCollision(nemici,e.getxCord()+1,e.getyCord())==0) muoviEnemyDestra((Enemy) e);
                            break;
                    }
                    if(random.nextInt(6)<3){
                        attaccoNemico((Enemy) e);
                        e.setMove(3);
                    }

                    notifica();
                    //System.out.println("entita:"+e.getNome()+"alle cordinate x:"+e.getxCord()+" y:"+ e.getyCord());
                }
                    catch (MovimentoInvalidoEntita ex){ System.out.println(ex.getMessage());}
            }
        }
            nemici.removeAll(tmp);
        checkPlayerCollision(nemici, player.getxCord(), player.getyCord());
        currentLevel.setEntita(nemici);

    }


    private  void attaccoNemico(Enemy e){
        if(e.getDirezione()!=Facing.NONE){
            if(e instanceof Husk){
                e.attacca();
                for(Entity entity: ((Husk) e).getAttacchi()){
                    //La posizione dell'attacco è lecita?
                    if(entity.getxCord()>=0 && entity.getxCord()<currentLevel.getWidth()){
                        if(entity.getyCord()>=0 && entity.getyCord()<currentLevel.getLength()){
                            if(checkCollision(currentLevel.getEntita(),entity.getxCord(),entity.getyCord())==0){
                                entityAttack(entity);
                                temp.add(entity);
                            }
                        }
                    }

                }

            }
            else{
                e.attacca();
                tempBull.addAll(((Mago)e).getProiettili());
            }
        }
    }

    private void muoviEnemySinistra( Enemy e) throws MovimentoInvalidoEntita {
        if (e.getxCord()-1 < 0) throw new MovimentoInvalidoEntita();
        else {
            e.moveLeft(1);
        }
    }
    private void muoviEnemyDestra( Enemy e) throws MovimentoInvalidoEntita {
            if (e.getxCord()+1 >= currentLevel.getWidth()) throw new MovimentoInvalidoEntita();
            else {
                e.moveRight(1);
            }
    }

    private void muoviEnemySu( Enemy e ) throws MovimentoInvalidoEntita {
            if (e.getyCord()-1 < 0) throw new MovimentoInvalidoEntita();
            else {
                e.moveUp(1);
            }
    }

    private void muoviEnemyGiu(Enemy e) throws MovimentoInvalidoEntita {
            if (e.getyCord()+1 >= currentLevel.getLength()) throw new MovimentoInvalidoEntita();
            else {
                e.moveDown(1);
            }
    }


    private void entityAttack(Entity e){
        if(player.getyCord()== e.getyCord() && player.getxCord()== e.getxCord()){
            if(e instanceof HuskAttack){
                danneggiaGiocatore(((HuskAttack)  e).getDannoArrecato());
            }
        }

    }
    public void triggerFirstUpdate(){
        notifica();
    }

    //Triggera la fine del gioco
    private void triggerGameOver(){
        isGameOver=true;
        notifica();
    }

    public void endGame(){
        try {

            caricaSave(f_path);
            player.muori();
            saveGame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void playAgain(){
        isGameOver=false;
        player.ritenta();
    }

    private void notifica(){
        setChanged();
        notifyObservers();
    }


    private int checkCollision(Collection<Entity> entities, int x, int y){
        for(Entity e : entities){
            if(e.getxCord()==x && e.getyCord()==y){
                if(e instanceof Enemy) return 1;

                if(e instanceof Drop) return 1;

                //entita statica come un NPC o ostacolo
                return 2;
            }
        }
        return 0;
    }
    public int checkPlayerCollision(Collection<Entity> entities, int x, int y){
        int rt=0;
        Collection<Entity> tmp = new ArrayList<>(currentLevel.getEntita());
        for(Entity e : entities)if(e.getxCord()==x && e.getyCord()==y) {
            if(e instanceof Enemy) danneggiaGiocatore(1);
            if(e instanceof Drop){
                if(((Drop)e).getTipo()==DropTypes.CURA)curaGiocatore(((Drop)e).getValore());
                tmp.remove(e);
                rt=1;
            }
            currentLevel.setEntita(tmp);
        }
        return rt;
    }



    private void enterGate(){
        for(Gate gate: currentLevel.getGates()){
            if(gate.enter(getPlayer().getxCord(), getPlayer().getyCord(),getPlayer().getDirezione()))changeLevel(gate.getId_Link());
        }
    }

    public boolean usaItem(String nomeItem){
        Item i = player.usa(nomeItem);
        if(i!=null){
            if( i instanceof Spada){
                int x_tmp=player.getxCord();
                int y_tmp=player.getyCord();
                Facing d = player.getDirezione();
                switch(d){
                    case UP->y_tmp-=1;
                    case DOWN -> y_tmp+=1;
                    case LEFT -> x_tmp-=1;
                    case RIGHT -> x_tmp+=1;
                }
                Collection<Entity> tmp = new ArrayList<>(currentLevel.getEntita());
                player.setMove(4);
                for(Entity e: tmp){
                    if(e.getxCord()==x_tmp && e.getyCord()==y_tmp)danneggiaEntita(e,i.getDanniArrecati());
                }
                return true;

            }
            if(i instanceof Fucile){
                Fucile a = (Fucile) i;
                a.usa();
                if(a.getBullet()!=null&&a.getBullet().getDistanza()==a.getBullet().getMxdistanza()){
                    tempBull.add(a.getBullet());
                    player.setMove(3);
                    return true;
                }
                return false;

            }
            if(i instanceof Staff){
                Staff a = (Staff) i;
                a.usa();
                tempBull.addAll(a.getBullet());
                player.setMove(3);
                return true;
            }
            return false;
        }
        return false;
    }

    public void moveBullets(){
        Collection<Bullet> tomp = new ArrayList<>();
        for(Bullet b : tempBull){
            b.fire();

            //controllo posizione lecita
            if(b.getxCord()<0||b.getxCord()>=currentLevel.getLength()||b.getyCord()<0||b.getyCord()>= currentLevel.getWidth()){
                b.setDistanza(0);
            }

            //controllo collisioni proiettili-entità
            Collection<Entity> tmpEntities = new ArrayList<>(currentLevel.getEntita());
            for(Entity e : tmpEntities){
                //ID 3 = Acqua, i proiettili possono passare.
                if(!(e instanceof Ostacolo) || (((Ostacolo)e).getId_tipo_ostacolo()!=3&&((Ostacolo)e).getId_tipo_ostacolo()!=8)){
                    if(e.getyCord()== b.getyCord()&&e.getxCord()==b.getxCord()){
                        if(e instanceof Enemy)
                            if(b instanceof PlayerShot) danneggiaEntita(e,((PlayerShot) b).getS().getDanniArrecati());
                        b.setDistanza(0);
                    }
                }

            }
            //controllo collisione tra proitettili
            for(Bullet c :tempBull){
                if(!b.equals(c)){
                    if(b.getxCord()==c.getxCord() && b.getyCord()==c.getyCord()){
                        b.setDistanza(0);
                        c.setDistanza(0);
                    }
                }
            }


            //controllo collisione con player
            if(!(b instanceof PlayerShot)){
                if(b.getyCord()== player.getyCord()&&b.getxCord()== player.getxCord()){
                        danneggiaGiocatore(((EnemyShot) b).getDanni());
                        b.setDistanza(0);

                }
            }
            if(b.getDistanza()<=0) tomp.add(b);
        }
        remTmpBull(tomp);
        notifica();
    }

    //getter
    public Level getCurrentLevel() {
        return currentLevel;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public Collection<Entity> getTemp() {
        return temp;
    }

    public Collection<Bullet> getTempBull() {
        return tempBull;
    }

    public void remTmpBull(Collection<Bullet> e){
        tempBull.removeAll(e);
    }

    public void resetTmpBull(){
        for(Bullet b: tempBull) b.setDistanza(0);
        tempBull.clear();
    }

    public boolean isShop() {
        return currentLevel.isShop();
    }
}

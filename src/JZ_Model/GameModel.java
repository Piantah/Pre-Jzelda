package JZ_Model;

import JZ_Eccezioni.MovimentoInvalidoEntita;
import JZ_Eccezioni.MovimentoInvalidoGiocatore;
import JZ_Model.Entities.*;
import JZ_Model.Entities.Proiettili.Proiettile;
import JZ_Model.Entities.Proiettili.ProiettileNemico;
import JZ_Model.Entities.Proiettili.ProiettileGiocatore;
import JZ_Model.Entities.Nemici.*;
import JZ_Model.Entities.OtherEntities.*;
import JZ_Model.Levels.*;
import JZ_Model.Items.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class GameModel extends Observable {
    private static GameModel instance;
    private boolean isGameOver;
    private final Player player;
    private String path="";
    private int punteggio;

    private Collection<Livello> livelli;
    private Livello livelloCorrente;
    private Collection<Entita> entitaTemporanee;
    private Collection<Proiettile> proiettiliTemp;



    //il Model è Singleton
    public static GameModel getInstance(){
        if(instance==null)instance=new GameModel();
        return instance;
    }

    private GameModel(){
        isGameOver=false;
        player= Player.getInstance();
        entitaTemporanee=new ArrayList<>();
        proiettiliTemp =new ArrayList<>();
        livelli = new ArrayList<>();
        assegnaLivelli();
        cambiaLivello(1);
    }

    //gestione salvataggi
    public void caricaSalvataggio(String f) throws FileNotFoundException {

            int tmpX=0;
            int tmpY=0;
            player.resetOggetti();
            if(!f.equals(path)){
                path = "JZ_Saves/" + f + ".txt";
            }
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
                                    case "Spada"->aggiungiItem(new Spada(s,20,player));
                                    case "Fucile"->aggiungiItem(new Fucile(s,20,player));
                                    case "Staffa"->aggiungiItem(new Staffa(s,player));
                                }
                            }}
                        case"Level_Id"-> cambiaLivello(Integer.parseInt(parti[1]));
                        case"Xp"->player.setXp(Integer.parseInt(parti[1]));
                        case"Morti"->player.setMorti(Integer.parseInt(parti[1]));
                        case"Tentativi"->player.setTenatativi(Integer.parseInt(parti[1]));
                    }

                    riga= reader.readLine();
                }
                player.setxCord(tmpX);
                player.setyCord(tmpY);
                assegnaLivelli();
            }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvaClassifica() throws FileNotFoundException {
        String classificaPath = "JZ_Saves/" + "classifica" + ".txt";
        File file = new File(classificaPath);
        HashMap<String,Integer> classifica = new HashMap<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            //stream per la lettura del file della classifica
            reader.lines().forEach(riga->{
                        String[] parti = riga.split(":");
                        classifica.put(parti[0], Integer.valueOf(parti[1]));
                    });

            //calcolo punteggio corrente
            calcolaPiunteggio();
            if(classifica.containsKey(player.getNome()))
                classifica.replace(player.getNome(), Math.max(classifica.get(player.getNome()),punteggio));
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

    public void salvaPartita() throws IOException {
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
            text.add("Level_Id:"+ livelloCorrente.getId());

            String oggetti = player.getOggetti().stream()
                    .map(Item::getNomeItem)
                    .collect(Collectors.joining(","));

            text.add("Item:"+oggetti);
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
    private void assegnaLivelli(){
        livelli.clear();
        Collection<Gate> uno = new ArrayList<>();
        uno.add(new Gate(7,0, Direzione.SU,2,1));
        uno.add(new Gate(0,8, Direzione.SINISTRA,3,1));
        uno.add(new Gate(7,15, Direzione.GIU,9,1));
        uno.add(new Gate(15,8, Direzione.DESTRA,4,1));


        Collection<Gate> due = new ArrayList<>();
        due.add(new Gate(7,15, Direzione.GIU,1,1));
        due.add(new Gate(7,0, Direzione.SU,5,1));
        due.add(new Gate(8,0, Direzione.SU,5,2));




        Collection<Gate> tre = new ArrayList<>();
        tre.add(new Gate(15,8, Direzione.DESTRA,1,1));
        tre.add(new Gate(0,8, Direzione.SINISTRA,6,1));
        tre.add(new Gate(8,0, Direzione.SU,8,1));


        Collection<Gate> shop = new ArrayList<>();
        shop.add(new Gate(7,0, Direzione.SU,1,1));


        Collection<Gate> quattro = new ArrayList<>();
        quattro.add(new Gate(0,8, Direzione.SINISTRA,1,1));

        Collection<Gate> cinque = new ArrayList<>();
        cinque.add(new Gate(7,15, Direzione.GIU,2,1));
        cinque.add(new Gate(8,15, Direzione.GIU,2,2));

        Collection<Gate> sei = new ArrayList<>();
        sei.add(new Gate(15,8, Direzione.DESTRA,3,1));
        sei.add(new Gate(2,0, Direzione.SU,7,1));

        Collection<Gate> sette = new ArrayList<>();
        sette.add(new Gate(2,15, Direzione.GIU,6,1));
        sette.add(new Gate(15,6, Direzione.DESTRA,8,1));
        sette.add(new Gate(15,13, Direzione.DESTRA,8,2));

        Collection<Gate> otto = new ArrayList<>();
        otto.add(new Gate(0,8, Direzione.SINISTRA,7,1));
        otto.add(new Gate(0,13, Direzione.SINISTRA,7,2));
        otto.add(new Gate(8,15, Direzione.GIU,3,1));



        this.livelli.add(new Livello(1, "livello uno", uno,"JZ_Levels/levelOne.txt"));
        this.livelli.add(new Livello(2, "livello due", due,"JZ_Levels/levelTwo.txt"));
        this.livelli.add(new Livello(3, "livello tre", tre,"JZ_Levels/levelThree.txt"));
        this.livelli.add(new Livello(4, "livello quattro", quattro,"JZ_Levels/levelFour.txt"));
        this.livelli.add(new Livello(5, "livello cinque", cinque,"JZ_Levels/levelFive.txt"));
        this.livelli.add(new Livello(6, "livello sei", sei,"JZ_Levels/levelSix.txt"));
        this.livelli.add(new Livello(7, "livello sette", sette,"JZ_Levels/levelSeven.txt"));
        this.livelli.add(new Livello(8, "livello otto", otto,"JZ_Levels/levelEight.txt"));
        this.livelli.add(new Livello(9, "Negozio", shop,"JZ_Levels/shop.txt"));


    }

    public void cambiaLivello(int id){
        int tmp = -10;
        Gate gate=null;
        if(livelloCorrente !=null) {
            tmp= livelloCorrente.getId();
            for(Gate g: livelloCorrente.getGates()){
                if(player.getxCord()==g.getX()&&player.getyCord()==g.getY())gate=g;
            }
        }
        for(Livello livello : livelli) if(livello.getId()==id) {
            livelloCorrente =livello;
            if(!livelloCorrente.isNegozio()){
                if(!livelloCorrente.getEntita().isEmpty()) livelloCorrente.rimuoviEntita();
                livelloCorrente.creaLivello();
            }
            for(Gate g: livelloCorrente.getGates()) if(gate!=null)if(g.getId_Link()==tmp&&gate.getnLink()==g.getnLink()){
                player.setyCord(g.getY());
                player.setxCord(g.getX());
            }
            entitaTemporanee.clear();
            resetProiettiliTemp();
            notifica();
        }
    }

    private void entraGate(){
        for(Gate gate: livelloCorrente.getGates()){
            if(gate.enter(getPlayer().getxCord(), getPlayer().getyCord(),getPlayer().getDirezione())) cambiaLivello(gate.getId_Link());
        }
    }

    // azioni su player
    public void danneggiaGiocatore(int danno){
        player.perdiVita(danno);
        notifica();
        if(player.getVita()<=0) scattaGameOver();
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
        player.aggiungiOggetti(i);
        notifica();
    }

    //movimento player
    public int muoviPlayerSinistra(){
        int rt=0;
        try{
            if (player.getxCord() ==0){
                player.movimentoSinistra(0);
                throw new MovimentoInvalidoGiocatore();
            }
            else if (controllaCollisoniGeneriche(livelloCorrente.getEntita(), player.getxCord()-1, player.getyCord())==2) {
                player.movimentoSinistra(0);
            }
            else {
                player.movimentoSinistra(1);
                rt= controlloCollisioniPlayer(livelloCorrente.getEntita());
                notifica();

            }
        } catch (MovimentoInvalidoGiocatore e) {
            System.out.println(e.getMessage());
        }
        entraGate();
        return rt;
    }

    public int muoviPlayerDestra(){
        int rt=0;
        try{
            if (player.getxCord() == livelloCorrente.getAltezza()-1) {
                player.movimentoDestra(0);
                throw new MovimentoInvalidoGiocatore();
            }
            else if (controllaCollisoniGeneriche(livelloCorrente.getEntita(), player.getxCord()+1, player.getyCord())==2) {
                player.movimentoDestra(0);
            }
            else {
                player.movimentoDestra(1);
                rt= controlloCollisioniPlayer(livelloCorrente.getEntita());
                notifica();
            }
        } catch (MovimentoInvalidoGiocatore e) {
            System.out.println(e.getMessage());
        }
        entraGate();
        return rt;
    }

    public int muoviPlayerSu(){
        int rt=0;
        try{
            if (player.getyCord() == 0) {
                player.movimentoSu(0);
                throw new MovimentoInvalidoGiocatore();
            } else if (controllaCollisoniGeneriche(livelloCorrente.getEntita(), player.getxCord(), player.getyCord()-1)==2) {
                player.movimentoSu(0);
            } else {

                player.movimentoSu(1);
                rt= controlloCollisioniPlayer(livelloCorrente.getEntita());
                notifica();
            }
        } catch (MovimentoInvalidoGiocatore e) {
            System.out.println(e.getMessage());
        }
        entraGate();
        return rt;
    }

    public int muoviPlayerGiu(){
        int rt=0;
        try{
            if (player.getyCord() == livelloCorrente.getLunghezza()-1) {
                player.movimentoGiu(0);
                throw new MovimentoInvalidoGiocatore();
            }
            else if (controllaCollisoniGeneriche(livelloCorrente.getEntita(), player.getxCord(), player.getyCord()+1)==2) {
                player.movimentoGiu(0);
            }
            else {
                player.movimentoGiu(1);
                rt= controlloCollisioniPlayer(livelloCorrente.getEntita());
                notifica();
            }
        } catch (MovimentoInvalidoGiocatore e) {
            System.out.println(e.getMessage());

        }
        entraGate();
        return rt;
    }

    //gestione shop e oggetti
    public boolean acquistaItem(String nomeItem){
        Mercante m = livelloCorrente.getMercante();
        if(m!=null){
            int c= m.getPrezzoItem(nomeItem);
            Item i = m.compraItem(nomeItem, player.getSoldi());
            if(i!=null){
                i.setPropietario(player);
                if(i instanceof Fucile){
                    for(Item itm :player.getOggetti()){
                        if(itm instanceof Fucile){
                            m.restock(i,c,1);
                            return false;
                        }
                    }
                }
                if(i instanceof Staffa){
                    for(Item itm :player.getOggetti()){
                        if(itm instanceof Staffa){
                            m.restock(i,c,1);
                            return false;
                        }
                    }
                }
                aggiungiItem(i);
                riduciSoldi(c);
                try {
                    salvaPartita();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        }
        return false;
    }

    public boolean usaItem(String nomeItem){
        Item i = player.usa(nomeItem);
        if(i!=null){
            i.usa();
            switch (i) {
                case Spada spada -> {
                    Punto puntoHelper = spada.getAttacco();
                    if(puntoHelper!=null){
                        Collection<Entita> tmp = new ArrayList<>(livelloCorrente.getEntita());
                        player.setMossa(4);
                        for (Entita e : tmp) {
                            if (e.getxCord() == puntoHelper.x && e.getyCord() == puntoHelper.y) danneggiaEntita(e, i.getDanniArrecati());
                        }
                        return true;
                    }
                }
                case Fucile fucile -> {
                    if (fucile.getProiettile() != null && fucile.getProiettile().getDistanza() == fucile.getProiettile().getMxdistanza()) {
                        proiettiliTemp.add(fucile.getProiettile());
                        player.setMossa(3);
                        return true;
                    }
                    return false;
                }
                case Staffa staffa -> {
                    proiettiliTemp.addAll(staffa.getBullet());
                    player.setMossa(3);
                    return true;
                }
                default -> {
                }
            }
            return false;
        }
        return false;
    }

    //danneggia entita
    private void danneggiaEntita(Entita e, int danno){
        e.setVita(e.getVita()-danno);
        if(e.getVita()<=0){
            if(e instanceof Nemico) {
                incrementaSoldiPlayer(((Nemico) e).getValoreDrop());
                player.guadagnaXp(((Nemico) e).getXpDati());
                Collection <Entita> tmp =  livelloCorrente.getEntita();
                System.out.println(e.getNome()+" è morto");
                Random r = new Random();
                if(r.nextInt(6)>=4){
                    tmp.add(new Drop(2, DropTypes.CURA,e.getxCord(),e.getyCord()));
                }
                tmp.remove(e);
                livelloCorrente.setEntita(tmp);


            }

        }
    }

    //comportamento passivo delle entità
    public void muoviNemici(){
            entitaTemporanee.clear();
            Collection<Entita> nemici = livelloCorrente.getEntita();
            for (Entita e : nemici) {
                if (e instanceof Nemico) {
                    try{
                    Random random = new Random();
                    switch (random.nextInt(4) + 1) {
                        case 1:
                            if(controllaCollisoniGeneriche(nemici,e.getxCord()-1,e.getyCord())==0) muoviNemicoSinistra((Nemico) e);
                            //danneggiaEntita(e,100);
                            //tmp.add(e);

                            break;
                        case 2:
                            if(controllaCollisoniGeneriche(nemici,e.getxCord(),e.getyCord()-1)==0) muoviNemicoSu((Nemico) e);
                            break;
                        case 3:
                            if(controllaCollisoniGeneriche(nemici,e.getxCord(),e.getyCord()+1)==0) muoviNemicoGiu((Nemico) e);
                            break;
                        case 4:
                            if(controllaCollisoniGeneriche(nemici,e.getxCord()+1,e.getyCord())==0) muoviNemicoDestra((Nemico) e);
                            break;
                    }
                    if(random.nextInt(6)<3){
                        attaccoNemico((Nemico) e);
                        e.setMossa(3);
                    }

                    notifica();
                    //System.out.println("entita:"+e.getNome()+"alle cordinate x:"+e.getxCord()+" y:"+ e.getyCord());
                }
                    catch (MovimentoInvalidoEntita ex){ System.out.println(ex.getMessage());}
            }
        }
        controlloCollisioniPlayer(nemici);
        livelloCorrente.setEntita(nemici);

    }

    private  void attaccoNemico(Nemico e){
        if(e.getDirezione()!= Direzione.NESSUNA){
            e.attacca();
            if(e instanceof Husk){
                for(Entita entita : ((Husk) e).getAttacchi()){
                    //La posizione dell'attacco è lecita?
                    if(entita.getxCord()>=0 && entita.getxCord()< livelloCorrente.getAltezza()){
                        if(entita.getyCord()>=0 && entita.getyCord()< livelloCorrente.getLunghezza()){
                            if(controllaCollisoniGeneriche(livelloCorrente.getEntita(), entita.getxCord(), entita.getyCord())==0){
                                attaccoHuskHelper(entita);
                                entitaTemporanee.add(entita);
                            }
                        }
                    }

                }

            }
            else{
                proiettiliTemp.addAll(((Mago)e).getProiettili());
            }
        }
    }

    private void attaccoHuskHelper(Entita e){
        if(player.getyCord()== e.getyCord() && player.getxCord()== e.getxCord()){
            if(e instanceof HuskAttacco){
                danneggiaGiocatore(((HuskAttacco)  e).getDannoArrecato());
            }
        }

    }

    private void muoviNemicoSinistra(Nemico e) throws MovimentoInvalidoEntita {
        if (e.getxCord()-1 < 0) throw new MovimentoInvalidoEntita();
        else {
            e.movimentoSinistra(1);
        }
    }

    private void muoviNemicoDestra(Nemico e) throws MovimentoInvalidoEntita {
            if (e.getxCord()+1 >= livelloCorrente.getAltezza()) throw new MovimentoInvalidoEntita();
            else {
                e.movimentoDestra(1);
            }
    }

    private void muoviNemicoSu(Nemico e ) throws MovimentoInvalidoEntita {
            if (e.getyCord()-1 < 0) throw new MovimentoInvalidoEntita();
            else {
                e.movimentoSu(1);
            }
    }

    private void muoviNemicoGiu(Nemico e) throws MovimentoInvalidoEntita {
            if (e.getyCord()+1 >= livelloCorrente.getLunghezza()) throw new MovimentoInvalidoEntita();
            else {
                e.movimentoGiu(1);
            }
    }

    //metodi dei proiettili
    public void muoviProiettili(){
        Collection<Proiettile> helper = new ArrayList<>();
        for(Proiettile b : proiettiliTemp){
            b.fuoco();

            //controllo posizione lecita
            if(b.getxCord()<0||b.getxCord()>= livelloCorrente.getLunghezza()||b.getyCord()<0||b.getyCord()>= livelloCorrente.getAltezza()){
                b.setDistanza(0);
            }

            //controllo collisioni proiettili-entità
            Collection<Entita> tmpEntities = new ArrayList<>(livelloCorrente.getEntita());
            for(Entita e : tmpEntities){
                //ID 3 = Acqua, i proiettili possono passare.
                if(!(e instanceof Ostacolo) || (((Ostacolo)e).getId_tipo_ostacolo()!=3&&((Ostacolo)e).getId_tipo_ostacolo()!=8)){
                    if(e.getyCord()== b.getyCord()&&e.getxCord()==b.getxCord()){
                        if(e instanceof Nemico)
                            if(b instanceof ProiettileGiocatore) danneggiaEntita(e,((ProiettileGiocatore) b).getOggetto().getDanniArrecati());
                        b.setDistanza(0);
                    }
                }

            }
            //controllo collisione tra proitettili
            for(Proiettile c : proiettiliTemp){
                if(!b.equals(c)){
                    if(b.getxCord()==c.getxCord() && b.getyCord()==c.getyCord()){
                        b.setDistanza(0);
                        c.setDistanza(0);
                    }
                }
            }


            //controllo collisione con player
            if(!(b instanceof ProiettileGiocatore)){
                if(b.getyCord()== player.getyCord()&&b.getxCord()== player.getxCord()){
                    danneggiaGiocatore(((ProiettileNemico) b).getDanni());
                    b.setDistanza(0);

                }
            }
            if(b.getDistanza()<=0) helper.add(b);
        }
        proiettiliTemp.removeAll(helper);
        notifica();
    }

    public void resetProiettiliTemp(){
        for(Proiettile b: proiettiliTemp) b.setDistanza(0);
        proiettiliTemp.clear();
    }

  //metodi relativi a far finire il gioco ed al rigioco
    private void scattaGameOver(){
        isGameOver=true;
        notifica();
    }

    public void partitaFinita(){
        try {
            caricaSalvataggio(path);
            player.muori();
            salvaPartita();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void giocaAncora(){
        isGameOver=false;
        player.ritenta();
    }

    private void notifica(){
        setChanged();
        notifyObservers();
    }

    //calcolo del punteggio della classifica
    private void calcolaPiunteggio(){
        if(player.getMorti()==0) punteggio=(player.getXp()*2)+player.getSoldi();
        else{
            punteggio=(((player.getXp()*2)+player.getSoldi())/player.getTenatativi())- getPlayer().getMorti();
        }
    }

    //collisioni
    private int controllaCollisoniGeneriche(Collection<Entita> entities, int x, int y){
        for(Entita e : entities){
            if(e.getxCord()==x && e.getyCord()==y){
                //1 sono tutte le entità attive Attraversabili
                if(e instanceof Nemico) return 1;

                if(e instanceof Drop) return 3;

                //entita statica come un NPC o ostacolo
                return 2;
            }
        }
        return 0;
    }

    public int controlloCollisioniPlayer(Collection<Entita> entities){
        int rt=0;
        Collection<Entita> tmp = new ArrayList<>(livelloCorrente.getEntita());
        for(Entita e : entities)if(e.getxCord()==player.getxCord() && e.getyCord()==player.getyCord()) {
            if(e instanceof Nemico) danneggiaGiocatore(1);
            if(e instanceof Drop){
                if(((Drop)e).getTipo()==DropTypes.CURA)curaGiocatore(((Drop)e).getValore());
                tmp.remove(e);
                rt=1;
            }
            livelloCorrente.setEntita(tmp);
        }
        return rt;
    }

    //getter
    public Livello getLivello() {
        return livelloCorrente;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isNegozio() {

        return livelloCorrente.isNegozio();
    }

    public Collection<Entita> getTemp() {
        return entitaTemporanee;
    }

    public Collection<Proiettile> getProiettiliTemp() {
        return proiettiliTemp;
    }
}

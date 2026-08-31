package JZ_Model.Levels;

import JZ_Model.Entities.Entita;
import JZ_Model.Entities.EntitaFactory;
import JZ_Model.Entities.OtherEntities.Mercante;
import JZ_Model.Entities.OtherEntities.Ostacolo;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public  class Livello extends Tabellone {
    private final String nomeLivello;
    private final int id;
    private final boolean isNegozio;
    protected Collection<Gate> gates;
    protected List<Punto> invalidi = new ArrayList<>();
    private String path;
    private Mercante mercante;

    public Livello(int id, String nomeLivello, Collection<Gate> gates, String path){
        super();
        this.id=id;
        this.nomeLivello=nomeLivello;
        this.gates=gates;
        this.path=path;
        if(id==9) isNegozio =true;
        else isNegozio =false;
        setGates();
        creaLivello();

    }
    public void rimuoviEntita(){
        Collection<Entita> tmp = new ArrayList<>();
        for(Entita e: entita)if(!(e instanceof Ostacolo))tmp.add(e);
        entita.removeAll(tmp);

    }
    private void setGates(){
        for(Gate g:getGates())invalidi.add(new Punto(g.getX(),g.getY()));
    }

    public void creaLivello(){
        File file = new File(path);
        int y=0;
        int e_id=0;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String riga = reader.readLine();
            while (riga != null) {
                int x=0;
                char[] caratteri =riga.strip().toCharArray();
                for(char c:caratteri){
                    int id= EntitaFactory.getId(c);
                    if(id==0){
                        id=e_id;
                        e_id+=1;
                    }
                    if(!invalidi.contains(new Punto(x,y))){
                        Entita e = EntitaFactory.creaEntita(c,id,x,y);
                        if(e!=null)entita.add(e);
                        if(isNegozio && e instanceof Mercante) mercante =(Mercante) e;
                    }
                    x+=1;
                }
                riga=reader.readLine();
                y+=1;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Mercante getMercante() {
        if(isNegozio){
            System.out.println("is shop");
            return mercante;
        }
        return null;
    }

    //getter
    public int getId() {
        return id;
    }

    public String getNomeLivello() {
        return nomeLivello;
    }

    public Collection<Gate> getGates() {
        return gates;
    }

    public boolean isNegozio() {
        return isNegozio;
    }
}

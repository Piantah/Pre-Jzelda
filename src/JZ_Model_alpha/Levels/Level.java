package JZ_Model_alpha.Levels;

import JZ_Model_alpha.Entities.Entity;
import JZ_Model_alpha.Entities.EntityFactory;
import JZ_Model_alpha.Entities.OtherEntities.Mercante;
import JZ_Model_alpha.Entities.OtherEntities.Ostacolo;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public  class Level extends GameBoard {
    private String nomeLivello;
    private int id;
    private boolean isShop;
    protected Collection<Gate> gates;
    protected List<Punto> invalidi = new ArrayList<>();
    private String path;
    private Mercante m;

    public Level(int id, String nomeLivello, Collection<Gate> gates,String path){
        super();
        this.id=id;
        this.nomeLivello=nomeLivello;
        this.gates=gates;
        this.path=path;
        if(id==9)isShop=true;
        else isShop=false;
        setGates();
        createLevel();

    }
    public void removeEnt(){
        Collection<Entity> tmp = new ArrayList<>();
        for(Entity e: entita)if(!(e instanceof Ostacolo))tmp.add(e);
        entita.removeAll(tmp);

    }
    private void setGates(){
        for(Gate g:getGates())invalidi.add(new Punto(g.getX(),g.getY()));
    }

    public void createLevel(){
        File file = new File(path);
        int y=0;
        int e_id=0;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String riga = reader.readLine();
            while (riga != null) {
                int x=0;
                char[] caratteri =riga.strip().toCharArray();
                for(char c:caratteri){
                    int id=EntityFactory.getId(c);
                    if(id==0){
                        id=e_id;
                        e_id+=1;
                    }
                    if(!invalidi.contains(new Punto(x,y))){
                        Entity e =EntityFactory.createEntity(c,id,x,y);
                        if(e!=null)entita.add(e);
                        if(isShop&& e instanceof Mercante)m=(Mercante) e;
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

    public Mercante getM() {
        if(isShop){
            System.out.println("is shop");
            return m;
        }
        return null;
    }

    //getter
    public int getId() {
        return id;
    }

    public Collection<Gate> getGates() {
        return gates;
    }

    public boolean isShop() {
        return isShop;
    }
}

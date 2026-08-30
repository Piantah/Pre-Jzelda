package JZ_Model_alpha.Levels;

import JZ_Model_alpha.Entities.Entity;
import JZ_Model_alpha.Entities.EntityFactory;
import JZ_Model_alpha.Entities.OtherEntities.Ostacolo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestLevel extends GameBoard{

        private String nomeLivello;
        private int id;
        protected Collection<Gate> gates;
        protected List<Punto> invalidi = new ArrayList<>();

        public TestLevel(int id, String nomeLivello, Collection<Gate> gates){
            super();
            this.id=id;
            this.nomeLivello=nomeLivello;
            this.gates=gates;
            setGates();
        }

//    @Override
//    protected void createEntities() {
//
//    }

    public void creaEnt(){
            //createEntities();
        }
        public void removeEnt(){
            Collection<Entity> tmp = new ArrayList<>();
            for(Entity e: entita)if(!(e instanceof Ostacolo))tmp.add(e);
            entita.removeAll(tmp);

        }
        private void setGates(){
            for(Gate g:getGates())invalidi.add(new Punto(g.getX(),g.getY()));
        }

        public void createLevel(String path) throws IOException {
            File file = new File(path);
            int x=0;
            int y=0;
            int e_id=0;
            try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String riga = reader.readLine();
                while (riga != null) {
                    x=0;
                    char[] caratteri =riga.strip().toCharArray();
                    for(char c:caratteri){
                        int id= EntityFactory.getId(c);
                        if(id==0){
                            id=e_id;
                            e_id+=1;
                        }
                        if(!invalidi.contains(new Punto(x,y))){
                            entita.add(EntityFactory.createEntity(c,id,x,y));
                        }
                        x+=1;
                    }
                    riga=reader.readLine();
                    y+=1;
                }
            }
        }

        //getter
        public int getId() {
            return id;
        }

        public Collection<Gate> getGates() {
            return gates;
        }

        public void setOstacoli(String nome, int id){
            for (int i = 0; i < getLength(); i++) {
                for (int j = 0; j < getWidth(); j++) {
                    if (i == 0 || i == getLength() - 1 || j == 0 || j == getWidth() - 1) {
                        if (!invalidi.contains(new Punto(i, j))) {
                            invalidi.add(new Punto(i, j));
                            entita.add(new Ostacolo(nome + i + "-" + j, id, i, j));
                        }
                    }


                }
            }
        }
    }


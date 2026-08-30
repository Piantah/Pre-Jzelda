package JZ_Model_alpha.Entities.OtherEntities;

import JZ_Model_alpha.Entities.Entity;
import JZ_Model_alpha.Items.Fucile;
import JZ_Model_alpha.Items.Item;
import JZ_Model_alpha.Items.Staff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Mercante extends Entity {
    private static Mercante instance;
    private class OggettoInVendita{
        private int costo;
        private Item oggetto;
        public OggettoInVendita(int costo,Item oggetto){
            this.oggetto=oggetto;
            this.costo=costo;
        }

        public int getCosto() {
            return costo;
        }

        public Item getOggetto() {
            return oggetto;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

    }


    HashMap<OggettoInVendita,Integer> oggettiInVendita;

    private Mercante(){
        super();
        oggettiInVendita= new HashMap<>();
        restock(new Fucile("Fucile",100,this), 20,1);
        restock(new Staff("Staff",this), 300,1);

    }
    private Mercante(int x,int y){
        super(x,y);
        oggettiInVendita= new HashMap<>();
        restock(new Fucile("Fucile",100,this), 30,1);
        restock(new Staff("Staff",this), 300,1);

    }
    public static Mercante getInstance(int x, int y){
        if(instance==null)instance=new Mercante(x,y);
        return instance;
    }

    public void restock(Item i, int prezzo, Integer q){
        OggettoInVendita o = new OggettoInVendita(prezzo,i);
        if(oggettiInVendita.containsKey(o)){
            oggettiInVendita.put(o,oggettiInVendita.get(o)+q);
        }
        else{
            oggettiInVendita.putIfAbsent(o,q);
        }
    }

    public Item compraItem(String nomeI,int soldi){
        for(OggettoInVendita o: oggettiInVendita.keySet()){
            if(o.getOggetto().getNomeItem().equals(nomeI)){
                if(o.getCosto()<=soldi){
                    if(oggettiInVendita.get(o)>0){
                        oggettiInVendita.replace(o,oggettiInVendita.get(o)-1);
                        return o.getOggetto();
                    }
                }
            }

        }
        return null;
    }

    public int getPrezzoItem(String nomeI){
        for(OggettoInVendita o: oggettiInVendita.keySet()){
            if(o.getOggetto().getNomeItem().equals(nomeI)){
                if(oggettiInVendita.get(o)>0) {
                    return o.getCosto();
                }
            }
        }
        return 0;
    }

    public Collection<Item> getSTockedItems(){
        Collection<Item> ret = new ArrayList<>();
        for(OggettoInVendita o: oggettiInVendita.keySet()){
            if(oggettiInVendita.get(o)>0) {
                ret.add(o.getOggetto());
            }
        }
        return ret;
    }






}

package JZ_Model.Entities.OtherEntities;

import JZ_Model.Entities.Entita;
import JZ_Model.Items.Fucile;
import JZ_Model.Items.Item;
import JZ_Model.Items.Staffa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Mercante extends Entita {
    //il mercante deve essere un SingleTon
    private static Mercante instance;
    private HashMap<OggettoInVendita,Integer> oggettiInVendita;

    //classe di supporto per lo shop del mercante
    private static class OggettoInVendita{
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

    //costruttore e singleton
    private Mercante(int x,int y){
        super(x,y);
        oggettiInVendita= new HashMap<>();
        restock(new Fucile("Fucile",100,this), 30,1);
        restock(new Staffa("Staffa",this), 300,1);

    }
    public static Mercante getInstance(int x, int y){
        if(instance==null)instance=new Mercante(x,y);
        return instance;
    }

    public void restock(Item i, int prezzo, Integer q){
        OggettoInVendita o = new OggettoInVendita(prezzo, i);
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

    public Collection<Item> getOggettiDisponibili(){
        Collection<Item> ret = new ArrayList<>();
        for(OggettoInVendita o: oggettiInVendita.keySet()){
            if(oggettiInVendita.get(o)>0) {
                ret.add(o.getOggetto());
            }
        }
        return ret;
    }






}

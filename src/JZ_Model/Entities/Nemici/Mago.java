package JZ_Model.Entities.Nemici;

import JZ_Model.Entities.Proiettili.Proiettile;
import JZ_Model.Entities.Proiettili.ProiettileNemico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Mago extends Nemico {
    private Collection<Proiettile> proiettili;

    public Mago(String nome, int id) {
        super(nome, id);
        setValoreDrop(15);
        setXpDati(10);
    }

    public Mago(String nome, int id, int x, int y) {
        super(nome, id, x, y);
        setValoreDrop(15);
        setXpDati(10);
    }



    //post fix dell'hus rendilo void
    public void attacca(){
        Random r = new Random();
        proiettili=new ArrayList<>();
        if(((r.nextInt(6)+1) < 5)){
            proiettili.add(new ProiettileNemico(this,5,2,1));

        }
        else{
            switch (getDirezione()){
                case DESTRA ->  {
                    proiettili.add(new ProiettileNemico(this,4,1,2,getxCord()+1,getyCord()));
                    proiettili.add(new ProiettileNemico(this,4,1,2,getxCord()+1,getyCord()+1));
                    proiettili.add(new ProiettileNemico(this,4,1,2,getxCord()+1,getyCord()-1));
                }
                case SINISTRA ->  {
                    proiettili.add(new ProiettileNemico(this,4,1,2,getxCord()-1,getyCord()));
                    proiettili.add(new ProiettileNemico(this,4,1,2,getxCord()-1,getyCord()+1));
                    proiettili.add(new ProiettileNemico(this,4,1,2,getxCord()-1,getyCord()-1));
                }
                case GIU ->  {
                    proiettili.add(new ProiettileNemico(this,4,1,2,getxCord(),getyCord()+1));
                    proiettili.add(new ProiettileNemico(this,4,1,2,getxCord()-1,getyCord()+1));
                    proiettili.add(new ProiettileNemico(this,4,1,2,getxCord()+1,getyCord()+1));
                }
                case SU -> {
                    proiettili.add(new ProiettileNemico(this,4,1,2,getxCord(),getyCord()-1));
                    proiettili.add(new ProiettileNemico(this,4,1,2,getxCord()-1,getyCord()-1));
                    proiettili.add(new ProiettileNemico(this,4,1,2,getxCord()+1,getyCord()-1));

                }
            }
        }
    }


    public Collection<Proiettile> getProiettili() {
        return proiettili;
    }
}

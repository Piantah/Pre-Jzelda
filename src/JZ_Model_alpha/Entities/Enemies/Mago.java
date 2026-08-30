package JZ_Model_alpha.Entities.Enemies;

import JZ_Model_alpha.Entities.Bullets.Bullet;
import JZ_Model_alpha.Entities.Bullets.EnemyShot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Mago extends Enemy{
    private Collection<Bullet> proiettili;

    public Mago(String nome, int id) {
        super(nome, id);
        setValue_drop(15);
        setXpOnKill(10);
    }

    public Mago(String nome, int id, int x, int y) {
        super(nome, id, x, y);
        setValue_drop(15);
        setXpOnKill(10);
    }



    //post fix dell'hus rendilo void
    public void attacca(){
        Random r = new Random();
        proiettili=new ArrayList<>();
        if(((r.nextInt(6)+1) < 5)){
            proiettili.add(new EnemyShot(this,5,2,1));

        }
        else{
            switch (getDirezione()){
                case RIGHT ->  {
                    proiettili.add(new EnemyShot(this,4,1,2,getxCord()+1,getyCord()));
                    proiettili.add(new EnemyShot(this,4,1,2,getxCord()+1,getyCord()+1));
                    proiettili.add(new EnemyShot(this,4,1,2,getxCord()+1,getyCord()-1));
                }
                case LEFT ->  {
                    proiettili.add(new EnemyShot(this,4,1,2,getxCord()-1,getyCord()));
                    proiettili.add(new EnemyShot(this,4,1,2,getxCord()-1,getyCord()+1));
                    proiettili.add(new EnemyShot(this,4,1,2,getxCord()-1,getyCord()-1));
                }
                case DOWN ->  {
                    proiettili.add(new EnemyShot(this,4,1,2,getxCord(),getyCord()+1));
                    proiettili.add(new EnemyShot(this,4,1,2,getxCord()-1,getyCord()+1));
                    proiettili.add(new EnemyShot(this,4,1,2,getxCord()+1,getyCord()+1));
                }
                case UP -> {
                    proiettili.add(new EnemyShot(this,4,1,2,getxCord(),getyCord()-1));
                    proiettili.add(new EnemyShot(this,4,1,2,getxCord()-1,getyCord()-1));
                    proiettili.add(new EnemyShot(this,4,1,2,getxCord()+1,getyCord()-1));

                }
            }
        }
    }


    public Collection<Bullet> getProiettili() {
        return proiettili;
    }
}

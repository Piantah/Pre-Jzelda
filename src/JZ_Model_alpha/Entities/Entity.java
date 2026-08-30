package JZ_Model_alpha.Entities;

public abstract class Entity {
    private int xCord;
    private int yCord;
    private String nome;
    private int vita;
    private Facing direzione;
    //indica se è vivo o morto
    private int status;
    //indica se si sta "muovendo"
    private int move;
    private int last;

    //costruttori
    public Entity(){
        this.xCord=0;
        this.yCord=0;
        direzione=Facing.NONE;
        move=0;
        last=2;
    }
    public Entity(int x,int y){
        this.xCord=x;
        this.yCord=y;
        direzione=Facing.NONE;
        move=0;
        last=2;
    }


    //attacco
    public void attacca(){
          return;
    }

    //metodi di movimento
    public void moveLeft(int coef){
        this.xCord-=coef;
        direzione=Facing.LEFT;
        changemove();
    }
    public void moveRight(int coef){
        this.xCord+=coef;
        direzione=Facing.RIGHT;
        changemove();

    }
    public void moveUp(int coef){
        this.yCord-=coef;
        direzione=Facing.UP;
        changemove();

    }
    public void moveDown(int coef){
        this.yCord+=coef;
        direzione=Facing.DOWN;
        changemove();

    }

    //setter
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setVita(int vita) {
        this.vita = vita;
    }
    public void setxCord(int xCord) {
        this.xCord = xCord;
    }
    public void setyCord(int yCord) {
        this.yCord = yCord;
    }

    public void setDirezione(Facing direzione) {
        this.direzione = direzione;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    //metodi getter

    public void resetMove() {
        this.move = 0;
    }
    private void changemove() {
        if(move==0){
            if(last==1){
                move=last;
                last=2;
            }
            else{
                move=last;
                last=1;
            }
        }

        else if (move==2){
            last=move;
            move=1;
        }
        else if (move==1){
            last=move;
            move=2;
        }
        else resetMove();
    }

    public int getxCord() {
        return xCord;
    }
    public int getyCord() {
        return yCord;
    }
    public int getVita() {
        return vita;
    }
    public String getNome() {
        return nome;
    }

    public Facing getDirezione() {
        return direzione;
    }

    public int getMove() {
        return move;
    }

    public int getStatus() {
        return status;
    }
}

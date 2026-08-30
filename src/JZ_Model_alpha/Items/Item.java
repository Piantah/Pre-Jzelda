package JZ_Model_alpha.Items;

public abstract class Item {
    private String nomeItem;
    private int danniArrecati;
    Item(String nomeItem, int danniArrecati){

        this.nomeItem=nomeItem;
        this.danniArrecati=danniArrecati;
    }
    public void usa(){}

    public int getDanniArrecati() {
        return danniArrecati;
    }

    public String getNomeItem() {
        return nomeItem;
    }

}

package JZ_Controller;

import JZ_Model_alpha.GameModel;
import JZ_View.View;

public class JZelda {
    // =^.^=
    public static void main(String[] args) {
        GameModel modello = GameModel.getInstance();
        View view = new View();
        MainController c = new MainController(modello,view);
    }

}

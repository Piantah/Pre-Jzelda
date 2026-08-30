package JZ_Eccezioni;

public class MovimentoInvalidoGiocatore extends RuntimeException {
    public MovimentoInvalidoGiocatore() {
        super("Il player sta tentando un movimento impossibile");
    }
}

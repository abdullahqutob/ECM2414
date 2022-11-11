import java.util.ArrayList;

public class Player extends Thread {

    private int playerDenomination;
    private ArrayList<Card> hand = new ArrayList<Card>();


    public Player(int playerDenomination){
        this.playerDenomination = playerDenomination;
    }




}

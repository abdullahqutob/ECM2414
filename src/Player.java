import java.util.ArrayList;

public class Player implements Runnable {

    private int playerDenomination;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private int drawDeck;
    private int discardedDeck;


    public Player(int number){
        this.playerDenomination = number;
        this.drawDeck = number;
        if(number == 0) {           // 0 will be Number of players in game
            discardedDeck = 1;
        }
        else {
            this.discardedDeck = number + 1;
        }
    }

    @Override
    public void run() {

    }
}

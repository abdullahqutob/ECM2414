public class Card {

    // exception for wrong input type (string)

    private final int CardNum;

    public Card(int CardNum){
        this.CardNum = CardNum;
    }

    public synchronized int getValue(){
        return CardNum;
    }
}

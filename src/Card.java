public class Card {

    private final int CardNum;

    public Card(int CardNum){
        this.CardNum = CardNum;
    }

    public synchronized int getCardNum(){
        return CardNum;
    }
}

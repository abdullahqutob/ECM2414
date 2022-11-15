public class Card {

    private final int CardNum;

    /**
     * Card constructor
     * @param CardNum Value of the card
     */
    public Card(int CardNum) {
        this.CardNum = CardNum;
    }

    /**
     * @return Value of the card
     */
    public synchronized int getValue() {
        return CardNum;
    }

}

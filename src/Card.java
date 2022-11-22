/**
 *  Card object which will constitute game's pack as well as players' respective hands and decks
 */
public class Card {

    /** Value of card */
    private final int CardNum;

    /**
     * Creates card based on its value
     * @param CardNum Value of the card
     */
    public Card(int CardNum) {
        this.CardNum = CardNum;
    }

    /**
     *  Provides the value of card
     * @return Value of the card
     */
    public synchronized int getValue() {
        return CardNum;
    }

}

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Deck of cards to be used by two players, one as a draw pile and one as a discard pile.
 */
public class Deck {
    /**
     *  Stores cards of deck
     */
    ArrayList<Card> deck = new ArrayList<Card>();
    /**
     *  Number of deck
     */
    int Number;

    /**
     * Create the deck based on specified its number
     * @param deckNumber
     */
    public Deck(int deckNumber) {
        this.Number=deckNumber;
    }

    /**
     *  Provides number of deck
     * @return number of deck
     */
    public int getNumberOfDeck() {
        return Number;
    }

    /**
     * Provides number cards in deck
     * @return Number of cards in deck
     */
    public int deckLength() {
        return this.deck.toArray().length;
    }


    /**
     *  Provides card "on top" of pile
     * @return First card from deck
     */
    public synchronized Card drawCard () {
        if (this.deck.size() == 0) {
            return null;
        } else {
            Card firstCard = this.deck.get(0);
            this.deck.remove(0);
            return firstCard;
        }
    }

    /**
     * Inserts card into end of deck
     * @param card Card to be inserted
     */
    public synchronized void insertCard (Card card) {
        this.deck.add(deck.size(), card);
    }

    /**
     * Writes deck's current cards to output text file
     */
    public void logStatus() {

        String deckCards = "deck " + getNumberOfDeck() + " contains: [ ";
        for(Card card: deck) {
            deckCards += card.getValue() + " ";
        }
        deckCards += "]";
        try {
            FileWriter deckLogger = new FileWriter("deckOutput\\deck" + Number + "_output.txt");
            deckLogger.write(deckCards);
            deckLogger.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

}

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Deck {

    ArrayList<Card> deck = new ArrayList<Card>();
    int Number;

    /**
     * Create the deck
     * @param deckNumber
     */
    public Deck(int deckNumber) {
        this.Number=deckNumber;
    }

    public int getNumberofDeck() {
        return Number;
    }

    public int deckLength() {
        return this.deck.toArray().length;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
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

        String deckCards = "";
        for(Card card: deck) {
            deckCards += " " + card.getValue();
        }

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

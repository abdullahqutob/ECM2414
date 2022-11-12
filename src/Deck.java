import java.util.ArrayList;

public class Deck {

  ArrayList<Card> deck = new ArrayList<Card>();
  int Number;

  public Deck(int deckNumber){

      this.Number=deckNumber;
      this.deck = deck;
  }

    /**
     *
     * @param card
     * @return first card from deck
     */
  public synchronized Card drawCard (Card card){
    //TODO: add empty deck thread handling (wait)
      Card firstCard = this.deck.get(0);
      this.deck.remove(0);
      return firstCard;
  }

    /**
     * Inserts card into end of deck
     * @param card
     */
    public void insertCard (Card card){
        this.deck.add(deck.size()-1, card);
    }
    public int getNumberofDeck() {
        return Number;
  }






    public ArrayList<Card> getDeck() {
        return deck;
    }
}

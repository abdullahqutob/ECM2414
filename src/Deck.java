import java.util.ArrayList;

public class Deck {

  ArrayList<Card> deck = new ArrayList<Card>();
  int Number;


  public Deck(int deckNumber){

      this.Number=deckNumber;
  }

    /**
     * @return first card from deck
     */
  public synchronized Card drawCard (){
      //TODO: add thread handling for empty deck (wait)
      Card firstCard = this.deck.get(0);
      this.deck.remove(0);
      return firstCard;
  }

    /**
     * Inserts card into end of deck
     * @param card Card to be inserted
     */
    public void insertCard (Card card){
        this.deck.add(deck.size(), card);
    }

    public void removeCard(Card card){
        this.deck.remove(card);
    }

    public int getNumberofDeck() {
        return Number;
  }

    public int deckLength(){
        return this.deck.toArray().length;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}

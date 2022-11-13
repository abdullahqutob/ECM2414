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
    public synchronized void insertCard (Card card){
        this.deck.add(deck.size(), card);
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

import java.util.ArrayList;

public class Deck {

  ArrayList<Card> deck = new ArrayList<Card>();
  int Number;

  public Deck(int deckNumber,ArrayList<Card>deck){

      this.Number=deckNumber;
      this.deck = deck;
  }

  public synchronized Card drawCard (Card card){

      Card firstCard = this.deck.get(0);
      return firstCard;
  }

    public int getNumberofDeck() {
        return Number;
  }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}

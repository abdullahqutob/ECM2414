import java.util.ArrayList;

public class Deck {

  ArrayList<Card> deck = new ArrayList<Card>();
  int numOfCards;

  public Deck(int numOfCards,ArrayList<Card>deck){
      this.numOfCards=numOfCards;
      this.deck = deck;
  }

  public synchronized Card drawCard (Card card){

      Card firstCard = this.deck.get(0);
      return firstCard;
  }

    public int getNumOfCards() {
        return numOfCards;
  }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}

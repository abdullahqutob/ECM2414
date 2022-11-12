import java.util.ArrayList;
import java.util.Random;

public class Player implements Runnable {

    private int playerDenomination;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private int drawDeck;
    private int discardedDeck;

    /**
     *
     * @param number
     */

    public Player(int number){
        this.playerDenomination = number;
        this.drawDeck = number;
        if(number == 0) {           // 0 will be Number of players in game
            discardedDeck = 1;
        }
        else {
            this.discardedDeck = number + 1;
        }
    }

    /**
     *
     * @param deckNumber
     * @return
     */
    public Deck selectDeck(int deckNumber)
    {
     for (Deck deck: CardGame.decks)
     {
         if(deck.getNumberofDeck() == deckNumber){
             return deck;
         }

     }

     return null;
    }

    /**
     * Mechanism which allows player to add card to hand and remove another.
     * @param newCard
     * @return
     */
    public Card addRemoveCard(Card newCard)
    {
        this.hand.add(newCard);
        boolean preferredCard = true;
        Card removedCard =  new Card(0);
        Random randomIndexGenerator = new Random();

        while (preferredCard == true){

            int randomIndex = (int) Math.floor(randomIndexGenerator.nextInt(5));
            if (this.hand.get(randomIndex).getCardNum()!=this.playerDenomination){
                preferredCard = false;
                removedCard = this.hand.get(randomIndex);
                this.hand.remove(randomIndex);

            }
        }
        return removedCard;
    }

    /**
     * Checks if player has won game
     * @return true or false
     */
    boolean hasPlayerWon(){
        for(int i=0;i<hand.size();i++){
            if(this.hand.get(i).getCardNum()!=playerDenomination) return false;
        }
        return true;
    }


    @Override
    public void run() {}


}

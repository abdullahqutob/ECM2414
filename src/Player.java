import java.util.ArrayList;
import java.util.Random;

public class Player implements Runnable {

    /**
     *  The denomination of player
     */
    private int playerDenomination;
    /**
     *  Hand of player, consisting of cards
     */
    private ArrayList<Card> hand = new ArrayList<Card>();
    /**
     *  Number of deck which player draws from.
     */
    private int drawDeck;
    /**
     *  Number of deck in which player discards cards.
     */
    private int discardedDeck;

    /**
     *  Constructor which creates player object
     *
     * @param number The denomination of the player
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

    public int getPlayerDenomination() {
        return playerDenomination;
    }

    /**
     * Mechanism to find particular deck number.
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
     *  Player reports moves
     */
    public void log(Card card){
        System.out.println("Player "+ getPlayerDenomination() + "draws a "+card.getCardNum() + "from "+ discardedDeck);

    }

    /**
     * Mechanism which allows player to add one card and remove another from hand.
     * @param newCard
     * @return
     */
    public Card addAndRemoveCard(Card newCard)
    {
        this.hand.add(newCard); //add card to hand
        boolean preferredCard = true; //
        Card removedCard =  new Card(0);
        Random randomIndexGenerator = new Random();

        while (preferredCard && !hasPlayerWon()){

            int randomIndex = (int) Math.floor(randomIndexGenerator.nextInt(5));
            if (this.hand.get(randomIndex).getCardNum()!=this.playerDenomination){
                preferredCard = false;
                removedCard = this.hand.get(randomIndex);
                this.hand.remove(randomIndex);

                // Remove card drawn from draw deck
                selectDeck(drawDeck). removeCard(removedCard);
                // Add discarded card to discard deck
                selectDeck(discardedDeck).insertCard(removedCard);
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

import java.io.FileWriter;
import java.io.IOException;
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

    private String getHand() {
       String output = "";
       for(int i=0; i<hand.size();i++){
           output+= hand.get(i).toString() + " ";
       }

       return output;
    }

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
    public synchronized Deck selectDeck(int deckNumber)
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
     *  Player n reports moves to respective text file in 'playerOutput' file
     */
    public void log (Card newCard, Card oldCard) throws IOException {
        try {
            FileWriter playerLogger = new FileWriter(("playerOutput\\player" + this.playerDenomination + "_output.txt"));
            playerLogger.write("Player " + getPlayerDenomination() + "draws a " + newCard.getValue() + "from " +
                    discardedDeck + '\n');
            playerLogger.write("Player discards a " + oldCard.getValue()+ '\n');
            playerLogger.write("Player " + getPlayerDenomination() + "current hand is " + getHand()+ '\n');
            playerLogger.close();

        }
        catch (IOException exception){
            System.out.println(exception);
        }
    }

    /**
     * Mechanism which allows player to add one card and remove another from hand; interacts with draw and discard pile.
     * @param newCard Card drawn by player
     * @return Card discarded by player
     */
    public Card addAndRemoveCards(Card newCard) throws IOException {
        this.hand.add(newCard); //add card to hand
        boolean preferredCard = true; //
        Card removedCard =  new Card(0);
        Random randomIndexGenerator = new Random();

        while (preferredCard){

            int randomIndex = (int) Math.floor(randomIndexGenerator.nextInt(5));
            if (this.hand.get(randomIndex).getValue()!=this.playerDenomination){
                preferredCard = false;
                removedCard = this.hand.get(randomIndex);
                this.hand.remove(randomIndex);

                // Remove card drawn from draw deck
                selectDeck(drawDeck). removeCard(removedCard);
                // Add discarded card to discard deck
                selectDeck(discardedDeck).insertCard(removedCard);

                log(newCard,removedCard);

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
            if(this.hand.get(i).getValue()!=playerDenomination) return false;
        }
        return true;
    }

    /**
     *
     * @param winningPlayerNumber Log player's end game state: win or loss
     * @throws IOException
     */
    public void playerWin(int winningPlayerNumber) throws IOException {
        try {
            FileWriter playerLogger = new FileWriter(("playerOutput\\player" + this.playerDenomination + "_output.txt"));
            // If given player object wins
            if (winningPlayerNumber == playerDenomination) {
                playerLogger.write("player " + playerDenomination + "wins" + '\n');
                playerLogger.write("player " + playerDenomination + "exits" + '\n');
                playerLogger.write("player " + playerDenomination + "hand: " + getHand() + '\n');
            }
            // If another player wins
            else {
                playerLogger.write("player" + +winningPlayerNumber + "has informed player " + playerDenomination +
                        "that player" + winningPlayerNumber + "has won" + '\n');
                playerLogger.write("player " + playerDenomination + "exits" + '\n');
                playerLogger.write("player " + playerDenomination + "hand: " + getHand() + '\n');
            }
            playerLogger.close();
        }
        catch (IOException exception){
            System.out.println(exception);
        }
    }
    @Override
    public void run() {

        try {
            FileWriter playerLogger = new FileWriter(("playerOutput\\player" + this.playerDenomination + "_output.txt"));
            playerLogger.write("player " + playerDenomination + " initial hand: " + getHand() + '\n');
            playerLogger.close();

            while (!hasPlayerWon()){
                //Card Game winner private boolean field is now true
                // Call Card game winner function which goes through all players and determines which one is winner


            }




        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }


}

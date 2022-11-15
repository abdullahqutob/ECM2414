import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Player extends Thread {

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
           output+= hand.get(i).getValue() + " ";
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
        if(number == CardGame.numOfPlayers) {           // 0 will be Number of players in game
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
            FileWriter playerLogger = new FileWriter(("playerOutput\\player" +
                    this.playerDenomination + "_output.txt"),true);
            playerLogger.write("Player " + getPlayerDenomination() + " draws a " + newCard.getValue() + " from " +
                    discardedDeck + '\n');
            playerLogger.write("Player discards a " + oldCard.getValue()+ '\n');
            playerLogger.write("Player " + getPlayerDenomination() + " current hand is " + getHand()+ '\n');

            // Test
            System.out.println("Player " + getPlayerDenomination() + " draws a " + newCard.getValue() + " from " +
                    discardedDeck + '\n');
            System.out.println("Player discards a " + oldCard.getValue()+ '\n');
            System.out.println("Player " + getPlayerDenomination() + " current hand is " + getHand()+ '\n');



            playerLogger.close();

        }
        catch (IOException exception){
            System.out.println(exception);
        }
    }

    /**
     * Mechanism which allows player to add one card and remove another from hand; interacts with draw and discard pile.
     *
     * @return Card discarded by player
     */
    public void addAndRemoveCards() throws IOException {

        // Take card drawn from draw deck
       Card drawnCard = selectDeck(drawDeck).drawCard();
       // As long as draw deck is not empty
        if (drawnCard != null) {
            this.hand.add(drawnCard); //add card to hand
            boolean preferredCard = true;
            //
            Card removedCard =  new Card(0);
            Random randomIndexGenerator = new Random();

            // Check if card has preferred value or not
            // If not, keep going through cards in hand until you find one that isn't
            // Winning conditions are always checked before player takes turn so will not be stuck in infinite loop
            while (preferredCard){

                int randomIndex = (int) Math.floor(randomIndexGenerator.nextInt(5));
                if (this.hand.get(randomIndex).getValue()!=this.playerDenomination){
                    preferredCard = false;
                    removedCard = this.hand.get(randomIndex);
                    this.hand.remove(randomIndex);

                    // Add discarded card to discard deck
                    selectDeck(discardedDeck).insertCard(removedCard);

                    log(drawnCard,removedCard);

                }


            }



        }

    }

    /**
     * Adds card to hand during start of game
     * @param card
     */
    public void addCardToHand(Card card){
        this.hand.add(card);
    }

    
    /**
     * Checks if player has won the game
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
    public void winDeclaration (int winningPlayerNumber) throws IOException {
        try {
            FileWriter playerLogger = new FileWriter(("playerOutput\\player" +
                    this.playerDenomination + "_output.txt"),true);
            // If given player object wins
            if (winningPlayerNumber == playerDenomination) {
                playerLogger.write("Player " + playerDenomination + " wins" + '\n');
                playerLogger.write("Player " + playerDenomination + " exits" + '\n');
                playerLogger.write("Player " + playerDenomination + " hand: " + getHand() + '\n');
            }
            // If another player wins
            else {
                playerLogger.write("Player " + +winningPlayerNumber + " has informed player " + playerDenomination +
                        " that player " + winningPlayerNumber + " has won" + '\n');
                playerLogger.write("Player " + playerDenomination + " exits" + '\n');
                playerLogger.write("Player " + playerDenomination + " hand: " + getHand() + '\n');
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
            FileWriter playerLogger = new FileWriter(("playerOutput\\player" +
                    this.playerDenomination + "_output.txt"),true);
            playerLogger.write("Player " + playerDenomination + " initial hand: " + getHand() + '\n');
            playerLogger.close();


            while (!CardGame.winner){
                // Checks if player satisfies winning conditions
                if(this.hasPlayerWon()){
                    //Card Game winner private boolean field is now true
                    CardGame.winner = true;
                    // Call Card game winner function which goes through all players and determines which one is winner
                    CardGame.checkForWinner(this.playerDenomination);
                    break;
                }
                addAndRemoveCards();
            }

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

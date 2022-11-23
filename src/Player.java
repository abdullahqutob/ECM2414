import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Player extends Thread {

    /** The denomination of player */
    private final int playerDenomination;

    /** Hand of player, consisting of cards */
    final ArrayList<Card> hand = new ArrayList<Card>();

    /** Number of deck which player draws from. */
    private final int drawDeck;

    /** Number of deck in which player discards cards. */
    private final int discardedDeck;

    /**
     * Creates player object based on its denomination
     * @param number The denomination of the player
     */
    public Player(int number) {
        this.playerDenomination = number;
        this.drawDeck = number;
        if(number == CardGame.numOfPlayers) {           // Player 0's deck will be numOfPlayers
            discardedDeck = 1;
        }
        else {
            this.discardedDeck = number + 1;
        }
    }

    /**
     * @return The cards in the player's hand
     */
    private String getHand() {
        String output = "";
        for(int i=0; i<hand.size();i++) {
            output+= hand.get(i).getValue() + " ";
        }
        return output;
    }

    /**
     * Provides player's denomination
     * @return player denomination
     */
    public int getPlayerDenomination() {
        return playerDenomination;
    }

    /**
     * Mechanism to find specified deck based on its number.
     * @param deckNumber
     * @return Specified deck
     */
    public synchronized Deck selectDeck(int deckNumber) {
        for (Deck deck: CardGame.decks) {
            if(deck.getNumberOfDeck() == deckNumber) {
                return deck;
            }
        }
        return null;
    }

    /**
     * Adds card to hand during start of game
     * @param card To be added to hand
     */
    public void addCardToHand(Card card) {
        this.hand.add(card);
    }

    /**
     * Adds card to hand during start of game
     * @param card To be removed from hand
     */
    public void removeCardFromHand(int card) {
        this.hand.remove(card);
    }

    /**
     *  Player n reports moves to respective text file in 'playerOutput' file
     */
    public void log (Card newCard, Card oldCard) throws IOException {
        try {
            FileWriter playerLogger = new FileWriter(("playerOutput\\player" +
                    this.playerDenomination + "_output.txt"),true);
            playerLogger.write("Player " + getPlayerDenomination() + " draws a " + newCard.getValue() + " from deck " +
                    drawDeck + '\n');
            playerLogger.write("Player " + getPlayerDenomination() + " discards a " + oldCard.getValue() + " to deck " + discardedDeck + '\n');
            playerLogger.write("Player " + getPlayerDenomination() + " current hand is " + getHand() + '\n' + '\n');

            // Test
            System.out.println("Player " + getPlayerDenomination() + " draws a " + newCard.getValue() + " from deck " +
                    drawDeck + '\n');
            System.out.println("Player " + getPlayerDenomination() + " discards a " + oldCard.getValue() + " to deck " + discardedDeck + '\n');
            System.out.println("Player " + getPlayerDenomination() + " current hand is " + getHand()+ '\n');


            playerLogger.close();

        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Mechanism which allows player to add one card and remove another from hand;
     * interacts with draw and discard pile.
     * @return Card discarded by player
     */
    public void addAndRemoveCards() throws IOException {

        // Take card drawn from draw deck
       Card drawnCard = selectDeck(drawDeck).drawCard();
       // As long as draw deck is not empty
        if (drawnCard != null) {
            addCardToHand(drawnCard);

            boolean preferredCard = true;
            Card removedCard;
            Random randomIndexGenerator = new Random();

            // Check if card has preferred value or not
            // If not, keep going through cards in hand until you find one that isn't
            // Winning conditions are always checked before player takes turn so will not be stuck in infinite loop
            while (preferredCard) {

                int randomIndex = (int) Math.floor(randomIndexGenerator.nextInt(5));
                // if the random card is not a players preferred card -> remove card
                if (this.hand.get(randomIndex).getValue()!=this.playerDenomination) {
                    preferredCard = false;
                    removedCard = this.hand.get(randomIndex);
                    removeCardFromHand(randomIndex);

                    // Add discarded card to discard deck
                    selectDeck(discardedDeck).insertCard(removedCard);

                    log(drawnCard,removedCard);

                }
            }

        }
    }

    /**
     * Checks if player has won the game
     * @return true or false
     */
    boolean hasPlayerWon() {
        for(int i=0;i<hand.size();i++) {
            if(this.hand.get(i).getValue()!=playerDenomination) return false;
        }
        return true;
    }

    /**
     * The winning player declares to all players that it has won,
     * and writes to its file that it has won.
     * Losing players log to their files the player that won
     * @param winningPlayerNumber Log player's end game state: win or loss
     * @throws IOException Error writing to text file
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
        catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     *  Implements players' behaviour in game
     */
    @Override
    public void run() {

        try {
            FileWriter playerLogger = new FileWriter(("playerOutput\\player" +
                    this.playerDenomination + "_output.txt"),true);
            playerLogger.write("Player " + playerDenomination + " initial hand: " + getHand() + '\n');
            playerLogger.close();


            while (!CardGame.winner) {
                // Checks if player satisfies winning conditions
                if(this.hasPlayerWon()) {
                    //Card Game winner private boolean field is now true
                    CardGame.winner = true;
                    // Call Card game winner function which goes through all players and determines which one is winner
                    CardGame.declareWinner(this.playerDenomination);
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  Implementation of the actual card game
 */
public class CardGame {

    /**
     *  Number of players participating in game
     */
    static int numOfPlayers;
    /**
     *  The pack of cards used in game
     */
    static ArrayList<Card> cardsPack = new ArrayList<>();
    /**
     *  Stores the players of the game
     */
    static ArrayList<Player> players = new ArrayList<>();
    /**
     * Stores decks of respective players
     */
    static ArrayList<Deck> decks = new ArrayList<>();
    /**
     *  Status of game, win or loss
     */
    static boolean winner = false;
    /**
     *  Used to ensure valid player input
     */

    static boolean validPlayerInput = false;

    /**
     * Requests the user to input the number of players,
     * then validates the input
     */
    public static void requestNumOfPlayers() {

        while (!validPlayerInput) {
            try {
                Scanner playersInput = new Scanner(System.in);
                System.out.println("Please enter the number of players: ");
                int tempPlayers = playersInput.nextInt();
                if (tempPlayers < 2) {
                    System.out.println(Pack.ANSI_RED + "Input is out of range, Must be greater than 2" + Pack.ANSI_RESET);
                } else {
                    validPlayerInput = true;
                    numOfPlayers = tempPlayers;
                }
            } catch (InputMismatchException e) {
                System.out.println(Pack.ANSI_RED + "Invalid input, please try again" + Pack.ANSI_RESET);
            }
        }
    }

    /**
     * Distribute cards from pack to player hands
     */
    public static void distributeCardsToPlayers() {
        for (int i = 0; i < cardsPack.size() / 2; i++) {
            players.get(i % numOfPlayers).addCardToHand(cardsPack.get(i));
        }
    }

    /**
     * Distribute cards from pack to deck
     */
    public static void distributeCardsToDecks() {
        for (int i = cardsPack.size() / 2; i < cardsPack.size(); i++) {
            decks.get(i % numOfPlayers).insertCard(cardsPack.get(i));
        }
    }

    /**
     * Flush old files for game re-run
     */
    private static void clearFiles() {
        File dir1 = new File("playerOutput");
        File dir2 = new File("deckOutput");

        //if playerOutput directory exists -> delete files
        if (dir1.exists()) {
            for (File file : new File("playerOutput").listFiles()) {
                file.delete();
            }
        }

        //if deckOutput directory exists -> delete files
        if (dir2.exists()) {
            for (File file : new File("deckOutput").listFiles()) {
                file.delete();
               }
       }

   }

    /**
     *  Create player files
     */
    private static void createPlayerFiles() {

        // Create directory for playerOutput files
        File playerOutputDirectory = new File("playerOutput");
        playerOutputDirectory.mkdir();

        for (Player player : players) {
            try {
                File newFile = new File(playerOutputDirectory, "player" +
                        player.getPlayerDenomination() + "_output.txt");
                // Create new empty file for loop
                newFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
           }
        }

    }

    /**
     *  Create deck files
     */
    private static void createDeckFiles() {

        // Create directory for playerOutput files
        File deckOutputDirectory = new File("deckOutput");
        deckOutputDirectory.mkdir();

        // Create deck files for respective decks
        for (Deck deck : decks) {
            try {
                File newFile = new File(deckOutputDirectory, "deck" + deck.getNumberofDeck() + "_output.txt");
                newFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

    /**
     * Create files for game start-up
     */
    public static void fileCreator() {
        clearFiles();
        createPlayerFiles();
        createDeckFiles();
    }

    /**
     * Starts Player threads
     */
    public static void startPlayers() {
        for(Player player:players) {
            player.start();
        }
    }

    /**
     * Stops all players and sends final messages
     * @param playerDenomination Player that has won
     * @throws IOException
     */
    public static void checkForWinner(int playerDenomination) throws IOException {

        for (Player player : players) {
            // Stops all players threads from continuing
            player.interrupt();
            // Declaration that player has won to itself and all other players
            player.winDeclaration(playerDenomination);
        }

        for (Deck deck : decks) {
            deck.logStatus();
        }

        System.out.println("Player " + playerDenomination + " Won!");

    }

    /**
     * Instantiates player and deck objects
     */
    public static void createPlayersAndDecks() {
        for (int i = 1; i <= numOfPlayers; i++) {

            Player newPlayer = new Player(i);
            players.add(newPlayer);

            Deck newDeck = new Deck(i);
            decks.add(newDeck);
        }
    }

    /**
     * Launches the game
     */
    public static void launchGame() {

        // Input and File setup
        requestNumOfPlayers();
        Pack.requestPackFile();

        // Create players
        createPlayersAndDecks();

        // Create files for Player and Deck logs
        fileCreator();

        // Distribute cards as per rules
        distributeCardsToPlayers();
        distributeCardsToDecks();

        // Play the game!
        startPlayers();

    }

    public static void main(String[] args) {
        launchGame();
    }

}

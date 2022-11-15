import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CardGame {

    static int numOfPlayers;
    static ArrayList<Card> cardsPack = new ArrayList<>();
    static ArrayList<Player> players = new ArrayList<>();
    static ArrayList<Deck> decks = new ArrayList<>();
    static boolean winner = false;


    static boolean validPlayerInput = false;
    public static void requestUserInput() {

        while (!validPlayerInput) {
            try {
                Scanner playersInput = new Scanner(System.in);
                System.out.println("Please enter the number of players: ");
                int tempPlayers = playersInput.nextInt();
                if (tempPlayers < 2) {
                    System.out.println("Input is out of range, Must be greater than 2");
                } else {
                    validPlayerInput = true;
                    numOfPlayers = tempPlayers;
                }


            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again");
            }

        }

    }


    // distribute cards from pack to player hands
    public static void distributeCardsToPlayers() {
        for (int i = 0; i < cardsPack.size() / 2; i++) {
            players.get(i % numOfPlayers).addCardToHand(cardsPack.get(i));
        }

    }

    // distribute cards from pack to deck
    public static void distributeCardsToDecks() {
        for (int i = cardsPack.size() / 2; i < cardsPack.size(); i++) {
            decks.get(i % numOfPlayers).insertCard(cardsPack.get(i));
        }
    }


    public static void main(String[] args) {
       // LaunchGame();


    }


    /**
     * Flush old files for game re-run
     */
   private static void clearFiles() {
       File dir1 = new File("playerOutput");
       File dir2 = new File("deckOutput");

        //if directory exists -> delete files
       if (dir1.exists()){
           for (File file : new File("playerOutput").listFiles()) {
               file.delete();
           }
       }

       if (dir2.exists()){
           for (File file : new File("deckOutput").listFiles()) {
               file.delete();

           }
       }

   }

    /**
     *  Create player files
     */
   private static void createPlayerFiles() {

       for (Player player : players) {
           try {
               File dir1 = new File("playerOutput");
               dir1.mkdir();
               File newFile = new File(dir1, "player" + player.getPlayerDenomination() + "_output.txt");
               // Create new empty file for loop
               newFile.createNewFile();
           }
           catch (IOException e) {
               System.out.println(e);
           }
       }

   }

    /**
     *  Create deck files
     */
   private static void createDeckFiles() {
       // Create deck files for respective decks
       File dir2 = new File("deckOutput");
       dir2.mkdir();
       for (Deck deck : decks) {
           try {
               File newFile = new File(dir2, "deck" + deck.getNumberofDeck() + "_output.txt");
               newFile.createNewFile();
           } catch (IOException e) {
               System.out.println(e);
           }
       }
   }

    // static ?

    /**
     * Create files for game start-up
     */
    public static void fileCreator()
   {
                clearFiles();
                createPlayerFiles();
                createDeckFiles();
   }



    /**
     *  Starts Player threads
     */
    public static void startPlayers()
    {
        for(Player player:players){
            player.start();
        }
    }


    public static void checkForWinner(int playerDenomination) throws IOException {
        for (Player player : players) {
            // Stops all players from continuing
            player.interrupt();
            // Declaration that player has won to itself and all other players
            player.winDeclaration(playerDenomination);
        }
        for (Deck deck : decks) {
            deck.logStatus();
        }
        System.out.println("Player " + playerDenomination + " Won!");
    }

    public static void createPlayersAndDecks()
    {
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
        requestUserInput();
        Pack.requestPackFile();


        // Create players
        createPlayersAndDecks();
//        fileCreator();

        // Create files for Player and Deck logs
        fileCreator();

        // Distribute cards as per rules
        distributeCardsToPlayers();
        distributeCardsToDecks();

        // Play the game!
        startPlayers();

    }
}

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
    static Pack pack = new Pack();
    static boolean winner = false;
    static int numOfDecks = numOfPlayers;


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
    public void distributeCardsToPlayers() {
        for (int i = 0; i < cardsPack.size() / 2; i++) {
            players.get(i % numOfPlayers).addCardToHand(cardsPack.get(i));
        }

    }

    // distribute cards from pack to deck
    public void distributeCardsToDecks() {
        for (int i = cardsPack.size() / 2; i < cardsPack.size(); i++) {
            decks.get(i % numOfPlayers).insertCard(cardsPack.get(i));
        }
    }


    public static void main(String[] args) {
       // LaunchGame();


    }

    public void LaunchGame() throws FileNotFoundException {
        requestUserInput();
        Pack.requestPackFile();
        // instantiate players
        // instantiate decks
        distributeCardsToPlayers();
        distributeCardsToDecks();

}

    /**
     * Flush old files for game re-run
     */
   private void clearFiles() {

       for (File file : new File("playerOutput").listFiles()) {
           file.delete();
       }


       for (File file : new File("deckOutput").listFiles()) {
           file.delete();

       }

   }

    /**
     *  Create player files
     */
   private void createPlayerFiles() {

       for (Player player : players) {
           try {
               File newFile = new File("playerOutput\\player" + player.getPlayerDenomination() +
                       "_output.txt");
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
   private void createDeckFiles() {
       // Create deck files for respective decks
       for (Deck deck : decks) {
           try {
               File newFile = new File("deckLogs\\deck" + deck.getNumberofDeck() + "_output.txt");
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
    public void fileCreator()
   {
                clearFiles();
               createPlayerFiles();
               createDeckFiles();
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
}

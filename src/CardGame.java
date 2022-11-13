import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {

    static int numOfPlayers;

    static ArrayList<Player> players = new ArrayList<>();
    static ArrayList<Deck> decks = new ArrayList<>();
    static Pack pack = new Pack();

    static boolean winner = false;

    static int numOfDecks = 0;


    static boolean playerRequested = false;
    public static int requestPlayerInput() {

        if (!playerRequested) {
            Scanner playersInput = new Scanner(System.in);
            System.out.println("Please enter the number of players: ");
            numOfPlayers = playersInput.nextInt();
            return numOfPlayers;
        } else {
            return  numOfPlayers;
        }
    }


    public static void main(String[] args) {
        LaunchGame();


    }

    public void LaunchGame() throws FileNotFoundException {
        requestPlayerInput();
        Pack.requestPackFile();


        pack.distributeCardsToPlayers();
        pack.distributeCardsToDecks();

}

    public static void winner(int playerNum) {
//        for (Player player : players) {
//            player.interrupt();
//            player.gameOver(playerNum);
//        }
//        for (CardDeck deck : decks) {
//            deck.logContent();
//        }
//        System.out.println("Player " + playerNum + " Won!");
    }
}
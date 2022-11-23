import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.Assert.*;

public class TestCardGame {

    /**
     * Tests the requesting of players
     */
    @Test
    public void testRequestNumOfPlayersStringInput() {
        System.out.println("\n" + "testRequestNumOfPlayersStringInput:");

        Pack pack = new Pack();
        String input = "invalid input";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        try {
            CardGame.requestNumOfPlayers();
        } catch (Exception e) {
            // exception thrown because the method recognized the input
            // is invalid, and requests it again, but there's no second input
            assertEquals(0, CardGame.numOfPlayers); // never updated
            System.out.println("Test was successful :)");
        }

    }

    /**
     * Tests the requesting of players
     */
    @Test
    public void testRequestNumOfPlayersValidInput() {
        System.out.println("\n" + "testRequestNumOfPlayersValidInput:");

        Pack pack = new Pack();
        String input = "5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        try {
            CardGame.requestNumOfPlayers();
            assertEquals(5, CardGame.numOfPlayers);
        } catch (Exception e) {
            System.out.println("Test failed");
        }

        System.out.println("Test was successful :)");
    }


    /**
     * Tests the requesting of players
     */
    @Test
    public void testRequestNumOfPlayersTooSmall() {
        System.out.println("\n" + "testRequestNumOfPlayersTooSmall:");


        Pack pack = new Pack();
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        try {
            CardGame.requestNumOfPlayers();
        } catch (Exception e) {
            assertEquals(0, CardGame.numOfPlayers); // never updated
            System.out.println("Test was successful :)");
        }

    }




    @Test
    public void testCreatePlayersAndDecks() {
        System.out.println("\n" + "testCreatePlayersAndDecks:");

        CardGame.numOfPlayers = 4;
        CardGame.createPlayersAndDecks();

        assertEquals(4, CardGame.players.size());
        assertEquals(CardGame.players.get(0).getPlayerDenomination(), 1);
        assertEquals(4, CardGame.decks.size());

        System.out.println("Test was successful :)");

        CardGame.numOfPlayers = 0; // reset for other tests
    }


    @Test
    public void testDistributeCardsToPlayersAndDecks() {
        System.out.println("\n" + "testDistributeCardsToPlayersAndDecks:");

        CardGame.numOfPlayers = 2;

        CardGame.players.add(new Player(1));
        CardGame.players.add(new Player(2));
        CardGame.decks.add(new Deck(1));
        CardGame.decks.add(new Deck(2));


        // to be given to players
        CardGame.cardsPack.add(new Card(1));
        CardGame.cardsPack.add(new Card(0));
        CardGame.cardsPack.add(new Card(2));
        CardGame.cardsPack.add(new Card(0));
        CardGame.cardsPack.add(new Card(3));
        CardGame.cardsPack.add(new Card(0));
        CardGame.cardsPack.add(new Card(4));
        CardGame.cardsPack.add(new Card(0));

        // to be given to decks
        CardGame.cardsPack.add(new Card(5));
        CardGame.cardsPack.add(new Card(0));
        CardGame.cardsPack.add(new Card(6));
        CardGame.cardsPack.add(new Card(0));
        CardGame.cardsPack.add(new Card(7));
        CardGame.cardsPack.add(new Card(0));
        CardGame.cardsPack.add(new Card(8));
        CardGame.cardsPack.add(new Card(0));

        CardGame.distributeCardsToPlayers();

        // the values of cards that player 1 received
        assertEquals(CardGame.players.get(0).hand.get(0).getValue(), 1 );
        assertEquals(CardGame.players.get(0).hand.get(1).getValue(), 2 );
        assertEquals(CardGame.players.get(0).hand.get(2).getValue(), 3 );
        assertEquals(CardGame.players.get(0).hand.get(3).getValue(), 4 );

        CardGame.distributeCardsToDecks();
        assertEquals(CardGame.decks.get(0).drawCard().getValue(), 5);
        assertEquals(CardGame.decks.get(0).drawCard().getValue(), 6);
        assertEquals(CardGame.decks.get(0).drawCard().getValue(), 7);
        assertEquals(CardGame.decks.get(0).drawCard().getValue(), 8);

        System.out.println("Test was successful :)");

        // reset for other tests
        CardGame.numOfPlayers = 0;
        CardGame.players.clear();
        CardGame.decks.clear();
    }


}

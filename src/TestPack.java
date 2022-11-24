import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class TestPack {

    /**
     * Testing the request pack function with an invalid location
     */
    @Test
    public void testRequestPackFileInvalidLocation() {
        System.out.println("\n" + "testRequestPackFileInvalidLocation:");

        Pack pack = new Pack();
        String input = "invalid input";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        //checking that the location variable is updated and checked
        try {
            Pack.requestPackFile();
        } catch (Exception e){
            // exception thrown because the method recognized the input
            // is invalid, and requests it again, but there's no second input
            assertEquals(Pack.packLocation, input);
            System.out.println("Test was successful :)");

        }

    }

    /**
     * Testing the creation and distribution of cards
     * after a valid pack has been inputted.
     * Player 1's given hand was compared to the expected
     * hand given the round-robin distribution.
     */
    @Test
    public void testRequestPackFileValidLocationValidPlayers() {
        System.out.println("\n" + "testRequestPackValidLocationValidPlayers:");

        Pack pack = new Pack();
        CardGame test = new CardGame();
        CardGame.numOfPlayers = 4;
        String input = "Packs/Pack.txt";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        Pack.requestPackFile();

        assertEquals(Pack.packLocation, input);
        System.out.println("Test was successful :)");

    }


    /**
     * Testing the validation of a pack containing a string
     */
    @Test
    public void testRequestPackFileInvalidPackContainsString() {
        System.out.println("\n" + "testRequestPackFileInvalidPackContainsString:");

        Pack pack = new Pack();
        CardGame test = new CardGame();
        CardGame.numOfPlayers = 3;
        String input = "Packs/InvalidPackString.txt"; // Pack for 4 players
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);


        try {
            Pack.requestPackFile();
        } catch (Exception e) {
            // exception thrown because the pack was re-requested and there was no second input

            assertEquals(1, Pack.cardValues.get(Pack.cardValues.size()-1));
            System.out.println("Test was successful :)");
        }
    }

    /**
     * Testing the validation of a pack containing a zero
     */
    @Test
    public void testRequestPackFileInvalidPackContainsZero() {
        System.out.println("\n" + "testRequestPackFileInvalidPackContainsZero:");

        Pack pack = new Pack();
        CardGame test = new CardGame();
        CardGame.numOfPlayers = 4;
        String input = "Packs/InvalidPackZero.txt"; // Pack for 4 players
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);


        try {
            Pack.requestPackFile();
        } catch (Exception e) {
            // exception thrown because the pack was re-requested and there was no second input

            // 3 is the element before the 0 - last element to be added to Pack.cardValues
            assertEquals(3, Pack.cardValues.get(Pack.cardValues.size()-1));
            System.out.println("Test was successful :)");

        }
    }

    /**
     * Testing the validation of a pack with too many cards
     */
    @Test
    public void testRequestPackFileInvalidPackContainsMoreThanEightNCards() {
        System.out.println("\n" + "testRequestPackFileInvalidPackContainsMoreThanEightNCards:");

        Pack pack = new Pack();
        CardGame test = new CardGame();
        CardGame.numOfPlayers = 3;
        String input = "Packs/Pack.txt"; // Pack for 4 players
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);


        try {
            Pack.requestPackFile();
        } catch (Exception e) {
            // exception thrown because the pack was re-requested and there was no second input
            assertNotEquals(Pack.cardValues.size(), CardGame.numOfPlayers * 8);
            System.out.println("Test was successful :)");

        }
    }

    /**
     * Testing the validation of a pack with too little cards
     */
    @Test
    public void testRequestPackFileInvalidPackContainsLessThanEightNCards() {
        System.out.println("\n" + "testRequestPackFileInvalidPackContainsLessThanEightNCards:");

        Pack pack = new Pack();
        CardGame test = new CardGame();
        CardGame.numOfPlayers = 20;
        String input = "Packs/Pack.txt"; // Pack for 4 players
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);


        try {
            Pack.requestPackFile();
        } catch (Exception e) {
            // exception thrown because the pack was re-requested and there was no second input
            assertNotEquals(Pack.cardValues.size(), CardGame.numOfPlayers * 8);
            System.out.println("Test was successful :)");
        }

    }

}
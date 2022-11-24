import org.junit.Test;
import static org.junit.Assert.*;

public class TestDeck {


    /**
     * Testing the creation of a deck object
     */
    @Test
    public void testDeckCreation(){
        System.out.println("\n" + "testDeckCreation:");

        Deck deck1 = new Deck(1);
        Card testCard1 = new Card(1);
        Card testCard2 = new Card(2);

        assertEquals(deck1.getNumberOfDeck(), 1);

        deck1.insertCard(testCard1);
        assertEquals(deck1.deckLength(), 1);

        deck1.insertCard(testCard2);
        assertEquals(deck1.deckLength(), 2);


        System.out.println("Test was successful :)");
    }

    /**
     * Testing the drawCard function
     */
    @Test
    public void testDeckDrawCard() {
        System.out.println("\n" + "testDeckDrawCard:");

        Deck deck1 = new Deck(1);
        Card testCard1 = new Card(1);
        Card testCard2 = new Card(2);

        deck1.insertCard(testCard1);
        deck1.insertCard(testCard2);

        assertEquals(deck1.drawCard(), testCard1);
        assertEquals(deck1.drawCard().getValue(), 2);
        assertEquals(deck1.deckLength(), 0);

        System.out.println("Test was successful :)");
    }

}

import org.junit.Test;
import static org.junit.Assert.*;

public class TestCard {

    /**
     * Tests the creation of the card objects
     */
    @Test
    public void testCreateCard() {
        System.out.println("\n" + "testCreateCard:");

        Card testCard1 = new Card(1);
        Card testCard2 = new Card(1);
        Card testCard3 = new Card(2);

        int card1Value = testCard1.getValue();
        int card2Value = testCard2.getValue();
        int card3Value = testCard3.getValue();

        assertEquals(card1Value, card2Value); // checking if values are the same
        assertNotEquals(card1Value, card3Value); // checking if values are different
        assertNotEquals(testCard1, testCard2); // testing that they are different objects

        System.out.println("Test was successful :)");

    }


}

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPlayer {

    /**
     * Testing the creation of a player
     */
    @Test
    public void testCreatePlayer() {
        System.out.println("\n" + "testCreatePlayer:");

        Player player = new Player(1);

        assertEquals(1, player.getPlayerDenomination());
        assertEquals(2, player.discardedDeck);
        assertEquals(1, player.drawDeck);

        System.out.println("Test was successful :)");
    }


    /**
     * Testing the addCardToHand and removeCardFromHand methods
     */
    @Test
    public void testAddCardAndRemoveCardFromHand() {
        System.out.println("\n" + "testAddCardAndRemoveCardFromHand:");

        Player player = new Player(1);

        Card card1 = new Card(1);
        player.addCardToHand(card1);
        assertEquals(card1, player.hand.get(0));
        player.removeCardFromHand(0);
        assertEquals(0, player.hand.size());

        System.out.println("Test was successful :)");
    }


    /**
     * Testing the function that checks if a player has won
     */
    @Test
    public void testHasPlayerWon() {
        System.out.println("\n" + "testHasPlayerWon:");


        Player player = new Player(1);

        Card card1 = new Card(1);
        Card card2 = new Card(1);
        Card card3 = new Card(1);
        Card card4 = new Card(1);
        Card card5 = new Card(2);

        player.addCardToHand(card5);
        player.addCardToHand(card1);
        player.addCardToHand(card2);
        player.addCardToHand(card3);

        assertFalse(player.hasPlayerWon());

        player.removeCardFromHand(0);
        player.addCardToHand(card4);

        assertTrue(player.hasPlayerWon());


        System.out.println("Test was successful :)");
    }


}
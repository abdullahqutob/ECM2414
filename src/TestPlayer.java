import org.junit.Test;

import java.io.IOException;

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

    /**
     * Testing the handling of a player accessing an empty deck
     */
    @Test
    public void testAccessEmptyDeck() throws IOException {
        System.out.println("\n" + "testAccessEmptyDeck:");

        Player player = new Player(1);
        Deck drawDeck = new Deck(1);
        Deck discardDeck = new Deck(2);
        CardGame.decks.add(drawDeck);
        CardGame.decks.add(discardDeck);

        player.addCardToHand(new Card(1));
        player.addCardToHand(new Card(1));
        player.addCardToHand(new Card(1));
        player.addCardToHand(new Card(3));

        player.addAndRemoveCards(); // drawDeck is empty

        drawDeck.insertCard(new Card(1));

        player.addAndRemoveCards(); // should discard 3 and add 1 to hand

        assertEquals(3, discardDeck.drawCard().getValue());
        assertTrue(player.hasPlayerWon());

        System.out.println("Test was successful :)");
    }


}
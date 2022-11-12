import static org.junit.Assert.*;

public class TestDeck {


    public static String testDeckCreationInsertion(){
        Deck deck1 = new Deck(1);
        Card testCard1 = new Card(1);
        Card testCard2 = new Card(2);

        deck1.insertCard(testCard1);
        assertEquals(deck1.deckLength(), 1);
        deck1.insertCard(testCard2);
        assertEquals(deck1.deckLength(), 2);

        return "testDeckCreation Successful";
    }

    public static String testDeckDrawCard(){
        Deck deck1 = new Deck(1);
        Card testCard1 = new Card(1);
        Card testCard2 = new Card(2);
        Card testCard3 = new Card(3);

        deck1.insertCard(testCard1);
        deck1.insertCard(testCard2);
        deck1.insertCard(testCard3);
        assertEquals(deck1.deckLength(), 3);
        assertEquals(deck1.drawCard(), testCard1);
        assertEquals(deck1.deckLength(), 2);

        return "testDeckDrawCard Successful";
    }

}


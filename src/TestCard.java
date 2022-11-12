import static org.junit.Assert.*;

public class TestCard {


    public static String testCreateCard(){
        Card testCard1 = new Card(1);
        Card testCard2 = new Card(1);
        Card testCard3 = new Card(2);


        int card1Value = testCard1.getCardNum();
        int card2Value = testCard2.getCardNum();
        int card3Value = testCard3.getCardNum();

        assertEquals(card1Value, card2Value);
        assertNotEquals(card1Value, card3Value);

        return "testCreateCard Successful";

    }








    }

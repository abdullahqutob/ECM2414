import static org.junit.Assert.*;

public class TestCard {


    public static String testCreateCard(){
        Card testCard1 = new Card(1);
        Card testCard2 = new Card(1);
        Card testCard3 = new Card(2);


        int card1Value = testCard1.getValue();
        int card2Value = testCard2.getValue();
        int card3Value = testCard3.getValue();

        assertEquals(card1Value, card2Value);
        assertNotEquals(card1Value, card3Value);

        return "testCreateCard Successful";

    }








    }

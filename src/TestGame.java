

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;


public class TestGame {
    public static void main(String[] args) throws FileNotFoundException {
        CardGame game = new CardGame();
        CardGame.numOfPlayers = 4;
        CardGame.cardsPack = new ArrayList<>(Arrays.asList(new Card(1),new Card(6) ,
                new Card(4),new Card (5), new Card( 1) ,new Card(2),
                new Card(1) , new Card (4), new Card(3),
                new Card(2), new Card(1) , new Card (2), new Card(8) ,
                new Card(7), new Card(4), new Card (2), new Card(4),
                new Card(2), new Card (3), new Card(1), new Card(5),
                new Card(3), new Card(1), new Card(5), new Card(2),
                new Card(3), new Card(4), new Card(5),new Card(4),
                new Card(1), new Card(4), new Card(5)));

        game.launchGame();

    }
}

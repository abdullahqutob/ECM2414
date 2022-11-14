import java.io.FileNotFoundException;

public class TestGame {
    public static void main(String[] args) throws FileNotFoundException {
        CardGame game = new CardGame();

//        CardGame.requestUserInput();
//        System.out.println(CardGame.numOfPlayers);
        Pack sdfk = new Pack();
        Pack.requestPackFile();
        game.LaunchGame();
    }

}

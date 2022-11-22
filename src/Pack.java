import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Pack of game; used to distribute cards to players and their respective decks
 */
public class Pack {

    static String packLocation = null;
    static ArrayList<Integer> cardValues = new ArrayList<Integer>();


    /**
     * Function that requests the user to input the location
     * of the pack then checks if it is valid,
     * if the file is not found, request another input
     */
    public static void requestPackFile() {
        // Pack location Input
        try {
            System.out.println("Please enter the location of the pack to load: ");
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner.nextLine();
            packLocation = String.valueOf(inputString);
            validatePack();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found, please try again");
            requestPackFile();
        }

    }

    /**
     * Function that checks if a pack is valid.
     * If it is valid ->  creates a pack of cards
     * If it is invalid -> requests another input
     * @throws FileNotFoundException if file location is incorrect or file does not exist
     */
    public static void validatePack() throws FileNotFoundException {

        cardValues.clear();

        File file = new File(packLocation);
        Scanner scan = new Scanner(file);

        // scanning the pack line by line
        while (scan.hasNextLine()) {
            try {
                String scanNextLine = scan.nextLine();
                int currentValue = Integer.parseInt(scanNextLine);
                if (currentValue <= 0) { // if pack contains a 0 or negative integers
                    System.out.println("Invalid Pack: Values cannot be less than or equal to 0");
                    requestPackFile();
                    return;
                }
                cardValues.add(currentValue);
            } catch (NumberFormatException e) { // if pack contains non-integers
                System.out.println("File must only contain Integers");
                requestPackFile();
                return;
            }
        }

        int eightNumOfPlayers= CardGame.numOfPlayers * 8;

        if (cardValues.size() == eightNumOfPlayers){
            // create pack of cards
            createCardsPack();
            System.out.println("Validation Successful");
            System.out.println("Game starting...");
        } else if (cardValues.size() > eightNumOfPlayers){
            System.out.println("Invalid Pack: Pack contains more than 8n cards");
            requestPackFile();
        } else if (cardValues.size() < eightNumOfPlayers) {
            System.out.println("Invalid Pack: Pack contains less than 8n cards");
            requestPackFile();
        }


    }

    /**
     * Creating the cards and adding it to the CardGame pack
     * for distribution to decks and players
     */
    public static void createCardsPack() {
        for (Integer value : cardValues) {
            CardGame.cardsPack.add(new Card(value));
        }
    }

}

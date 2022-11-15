import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pack {
    static String packLocation = null;

    // colorful error or success messages
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

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
            System.out.println(ANSI_RED + "Error: File not found, please try again" + ANSI_RESET);
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

        ArrayList<Integer> cardValues = new ArrayList<Integer>();

        File file = new File(packLocation);
        Scanner scan = new Scanner(file);

        // scanning the pack line by line
        while (scan.hasNextLine()) {
            try {
                String scanNextLine = scan.nextLine();
                int currentValue = Integer.parseInt(scanNextLine);
                if (currentValue <= 0) { // if pack contains a 0 or negative integers
                    System.out.println(ANSI_RED + "Invalid Pack: Values cannot be less than or equal to 0" + ANSI_RESET);
                    requestPackFile();
                    return;
                }
                cardValues.add(currentValue);
            } catch (NumberFormatException e) { // if pack contains non-integers
                System.out.println(ANSI_RED + "File must only contain Integers" + ANSI_RESET);
                requestPackFile();
                return;
            }
        }

        int cardModPlayer = cardValues.size() % CardGame.numOfPlayers;
        if (cardModPlayer == 0) {
            // create pack of cards
            for (Integer value : cardValues) {
                CardGame.cardsPack.add(new Card(value));
            }
        } else {
            System.out.println(ANSI_RED + "Invalid Pack: Pack does not contain 8n cards" + ANSI_RESET);
            requestPackFile();
        }

        System.out.println(ANSI_GREEN + "Validation Successful" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Game starting..." + ANSI_RESET);
    }

}

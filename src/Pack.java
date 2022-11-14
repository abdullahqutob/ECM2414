import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pack {
    static String packLocation = null;

    /**
     * Function that requests the user to input the location of
     * the pack then checks if it is valid,
     * if the file is not found, request another location input
     */
    public static void requestPackFile() {
        // Pack location Input
        try {
            System.out.println("Please enter the location of the pack to load: ");
            Scanner packLocationInput = new Scanner(System.in);
            packLocation = String.valueOf(packLocationInput);
            validatePack();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found, please try again");
            requestPackFile();
        }


    }

    /**
     * Function that checks if a pack is valid.
     * If it is valid, it creates a pack of cards
     * If it is not valid, it requests another input
     * @throws FileNotFoundException if file location is incorrect or file odes not exist
     */
    public static void validatePack() throws FileNotFoundException {

        ArrayList<Integer> values = readPack(packLocation);

        if ((values.size() / CardGame.numOfPlayers) == 8){
            // create pack of cards
            for (Integer value : values) {
                CardGame.cardsPack.add(new Card(value));
            }
        } else if ((values.size() / CardGame.numOfPlayers) > 8){
            System.out.println("Invalid Pack: Pack contains more than 8n cards");
            requestPackFile();
        } else if ((values.size() / CardGame.numOfPlayers) < 8) {
            System.out.println("Invalid Pack: Pack contains less than 8n cards");
            requestPackFile();
        }
    }



    /**
     * Function that reads inputted Pack and returns a list of card values
     * @param fileName Location of pack file
     * @return cardValues as a list of integers
     * @throws FileNotFoundException if Pack file could not be opened
     */
        public static ArrayList<Integer> readPack(String fileName) throws FileNotFoundException {

            // create array for card values
            ArrayList<Integer> cardValues = new ArrayList<Integer>();

            // throws exception if no file is inputted
            if (fileName == null) {
                throw new FileNotFoundException("File location not entered.");
            }

            File file = new File(fileName);
            Scanner scan = new Scanner(file);


            while (scan.hasNextLine()) {
                try {
                    int currentValue = Integer.parseInt(scan.nextLine());
                    if (currentValue <= 0) {
                        System.out.println("Invalid Pack: Values cannot be less than or equal to 0");
                    }
                    cardValues.add(currentValue);
                } catch (NumberFormatException e) { // if line contains non-integers
                    System.out.println("File must only contain Integers");
                }
            }


            return cardValues;
        }


}


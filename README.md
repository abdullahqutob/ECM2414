# ECM2414 Card Game

This project is a pair-programming assignment that implements threading and user inputs, to run a card game with 2 -> n players. At the start of the game, each player is given 4 cards from the pack of cards in a round-robin fashion. There are equally as many decks as there are players, which also receive 4 cards at the start of the game from round-robin distribution. Each player is assigned a deck to draw cards from, and a deck to discard cards. The goal of the player is to collect 4 of the same cards, preferably with the same denomination as the player.

With each iteration, the players write to their own designated output text files their moves (the card they draw, the card they discard, and their hand). When a player has won, it promptly interrupts all other players from executing and informs them of their victory. The players then write to their designated text output file that a player has won, and their final hand. The decks are also instructed to log their current holdings to their text files.

     Disclaimer - To avoid untested issues, please run on a windows device

## How to run the JAR file

-   Place the pack file same directory as the jar file.

-   Enter the command: `java -jar cards.jar`
-   Enter the number of players you'd like to play
-   Enter the location of the pack file
    -   If it is in another directory type the directory name then the pack file name, i.e., `PackFolder/Pack.txt`
    -   If it is in the same directory as the jar file, just type the name of the pack file
        ![image](https://user-images.githubusercontent.com/96269432/203731550-e5c7d266-6ebd-4f5f-9049-c85126080c61.png)
    ### Game in action:
     <img width="600" alt="image" src="https://user-images.githubusercontent.com/96269432/229861825-e35cc985-5a5c-437f-946d-519c157cab14.png">

## How to run the source code

-   Navigate to `ECM2414/src`

-   enter the command `java CardGame`
-   Enter the number of players you'd like to play
-   Enter the location of the pack file

## How to run the test suite

-   Import the project into IntelliJ

![image](https://user-images.githubusercontent.com/96269432/203748659-652a2397-f039-4ef7-bfd0-efc3f4c017d9.png)
![image](https://user-images.githubusercontent.com/96269432/203748670-58d0944f-0847-4efa-abe6-bf3c86b6da82.png)

-   Go to `ECM2414/src/TestSuite.java`
-   Click on `'Run Test Suite'`
    ![image](https://user-images.githubusercontent.com/96269432/203731124-d2070822-fe07-4403-80ca-4a31d817f948.png)
    ![image](https://user-images.githubusercontent.com/96269432/203731368-57d88fc7-d7b5-4115-85e6-94c8dcf55a6b.png)

# Creators

-   Qutob, Abdullah - 041184
-   Cooklin, Sam - 244903

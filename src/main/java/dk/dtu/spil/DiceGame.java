package dk.dtu.spil;

import java.util.Scanner;
import java.util.Arrays;
import dk.dtu.spil.DiceManager.DiceCup;
import dk.dtu.spil.PlayerManager.Player;

//***************************************************************//
// Main class for the DiceGame program
//***************************************************************//
public class DiceGame {
    private final static DiceManager dm = new DiceManager();
    private final static PlayerManager pm = new PlayerManager();

    //***************************************************************//
    // Main function for the dice game.
    //***************************************************************//
    public static void main(String[] args) {

        //***************************************************************//
        // Sets the new players, and inputs their names.
        //***************************************************************//
        Scanner input = new Scanner(System.in);

        System.out.print("Write player 1 name: ");
        String User1 = input.nextLine();
        System.out.print("Write player 2 name: ");
        String User2 = input.nextLine();

        System.out.println();

        Player player1 = pm.createPlayer(User1);
        Player player2 = pm.createPlayer(User2);

        DiceCup diceCup = dm.createDiceCup();

        //***************************************************************//
        // Loops the raffling as long as none of the players
        // have reached 40 points, it also reads out the result of each raffle
        //***************************************************************//

        while (player1.getPoints() <= 39 && player2.getPoints() <= 39) {
            diceCup.raffleCup();
            player1.addPoints(diceCup.getSum());

            System.out.println(player1.getName() + " Got:" + Arrays.toString(new int[]{diceCup.getDiceValues()[0]}) + " and " + Arrays.toString(new int[]{diceCup.getDiceValues()[1]}) + " - Total: " + player1.getPoints());

            diceCup.raffleCup();
            player2.addPoints(diceCup.getSum());

            System.out.println(player2.getName() + " Got:" + Arrays.toString(new int[]{diceCup.getDiceValues()[0]}) + " and " + Arrays.toString(new int[]{diceCup.getDiceValues()[1]}) + " - Total: " + player2.getPoints());
            System.out.println();
        }

        //***************************************************************//
        // Writes which player won or if it was a tie between the two
        //***************************************************************//

        if (player1.getPoints() >= 40 && player2.getPoints() < 40) {
            System.out.println(User1 + " has won the game ");
        }
        else if (player2.getPoints() >= 40 && player1.getPoints() < 40 ) {
            System.out.println(User2 + " has won the game " );
        }
        else if (player1.getPoints() >= 40 && player2.getPoints() >= 40) {
            System.out.println("The game was a tie between " + User1 + " and " + User2);
        }
        else {
            System.out.println("An error has occurred!");
        }
    }
}
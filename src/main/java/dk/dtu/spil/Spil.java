package dk.dtu.spil;

import dk.dtu.spil.DiceManager.DiceCup;
import java.util.Arrays;
import dk.dtu.spil.PlayerManager.Player;

// Main class for the dicethrow game program
public class Spil {
    private final static DiceManager dm = new DiceManager();
    private final static PlayerManager pm = new PlayerManager();

    // Main function for the game
    public static void main(String[] args) {
        //Sets the new players. The names are placeholders
        Player player1 = pm.new Player("John John");
        Player player2 = pm.new Player("Janet Jackson");

        DiceCup diceCup = dm.new DiceCup();

        while (player1.getPoints() <= 39 && player2.getPoints() <= 39){
            diceCup.raffleCup();
            player1.addPoints(diceCup.getSum());

            System.out.println(player1.getName() + " har " + player1.getPoints());

            diceCup.raffleCup();
            player2.addPoints(diceCup.getSum());


            System.out.println(player2.getName() + " har " + player2.getPoints());

        }
/*
        for (int i = 0; i < 100; i++) {
            diceCup.raffleCup();

            pm.player.addPoints += diceCup.getSum();

            System.out.println("DEBUG Nr. " + (i + 1) + ": " + Arrays.toString(diceCup.getDiceValues()) + " (" + diceCup.getSum() + ") - PlayerPoint = " + pm.player1Points + " - isSimilar=" + diceCup.isSimilar());
        }
*/

//        boolean win = false;
//        int[] player1Terning = new int[2];
//        int[] player2Terning = new int[2];
//
//        int minTerningVaerdi = 1; // Sets the minimum value of the die.
//        int maxTerningVaerdi = 6; // Sets the maximum value of the die.
//
//        int player1Points = 0; //Player points. Always 0 at the start of the game.
//        int player2Points = 0; // --||--
//
//        while (!win) {
//
//            // Roll the dice for player1
//            for (int i = 0; i < 2; i++)
//            {
//                player1Terning[i] = terning(minTerningVaerdi, maxTerningVaerdi);
//            }
//            System.out.println("Player1Points: " + player1Points);
//            System.out.println("terning 1 = " + player1Terning[0] + " terning 2 = " + player1Terning[1]);
//
//            // Roll the dice for player2
//            for (int i = 0; i < 2; i++) {
//                player2Terning[i] = terning(minTerningVaerdi, maxTerningVaerdi);
//            }
//            System.out.println("Player2Points: " + player2Points);
//            System.out.println("terning 1 = " + player2Terning[0] + " terning 2 = " + player2Terning[1]);
//
//            // Add points to existing
//            player1Points += player1Terning[0] + player1Terning[1];
//            player2Points += player2Terning[0] + player2Terning[1];
//
//            //They player loses their points if they hit 1 on both die
//            if (player1Terning[0] == 1 && player1Terning[1] == 1) {
//                player1Points = 0;
//            }
//            if (player2Terning[0] == 1 && player2Terning[1] == 1) {
//                player2Points = 0;
//            }
//
//            //Chance win to true
//            if (player1Points >= 40 || player2Points >= 40) {
//                win = true;
//
//                //Print who wins
//                if (player1Points < player2Points) System.out.println("Player 2 wins, with " + player2Points
//                        + " over Player 1 who haves " + player1Points + " points");
//
//                else if (player1Points > player2Points) System.out.println("Player 1 wins, with " + player1Points
//                        + " over Player 2 who haves " + player2Points + " points");
//
//                else System.out.println("Both players are tied with them both having" + player2Points + " points");
//
//
//            }
//
//        }
    }
}
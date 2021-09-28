package dk.dtu.spil;

import java.util.Arrays;
import java.util.List;

import dk.dtu.spil.DiceManager.DiceCup;
import dk.dtu.spil.PlayerManager.Player;

//***************************************************************//
// Main class for the DiceGame program
//***************************************************************//
public class DiceGame {
    private final static DiceManager dm = new DiceManager();
    private final static PlayerManager pm = new PlayerManager();
    private final static GUIManager gm = new GUIManager();

    private final static DiceCup diceCup = dm.createDiceCup();
    private static Player player1;
    private static Player player2;
    private static boolean isPlaying = true;
    private final static boolean debug = ((System.getenv("debug") != null) || (System.getProperty("debug") != null));

    //***************************************************************//
    // Main function for the dice game.
    //***************************************************************//
    public static void main(String[] args) {
        //***************************************************************//
        // Creates the players through PlayerManager.
        //***************************************************************//
        player1 = pm.createPlayer(gm);
        player2 = pm.createPlayer(gm);

        //***************************************************************//
        // Play the game until they want to stop playing.
        //***************************************************************//
        while (isPlaying) {
            play();
        }

        gm.closeGUI();
        System.exit(0);
    }

    private static void play() {
        //***************************************************************//
        // Loops the raffling as long as no one has reached 40 points
        //***************************************************************//
        while (player1.getPoints() <= 39 && player2.getPoints() <= 39) {
            if (player2.getPoints() <= 39) {
                // If the player won the game (by getting two 6's, two times)
                if (playerPlay(player1)) { break; }
            }
            if (player1.getPoints() <= 39) {
                // If the player won the game (by getting two 6's, two times)
                if (playerPlay(player2)) { break; }
            }
        }

        //***************************************************************//
        // Writes which player won, by checking if one of the players are
        // over 40 points, if so, it writes which player it was
        //***************************************************************//
        String message;
        if (player1.getPoints() >= 40 || player2.getPoints() >= 40) {
            if (player1.getPoints() >= 40) {
                message = player1.getName() + " has won the game!";
                System.out.println(message);
                gm.showMessage(message);
            }
            if (player2.getPoints() >= 40) {
                message = player2.getName() + " has won the game!";
                System.out.println(message);
                gm.showMessage(message);
            }
        }

        // Ask if they want to continue playing - reset points and let the loop continue
        // Otherwise set isPlaying to false and the main loop will exit
        if (gm.askPrompt("Vil I spille igen?")) {
            player1.setPoints(0);
            player2.setPoints(0);
        }
        else {
            isPlaying = false;
        }
    }

    private static boolean playerPlay(Player player) {
        if (!debug) {
            gm.waitUserRoll(player.getName());
        }

        diceCup.raffleCup();
        int[] diceValues = diceCup.getDiceValues();

        // Add the roll to the player
        player.addRoll(diceValues[0], diceValues[1]);

        // Update the GUI with dice values
        gm.updateDice(diceValues[0], diceValues[1]);
        // Add points to the player
        player.addPoints(diceCup.getSum());

        System.out.println(player.getName() + " Got:" + Arrays.toString(new int[]{diceValues[0]}) + " and " + Arrays.toString(new int[]{diceValues[1]}) + " - Total: " + player.getPoints());

        //***************************************************************//
        // an if statement that checks if the player got the same number
        // on both dice, if that condition is met, the player gets another try
        //***************************************************************//
        if (diceCup.isSimilar()) {
            //***************************************************************//
            // Sets the player points to 0 when both dice hit 1
            //***************************************************************//
            if (diceValues[0]==1){
                gm.showMessage("You got two 1's, unlucky! You've lost all your points!");
                System.out.println("reset points for " + player.getName() + " (two 1's)");
                player.setPoints(0);
            }

            // If the player got two 6's, check if they also got two 6's last roll
            if (diceValues[0]==6) {
                List<Integer> lastFourRolls = player.getLastFourRolls();

                System.out.println(lastFourRolls);
                if (lastFourRolls.size() == 4) {
                    if (lastFourRolls.get(0)==6 && lastFourRolls.get(1)==6) {
                        System.out.println(player.getName() + " got two 6's in a row, return true.");
                        gm.showMessage(player.getName() + " got a jackpot, and won the game!");
                        return true;
                    }
                }
            }

            System.out.println(player.getName() + " got the same on both dice and gets another turn!");
            gm.showMessage(player.getName() + " got the same on both dice and gets another turn!");
            return playerPlay(player);
        }
        return false;
    }
}

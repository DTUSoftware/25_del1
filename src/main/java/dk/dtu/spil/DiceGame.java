package dk.dtu.spil;

import java.util.Arrays;
import java.util.List;
import dk.dtu.spil.DiceManager.DiceCup;
import dk.dtu.spil.PlayerManager.Player;

//***************************************************************//
// Main class for the DiceGame program
//***************************************************************//
public class DiceGame {
    //***************************************************************//
    // We import the managers.
    //***************************************************************//
    private final static DiceManager dm = new DiceManager();
    private final static PlayerManager pm = new PlayerManager();
    private final static GUIManager gm = new GUIManager();

    //***************************************************************//
    // We create objects for the diceCup and the two players.
    //***************************************************************//
    private final static DiceCup diceCup = dm.createDiceCup();
    private static Player player1;
    private static Player player2;

    //***************************************************************//
    // We create variables for whether they are currently playing,
    // and for whether debugging is enabled.
    //***************************************************************//
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

        //***************************************************************//
        // After they have stopped playing, close the GUI and exit.
        //***************************************************************//
        gm.closeGUI();
        System.exit(0);
    }

    private static void play() {
        //***************************************************************//
        // Loops the raffling between players as long as no one has won.
        //***************************************************************//
        while (true) {
            if (playerPlay(player1)) { break; }
            if (playerPlay(player2)) { break; }
        }

        //***************************************************************//
        // Ask if they want to continue playing - reset points and let
        // the loop continue. Otherwise, set isPlaying to false and the
        // main loop will exit.
        //***************************************************************//
        if (gm.askPrompt("Do you want to play again?")) {
            player1.setPoints(0);
            player2.setPoints(0);
        }
        else {
            isPlaying = false;
        }
    }

    //***************************************************************//
    // This function returns true if the player won.
    // The function is responsible for the whole turn of the player.
    //***************************************************************//
    private static boolean playerPlay(Player player) {
        //***************************************************************//
        // If debugging is enabled through an environment variable or
        // a system property, ex. 'java -Ddebug=true -jar spil.jar',
        // then skip the part where we wait for user rolls.
        //***************************************************************//
        if (!debug) {
            gm.waitUserRoll(player);
        }

        //***************************************************************//
        // Raffle the cup to randomize the values of the two dice.
        //***************************************************************//
        diceCup.raffleCup();

        //***************************************************************//
        // Get the values of the two dice.
        //***************************************************************//
        int[] diceValues = diceCup.getDiceValues();

        //***************************************************************//
        // Update the GUI with dice values.
        //***************************************************************//
        gm.updateDice(diceValues[0], diceValues[1]);

        //***************************************************************//
        // Add the roll to the player and add the points.
        //***************************************************************//
        player.addRoll(diceValues[0], diceValues[1]);
        player.addPoints(diceCup.getSum());

        // Debugging
        System.out.println(player.getName() + " Got:" + Arrays.toString(new int[]{diceValues[0]}) + " and " + Arrays.toString(new int[]{diceValues[1]}) + " - Total: " + player.getPoints());

        //***************************************************************//
        // An if statement that checks if the player got the same number
        // on both dice, if that condition is met, the player gets
        // another try.
        // After extra assignments 3 and 4 - this also checks for the
        // win conditions, like two 6's in a row, or getting two similar
        // dice after getting 40 points.
        //***************************************************************//
        if (diceCup.isSimilar()) {
            //***************************************************************//
            // If the player had 40 points or more, before getting points
            // added, return true and win the game.
            //***************************************************************//
            if ((player.getPoints() - diceCup.getSum()) >= 40) {
                System.out.println(player.getName() + " got two equal after 40 and won, return true");
                String message = player.getName() + " has won the game!";
                System.out.println(message);
                gm.showMessage(message);
                return true;
            }

            //***************************************************************//
            // Sets the player points to 0 when both dice are 1.
            //***************************************************************//
            if (diceValues[0]==1){
                gm.showMessage("You got two 1's, unlucky! You've lost all your points!");
                System.out.println("reset points for " + player.getName() + " (two 1's)");
                player.setPoints(0);
            }

            //***************************************************************//
            // If the player got two 6's, check if they also got two 6's
            // on their last roll. If so, return true and win.
            //***************************************************************//
            if (diceValues[0]==6) {
                //***************************************************************//
                // Gets the last four rolls.
                //***************************************************************//
                List<Integer> lastFourRolls = player.getLastFourRolls();

                // Debugging
                System.out.println(lastFourRolls);

                //***************************************************************//
                // We ensure that the player has rolled 4 dice, and not just
                // the first two dice.
                //***************************************************************//
                if (lastFourRolls.size() == 4) {
                    //***************************************************************//
                    // We ensure that both of the dice on the last roll were 6's.
                    //***************************************************************//
                    if (lastFourRolls.get(0)==6 && lastFourRolls.get(1)==6) {
                        System.out.println(player.getName() + " got two 6's in a row, return true.");
                        gm.showMessage(player.getName() + " got a jackpot, and won the game!");
                        return true;
                    }
                }
            }

            //***************************************************************//
            // If we haven't returned already, due to win conditions, then
            // give the player another turn by calling the playerPlay()
            // method again.
            //
            // NOTE: THIS IS A RECURSIVE CALL. We don't expect the player to
            // keep rolling equal dice, and the probability of that is so
            // low, that we should never reach stack overflow (since they
            // also win at 40 points+).
            //***************************************************************//
            System.out.println(player.getName() + " got the same on both dice and gets another turn!");
            gm.showMessage(player.getName() + " got the same on both dice and gets another turn!");
            return playerPlay(player);
        }
        //***************************************************************//
        // If we get to this point, then the player didn't win, and we
        // return false and hand it over to the loop, to then give the
        // next turn to the other player.
        //***************************************************************//
        return false;
    }
}

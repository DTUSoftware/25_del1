package dk.dtu.spil;

import java.util.Arrays;
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
        // Loops the raffling as long as none of the players
        // have reached 40 points, it also reads out the result of each raffle
        //***************************************************************//
        while (player1.getPoints() <= 39 && player2.getPoints() <= 39) {
            playerPlay(player1);
            playerPlay(player2);
        }

        //***************************************************************//
        // Writes which player won or if it was a tie between the two
        //***************************************************************//
        String message;
        if (player1.getPoints() >= 40 && player2.getPoints() < 40) {
            message = player1.getName() + " has won the game!";
            System.out.println(message);
            gm.showMessage(message);
        }
        else if (player2.getPoints() >= 40 && player1.getPoints() < 40 ) {
            message = player2.getName() + " has won the game!";
            System.out.println(message);
            gm.showMessage(message);
        }
        else if (player1.getPoints() >= 40 && player2.getPoints() >= 40) {
            message = "The game was a tie between " + player1.getName() + " and " + player2.getName() + "!";
            System.out.println(message);
            gm.showMessage(message);
        }
        else {
            message = "An error has occurred!";
            System.out.println(message);
            gm.showMessage(message);

            isPlaying = false;
            return;
        }

        if (gm.askPrompt("Vil I spille igen?")) {
            player1.setPoints(0);
            player2.setPoints(0);
        }
        else {
            isPlaying = false;
        }
    }

    private static void playerPlay(Player player) {
        if (!debug) {
            gm.waitUserRoll(player.getName());
        }

        diceCup.raffleCup();
        int[] diceValues = diceCup.getDiceValues();

        gm.updateDice(diceValues[0], diceValues[1]);
        player.addPoints(diceCup.getSum());

        System.out.println(player.getName() + " Got:" + Arrays.toString(new int[]{diceValues[0]}) + " and " + Arrays.toString(new int[]{diceValues[1]}) + " - Total: " + player.getPoints());
        //***************************************************************//
        // Sets the player points to 0 when both dice hit 1
        //***************************************************************//
        if (diceValues[0]==1 && diceValues[1]==1){
            player.setPoints(0);
        }

    }
}
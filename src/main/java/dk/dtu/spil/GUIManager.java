package dk.dtu.spil;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;
import java.awt.*;

//***************************************************************//
// The GUIManager is a constructor which initializes a single
// instance of the dtudiplom GUI interface.
//***************************************************************//
public class GUIManager {
    private GUI gui;
//    private final List<Color> colors = Arrays.asList(Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW);

    //***************************************************************//
    // The GUIManager constructor initializes the GUI instance.
    //***************************************************************//
    public GUIManager() {
        GUI_Field[] fields = new GUI_Field[0];

        gui = new GUI(fields, Color.decode("0x"+"3E6990"));

        gui.setDice(6, 6);
    }

    //***************************************************************//
    // Function to close the GUI.
    //***************************************************************//
    public void closeGUI() {
        gui.close();
    }

    //***************************************************************//
    // Function to update the Dice on the GUI.
    //***************************************************************//
    public void updateDice(int faceValue1, int faceValue2) {
        gui.setDice(faceValue1, faceValue2);
    }

    //***************************************************************//
    // Helper function to show a message to a player.
    //***************************************************************//
    public void showMessage(String message) {
        gui.showMessage(message);
    }

    //***************************************************************//
    // Helper function to ask a prompt to a player and return the
    // result.
    //***************************************************************//
    public boolean askPrompt(String question) {
        return gui.getUserLeftButtonPressed(question, "Ja", "Nej");
    }

    //***************************************************************//
    // Function to wait for the user to roll their dice (clicking
    // a button). The loop won't continue before they click.
    //***************************************************************//
    public void waitUserRoll(PlayerManager.Player player) {
        if (player.getPoints() < 40) {
            gui.showMessage(player.getName() + "'s turn. Click to roll the dice!");
        }
        else {
            gui.showMessage(player.getName() + "'s turn. You need to roll two identical dice to win the game! Click to roll the dice!");
        }
    }

    //***************************************************************//
    // Function to create a new GUIPlayer. This function is
    // for example used in the PlayerManager, where it is passed
    // an instance of the GUIManager by the main function.
    //***************************************************************//
    public GUI_Player createGUIPlayer(int startingBalance) {
        String player_name = gui.getUserString("Enter name for player");

        GUI_Car car = new GUI_Car();
        // The car color is random anyway, lol
        // car.setPrimaryColor(colors.get(rand.nextInt(colors.size())));

        GUI_Player player = new GUI_Player(player_name, startingBalance, car);

        gui.addPlayer(player);

        return player;
    }
}

package dk.dtu.spil;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;

import java.awt.*;

// TODO: Write comments for the DiceManager
public class GUIManager {
    public GUI gui;
//    private final List<Color> colors = Arrays.asList(Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW);

    public GUIManager() {
        GUI_Field[] fields = new GUI_Field[0];

        gui = new GUI(fields, Color.decode("0x"+"3E6990"));

        gui.setDice(6, 6);
    }

    public void closeGUI() {
        gui.close();
    }

    public void updateDice(int faceValue1, int faceValue2) {
        gui.setDice(faceValue1, faceValue2);
    }

    public void showMessage(String message) {
        gui.showMessage(message);
    }

    public boolean askPrompt(String question) {
        return gui.getUserLeftButtonPressed(question, "Ja", "Nej");
    }

    public void waitUserRoll(String playerName) {
        gui.showMessage(playerName + "'s tur. Klik for at rulle terningerne!");
//        gui.getUserButtonPressed(
//                "Klik for at rulle terningerne",
//                "Rul terningerne", "Rul terningerne"
//        );
    }

//    private GUI_Field[] initializeFields() {
//        GUI_Field[] _fields = new GUI_Field[40];
//
//        _fields[0] = new GUI_Street();
//
//        GUI_Street testStreet= new GUI_Street();
//        testStreet.setTitle("Anker Engelundsvej");
//        testStreet.setBorder(Color.CYAN); //Useful to show owner
//        testStreet.setRent("600,-");
//        _fields[1] = testStreet;
//
//        for (int i = 2; i < _fields.length; i++) {
//            _fields[i] = new GUI_Street();
//        }
//
//        return _fields;
//    }

    public GUI_Player createGUIPlayer(int startingBalance) {
        String player_name = gui.getUserString("Indtast navn på spiller");

        GUI_Car car = new GUI_Car();
        // The car color is random anyway, lol
        // car.setPrimaryColor(colors.get(rand.nextInt(colors.size())));

        GUI_Player player = new GUI_Player(player_name, startingBalance, car);

        gui.addPlayer(player);

        return player;
    }
}

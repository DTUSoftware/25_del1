package dk.dtu.spil;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GUIManager {
    private GUI_Field[] fields;
    public GUI gui;
    private List<Color> colors = Arrays.asList(Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW);


    public GUIManager() {
//        fields = initializeFields();
        fields = new GUI_Field[0];

        gui = new GUI(fields, Color.MAGENTA);

        createPlayers(2);

        gui.setDice(3, 4);
    }

    private GUI_Field[] initializeFields() {
        GUI_Field[] _fields = new GUI_Field[40];

        _fields[0] = new GUI_Street();

        GUI_Street testStreet= new GUI_Street();
        testStreet.setTitle("Anker Engelundsvej");
        testStreet.setBorder(Color.CYAN); //Useful to show owner
        testStreet.setRent("600,-");
        _fields[1] = testStreet;

        for (int i = 2; i < _fields.length; i++) {
            _fields[i] = new GUI_Street();
        }

        return _fields;
    }

    private void createPlayers(int playerCount) {
        Random rand = new Random();

        for (int i = 0; i < playerCount; i++) {
            String player_name = gui.getUserString("Indtast et navn");

            GUI_Car car = new GUI_Car();
            // The car color is random anyway, lol
//            car.setPrimaryColor(colors.get(rand.nextInt(colors.size())));

            GUI_Player player = new GUI_Player(player_name, 0, car);

            gui.addPlayer(player);
        }
    }

}

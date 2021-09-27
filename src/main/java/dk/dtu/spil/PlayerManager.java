package dk.dtu.spil;

import gui_fields.GUI_Player;
import java.util.HashMap;

//***************************************************************//
// Class that can set points to a player
//***************************************************************//
public class PlayerManager {
    private final int playerStartpoints = 0;
    private HashMap<Integer, Player> players = new HashMap<>();

    // Package-private class
    class Player {
        //***************************************************************//
        // private integer that make sure the startpoints is correct,
        // and initializes the points
        //***************************************************************//
        private int points = playerStartpoints;
        private final GUI_Player guiPlayer;
        private final String name;

        //***************************************************************//
        // Constructor: Sets the name of the player
        //***************************************************************//
        public Player(GUIManager gm) {
            guiPlayer = gm.createGUIPlayer(points);
            name = guiPlayer.getName();
        }

        //***************************************************************//
        // Add points to the player
        //***************************************************************//
        public void addPoints(int p) {
            points += p;
            guiPlayer.setBalance(points);
        }

        //***************************************************************//
        // Can set / overwrite the points for the player
        //***************************************************************//
        public void setPoints(int newPoints) {
            points = newPoints;
            guiPlayer.setBalance(points);
        }

        //***************************************************************//
        // Returns the points of the player
        //***************************************************************//
        public int getPoints() {
            return points;
        }

        //***************************************************************//
        // Returns the name of the player
        //***************************************************************//
        public String getName() {
            return name;
        }
    }

    public Player createPlayer(GUIManager gm) {
        Player player = new Player(gm);
        players.put(players.size(), player);
        return player;
    }
}

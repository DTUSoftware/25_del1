package dk.dtu.spil;

import gui_fields.GUI_Player;
import java.util.*;

//***************************************************************//
// The PlayerManager is a controller used to create and
// manage Player instances.
//***************************************************************//
public class PlayerManager {
    //***************************************************************//
    // We create a variable to define how many points a Player
    // should be given to begin with. This could be changed
    // when creating a new game - ex. for different gamemodes.
    //***************************************************************//
    private final int playerStartpoints = 0;

    //***************************************************************//
    // We create a HashMap to keep the players and their numbers.
    //***************************************************************//
    private HashMap<Integer, Player> players = new HashMap<>();

    //***************************************************************//
    // The Player class is a package-private class, and is only meant
    // to be accessed through the PlayerManager instance.
    // A Player instance is referring to a real player.
    //***************************************************************//
    class Player {
        //***************************************************************//
        // Private integer that make sure the startpoints is correct,
        // and initializes the points.
        //***************************************************************//
        private int points = playerStartpoints;

        //***************************************************************//
        // We also create private variables for the GUIPlayer instance
        // that is linked to the player in question. This is so that we
        // can update the GUI to reflect changes to the player.
        //***************************************************************//
        private final GUI_Player guiPlayer;

        //***************************************************************//
        // A player also has a name, duh.
        //***************************************************************//
        private final String name;

        //***************************************************************//
        // We initialize an ArrayDeque to keep the last 4 rolls that the
        // player made.
        //***************************************************************//
        private Deque<Integer> lastFourRolls = new ArrayDeque<>(4);

        //***************************************************************//
        // Constructor: Sets the name of the player and initializes a
        // GUIPlayer for the Player.
        //***************************************************************//
        public Player(GUIManager gm) {
            guiPlayer = gm.createGUIPlayer(points);
            name = guiPlayer.getName();
        }

        //***************************************************************//
        // Add points to the player.
        //***************************************************************//
        public void addPoints(int p) {
            points += p;
            guiPlayer.setBalance(points);
        }

        //***************************************************************//
        // Can set / overwrite the points for the player.
        //***************************************************************//
        public void setPoints(int newPoints) {
            points = newPoints;
            guiPlayer.setBalance(points);
        }

        //***************************************************************//
        // Returns the points of the player.
        //***************************************************************//
        public int getPoints() {
            return points;
        }

        //***************************************************************//
        // Returns the name of the player.
        //***************************************************************//
        public String getName() {
            return name;
        }

        //***************************************************************//
        // Add a roll to the deque holding the last four rolls of the
        // Player.
        //***************************************************************//
        public void addRoll(int faceValue1, int faceValue2) {
            lastFourRolls.add(faceValue1);
            lastFourRolls.add(faceValue2);
            // Make sure that the dequeue is only 4 long
            if (lastFourRolls.size() > 4) {
                while (lastFourRolls.size() > 4) {
                    lastFourRolls.remove();
                }
            }
        }

        //***************************************************************//
        // Return the last four rolls of the player (or two, if the
        // Player just started playing).
        //***************************************************************//
        public List<Integer> getLastFourRolls() {
            return new ArrayList<>(lastFourRolls);
        }
    }

    //***************************************************************//
    // Function to create a new Player through the PlayerManager
    // controller. This is the intended way to create a new Player
    // instance.
    //***************************************************************//
    public Player createPlayer(GUIManager gm) {
        Player player = new Player(gm);
        players.put(players.size(), player);
        return player;
    }
}

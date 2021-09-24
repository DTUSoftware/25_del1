package dk.dtu.spil;

import java.util.ArrayList;

//-----------------------------------------------------------------
// Class that can set points to a player
//-----------------------------------------------------------------
public class PlayerManager {
    private final int playerStartpoints = 0;
    private ArrayList<Player> players = new ArrayList<>();

    public class Player {
        //-----------------------------------------------------------------
        // private integer that make sure the startpoints is 0, and
        //initializes the points
        //-----------------------------------------------------------------
        private int points = playerStartpoints;
        private String name;

        //-----------------------------------------------------------------
        //Constructer: Sets the name of the player
        //-----------------------------------------------------------------
        public Player(String _name) {
            name = _name;
        }

        //-----------------------------------------------------------------
        // Adds points to the player
        //-----------------------------------------------------------------
        public void addPoints(int p) {
            points += p;
        }

        //-----------------------------------------------------------------
        // Can set / overwrite the points for the player
        //-----------------------------------------------------------------
        public void setPoints(int newPoints) {
            points = newPoints;
        }

        //-----------------------------------------------------------------
        // Returns the points of the player
        //-----------------------------------------------------------------
        public int getPoints() {
            return points;
        }

        //-----------------------------------------------------------------
        // Returns the name of the player
        //-----------------------------------------------------------------
        public String getName() {
            return name;
        }

    }

    public Player createPlayer(String name) {
        Player player = new Player(name);
        players.add(player);
        return player;
    }

}

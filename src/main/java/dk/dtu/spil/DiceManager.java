package dk.dtu.spil;

import java.util.Random;

//***************************************************************//
// The DiceManager is the controller used to create new Die and
// DiceCup objects.
//***************************************************************//
public class DiceManager {
    //***************************************************************//
    // We create two variables for the minimum and maximum values of
    // the die. This could be used in the future to create games
    // where there are used dice with multiple numbers, like using
    // a D20.
    //***************************************************************//
    private final int minDieValue = 1; // Sets the minimum value of the die.
    private final int maxDieValue = 6; // Sets the maximum value of the die.

    //***************************************************************//
    // We create a new instance of Random to generate die numbers.
    // By default, the seed should be totally random every run.
    //***************************************************************//
    private final Random rand = new Random();

    //***************************************************************//
    // The Die class represents a single Die.
    // A Die can be raffled, and keeps a faceValue which can be read.
    //***************************************************************//
    private class Die {
        //***************************************************************//
        // We set the faceValue to 6 by default. It is raffled
        // before playing.
        //***************************************************************//
        private int faceValue = 6;

        //***************************************************************//
        // We use Random to generate a random Integer between the min
        // and max die values.
        // We use Random.nextInt() instead of Math.random(), since it is
        // both faster and more efficient
        // (see https://stackoverflow.com/a/738651/12418245).
        //***************************************************************//
        public void raffle() {
            // value = (int) ((Math.random() * (maxDieValue-minDieValue+1)) + minDieValue);
            faceValue = rand.nextInt(maxDieValue) + minDieValue;
        }

        //***************************************************************//
        // Function to get the value of the Die.
        //***************************************************************//
        public int getValue() {
            return faceValue;
        }
    }

    //***************************************************************//
    // The DiceCup class is a package-private class, such that it
    // should get created through an instance of the class (ex.
    // an instance of DiceManager).
    // An instance of DiceCup holds two Die instances, which are
    // created on initialization.
    //***************************************************************//
    class DiceCup {
        //***************************************************************//
        // The two Die instances are kept in the DiceCup.
        //***************************************************************//
        private Die die1;
        private Die die2;

        //***************************************************************//
        // When a new DiceCup instance is created, two Die instances are
        // created with it.
        //***************************************************************//
        public DiceCup() {
            die1 = new Die();
            die2 = new Die();
        }

        //***************************************************************//
        // A function to easily raffle the whole cup (as you would do
        // in real-life as well).
        //***************************************************************//
        public void raffleCup() {
            die1.raffle();
            die2.raffle();
        }

        //***************************************************************//
        // A public function to get the values of the two dice
        // separately.
        //***************************************************************//
        public int[] getDiceValues() {
            return new int[] {die1.getValue(), die2.getValue()};
        }

        //***************************************************************//
        // A public function to easily get the sum of the two dice.
        // This function is created to allow ease-of-use, instead of
        // having to getDiceValues() and then add those two together,
        // every time.
        //***************************************************************//
        public int getSum() {
            return (die1.getValue() + die2.getValue());
        }

        //***************************************************************//'
        // A public function to quickly return a boolean value, about
        // whether the two dice are similar or not. Again, for ease-of-
        // use.
        //***************************************************************//
        public boolean isSimilar() {
            return (die1.getValue() == die2.getValue());
        }
    }

    //***************************************************************//
    // A public function to return a new instance of the DiceCup.
    //***************************************************************//
    public DiceCup createDiceCup() {
        return new DiceCup();
    }
}

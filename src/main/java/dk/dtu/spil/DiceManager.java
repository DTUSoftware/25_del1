package dk.dtu.spil;

import java.util.Random;

// TODO: Write comments for the DiceManager
public class DiceManager {
    private final int minDieValue = 1; // Sets the minimum value of the die.
    private final int maxDieValue = 6; // Sets the maximum value of the die.
    private final Random rand = new Random();

    private class Die {
        private int value = 6;

        public void raffle() {
            // Random.nextInt() is faster and more efficient than Math.random() (see https://stackoverflow.com/a/738651/12418245)
            // value = (int) ((Math.random() * (maxDieValue-minDieValue+1)) + minDieValue);
            value = rand.nextInt(maxDieValue) + minDieValue;
        }

        public int getValue() {
            return value;
        }
    }

    // Package-private class
    class DiceCup {
        private Die die1;
        private Die die2;

        public DiceCup() {
            die1 = new Die();
            die2 = new Die();
        }

        public void raffleCup() {
            die1.raffle();
            die2.raffle();
        }

        public int[] getDiceValues() {
            return new int[] {die1.getValue(), die2.getValue()};
        }

        public int getSum() {
            return (die1.getValue() + die2.getValue());
        }

        public boolean isSimilar() {
            return (die1.getValue() == die2.getValue());
        }
    }

    public DiceCup createDiceCup() {
        return new DiceCup();
    }
}

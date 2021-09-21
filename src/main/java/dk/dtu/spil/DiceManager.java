package dk.dtu.spil;

import java.util.Random;

public class DiceManager {
    private final int minDieValue = 1; // Sets the minimum value of the die.
    private final int maxDieValue = 6; // Sets the maximum value of the die.
    // private Random rand = new Random();

    public DiceManager() {};

    public class Die {
        private int value = 6;

        public void raffle() {
            value = (int) ((Math.random() * (maxDieValue-minDieValue+1)) + minDieValue);
            // value = rand.nextInt(maxDieValue-minDieValue) + minDieValue;
        }

        public int getValue() {
            return value;
        }
    }

    public class DiceCup {
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
}

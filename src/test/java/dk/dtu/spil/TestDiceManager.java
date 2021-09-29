package dk.dtu.spil;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import dk.dtu.spil.DiceManager.DiceCup;
import java.util.HashMap;

//***************************************************************//
// Class for performing unit tests on the DiceManager class.
//***************************************************************//
public class TestDiceManager {
    //***************************************************************//
    // We initialize an instance of the DiceManager.
    //***************************************************************//
    private final static DiceManager dm = new DiceManager();

    //***************************************************************//
    // We test Die and DiceCup creation.
    //***************************************************************//
    @Test
    public void testDiceCupAndDieCreation() {
        // We create a DiceCup
        DiceCup testCup = dm.createDiceCup();
        // We ensure that we can read the values of the two die
        // and that they are both 6, which is the default on
        // creation.
        assertArrayEquals(new int[] {6, 6}, testCup.getDiceValues());
    }

    //***************************************************************//
    // We test 1000 Die rolls, to ensure that the values are only
    // between 1 and 6.
    //***************************************************************//
    @Test
    public void testDiceCupRaffle() {
        // We create a DiceCup
        DiceCup testCup = dm.createDiceCup();
        // We test the dice cup for 1000 throws
        for (int i = 0; i < 1000; i++) {
            // System.out.println("Testing raffle #" + i+1);
            // We raffle the cup
            testCup.raffleCup();
            // We get the values from the cup
            int[] diceValues = testCup.getDiceValues();
            // Ensure that the value is between 1 and 6
            assertTrue((1 <= diceValues[0] && diceValues[0] <= 6) &&
                    (1 <= diceValues[1] && diceValues[1] <= 6));
        }
    }

    //***************************************************************//
    // We test the DiceCup for 10 million rolls, and ensuring that
    // the probability of the rolls are theoretically correct.
    //***************************************************************//
    @Test
    public void testRaffleProbability() {
        // We create a HashMap to keep how many instances of the
        // different die values that we have rolled
        HashMap<Integer, Integer> allDiceValues = new HashMap<Integer, Integer>() {{
            put(1, 0);
            put(2, 0);
            put(3, 0);
            put(4, 0);
            put(5, 0);
            put(6, 0);
        }};
        // We also keep an integer to keep track of how many equal throws are made
        int equalThrows = 0;

        // We create a new DiceCup.
        DiceCup testCup = dm.createDiceCup();

        // We test the dice cup for x amount of throws
        int throwAmount = 10000000;
        for (int i = 0; i < throwAmount; i++) {
            // We raffle the cup
            testCup.raffleCup();

            // We get the values
            int[] diceValues = testCup.getDiceValues();

            // If the values are equal, add one to equalThrows
            if (diceValues[0] == diceValues[1]) {
                equalThrows++;
            }

            // Add the values of the two dice to the HashMap
            allDiceValues.put(diceValues[0], allDiceValues.get(diceValues[0])+1);
            allDiceValues.put(diceValues[1], allDiceValues.get(diceValues[1])+1);
        }

        // We calculate the ratio between the equal throws and the amount of throws
        double ratio = (((double) equalThrows)/((double) throwAmount));

        //***************************************************************//
        // We ensure that the calculated ratio is within a respectable
        // limit. Theoretical ratio:
        // 6/36 = 1/6 = 0.1666
        //***************************************************************//
        assertTrue((ratio > (0.1666-0.005)) && (ratio < (0.1666+0.005)));

        //***************************************************************//
        // In the following for-loop, we loop through the different die
        // values and ensure that the calculated ratio is within a
        // respectable limit. Theoretical ratio:
        // 1/6 = 0.1666
        //***************************************************************//
        for (int value : allDiceValues.values()) {
            ratio = (((double) value)/((double) (throwAmount*2)));
            assertTrue((ratio > (0.1666-0.005)) && (ratio < (0.1666+0.005)));
        }
    }

}

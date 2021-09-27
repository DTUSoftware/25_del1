package dk.dtu.spil;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import dk.dtu.spil.DiceManager.DiceCup;

import java.util.HashMap;

public class TestDiceManager {
    private final static DiceManager dm = new DiceManager();

    @Test
    public void testDiceCupAndDieCreation() {
        DiceCup testCup = dm.createDiceCup();
        assertArrayEquals(new int[] {6, 6}, testCup.getDiceValues());
    }

    @Test
    public void testDiceCupRaffle() {
        DiceCup testCup = dm.createDiceCup();
        // We test the dice cup for 1000 throws
        for (int i = 0; i < 1000; i++) {
            // System.out.println("Testing raffle #" + i+1);
            testCup.raffleCup();
            int[] diceValues = testCup.getDiceValues();
            assertTrue((1 <= diceValues[0] && diceValues[0] <= 6) &&
                    (1 <= diceValues[1] && diceValues[1] <= 6));
        }
    }

    @Test
    public void testRaffleProbability() {
        HashMap<Integer, Integer> allDiceValues = new HashMap<>() {{
            put(1, 0);
            put(2, 0);
            put(3, 0);
            put(4, 0);
            put(5, 0);
            put(6, 0);
        }};
        int equalThrows = 0;

        DiceCup testCup = dm.createDiceCup();

        // We test the dice cup for x amount of throws
        int throwAmount = 10000000;
        for (int i = 0; i < throwAmount; i++) {
            testCup.raffleCup();

            int[] diceValues = testCup.getDiceValues();

            if (diceValues[0] == diceValues[1]) {
                equalThrows++;
            }

            allDiceValues.put(diceValues[0], allDiceValues.get(diceValues[0])+1);
            allDiceValues.put(diceValues[1], allDiceValues.get(diceValues[1])+1);
        }

        System.out.println("All rolled dice counts: " + allDiceValues);
        double ratio = (((double) equalThrows)/((double) throwAmount));
        System.out.println("Equal throws: " + equalThrows + " - " + ratio);

        // 6/36 = 1/6 = 0.1666
        assertTrue((ratio > (0.1666-0.005)) && (ratio < (0.1666+0.005)));

        // 1/6 = 0.1666
        for (int value : allDiceValues.values()) {
            ratio = (((double) value)/((double) (throwAmount*2)));
            assertTrue((ratio > (0.1666-0.005)) && (ratio < (0.1666+0.005)));
        }
    }

}

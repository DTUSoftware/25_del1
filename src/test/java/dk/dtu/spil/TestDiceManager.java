package dk.dtu.spil;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import dk.dtu.spil.DiceManager.DiceCup;

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

}

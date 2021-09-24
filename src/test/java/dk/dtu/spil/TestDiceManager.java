package dk.dtu.spil;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestDiceManager {
    private final static DiceManager dm = new DiceManager();

    @Test
    public void testDiceCupAndDieCreation() {
        DiceManager.DiceCup testCup = dm.new DiceCup();
        assertArrayEquals(new int[] {6, 6}, testCup.getDiceValues());
    }

    @Test
    public void testDiceCupRaffle() {
        DiceManager.DiceCup testCup = dm.new DiceCup();
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

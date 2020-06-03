package macior.strategygame;

import macior.strategygame.game.RatioSet;
import macior.strategygame.service.executionChain.sharedUtilities.DamageRatiosCalculator;
import org.junit.jupiter.api.Test;

public class BattleTests {

    @Test
    public void testRatios(){
        RatioSet ratioSet = new RatioSet(0.3, 0.6, 0.1);
        DamageRatiosCalculator calculator = new DamageRatiosCalculator();

        RatioSet result = calculator.getDamageRatios(ratioSet, 0.97);
        System.out.println(result);
    }
}

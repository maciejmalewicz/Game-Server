package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades;

import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ProductionManagers2 extends Upgrade {

    public double PRODUCTION_BONUS;

    public ProductionManagers2(){
//        value = 0.2;
//
//        //mining drill, excavator and advanced physics
//        REQUIREMENTS = new int[3];
//        REQUIREMENTS[0] = 2;
//        REQUIREMENTS[1] = 3;
//        REQUIREMENTS[2] = 4;
//
//        COST = new ResourceSet(100, 100, 100);
//
//        RESEARCH_TIME = new Time(1, 0);
    }
    @Override
    public String toString(){
        return COST + " " + Arrays.toString(REQUIREMENTS) + " " + RESEARCH_TIME + " " + PRODUCTION_BONUS;
    }
}

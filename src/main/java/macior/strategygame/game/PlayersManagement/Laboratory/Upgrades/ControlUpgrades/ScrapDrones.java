package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ControlUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ScrapDrones extends Upgrade {

    public double BUILDING_TIME_REDUCTION;

    public ScrapDrones(){
//        value = 0.4;
//
//        //conquerors land
//        REQUIREMENTS[0] = 1;
//
//        COST = new ResourceSet(100, 100, 100);
//
//        RESEARCH_TIME = new Time(1, 0);
    }

    @Override
    public String toString(){
        return COST + " " + Arrays.toString(REQUIREMENTS) + " " + RESEARCH_TIME + " " + BUILDING_TIME_REDUCTION;
    }
}

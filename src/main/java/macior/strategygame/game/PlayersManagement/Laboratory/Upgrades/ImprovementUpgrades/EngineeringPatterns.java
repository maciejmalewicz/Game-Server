package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class EngineeringPatterns extends Upgrade {

    public double COST_REDUCTION;

    public EngineeringPatterns(){
//        value = 0.3;
//
//        //mining drill
//        REQUIREMENTS[0] = 2;
//
//        COST = new ResourceSet(100, 100, 100);
//
//        RESEARCH_TIME = new Time(1, 0);
    }

    @Override
    public String toString(){
        return COST + " " + Arrays.toString(REQUIREMENTS) + " " + RESEARCH_TIME + " " + COST_REDUCTION;
    }
}

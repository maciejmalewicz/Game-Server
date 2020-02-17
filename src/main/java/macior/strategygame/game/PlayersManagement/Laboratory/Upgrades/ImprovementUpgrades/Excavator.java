package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Excavator extends Upgrade {

    public double PRODUCTION_BONUS;

    public Excavator(){
//        value = 0.3;
//
//        //production managers 1
//        REQUIREMENTS[0] = 0;
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

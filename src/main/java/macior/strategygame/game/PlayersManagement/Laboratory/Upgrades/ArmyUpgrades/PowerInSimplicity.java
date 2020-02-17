package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ArmyUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PowerInSimplicity extends Upgrade {

    public double PRODUCTION_TIME_REDUCTION;
    public PowerInSimplicity(){
//        //increase speed production
//        value = 0.5;
//        //none - it's a root of our tree
//        REQUIREMENTS = new int[0];
//
//        COST = new ResourceSet(100, 100, 100);
//
//        RESEARCH_TIME = new Time(1, 0);
    }

    @Override
    public String toString(){
        return COST + " " + Arrays.toString(REQUIREMENTS) + " " + RESEARCH_TIME + " " + PRODUCTION_TIME_REDUCTION;
    }


}

package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;

import java.util.Arrays;

public class Architecture extends Upgrade {



    public Architecture(){
//        value = 1;
//
//        //engineering patterns
//        REQUIREMENTS[0] = 5;
//
//        COST = new ResourceSet(100, 100, 100);
//
//        RESEARCH_TIME = new Time(1, 0);
    }

    @Override
    public String toString(){
        return COST + " " + Arrays.toString(REQUIREMENTS) + " " + RESEARCH_TIME;
    }
}

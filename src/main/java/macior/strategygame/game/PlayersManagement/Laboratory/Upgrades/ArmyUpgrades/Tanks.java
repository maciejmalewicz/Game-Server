package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ArmyUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class Tanks extends Upgrade {
    //unlocks tanks

    public Tanks(){
        //assembly line
//        REQUIREMENTS[0] = 2;
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

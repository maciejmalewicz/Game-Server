package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ControlUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Pyrotechnics extends Upgrade {

    public double ROCKET_RELOADING_TIME_REDUCTION;

    public Pyrotechnics(){
//        value = 0.3;
//
//        //building engineers and (conquer and protect 2)
//        REQUIREMENTS = new int[2];
//        REQUIREMENTS[0] = 10;
//        REQUIREMENTS[1] = 3;
//
//        COST = new ResourceSet(100, 100, 100);
//
//        RESEARCH_TIME = new Time(1, 0);
    }

    @Override
    public String toString(){
        return COST + " " + Arrays.toString(REQUIREMENTS) + " " + RESEARCH_TIME + " " + ROCKET_RELOADING_TIME_REDUCTION;
    }
}

package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ControlUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Infrastructure extends Upgrade {

    public double TRAVELING_TIME_REDUCTION;

    public Infrastructure(){
//        value = 0.3;
//
//        //minesweepers
//        REQUIREMENTS[0] = 0;
//
//        COST = new ResourceSet(100, 100, 100);
//
//        RESEARCH_TIME = new Time(1, 0);
    }

    @Override
    public String toString(){
        return COST + " " + Arrays.toString(REQUIREMENTS) + " " + RESEARCH_TIME + " " + TRAVELING_TIME_REDUCTION;
    }
}

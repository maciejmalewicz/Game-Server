package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ArmyUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SolarPanels extends Upgrade {

    public double ATTACKING_COST_REDUCTION;
    public SolarPanels(){
//        value = 0.3;
//
//        //power in simplicity
//        REQUIREMENTS[0] = 0;
//
//        COST = new ResourceSet(100, 100, 100);
//
//        RESEARCH_TIME = new Time(1, 0);
    }

    @Override
    public String toString(){
        return COST + " " + Arrays.toString(REQUIREMENTS) + " " + RESEARCH_TIME + " " + ATTACKING_COST_REDUCTION;
    }
}

package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ManagersAI extends Upgrade {

    public double BONUS_PER_MINUTE;
    public double MAX_BONUS;

    public ManagersAI(){
//        value = 0.05;
//
//        //production managers 2
//        REQUIREMENTS[0] = 1;
//
//        COST = new ResourceSet(100, 100, 100);
//
//        RESEARCH_TIME = new Time(1, 0);
    }

    @Override
    public String toString(){
        return COST + " " + Arrays.toString(REQUIREMENTS) + " " + RESEARCH_TIME + " " + BONUS_PER_MINUTE + " " + MAX_BONUS;
    }
}

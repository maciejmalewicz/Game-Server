package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//upgrades production of all resources by some %
//nothing is required - root upgrade

@Component
public class ProductionManagers1 extends Upgrade {

    public double PRODUCTION_BONUS;

    @Override
    public String toString(){
        return COST + " " + Arrays.toString(REQUIREMENTS) + " " + RESEARCH_TIME + " " + PRODUCTION_BONUS;
    }
}

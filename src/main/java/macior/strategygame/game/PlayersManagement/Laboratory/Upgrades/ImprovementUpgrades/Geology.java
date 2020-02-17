package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades;

import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Geology extends Upgrade {

    public double EXCHANGE_RATIO;

    public Geology(){

//        value = 0.3;
//
//        //mining drill and excavator
//        REQUIREMENTS = new int[2];
//        REQUIREMENTS[0] = 2;
//        REQUIREMENTS[1] = 3;
//
//        COST = new ResourceSet(100, 100, 100);
//
//        RESEARCH_TIME = new Time(1, 0);
    }

    @Override
    public String toString(){
        return COST + " " + Arrays.toString(REQUIREMENTS) + " " + RESEARCH_TIME + " " + EXCHANGE_RATIO;
    }
}

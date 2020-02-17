package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ControlUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;

public class Infrastructure extends Upgrade {

    public Infrastructure(){
        value = 0.3;

        //minesweepers
        REQUIREMENTS[0] = 0;

        COST = new ResourceSet(100, 100, 100);

        RESEARCH_TIME = new Time(1, 0);
    }
}

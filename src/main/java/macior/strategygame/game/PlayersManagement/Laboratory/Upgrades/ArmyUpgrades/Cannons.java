package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ArmyUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;

public class Cannons extends Upgrade {
    //unlocks cannons

    public Cannons(){
        //tanks
        REQUIREMENTS[0] = 4;

        COST = new ResourceSet(100, 100, 100);

        RESEARCH_TIME = new Time(1, 0);
    }
}

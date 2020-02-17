package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ArmyUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;

public class BattleFormation extends Upgrade {

    public BattleFormation(){
        value = 0.03;

        //cannon platform and mobility
        REQUIREMENTS = new int[2];
        REQUIREMENTS[0] = 8;
        REQUIREMENTS[1] = 5;

        COST = new ResourceSet(100, 100, 100);

        RESEARCH_TIME = new Time(1, 0);
    }
}

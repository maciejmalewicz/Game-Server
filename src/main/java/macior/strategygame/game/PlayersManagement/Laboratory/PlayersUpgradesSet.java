package macior.strategygame.game.PlayersManagement.Laboratory;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ArmyUpgrades.ArmyUpgradeFinder;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ControlUpgrades.ControlUpgradeFinder;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades.ImprovementUpgradesFinder;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;

public class PlayersUpgradesSet {

    //first 11
    //private boolean[] armyUpgrades = new boolean[11];
    //second 11
    //private boolean[] controlUpgrades = new boolean[11];
    //third 11
    //private boolean[] improvementUpgrades = new boolean[11];

    private boolean[] upgrades = new boolean[33];

    public boolean upgraded(int index){
        return upgrades[index];
    }
}

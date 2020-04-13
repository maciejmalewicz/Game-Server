package macior.strategygame.game.PlayersManagement.Laboratory;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ArmyUpgrades.ArmyUpgradeFinder;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ControlUpgrades.ControlUpgradeFinder;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades.ImprovementUpgradesFinder;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;

public class PlayersUpgradesSet {

    private boolean[] upgrades = new boolean[33];

    public PlayersUpgradesSet(){
        int[] upgraded = {29};
        for (int a: upgraded){
            upgrades[a] = true;
        }
    }

    public boolean upgraded(int index){
        return upgrades[index];
    }

    public boolean[] getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(boolean[] upgrades) {
        this.upgrades = upgrades;
    }
}

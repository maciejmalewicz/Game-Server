package macior.strategygame.game.PlayersManagement.Laboratory;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ArmyUpgrades.ArmyUpgradeFinder;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ControlUpgrades.ControlUpgradeFinder;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades.ImprovementUpgradesFinder;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;

public class PlayersUpgradesSet {

    private boolean[] armyUpgrades = new boolean[11];
    private boolean[] controlUpgrades = new boolean[11];
    private boolean[] improvementUpgrades = new boolean[11];

    public PlayersUpgradesSet(){

    }

    public static void main(String[] args){
        PlayersUpgradesSet upgrades = new PlayersUpgradesSet();
        upgrades.upgradeArmy(0);
        upgrades.upgradeArmy(2);
        upgrades.upgradeArmy(9);
    }

    public boolean hasArmyUpgrade(int upgrade){
        return armyUpgrades[upgrade];
    }

    public boolean hasControlUpgrade(int upgrade){
        return controlUpgrades[upgrade];
    }

    public boolean hasImprovementUpgrade(int upgrade){
        return improvementUpgrades[upgrade];
    }

    public boolean upgradeArmy(int index){
        Upgrade upgrade = ArmyUpgradeFinder.get(index);
        if (upgrade.canBeUpgraded(armyUpgrades)){
            armyUpgrades[index] = true;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean upgradeControl(int index){
        Upgrade upgrade = ControlUpgradeFinder.get(index);
        if (upgrade.canBeUpgraded(armyUpgrades)){
            armyUpgrades[index] = true;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean upgradeImprovement(int index){
        Upgrade upgrade = ImprovementUpgradesFinder.get(index);
        if (upgrade.canBeUpgraded(armyUpgrades)){
            armyUpgrades[index] = true;
            return true;
        }
        else {
            return false;
        }
    }



}

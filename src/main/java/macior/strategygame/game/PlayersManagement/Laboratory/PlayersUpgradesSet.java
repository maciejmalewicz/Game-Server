package macior.strategygame.game.PlayersManagement.Laboratory;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;

public class PlayersUpgradesSet {

    private boolean[] upgrades = new boolean[33];

    public PlayersUpgradesSet(){
        int[] upgraded = {Upgrades.TANKS, Upgrades.CANNONS, Upgrades.ASSEMBLY_LINES, Upgrades.MASS_PRODUCTION,
        Upgrades.POWER_IN_SIMPLICITY};
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

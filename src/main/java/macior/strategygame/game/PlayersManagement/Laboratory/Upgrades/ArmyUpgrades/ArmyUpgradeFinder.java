package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ArmyUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;

import java.util.HashMap;

public final class ArmyUpgradeFinder {

    private static HashMap<Integer, Upgrade> map
            = new HashMap<Integer, Upgrade>();

    static {
        map.put(0, new PowerInSimplicity());
        map.put(1, new AdvancedDroids());
        map.put(2, new AssemblyLines());
        map.put(3, new MassProduction());
        map.put(4, new Tanks());
        map.put(5, new Mobility());
        map.put(6, new ShootingWindow());
        map.put(7, new Cannons());
        map.put(8, new CannonPlatform());
        map.put(9, new BattleFormation());
        map.put(10, new SolarPanels());
    }


    public static Upgrade get(int index){
        return map.get(index);
    }

}

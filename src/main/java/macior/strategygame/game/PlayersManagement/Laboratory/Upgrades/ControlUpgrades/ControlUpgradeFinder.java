package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ControlUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;

import java.util.HashMap;

public final class ControlUpgradeFinder {

    private static HashMap<Integer, Upgrade> map
            = new HashMap<Integer, Upgrade>();

    static {
        map.put(0, new Minesweepers());
        map.put(1, new ConquerorsLand());
        map.put(2, new TreasureHaunters());
        map.put(3, new BuildingEngineers());
        map.put(4, new ScrapDrones());
        map.put(5, new Fortress());
        map.put(6, new Pyrotechnics());
        map.put(7, new Accuracy());
        map.put(8, new Infrastructure());
        map.put(9, new ConquerAndProtect1());
        map.put(10, new ConquerAndProtect2());
    }


    public static Upgrade get(int index){
        return map.get(index);
    }
}

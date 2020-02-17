package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades;

import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;

import java.util.HashMap;

public final class ImprovementUpgradesFinder {

    private static HashMap<Integer, Upgrade> map
            = new HashMap<Integer, Upgrade>();

    static {
        map.put(0, new ProductionManagers1());
        map.put(1, new ProductionManagers2());
        map.put(2, new MiningDrill());
        map.put(3, new Excavator());
        map.put(4, new AdvancedPhysics());
        map.put(5, new EngineeringPatterns());
        map.put(6, new SpaceManagement());
        map.put(7, new TransportTrains());
        map.put(8, new Geology());
        map.put(9, new Architecture());
        map.put(10, new ManagersAI());
    }


    public static Upgrade get(int index){
        return map.get(index);
    }
}

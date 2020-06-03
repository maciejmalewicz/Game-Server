package macior.strategygame.service.executionChain.sharedUtilities;

import macior.strategygame.game.BattlesManagement.Army;
import macior.strategygame.game.RatioSet;
import org.springframework.stereotype.Component;

@Component
public class ArmyDamageDealer {

    public void damageArmy(Army army, RatioSet damageRatios){
        int droids = (int)Math.ceil(army.getDroids()*(1-damageRatios.firstRatio));
        int tanks = (int)Math.ceil(army.getTanks()*(1-damageRatios.secondRatio));
        int cannons = (int)Math.ceil(army.getCannons()*(1-damageRatios.thirdRatio));

        army.setDroids(droids);
        army.setTanks(tanks);
        army.setCannons(cannons);
    }
}

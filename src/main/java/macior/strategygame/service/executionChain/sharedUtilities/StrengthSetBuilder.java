package macior.strategygame.service.executionChain.sharedUtilities;

import macior.strategygame.game.BattlesManagement.Army;
import macior.strategygame.models.game.configuration.ArmyBalanceConfig;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.service.executionChain.models.StrengthSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StrengthSetBuilder {

    @Autowired
    private GameConfiguration configuration;

    public StrengthSet build(Army army){

        ArmyBalanceConfig config = configuration.getArmyBalanceConfig();
        StrengthSet strengthSet = new StrengthSet();

        strengthSet.DROIDS_ATTACK = config.getDroidsConfig().ATTACK*army.getDroids();
        strengthSet.DROIDS_DEFENCE = config.getDroidsConfig().DEFENCE*army.getDroids();

        strengthSet.TANKS_ATTACK = config.getTanksConfig().ATTACK*army.getTanks();
        strengthSet.TANKS_DEFENCE = config.getTanksConfig().DEFENCE*army.getTanks();

        strengthSet.CANNONS_ATTACK = config.getCannonsConfig().ATTACK*army.getCannons();
        strengthSet.CANNONS_DEFENCE = config.getCannonsConfig().DEFENCE*army.getCannons();

        return strengthSet;
    }
}

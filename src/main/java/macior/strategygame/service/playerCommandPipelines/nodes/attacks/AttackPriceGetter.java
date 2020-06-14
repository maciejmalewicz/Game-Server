package macior.strategygame.service.playerCommandPipelines.nodes.attacks;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BattlesManagement.Army;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.service.playerCommandPipelines.models.ArmyTransferModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackPriceGetter extends ChainNode<ArmyTransferModel> {

    @Autowired
    private GameConfiguration configuration;

    @Override
    public void execute(ArmyTransferModel model, ChainExecutor executor) {
        AttackRequest request = (AttackRequest)model.REQUEST;
        model.PRICE = getPriceToPay(request.getArmy(), model.PLAYER);
    }

    private ResourceSet getPriceToPay(Army toSend, Player buyer){
        ResourceSet toPay = new ResourceSet(0, 0, 0);
        double marchingCost = 0;

        double droidsCost
                = configuration.getArmyBalanceConfig().getDroidsConfig().MARCHING_ELECTRICITY_COST
                * toSend.getDroids();
        marchingCost += droidsCost;

        double tanksCost
                = configuration.getArmyBalanceConfig().getTanksConfig().MARCHING_ELECTRICITY_COST
                * toSend.getTanks();
        marchingCost += tanksCost;

        double cannonsCost
                = configuration.getArmyBalanceConfig().getCannonsConfig().MARCHING_ELECTRICITY_COST
                * toSend.getCannons();
        marchingCost += cannonsCost;

        double coefficient = configuration.getMainConfig().ATTACKING_COST_COEFFICIENT;

        if (buyer.getUpgradesSet().upgraded(Upgrades.SOLAR_PANELS)){

            double attackingCostReduction = configuration.getUpgradesConfig()
                    .getArmyUpgradesConfig().getSolarPanels().ATTACKING_COST_REDUCTION;

            coefficient = coefficient*(1-attackingCostReduction);
        }
        double attackingCost = marchingCost * coefficient;

        toPay.ELECTRICITY += (marchingCost + attackingCost);

        return toPay.canPurchase(buyer);
    }


}

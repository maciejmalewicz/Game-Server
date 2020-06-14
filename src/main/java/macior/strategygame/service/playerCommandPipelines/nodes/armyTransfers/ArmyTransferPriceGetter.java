package macior.strategygame.service.playerCommandPipelines.nodes.armyTransfers;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BattlesManagement.Army;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.service.playerCommandPipelines.models.ArmyTransferModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArmyTransferPriceGetter extends ChainNode<ArmyTransferModel> {

    @Autowired
    private GameConfiguration configuration;

    @Override
    public void execute(ArmyTransferModel model, ChainExecutor executor) {
        ArmyTransferRequest request = (ArmyTransferRequest)model.REQUEST;
        model.PRICE = getPriceToPay(request.getArmy(), model.PLAYER);
    }

    private ResourceSet getPriceToPay(Army toSend, Player buyer){
        ResourceSet toPay = new ResourceSet(0, 0, 0);

        double droidsCost
                = configuration.getArmyBalanceConfig().getDroidsConfig().MARCHING_ELECTRICITY_COST
                * toSend.getDroids();
        toPay.ELECTRICITY += (int)droidsCost;

        double tanksCost
                = configuration.getArmyBalanceConfig().getTanksConfig().MARCHING_ELECTRICITY_COST
                * toSend.getDroids();
        toPay.ELECTRICITY += (int)tanksCost;

        double cannonsCost
                = configuration.getArmyBalanceConfig().getCannonsConfig().MARCHING_ELECTRICITY_COST
                * toSend.getCannons();
        toPay.ELECTRICITY += (int)cannonsCost;

        return toPay.canPurchase(buyer);
    }
}

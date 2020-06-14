package macior.strategygame.service.playerCommandPipelines.nodes.armyTransfers;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BattlesManagement.Army;
import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.service.playerCommandPipelines.models.ArmyTransferModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class ArmyTransferArmyValidator extends ChainNode<ArmyTransferModel> {

    @Override
    public void execute(ArmyTransferModel model, ChainExecutor executor) {
        ArmyTransferRequest request = (ArmyTransferRequest)model.REQUEST;

        Army currentArmy = model.AREA_UNIT.getArmy();
        Army toSend = request.getArmy();

        if (isArmyNegative(toSend)){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.NEGATIVE_ARMY_AMOUNT);
        }

        if (isArmyEmpty(toSend)){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.EMPTY_ARMY);
        }

        if (!hasEnoughArmy(currentArmy, toSend)){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.TOO_BIG_ARMY);
        }
    }

    private boolean isArmyNegative(Army toSend){
        return toSend.getDroids() < 0 || toSend.getTanks() < 0 || toSend.getCannons() < 0;
    }

    private boolean isArmyEmpty(Army toSend){
        return toSend.getDroids() == 0 && toSend.getTanks() == 0 && toSend.getCannons() == 0;
    }

    private boolean hasEnoughArmy(Army current, Army toSend){
        return toSend.getDroids() <= current.getDroids()
                && toSend.getTanks() <= current.getTanks()
                && toSend.getCannons() <= current.getCannons();
    }


}

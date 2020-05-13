package macior.strategygame.service.chainOfResponsibility.nodes.armyTransfers;

import macior.strategygame.game.BattlesManagement.Army;
import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.service.chainOfResponsibility.models.ArmyTransferModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class ArmyTransferArmyValidator extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTransferModel transferModel = (ArmyTransferModel)model;
        ArmyTransferRequest request = (ArmyTransferRequest)transferModel.REQUEST;

        Army currentArmy = transferModel.AREA_UNIT.getArmy();
        Army toSend = request.getArmy();

        if (isArmyNegative(toSend)){
            model.RESPONSE.setStatus(GameErrors.NEGATIVE_ARMY_AMOUNT);
        }

        if (isArmyEmpty(toSend)){
            model.RESPONSE.setStatus(GameErrors.EMPTY_ARMY);
        }

        if (!hasEnoughArmy(currentArmy, toSend)){
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

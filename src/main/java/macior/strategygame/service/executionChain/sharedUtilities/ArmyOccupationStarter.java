package macior.strategygame.service.executionChain.sharedUtilities;

import macior.strategygame.service.executionChain.models.BattleModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ArmyOccupationStarter {

    public void startOccupation(BattleModel model){
        model.ATTACK.TO.setArmy(model.ATTACK.ARMY);
    }

}

package macior.strategygame.service.battlePipelines.sharedUtilities;

import macior.strategygame.service.battlePipelines.models.BattleModel;
import org.springframework.stereotype.Component;

@Component
public class ArmyOccupationStarter {

    public void startOccupation(BattleModel model){
        model.ATTACK.TO.setArmy(model.ATTACK.ARMY);
    }

}

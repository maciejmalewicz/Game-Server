package macior.strategygame.service.executionChain.sharedUtilities;

import macior.strategygame.service.executionChain.models.BattleModel;
import org.springframework.stereotype.Component;


@Component
public class AreaConqueringService {

    //launched if attacker wins
    public void conquerArea(BattleModel battleModel){
        //player has the field now
        battleModel.ATTACK.TO.setOwner(battleModel.ATTACK.ATTACKER);
    }


}

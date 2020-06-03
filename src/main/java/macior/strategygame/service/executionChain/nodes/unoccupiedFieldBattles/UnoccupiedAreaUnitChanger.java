package macior.strategygame.service.executionChain.nodes.unoccupiedFieldBattles;

import macior.strategygame.service.executionChain.models.BattleModel;
import macior.strategygame.service.executionChain.nodes.ExecutionNode;
import macior.strategygame.service.executionChain.sharedUtilities.AreaConqueringService;
import macior.strategygame.service.executionChain.sharedUtilities.ArmyOccupationStarter;
import macior.strategygame.service.executionChain.sharedUtilities.MainBuildingDamagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnoccupiedAreaUnitChanger extends ExecutionNode<BattleModel> {

    @Autowired
    private AreaConqueringService conqueringService;

    @Autowired
    private MainBuildingDamagingService mainBuildingDamagingService;

    @Autowired
    private ArmyOccupationStarter armyOccupationStarter;

    @Override
    public void execute(BattleModel model) {
        if (model.ATTACKER_HAS_WON){
            conqueringService.conquerArea(model); //setting new owner
            mainBuildingDamagingService.damageMainBuilding(model); //damaging main building
            armyOccupationStarter.startOccupation(model); //sending attacking army there
        }
    }


}

package macior.strategygame.service.battlePipelines.nodes.unoccupiedFieldBattles;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.service.battlePipelines.models.BattleModel;
import macior.strategygame.service.battlePipelines.sharedUtilities.AreaConqueringService;
import macior.strategygame.service.battlePipelines.sharedUtilities.ArmyOccupationStarter;
import macior.strategygame.service.battlePipelines.sharedUtilities.MainBuildingDamagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnoccupiedAreaUnitChanger extends ChainNode<BattleModel> {

    @Autowired
    private AreaConqueringService conqueringService;

    @Autowired
    private MainBuildingDamagingService mainBuildingDamagingService;

    @Autowired
    private ArmyOccupationStarter armyOccupationStarter;


    @Override
    public void execute(BattleModel model, ChainExecutor executor) {
        if (model.ATTACKER_HAS_WON){
            conqueringService.conquerArea(model); //setting new owner
            mainBuildingDamagingService.damageMainBuilding(model); //damaging main building
            armyOccupationStarter.startOccupation(model); //sending attacking army there
        }
    }
}

package macior.strategygame.service.game.playersControlls;

import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.chainOfResponsibility.ChainOfResponsibility;
import macior.strategygame.service.chainOfResponsibility.nodes.AreaUnitValidator;
import macior.strategygame.service.chainOfResponsibility.nodes.CodeChangingNode;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.chainOfResponsibility.nodes.PlayerRetrievingNode;
import macior.strategygame.service.chainOfResponsibility.nodes.armyTraining.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainArmyService {

    private ChainOfResponsibility chainOfResponsibility;

    @Autowired
    public TrainArmyService(CodeChangingNode codeChangingNode,
                            PlayerRetrievingNode playerRetrievingNode,
                            ArmyTrainingRequestValidator armyTrainingRequestValidator,
                            AreaUnitValidator areaUnitValidator,
                            ArmyTrainingBuildingGetter buildingGetter,
                            ArmyTrainingUpgradesValidator upgradesValidator,
                            ArmyTrainingConfigurationGetter configurationGetter,
                            ArmyTrainingPriceGetter priceGetter,
                            ArmyTrainingTimeGetter timeGetter
                            ){

        chainOfResponsibility = new ChainOfResponsibility( new Node[]{
                codeChangingNode,
                playerRetrievingNode,
                armyTrainingRequestValidator,
                areaUnitValidator,
                buildingGetter,
                upgradesValidator,
                configurationGetter,
                priceGetter,
                timeGetter
        });
    }

    public TimeResponse trainArmy(String code, ArmyTrainingRequest request){
        return null;
    }
}

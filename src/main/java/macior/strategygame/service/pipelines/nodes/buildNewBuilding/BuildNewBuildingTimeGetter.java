package macior.strategygame.service.pipelines.nodes.buildNewBuilding;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.pipelines.models.BuildNewBuildingModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.game.playersControlls.BuildingsPlacesMapperService;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildNewBuildingTimeGetter extends ChainNode<BuildNewBuildingModel> {

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    private BuildingsPlacesMapperService buildingsMapper;

    @Autowired
    private GameConfiguration configuration;

    @Override
    public void execute(BuildNewBuildingModel model, ChainExecutor executor) {
        BuildingRequest request = (BuildingRequest)model.REQUEST;
        //updating model
        model.FINISHING_TIME = getTimeWhenFinishes(model.PLAYER, request);
        //updating response
        TimeResponse response = (TimeResponse)model.RESPONSE;
        response.setFinishingTime(model.FINISHING_TIME);
    }

    private int getTimeWhenFinishes(Player player, BuildingRequest request){
        BuildingConfig config = buildingsMapper.getConfiguration(request.getBuilding());
        int seconds = config.getTime(1).toSeconds();
        seconds = applyDurationDiscounts(seconds, request, player.getUpgradesSet());
        return player.getGame().getTimeManager().getPostponedEventTime(seconds);
    }

    private int applyDurationDiscounts(int time, BuildingRequest request, PlayersUpgradesSet upgrades){
        double discount = 0.0;
        if (request.getBuilding() == 4 && upgrades.upgraded(Upgrades.SCRAP_DRONES)){
            discount += configuration.getUpgradesConfig().getControlUpgradesConfig()
                    .getScrapDrones().BUILDING_TIME_REDUCTION;
        }
        if (upgrades.upgraded(Upgrades.BUILDING_ENGINEERS)){
            discount += configuration.getUpgradesConfig().getControlUpgradesConfig()
                    .getBuildingEngineers().BUILDING_TIME_REDUCTION;
        }
        return (int)((1-discount)*time);
    }


}

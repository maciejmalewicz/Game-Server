package macior.strategygame.service.chainOfResponsibility.nodes.buildNewBuilding;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.service.chainOfResponsibility.models.BuildNewBuildingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.game.playersControlls.BuildingsPlacesMapperService;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildNewBuildingTimeGetter extends Node {

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    private BuildingsPlacesMapperService buildingsMapper;

    @Autowired
    private GameConfiguration configuration;

    @Override
    public void applyChanges(ChainModel model) {
        BuildNewBuildingModel buildingModel = (BuildNewBuildingModel)model;
        buildingModel.FINISHING_TIME = getTimeWhenFinishes(buildingModel.PLAYER, buildingModel.REQUEST);
    }

    private int getTimeWhenFinishes(Player player, BuildingRequest request){
        BuildingConfig config = buildingsMapper.getConfiguration(request.getBuilding());
        int seconds = config.LEVEL1_BUILDING_TIME.toSeconds();
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

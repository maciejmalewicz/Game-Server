package macior.strategygame.service.chainOfResponsibility.nodes;

import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.service.chainOfResponsibility.models.BuildNewBuildingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.models.PlayerChangesModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerRetrievingNode extends Node{

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    private RequestsValidator requestsValidator;


    @Override
    protected Node getNext(ChainModel model) {
        return requestsValidator;
    }

    @Override
    protected void applyChanges(ChainModel model) {
        Player player = mapper.getPlayerById(model.ID);
        if (player == null){
            model.RESPONSE.setStatus(GameErrors.GAME_NOT_FOUND);
        }
        ((PlayerChangesModel) model).PLAYER = player;
        System.out.println();
    }
}

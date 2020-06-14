package macior.strategygame.service.pipelines.nodes;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.PlayerChangesModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerRetrievingNode extends ChainNode<PlayerChangesModel> {

    @Autowired
    private PlayerGameMapperService mapper;

    @Override
    public void execute(PlayerChangesModel model, ChainExecutor executor) {
        Player player = mapper.getPlayerById(model.ID);
        if (player == null){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.GAME_NOT_FOUND);
        }
        model.PLAYER = player;
    }
}

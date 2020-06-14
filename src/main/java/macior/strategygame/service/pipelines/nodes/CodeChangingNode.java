package macior.strategygame.service.pipelines.nodes;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CodeChangingNode extends ChainNode<ChainModel> {

    @Autowired
    private PlayerGameMapperService mapper;

    @Override
    public void execute(ChainModel model, ChainExecutor executor) {
        int id = mapper.getId(model.CODE);
        if (id == -1){
            model.RESPONSE.setStatus(GameErrors.ACCESS_DENIED);
            model.RESPONSE.setCode("UNKNOWN CODE");
            executor.stop();
        }
        String newCode = mapper.updateCode(model.CODE);
        model.RESPONSE.setCode(newCode);
        model.ID = id;
    }
}

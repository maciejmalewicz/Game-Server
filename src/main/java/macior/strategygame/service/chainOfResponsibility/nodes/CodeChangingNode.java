package macior.strategygame.service.chainOfResponsibility.nodes;

import macior.strategygame.models.StatusResponse;
import macior.strategygame.service.chainOfResponsibility.models.IChainModel;

public class CodeChangingNode extends Node{


    @Override
    protected StatusResponse forward(IChainModel model) {
        return null;
    }

    @Override
    protected StatusResponse applyChanges(IChainModel model) {
        return null;
    }
}

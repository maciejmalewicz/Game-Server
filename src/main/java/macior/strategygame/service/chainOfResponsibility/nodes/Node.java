package macior.strategygame.service.chainOfResponsibility.nodes;

import macior.strategygame.models.StatusResponse;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;

public abstract class Node {

    //forwarding to the next node
    protected abstract StatusResponse forward(ChainModel model);

    //doing some actions
    protected abstract StatusResponse applyChanges(ChainModel model);

    public StatusResponse invoke(ChainModel model){
        applyChanges(model);
        return null;
    }
}

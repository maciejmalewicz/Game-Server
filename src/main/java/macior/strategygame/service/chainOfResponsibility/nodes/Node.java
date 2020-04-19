package macior.strategygame.service.chainOfResponsibility.nodes;

import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;

public abstract class Node {


    //functions to implement

    //this one is for accessing next node, depending on model type
    //should return null if there are no more nodes
    protected abstract Node getNext(ChainModel model);

    //here placing exact code, that changes the model and game
    protected abstract void applyChanges(ChainModel model);

    public StatusResponse invoke(ChainModel model){
        applyChanges(model);
        if (model.RESPONSE.getStatus() != 0){
            return model.RESPONSE;
        }
        return forward(model);
    }

    //forwarding to the next node
    private StatusResponse forward(ChainModel model){
        if (isEndNode(model)){
            return model.RESPONSE;
        }
        return getNext(model).invoke(model);
    }

    private boolean isEndNode(ChainModel model){
        return getNext(model) == null;
    }
}

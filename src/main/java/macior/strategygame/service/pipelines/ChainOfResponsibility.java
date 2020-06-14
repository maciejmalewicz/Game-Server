package macior.strategygame.service.pipelines;

import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;

public class ChainOfResponsibility {

    private Node[] nodes;

    public ChainOfResponsibility(Node[] nodes){
        this.nodes = nodes;
    }

    public StatusResponse execute(ChainModel model){
        for (Node node: nodes){
            node.applyChanges(model);
            if (model.RESPONSE.getStatus() != 0){
                return model.RESPONSE;
            }
        }
        return model.RESPONSE;
    }
}

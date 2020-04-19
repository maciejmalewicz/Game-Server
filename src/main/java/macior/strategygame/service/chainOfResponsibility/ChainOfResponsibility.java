package macior.strategygame.service.chainOfResponsibility;

import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;

public class ChainOfResponsibility {

    private Node[] nodes;

    public ChainOfResponsibility(Node[] nodes){
        this.nodes = nodes;
    }

    public StatusResponse execute(ChainModel model){
        for (Node node: nodes){
            node.invoke(model);
        }
    }
}

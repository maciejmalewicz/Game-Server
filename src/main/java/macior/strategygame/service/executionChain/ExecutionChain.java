package macior.strategygame.service.executionChain;

import macior.strategygame.service.executionChain.nodes.ExecutionNode;

public abstract class ExecutionChain <Output, Model> {

    private ExecutionNode<Model>[] nodes;

    public ExecutionChain(ExecutionNode<Model>[] executionNodes){
        nodes = executionNodes;
    }

    public Output execute(Model model){
        for (ExecutionNode<Model> node : nodes){
            node.execute(model);
        }
        return extractOutput(model);
    }

    protected abstract Output extractOutput(Model model);

}

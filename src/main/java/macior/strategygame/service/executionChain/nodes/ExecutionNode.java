package macior.strategygame.service.executionChain.nodes;

public abstract class ExecutionNode<T> {

    public abstract void execute(T model);
}

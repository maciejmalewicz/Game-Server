package macior.strategygame.service.battlePipelines.nodes;

public abstract class ExecutionNode<T> {

    public abstract void execute(T model);
}

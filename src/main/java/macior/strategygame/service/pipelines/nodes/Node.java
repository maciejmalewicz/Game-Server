package macior.strategygame.service.pipelines.nodes;

import macior.strategygame.service.pipelines.models.ChainModel;

public abstract class Node {

    //here placing exact code, that changes the model and game
    //if something goes wrong, should set the status to some error code number
    public abstract void applyChanges(ChainModel model);

}

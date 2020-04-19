package macior.strategygame.service.chainOfResponsibility.nodes;

import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;

public abstract class Node {

    //here placing exact code, that changes the model and game
    //if something goes wrong, should set the status to some error code number
    public abstract void applyChanges(ChainModel model);

}

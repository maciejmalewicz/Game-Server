package macior.strategygame.service.pipelines.models;

import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.Utilities.ResourceSet;

public class PlayerChangesModel extends ChainModel {

    public Player PLAYER;
    public int FINISHING_TIME;
    public ResourceSet PRICE;
}

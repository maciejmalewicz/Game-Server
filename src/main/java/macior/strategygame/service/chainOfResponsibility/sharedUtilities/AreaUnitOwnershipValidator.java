package macior.strategygame.service.chainOfResponsibility.sharedUtilities;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.game.PlayersManagement.Player;
import org.springframework.stereotype.Component;

@Component
public class AreaUnitOwnershipValidator {

    public boolean isLocationOwnedBy(Player player, Location location){
        AreaUnit unit = player.getGame().getBoard().getAreaUnit(location);
        return isAreaOwnedBy(player, unit);
    }

    public boolean isAreaOwnedBy(Player player, AreaUnit unit){
        if (unit == null){
            return false;
        }
        return unit.getOwner() == player;
    }
}

package macior.strategygame.game.BoardManagement;


import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.BigBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.SmallBuilding;
import macior.strategygame.game.PlayersManagement.Player;

public class AreaUnit {

    private Player owner = null;
    private Location location;
    private BigBuilding bigBuilding = null;

    private SmallBuilding northBuilding = null;
    private SmallBuilding southBuilding = null;
    private SmallBuilding westBuilding = null;
    private SmallBuilding eastBuilding = null;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public BigBuilding getBigBuilding() {
        return bigBuilding;
    }

    public void setBigBuilding(BigBuilding bigBuilding) {
        this.bigBuilding = bigBuilding;
    }
}

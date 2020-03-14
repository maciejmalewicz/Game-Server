package macior.strategygame.game.BoardManagement;


import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.BigBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.SmallBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.Walls;
import macior.strategygame.game.PlayersManagement.Player;

public class AreaUnit {

    private Player owner = null;
    private Location location = null;
    private BigBuilding bigBuilding = null;
    private Walls walls = null;

    private SmallBuilding northBuilding = null;
    private SmallBuilding southBuilding = null;
    private SmallBuilding westBuilding = null;
    private SmallBuilding eastBuilding = null;

    public Walls getWalls() {
        return walls;
    }

    public void setWalls(Walls walls) {
        this.walls = walls;
    }

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

    public SmallBuilding getNorthBuilding() {
        return northBuilding;
    }

    public void setNorthBuilding(SmallBuilding northBuilding) {
        this.northBuilding = northBuilding;
    }

    public SmallBuilding getSouthBuilding() {
        return southBuilding;
    }

    public void setSouthBuilding(SmallBuilding southBuilding) {
        this.southBuilding = southBuilding;
    }

    public SmallBuilding getWestBuilding() {
        return westBuilding;
    }

    public void setWestBuilding(SmallBuilding westBuilding) {
        this.westBuilding = westBuilding;
    }

    public SmallBuilding getEastBuilding() {
        return eastBuilding;
    }

    public void setEastBuilding(SmallBuilding eastBuilding) {
        this.eastBuilding = eastBuilding;
    }
}

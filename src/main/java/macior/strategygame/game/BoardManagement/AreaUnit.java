package macior.strategygame.game.BoardManagement;


import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.PlayersManagement.Player;

public class AreaUnit {

    private Player owner = null;
    private Location location = null;
    private Building bigBuilding = null;
    private Building walls = null;

    private Building northBuilding = null;
    private Building southBuilding = null;
    private Building westBuilding = null;
    private Building eastBuilding = null;

    private BuildingQueue buildingQueue = new BuildingQueue();

    public BuildingQueue getBuildingQueue() {
        return buildingQueue;
    }

    public void setBuildingQueue(BuildingQueue buildingQueue) {
        this.buildingQueue = buildingQueue;
    }

    public Building getWalls() {
        return walls;
    }

    public void setWalls(Building walls) {
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

    public Building getBigBuilding() {
        return bigBuilding;
    }

    public void setBigBuilding(Building bigBuilding) {
        this.bigBuilding = bigBuilding;
    }

    public Building getNorthBuilding() {
        return northBuilding;
    }

    public void setNorthBuilding(Building northBuilding) {
        this.northBuilding = northBuilding;
    }

    public Building getSouthBuilding() {
        return southBuilding;
    }

    public void setSouthBuilding(Building southBuilding) {
        this.southBuilding = southBuilding;
    }

    public Building getWestBuilding() {
        return westBuilding;
    }

    public void setWestBuilding(Building westBuilding) {
        this.westBuilding = westBuilding;
    }

    public Building getEastBuilding() {
        return eastBuilding;
    }

    public void setEastBuilding(Building eastBuilding) {
        this.eastBuilding = eastBuilding;
    }

    public void setBuilding(int index, Building building){
        switch (index){
            case 1:
                setBigBuilding( building);
                break;
            case 2:
                setNorthBuilding(building);
                break;
            case 3:
                setSouthBuilding(building);
                break;
            case 4:
                setWestBuilding(building);
                break;
            case 5:
                setEastBuilding(building);
                break;
            case 6:
                setWalls(building);
                break;
        }
    }

    public int getPlace(Building building){
        if (building == getBigBuilding()){
            return 1;
        }
        if (building == getNorthBuilding()){
            return 2;
        }
        if (building == getSouthBuilding()){
            return 3;
        }
        if (building == getWestBuilding()){
            return 4;
        }
        if (building == getEastBuilding()){
            return 5;
        }
        return -1;
    }

}

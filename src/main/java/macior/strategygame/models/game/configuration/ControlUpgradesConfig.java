package macior.strategygame.models.game.configuration;

import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ControlUpgrades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ControlUpgradesConfig {

    @Autowired
    @Qualifier("minesweepersBean") //todo qualifiers for the rest, delete primary and secondary values
    private Minesweepers minesweepers;

    @Autowired
    @Qualifier("conquerorsLandBean")
    private ConquerorsLand conquerorsLand;

    @Autowired
    @Qualifier("treasureHauntersBean")
    private TreasureHaunters treasureHaunters;

    @Autowired
    @Qualifier("buildingEngineersBean")
    private BuildingEngineers buildingEngineers;

    @Autowired
    @Qualifier("scrapDronesBean")
    private ScrapDrones scrapDrones;

    @Autowired
    @Qualifier("fortressBean")
    private Fortress fortress;

    @Autowired
    @Qualifier("pyrotechnicsBean")
    private Pyrotechnics pyrotechnics;

    @Autowired
    @Qualifier("accuracyBean")
    private Accuracy accuracy;

    @Autowired
    @Qualifier("infrastructureBean")
    private Infrastructure infrastructure;

    @Autowired
    @Qualifier("conquerAndProtect1Bean")
    private ConquerAndProtect1 conquerAndProtect1;

    @Autowired
    @Qualifier("conquerAndProtect2Bean")
    private ConquerAndProtect2 conquerAndProtect2;

    public Minesweepers getMinesweepers() {
        return minesweepers;
    }

    public void setMinesweepers(Minesweepers minesweepers) {
        this.minesweepers = minesweepers;
    }

    public ConquerorsLand getConquerorsLand() {
        return conquerorsLand;
    }

    public void setConquerorsLand(ConquerorsLand conquerorsLand) {
        this.conquerorsLand = conquerorsLand;
    }

    public TreasureHaunters getTreasureHaunters() {
        return treasureHaunters;
    }

    public void setTreasureHaunters(TreasureHaunters treasureHaunters) {
        this.treasureHaunters = treasureHaunters;
    }

    public BuildingEngineers getBuildingEngineers() {
        return buildingEngineers;
    }

    public void setBuildingEngineers(BuildingEngineers buildingEngineers) {
        this.buildingEngineers = buildingEngineers;
    }

    public ScrapDrones getScrapDrones() {
        return scrapDrones;
    }

    public void setScrapDrones(ScrapDrones scrapDrones) {
        this.scrapDrones = scrapDrones;
    }

    public Fortress getFortress() {
        return fortress;
    }

    public void setFortress(Fortress fortress) {
        this.fortress = fortress;
    }

    public Pyrotechnics getPyrotechnics() {
        return pyrotechnics;
    }

    public void setPyrotechnics(Pyrotechnics pyrotechnics) {
        this.pyrotechnics = pyrotechnics;
    }

    public Accuracy getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
    }

    public Infrastructure getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }

    public ConquerAndProtect1 getConquerAndProtect1() {
        return conquerAndProtect1;
    }

    public void setConquerAndProtect1(ConquerAndProtect1 conquerAndProtect1) {
        this.conquerAndProtect1 = conquerAndProtect1;
    }

    public ConquerAndProtect2 getConquerAndProtect2() {
        return conquerAndProtect2;
    }

    public void setConquerAndProtect2(ConquerAndProtect2 conquerAndProtect2) {
        this.conquerAndProtect2 = conquerAndProtect2;
    }
}

package macior.strategygame.game;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Board;
import macior.strategygame.game.BoardManagement.BoardSettings;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.BigBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.resourceFactories.BigBuildingMaterialsFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.resourceFactories.BigElectricityFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.resourceFactories.BigFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.resourceFactories.BigMetalFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.resourceFactories.SmallBuildingMaterialsFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.resourceFactories.SmallElectricityFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.resourceFactories.SmallFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.resourceFactories.SmallMetalFactory;
import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.configuration.BigBuildingsConfig;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.configuration.ImprovementUpgradesConfig;
import macior.strategygame.models.game.configuration.SmallBuildingsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class PlayersIncomeHandler {

    private Board board;

    private GameConfiguration config;

    public PlayersIncomeHandler(GameConfiguration config){
        this.config = config;
    }

    public void refreshIncomes(){
        //preparation
        ResourceSet player1Income = new ResourceSet(0, 0, 0);
        ResourceSet player2Income = new ResourceSet(0, 0, 0);
        ResourceSet player3Income = new ResourceSet(0, 0, 0);
        ResourceSet player4Income = new ResourceSet(0, 0, 0);
        Location currentLocation = new Location(0, 0);
        //get incomes from single area units
        for (int i = 0; i < BoardSettings.BOARD_ROWS; i++){
            for (int j = 0; j < BoardSettings.BOARD_COLUMNS; j++){
                currentLocation.setRow(i);
                currentLocation.setColumn(j);
                AreaUnit unit = board.getAreaUnit(currentLocation);
                Player owner = unit.getOwner();
                if (owner != null){
                    if (owner == board.getPlayer1()){
                        addIncomeFromUnit(unit, player1Income, owner.getUpgradesSet());
                    } else if (owner == board.getPlayer2()){
                        addIncomeFromUnit(unit, player2Income, owner.getUpgradesSet());
                    } else if (owner == board.getPlayer3()){
                        addIncomeFromUnit(unit, player3Income, owner.getUpgradesSet());
                    } else if (owner == board.getPlayer4()){
                        addIncomeFromUnit(unit, player4Income, owner.getUpgradesSet());
                    } else {
                        System.out.println("ERRORRRRR");
                    }
                }
            }
        }
        addUpgradesBonuses(player1Income, board.getPlayer1().getUpgradesSet());
        addUpgradesBonuses(player2Income, board.getPlayer2().getUpgradesSet());
        addUpgradesBonuses(player3Income, board.getPlayer3().getUpgradesSet());
        addUpgradesBonuses(player4Income, board.getPlayer4().getUpgradesSet());

        board.getPlayer1().setIncome(player1Income);
        board.getPlayer2().setIncome(player2Income);
        board.getPlayer3().setIncome(player3Income);
        board.getPlayer4().setIncome(player4Income);

        //System.out.println(board.getPlayer1().getIncome());
        //System.out.println(board.getPlayer2().getIncome());
        //System.out.println(board.getPlayer3().getIncome());
        //System.out.println(board.getPlayer4().getIncome());

    }

    private void addUpgradesBonuses(ResourceSet toChange, PlayersUpgradesSet upgradesSet){
        RatioSet ratios = new RatioSet();
        double bonusRatio;
        ImprovementUpgradesConfig ic = config.getUpgradesConfig().getImprovementUpgradesConfig();
        //production managers
        if (upgradesSet.upgraded(Upgrades.PRODUCTION_MANAGERS_I)){
            bonusRatio = ic.getProductionManagers1().PRODUCTION_BONUS;
            ratios.increaseRatios(bonusRatio);
        }
        if (upgradesSet.upgraded(Upgrades.PRODUCTION_MANAGERS_II)){
            bonusRatio = ic.getProductionManagers2().PRODUCTION_BONUS;
            ratios.increaseRatios(bonusRatio);
        }
        //for single resources
        if (upgradesSet.upgraded(Upgrades.MINING_DRILL)){
            bonusRatio = ic.getMiningDrill().PRODUCTION_BONUS;
            ratios.metalRatio += bonusRatio;
        }
        if (upgradesSet.upgraded(Upgrades.EXCAVATOR)){
            bonusRatio = ic.getExcavator().PRODUCTION_BONUS;
            ratios.buildingMaterialsRatio += bonusRatio;
        }
        if (upgradesSet.upgraded(Upgrades.ADVANCED_PHYSICS)){
            bonusRatio = ic.getAdvancedPhysics().PRODUCTION_BONUS;
            ratios.electricityRatio += bonusRatio;
        }
        //todo A.I.
        toChange.multiplyResources(ratios);
    }

    private void addIncomeFromUnit(AreaUnit unit, ResourceSet resources, PlayersUpgradesSet upgrades){
        if (upgrades.upgraded(Upgrades.TREASURE_HAUNTERS)){
            int amount = config.getUpgradesConfig().getControlUpgradesConfig()
                    .getTreasureHaunters().PRODUCTION_PER_AREA_UNIT;
            resources.addResources(amount, amount, amount);
        }
        addIncomesFromBuildings(unit, upgrades, resources);
    }

    private void addIncomesFromBuildings(AreaUnit unit, PlayersUpgradesSet upgrades, ResourceSet toChange){
        Building mainBuilding = unit.getBigBuilding();
        addIncomeOfBuilding(mainBuilding, mainBuilding, upgrades, toChange);
        Building currentBuilding;
        currentBuilding = unit.getNorthBuilding();
        addIncomeOfBuilding(mainBuilding, currentBuilding, upgrades, toChange);
        currentBuilding = unit.getSouthBuilding();
        addIncomeOfBuilding(mainBuilding, currentBuilding, upgrades, toChange);
        currentBuilding = unit.getWestBuilding();
        addIncomeOfBuilding(mainBuilding, currentBuilding, upgrades, toChange);
        currentBuilding = unit.getEastBuilding();
        addIncomeOfBuilding(mainBuilding, currentBuilding, upgrades, toChange);
    }

    private void addIncomeOfBuilding(Building mainBuilding, Building building, PlayersUpgradesSet upgrades, ResourceSet toChange){
        if (building == null){ //nothing is built
            return;
        }
        if (building instanceof SmallFactory){
            addIncomeOfSmallFactory(mainBuilding, building, toChange, upgrades);
        }
        if (building instanceof BigFactory){
            addIncomeOfBigFactory(building, toChange, upgrades);
        }
    }

    private void addIncomeOfSmallFactory(Building mainBuilding, Building building,
                                         ResourceSet toChange, PlayersUpgradesSet upgradesSet){
        SmallBuildingsConfig sbc = config.getSmallBuildingsConfig();
        ResourceSet toAdd = new ResourceSet(0, 0, 0);
        double ratio = 1 + config.getUpgradesConfig()
                .getImprovementUpgradesConfig().getTransportTrains().PRODUCTION_BONUS;
        if (building instanceof SmallMetalFactory){
            int production = sbc.getSmallMetalFactoryConfig().getProduction(building.LEVEL);
            toAdd.addResources(production, 0, 0);
            //check transport trains condition - main building must be corresponding factory and trains must be upgraded
            if (mainBuilding != null && upgradesSet.upgraded(Upgrades.TRANSPORT_TRAINS)
                    && mainBuilding instanceof BigMetalFactory){
                toAdd.multiplyResources(ratio);
            }
        }
        if (building instanceof SmallBuildingMaterialsFactory){
            int production = sbc.getSmallBuildingMaterialsFactoryConfig().getProduction(building.LEVEL);
            toAdd.addResources(0, production, 0);
            if (mainBuilding != null && upgradesSet.upgraded(Upgrades.TRANSPORT_TRAINS)
                    && mainBuilding instanceof BigBuildingMaterialsFactory){
                toAdd.multiplyResources(ratio);
            }
        }
        if (building instanceof SmallElectricityFactory){
            int production = sbc.getSmallElectricityFactoryConfig().getProduction(building.LEVEL);
            toAdd.addResources(0, 0, production);
            if (mainBuilding != null && upgradesSet.upgraded(Upgrades.TRANSPORT_TRAINS)
                    && mainBuilding instanceof BigElectricityFactory){
                toAdd.multiplyResources(ratio);
            }
        }
        toChange.addResources(toAdd);
    }

    private void addIncomeOfBigFactory(Building building, ResourceSet toChange, PlayersUpgradesSet upgrades){
        BigBuildingsConfig bbc = config.getBigBuildingsConfig();
        ResourceSet toAdd = new ResourceSet(0, 0, 0);
        if (building instanceof BigMetalFactory){
            int production = bbc.getBigMetalFactoryConfig().getProduction(building.LEVEL);
            toAdd.addResources(production, 0, 0);
        }
        if (building instanceof BigBuildingMaterialsFactory){
            int production = bbc.getBigBuildingMaterialsFactoryConfig().getProduction(building.LEVEL);
            toAdd.addResources(0, production, 0);
        }
        if (building instanceof BigElectricityFactory){
            int production = bbc.getBigElectricityFactoryConfig().getProduction(building.LEVEL);
            toAdd.addResources(0, 0, production);
        }
        //handling conquerors land upgrade
        if (upgrades.upgraded(Upgrades.CONQUERORS_LAND)){
            double ratio = 1 + config.getUpgradesConfig().getControlUpgradesConfig()
                    .getConquerorsLand().PRODUCTION_BONUS;
            toAdd.multiplyResources(ratio);
        }
        toChange.addResources(toAdd);
    }


    public void addNewResources(){
        Player player1 = board.getPlayer1();
        player1.getResources().addResources(player1.getIncome());

        Player player2 = board.getPlayer2();
        player2.getResources().addResources(player2.getIncome());

        Player player3 = board.getPlayer3();
        player3.getResources().addResources(player3.getIncome());

        Player player4 = board.getPlayer4();
        player4.getResources().addResources(player4.getIncome());
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public GameConfiguration getConfig() {
        return config;
    }

    public void setConfig(GameConfiguration config) {
        this.config = config;
    }



}

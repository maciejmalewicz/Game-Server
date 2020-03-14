package macior.strategygame.game.BoardManagement;


import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.*;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.resourceFactories.BigBuildingMaterialsFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.resourceFactories.BigElectricityFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.resourceFactories.BigMetalFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.Walls;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.PlayersManagement.PlayersSet;
import macior.strategygame.game.Utilities.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BoardBuilder {

    @Autowired
    private AreaUnitConverter areaUnitConverter;

    public Board buildBoard(PlayersSet set){
        Player player1 = set.getPlayer1();
        Player player2 = set.getPlayer2();
        Player player3 = set.getPlayer3();
        Player player4 = set.getPlayer4();
        return buildBoard(player1, player2, player3, player4);
    }

    public Board buildBoard(Player player1, Player player2, Player player3, Player player4){
        Board out = new Board();
        out.setAreaUnitConverter(this.areaUnitConverter);
        setPLayers(out, player1, player2, player3, player4);
        swapPlayers(out);
        createAreaUnits(out);
        createPlayerBuildings(out);
        createFreeBuildings(out);
        createRockets(out);
        createBoss(out);
        addTestObjects(out); //todo remove
        return out;
    }

    private void addTestObjects(Board board){
        Location location = new Location(0, 0);
        Walls walls1 = new Walls();
        walls1.LEVEL = 2;
        board.getAreaUnit(location).setWalls(walls1);
        location.setColumn(10);
        location.setRow(0);
        Walls walls2 = new Walls();
        walls2.LEVEL = 1;
        board.getAreaUnit(location).setWalls(walls2);
    }

    private void createBoss(Board board){
        Location location = new Location(BoardSettings.BOARD_ROWS/2, BoardSettings.BOARD_COLUMNS/2);
        BossAreaUnit boss = new BossAreaUnit();
        board.setAreaUnit(boss, location);
    }

    private void createRockets(Board board){
        //NORTH
        int row = RandomGenerator.generateBetween(0, BoardSettings.BOARD_ROWS/2-2);
        Location location = new Location(row, BoardSettings.BOARD_COLUMNS/2);
        AreaUnit unit = board.getAreaUnit(location);
        Rocket rocket = new Rocket();
        unit.setBigBuilding(rocket);

        //SOUTH
        row = RandomGenerator.generateBetween(BoardSettings.BOARD_ROWS/2+2, BoardSettings.BOARD_ROWS-1);
        location = new Location(row, BoardSettings.BOARD_COLUMNS/2);
        unit = board.getAreaUnit(location);
        rocket = new Rocket();
        unit.setBigBuilding(rocket);

        //WEST
        int column = RandomGenerator.generateBetween(0, BoardSettings.BOARD_COLUMNS/2-2);
        location = new Location(BoardSettings.BOARD_ROWS/2, column);
        unit = board.getAreaUnit(location);
        rocket = new Rocket();
        unit.setBigBuilding(rocket);

        //EAST
        column = RandomGenerator.generateBetween(BoardSettings.BOARD_COLUMNS/2+2, BoardSettings.BOARD_COLUMNS-1);
        location = new Location(BoardSettings.BOARD_ROWS/2, column);
        unit = board.getAreaUnit(location);
        rocket = new Rocket();
        unit.setBigBuilding(rocket);
    }


    private void createFreeBuildings(Board board){
        List<Location> mapPart = new ArrayList<Location>();
        for (int i = 0; i < 2; i++){
            for (int j = 2; j < BoardSettings.BOARD_COLUMNS/2; j++){
                Location location = new Location(i, j);
                mapPart.add(location);
            }
        }
        for (int i = 2; i < BoardSettings.BOARD_ROWS/2-1; i++){
            for (int j = 0; j < BoardSettings.BOARD_COLUMNS/2; j++){
                Location location = new Location(i, j);
                mapPart.add(location);
            }
        }
        for (int i = 0; i < BoardSettings.BOARD_COLUMNS/2-1; i++){
            Location location = new Location(BoardSettings.BOARD_ROWS/2-1, i);
            mapPart.add(location);
        }
        createPartOfFreeBuildings(mapPart, board);

        mapPart = new ArrayList<Location>();
        for (int i = 0; i < 2; i++){
            for (int j = BoardSettings.BOARD_COLUMNS/2 + 1; j < BoardSettings.BOARD_COLUMNS-2; j++){
                Location location = new Location(i, j);
                mapPart.add(location);
            }
        }
        for (int i = 2; i < BoardSettings.BOARD_ROWS/2-1; i++){
            for (int j = BoardSettings.BOARD_COLUMNS/2 + 1; j < BoardSettings.BOARD_COLUMNS; j++){
                Location location = new Location(i, j);
                mapPart.add(location);
            }
        }
        for (int i = BoardSettings.BOARD_COLUMNS/2 + 2; i < BoardSettings.BOARD_COLUMNS; i++){
            Location location = new Location(BoardSettings.BOARD_ROWS/2-1, i);
            mapPart.add(location);
        }
        createPartOfFreeBuildings(mapPart, board);

        mapPart = new ArrayList<Location>();
        for (int i = 0; i < BoardSettings.BOARD_COLUMNS/2-1; i++){
            Location location = new Location(BoardSettings.BOARD_ROWS/2+1, i);
            mapPart.add(location);
        }
        for (int i = BoardSettings.BOARD_ROWS/2 + 2; i < BoardSettings.BOARD_ROWS-2; i++){
            for (int j = 0; j < BoardSettings.BOARD_COLUMNS/2; j++){
                Location location = new Location(i, j);
                mapPart.add(location);
            }
        }
        for (int i = BoardSettings.BOARD_ROWS-2; i < BoardSettings.BOARD_ROWS; i++){
            for (int j = 2; j < BoardSettings.BOARD_COLUMNS/2; j++){
                Location location = new Location(i, j);
                mapPart.add(location);
            }
        }
        createPartOfFreeBuildings(mapPart, board);

        mapPart = new ArrayList<Location>();
        for (int i = BoardSettings.BOARD_COLUMNS/2+2; i < BoardSettings.BOARD_COLUMNS; i++){
            Location location = new Location(BoardSettings.BOARD_ROWS/2+1, i);
            mapPart.add(location);
        }
        for (int i = BoardSettings.BOARD_ROWS/2 + 2; i < BoardSettings.BOARD_ROWS-2; i++){
            for (int j = BoardSettings.BOARD_COLUMNS/2+1; j < BoardSettings.BOARD_COLUMNS; j++){
                Location location = new Location(i, j);
                mapPart.add(location);
            }
        }
        for (int i = BoardSettings.BOARD_ROWS-2; i < BoardSettings.BOARD_ROWS; i++){
            for (int j = BoardSettings.BOARD_COLUMNS/2+1; j < BoardSettings.BOARD_COLUMNS-2; j++){
                Location location = new Location(i, j);
                mapPart.add(location);
            }
        }
        createPartOfFreeBuildings(mapPart, board);
    }

    private void createPartOfFreeBuildings(List<Location> available, Board board){
        Tower tower1 = new Tower();
        Tower tower2 = new Tower();
        BigBuildingMaterialsFactory materialsFactory1 = new BigBuildingMaterialsFactory();
        BigBuildingMaterialsFactory materialsFactory2 = new BigBuildingMaterialsFactory();
        BigElectricityFactory electricityFactory1 = new BigElectricityFactory();
        BigElectricityFactory electricityFactory2 = new BigElectricityFactory();
        BigMetalFactory metalFactory1 = new BigMetalFactory();
        BigMetalFactory metalFactory2 = new BigMetalFactory();

        List<BigBuilding> buildingsToAdd = new ArrayList<BigBuilding>();
        buildingsToAdd.add(tower1);
        buildingsToAdd.add(tower2);
        buildingsToAdd.add(materialsFactory1);
        buildingsToAdd.add(materialsFactory2);
        buildingsToAdd.add(electricityFactory1);
        buildingsToAdd.add(electricityFactory2);
        buildingsToAdd.add(metalFactory1);
        buildingsToAdd.add(metalFactory2);

        Location current;
        AreaUnit unit;

        while (buildingsToAdd.size() > 0){
            current = available.get(RandomGenerator.generateBetween(0, available.size()-1));
            unit = board.getAreaUnit(current);
            if (unit.getBigBuilding() == null){
                unit.setBigBuilding(buildingsToAdd.get(buildingsToAdd.size()-1));
                buildingsToAdd.remove(buildingsToAdd.size()-1);
            }
        }
    }


    private void createPlayerBuildings(Board board){
        addMainTowers(board);
        addPLayersFactories(board);
    }



    private void addMainTowers(Board board){
        Location location1 = new Location(0, 0);
        AreaUnit areaUnit1 = board.getAreaUnit(location1);
        BigBuilding mainTower1 = new MainTower();
        areaUnit1.setBigBuilding(mainTower1);
        areaUnit1.setOwner(board.getPlayer1());

        Location location2 = new Location(0, BoardSettings.BOARD_COLUMNS-1);
        AreaUnit areaUnit2 = board.getAreaUnit(location2);
        BigBuilding mainTower2 = new MainTower();
        areaUnit2.setBigBuilding(mainTower2);
        areaUnit2.setOwner(board.getPlayer2());

        Location location3 = new Location(BoardSettings.BOARD_ROWS-1, 0);
        AreaUnit areaUnit3 = board.getAreaUnit(location3);
        BigBuilding mainTower3 = new MainTower();
        areaUnit3.setBigBuilding(mainTower3);
        areaUnit3.setOwner(board.getPlayer3());

        Location location4 = new Location(BoardSettings.BOARD_ROWS-1, BoardSettings.BOARD_COLUMNS-1);
        AreaUnit areaUnit4 = board.getAreaUnit(location4);
        BigBuilding mainTower4 = new MainTower();
        areaUnit4.setBigBuilding(mainTower4);
        areaUnit4.setOwner(board.getPlayer4());
    }

    private void addPLayersFactories(Board board){
        Location loc1 = new Location(0, 1);
        Location loc2 = new Location(1, 0);
        Location loc3 = new Location(1, 1);
        Player player = board.getPlayer1();
        addSinglePlayersFactories(player, loc1, loc2, loc3, board);

        loc1 = new Location(0, BoardSettings.BOARD_COLUMNS-2);
        loc2 = new Location(1, BoardSettings.BOARD_COLUMNS-2);
        loc3 = new Location(1, BoardSettings.BOARD_COLUMNS-1);
        player = board.getPlayer2();
        addSinglePlayersFactories(player, loc1, loc2, loc3, board);

        loc1 = new Location(BoardSettings.BOARD_ROWS-2, 0);
        loc2 = new Location(BoardSettings.BOARD_ROWS-2, 1);
        loc3 = new Location(BoardSettings.BOARD_ROWS-1, 1);
        player = board.getPlayer3();
        addSinglePlayersFactories(player, loc1, loc2, loc3, board);

        loc1 = new Location(BoardSettings.BOARD_ROWS-2, BoardSettings.BOARD_COLUMNS-2);
        loc2 = new Location(BoardSettings.BOARD_ROWS-2, BoardSettings.BOARD_COLUMNS-1);
        loc3 = new Location(BoardSettings.BOARD_ROWS-1, BoardSettings.BOARD_COLUMNS-2);
        player = board.getPlayer4();
        addSinglePlayersFactories(player, loc1, loc2, loc3, board);
    }

    private void addSinglePlayersFactories(Player player, Location location1, Location location2,
                                           Location location3, Board board){
        Location current;
        ArrayList<Location> availableLocations = new ArrayList<Location>();

        availableLocations.add(location1);
        availableLocations.add(location2);
        availableLocations.add(location3);

        BigBuildingMaterialsFactory materialsFactory = new BigBuildingMaterialsFactory();
        current = availableLocations.get(RandomGenerator.generateBetween(0, availableLocations.size()-1));
        AreaUnit areaUnit11 = board.getAreaUnit(current);
        areaUnit11.setBigBuilding(materialsFactory);
        areaUnit11.setOwner(player);
        availableLocations.remove(current);

        BigElectricityFactory electricityFactory = new BigElectricityFactory();
        current = availableLocations.get(RandomGenerator.generateBetween(0, availableLocations.size()-1));
        AreaUnit areaUnit12 = board.getAreaUnit(current);
        areaUnit12.setBigBuilding(electricityFactory);
        areaUnit12.setOwner(player);
        availableLocations.remove(current);

        BigMetalFactory metalFactory = new BigMetalFactory();
        current = availableLocations.get(0);
        AreaUnit areaUnit3 = board.getAreaUnit(current);
        areaUnit3.setBigBuilding(metalFactory);
        areaUnit3.setOwner(player);
        availableLocations.remove(current);
    }


    private void createAreaUnits(Board board){
        for (int i = 0; i < BoardSettings.BOARD_ROWS; i++){
            for (int j = 0; j < BoardSettings.BOARD_COLUMNS; j++){
                Location location = new Location(i, j);
                AreaUnit unit = new AreaUnit();
                unit.setLocation(location);
                board.setAreaUnit(unit, location);
            }
        }
    }

    private void setPLayers(Board board, Player player1, Player player2, Player player3, Player player4){
        board.setPlayer1(player1);
        board.setPlayer2(player2);
        board.setPlayer3(player3);
        board.setPlayer4(player4);
    }

    public static void swapPlayers(Board board){
        ArrayList<Player> in = new ArrayList<Player>();
        ArrayList<Player> out = new ArrayList<Player>();

        in.add(board.getPlayer1());
        in.add(board.getPlayer2());
        in.add(board.getPlayer3());
        in.add(board.getPlayer4());

        for (int i = (in.size() - 1); i >= 0; i--){
            int index = RandomGenerator.generateBetween(0, i);
            Player player = in.get(index);
            in.remove(player);
            out.add(player);
        }

        board.setPlayer1(out.get(0));
        board.setPlayer2(out.get(1));
        board.setPlayer3(out.get(2));
        board.setPlayer4(out.get(3));
    }
}

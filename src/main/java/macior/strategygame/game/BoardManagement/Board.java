package macior.strategygame.game.BoardManagement;


import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.models.game.AreaUnitMessage;
import macior.strategygame.models.game.BoardMessage;
import org.springframework.beans.factory.annotation.Autowired;

public class Board {

    private AreaUnitConverter areaUnitConverter;

    private AreaUnit[][] areaUnits = new AreaUnit[BoardSettings.BOARD_ROWS][BoardSettings.BOARD_COLUMNS];

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer3() {
        return player3;
    }

    public void setPlayer3(Player player3) {
        this.player3 = player3;
    }

    public Player getPlayer4() {
        return player4;
    }

    public void setPlayer4(Player player4) {
        this.player4 = player4;
    }

    public AreaUnit getAreaUnit(Location location){
        return areaUnits[location.getRow()][location.getColumn()];
    }

    public void setAreaUnit(AreaUnit unit, Location location){
        areaUnits[location.getRow()][location.getColumn()] = unit;
    }

    public BoardMessage toMessage(Player requestor){
        BoardMessage message = new BoardMessage();
        message.units = new AreaUnitMessage[areaUnits.length][areaUnits[0].length];
        for (int i = 0; i < areaUnits.length; i++){
            for (int j = 0; j < areaUnits[i].length; j++){

                message.units[i][j] = areaUnitConverter.convert(requestor, areaUnits[i][j]);
            }
        }
        return message;
    }

    public AreaUnitConverter getAreaUnitConverter() {
        return areaUnitConverter;
    }

    public void setAreaUnitConverter(AreaUnitConverter areaUnitConverter) {
        this.areaUnitConverter = areaUnitConverter;
    }

    //    public void printBuildings(){
//        for (AreaUnit[] row : areaUnits){
//            for (AreaUnit unit : row){
//                if (unit.getClass() == BossAreaUnit.class){
//                    System.out.print("N");
//                }
//                else if (unit.getBigBuilding() == null)
//                    System.out.print("#");
//                else if (unit.getBigBuilding().getClass() == Tower.class)
//                    System.out.print("T");
//                else if (unit.getBigBuilding().getClass() == BigBuildingMaterialsFactory.class)
//                    System.out.print("B");
//                else if (unit.getBigBuilding().getClass() == BigMetalFactory.class)
//                    System.out.print("M");
//                else if (unit.getBigBuilding().getClass() == BigElectricityFactory.class){
//                    System.out.print("E");
//                }
//                else if (unit.getBigBuilding().getClass() == MainTower.class){
//                    System.out.print("H");
//                }
//                else if (unit.getBigBuilding().getClass() == Rocket.class){
//                    System.out.print("R");
//                }
//            }
//            System.out.println();
//        }
//    }

//    public void print(){
//        for (AreaUnit[] row : areaUnits){
//            for (AreaUnit unit : row){
//                System.out.println(unit);
//                try {
//                    System.out.println(unit.getLocation());
//                } catch (NullPointerException exc){
//                    System.out.println(":(");
//                }
//                try {
//                    System.out.println(unit.getOwner());
//                } catch (NullPointerException exc){
//                    System.out.println(":<");
//                }
//                try {
//                    System.out.println(unit.getBigBuilding());
//                } catch (NullPointerException exc){
//                    System.out.println(":>");
//                }
//                System.out.println();
//            }
//        }
//    }
}

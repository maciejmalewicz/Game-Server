package macior.strategygame.game;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Board;
import macior.strategygame.game.BoardManagement.BoardSettings;
import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.Utilities.ResourceSet;

public class PlayersIncomeHandler {

    private Board board;

    public void refreshIncomes(){
        ResourceSet player1Income = new ResourceSet(0, 0, 0);
        ResourceSet player2Income = new ResourceSet(0, 0, 0);
        ResourceSet player3Income = new ResourceSet(0, 0, 0);
        ResourceSet player4Income = new ResourceSet(0, 0, 0);
        Location currentLocation = new Location(0, 0);
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
        board.getPlayer1().setIncome(player1Income);
        board.getPlayer2().setIncome(player2Income);
        board.getPlayer3().setIncome(player3Income);
        board.getPlayer4().setIncome(player4Income);

        System.out.println(board.getPlayer1().getIncome());
        System.out.println(board.getPlayer2().getIncome());
        System.out.println(board.getPlayer3().getIncome());
        System.out.println(board.getPlayer4().getIncome());

    }

    public void addIncomeFromUnit(AreaUnit unit, ResourceSet resources, PlayersUpgradesSet upgrades){
        resources.addResources(5, 5, 5);
        //todo all this complex stuff
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


}

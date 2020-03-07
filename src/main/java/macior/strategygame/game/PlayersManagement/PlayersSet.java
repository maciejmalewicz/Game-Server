package macior.strategygame.game.PlayersManagement;

public class PlayersSet {

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    public PlayersSet(Player p1, Player p2, Player p3, Player p4){
        player1 = p1;
        player2 = p2;
        player3 = p3;
        player4 = p4;
    }

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
}

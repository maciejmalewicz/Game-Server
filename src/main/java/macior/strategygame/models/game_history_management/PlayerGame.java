package macior.strategygame.models.game_history_management;

import macior.strategygame.game.Game;
import macior.strategygame.models.User;

import javax.persistence.*;

//something like game attendance
@Entity(name = "player_game")
public class PlayerGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_player_game")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player")
    private User player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game")
    private GamePassed game;

    @Column(name = "result")
    private double result;

    public String toString(){
        return player + " " + game + " " + result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public GamePassed getGame() {
        return game;
    }

    public void setGame(GamePassed game) {
        this.game = game;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}

package macior.strategygame.models.game_history_management;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "game_passed")
public class GamePassed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int gameId;

    @Column(name = "date_finished")
    private Date endDate;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString(){
        return gameId + " " + endDate;
    }
}

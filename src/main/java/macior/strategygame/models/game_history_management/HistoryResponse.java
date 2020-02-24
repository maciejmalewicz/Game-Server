package macior.strategygame.models.game_history_management;

import java.util.ArrayList;
import java.util.List;

public class HistoryResponse {

    private String newCode;
    private List<HistoryUnit> games = new ArrayList<>();

    public String getNewCode() {
        return newCode;
    }

    public void setNewCode(String newCode) {
        this.newCode = newCode;
    }

    public List<HistoryUnit> getGames() {
        return games;
    }

    public void setGames(List<HistoryUnit> games) {
        this.games = games;
    }
}

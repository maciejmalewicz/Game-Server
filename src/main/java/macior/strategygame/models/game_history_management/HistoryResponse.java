package macior.strategygame.models.game_history_management;

import macior.strategygame.models.StatusResponse;

import java.util.ArrayList;
import java.util.List;

public class HistoryResponse extends StatusResponse {

    private List<HistoryUnit> games = new ArrayList<>();

    public List<HistoryUnit> getGames() {
        return games;
    }

    public void setGames(List<HistoryUnit> games) {
        this.games = games;
    }
}

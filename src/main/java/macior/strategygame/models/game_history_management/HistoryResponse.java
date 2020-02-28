package macior.strategygame.models.game_history_management;

import macior.strategygame.models.ResponseBase;

import java.util.ArrayList;
import java.util.List;

public class HistoryResponse extends ResponseBase {

    private List<HistoryUnit> games = new ArrayList<>();

    public List<HistoryUnit> getGames() {
        return games;
    }

    public void setGames(List<HistoryUnit> games) {
        this.games = games;
    }
}

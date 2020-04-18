package macior.strategygame.models.friends;

import macior.strategygame.models.StatusResponse;
import macior.strategygame.models.game_history_management.HistoryUnit;

import java.util.List;

public class ProfileResponse extends StatusResponse {

    private String login;
    private long experience;
    private List<HistoryUnit> history;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public List<HistoryUnit> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryUnit> history) {
        this.history = history;
    }
}

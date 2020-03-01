package macior.strategygame.service.menu;

import macior.strategygame.dao.HistoryDAO;
import macior.strategygame.dao.users.UserDAO;
import macior.strategygame.models.User;
import macior.strategygame.models.game_history_management.HistoryResponse;
import macior.strategygame.models.game_history_management.HistoryUnit;
import macior.strategygame.service.utilities.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    @Qualifier("historyDAO")
    private HistoryDAO historyDAO;

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    public HistoryResponse getHistory(String code){
        int playersId = mapper.getId(code);
        List<HistoryUnit> units = new ArrayList<>();
        if (playersId != -1){
            User user = userDAO.getById(playersId).orElse(null);
            units = historyDAO.getGamesHistory(user);
        }
        HistoryResponse response = new HistoryResponse();
        String newCode = mapper.updateCode(code);
        System.out.println(newCode);
        response.setGames(units);
        response.setCode(newCode);
        return response;
    }
}

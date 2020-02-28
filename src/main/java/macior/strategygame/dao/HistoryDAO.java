package macior.strategygame.dao;

import macior.strategygame.game.Game;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.models.User;
import macior.strategygame.models.game_history_management.GamePassed;
import macior.strategygame.models.game_history_management.HistoryUnit;
import macior.strategygame.models.game_history_management.PlayerGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository("historyDAO")
public class HistoryDAO {

    @Autowired
    private Context context;

//    public GamePassed getGameById(int id){
//        EntityManager manager = context.entityManager();
//        GamePassed out = manager.find(GamePassed.class, id);
//        return out;
//    }

    public List<HistoryUnit> getGamesHistory(User user){

        CriteriaBuilder criteriaBuilder = context.criteriaBuilder();
        CriteriaQuery<PlayerGame> criteria = criteriaBuilder.createQuery(PlayerGame.class);
        Root<PlayerGame> playerGameRoot = criteria.from(PlayerGame.class);
        Predicate idPredicate = criteriaBuilder.equal(playerGameRoot.get("player"), user);
        criteria.where(idPredicate);
        Query criteriaQuery = context.entityManager().createQuery(criteria);
        List<PlayerGame> result = criteriaQuery.getResultList();
        List<HistoryUnit> out = new ArrayList<>();

        for (PlayerGame pg : result){
            GamePassed game = pg.getGame();
            HistoryUnit unit = buildHistoryUnit(game);
            unit.setExperienceGained(pg.getExperienceGained());
            out.add(unit);
        }
        return out;
    }

    //get history unit from game
    public HistoryUnit buildHistoryUnit(GamePassed passed){
        HistoryUnit unit = new HistoryUnit();
        unit.setDate(passed.getEndDate());
        List<PlayerGame> attendances = getAttendances(passed);

        if (attendances.size() > 0){
            PlayerGame attendance = attendances.get(0);
            unit.setPlayer1Login(attendance.getPlayer().getLogin());
            unit.setPlayer1Result(attendance.getResult());
        }

        if (attendances.size() > 1){
            PlayerGame attendance2 = attendances.get(1);
            unit.setPlayer2Login(attendance2.getPlayer().getLogin());
            unit.setPlayer2Result(attendance2.getResult());
        }

        if (attendances.size() > 2){
            PlayerGame attendance3 = attendances.get(2);
            unit.setPlayer3Login(attendance3.getPlayer().getLogin());
            unit.setPlayer3Result(attendance3.getResult());
        }

        if (attendances.size() > 3){
            PlayerGame attendance4 = attendances.get(3);
            unit.setPlayer4Login(attendance4.getPlayer().getLogin());
            unit.setPlayer4Result(attendance4.getResult());
        }

        return unit;
    }

    private List<PlayerGame> getAttendances(GamePassed game){
        CriteriaBuilder criteriaBuilder = context.criteriaBuilder();
        CriteriaQuery<PlayerGame> criteria = criteriaBuilder.createQuery(PlayerGame.class);
        Root<PlayerGame> playerGameRoot = criteria.from(PlayerGame.class);
        Predicate gamePredicate = criteriaBuilder.equal(playerGameRoot.get("game"), game);
        criteria.where(gamePredicate);
        Query criteriaQuery = context.entityManager().createQuery(criteria);
        return criteriaQuery.getResultList();
    }

}

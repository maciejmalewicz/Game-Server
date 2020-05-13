package macior.strategygame.service.game.battles;

import macior.strategygame.game.BattlesManagement.Attack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BattleStartingService {

    @Autowired
    private UnoccupiedFieldBattleService unoccupiedService;

    @Autowired
    private OccupiedFieldBattleService occupiedService;

    public void battle(Attack attack){
        if (attack.DEFENDER == null){
            unoccupiedService.battle(attack);
        }
        System.out.println("ATTACKING!");
    }
}

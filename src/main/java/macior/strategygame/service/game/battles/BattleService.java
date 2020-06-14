package macior.strategygame.service.game.battles;

import macior.strategygame.game.BattlesManagement.Attack;
import macior.strategygame.game.BoardManagement.AreaUnitConverter;
import macior.strategygame.service.battlePipelines.models.BattleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BattleService {

    @Autowired
    private UnoccupiedFieldBattleService unoccupiedService;

    @Autowired
    private OccupiedFieldBattleService occupiedService;

    @Autowired
    private AreaUnitConverter converter;

    public BattleResult battle(Attack attack){
        BattleResult battleResult;
        if (attack.DEFENDER == null){
            battleResult = unoccupiedService.battle(attack);
        } else {
            battleResult = null;
        }
        return battleResult;
    }

    public AreaUnitConverter getConverter() {
        return converter;
    }
}

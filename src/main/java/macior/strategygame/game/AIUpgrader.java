package macior.strategygame.game;

import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades.ManagersAI;
import macior.strategygame.game.PlayersManagement.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;

//class responsible for increasing production bonus caused by having Managers A.I. upgraded
@Component
public class AIUpgrader {

    @Autowired
    private ManagersAI managersAI;

    public void upgradeAI(Player player){
        double currentBonus = player.getBonusFromAI();
        currentBonus += managersAI.BONUS_PER_MINUTE;
        if (currentBonus > managersAI.MAX_BONUS){
            currentBonus = managersAI.MAX_BONUS;
        }
        player.setBonusFromAI(currentBonus);
    }

    public boolean canBeStillUpgraded(Player player){
        return player.getBonusFromAI() < managersAI.MAX_BONUS;
    }
}

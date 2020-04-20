package macior.strategygame.models.game.configuration;

import macior.strategygame.game.BattlesManagement.configuration.CannonsConfig;
import macior.strategygame.game.BattlesManagement.configuration.DroidsConfig;
import macior.strategygame.game.BattlesManagement.configuration.TanksConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArmyBalanceConfig {

    @Autowired
    private DroidsConfig droidsConfig;

    @Autowired
    private TanksConfig tanksConfig;

    @Autowired
    private CannonsConfig cannonsConfig;

    public DroidsConfig getDroidsConfig() {
        return droidsConfig;
    }

    public void setDroidsConfig(DroidsConfig droidsConfig) {
        this.droidsConfig = droidsConfig;
    }

    public TanksConfig getTanksConfig() {
        return tanksConfig;
    }

    public void setTanksConfig(TanksConfig tanksConfig) {
        this.tanksConfig = tanksConfig;
    }

    public CannonsConfig getCannonsConfig() {
        return cannonsConfig;
    }

    public void setCannonsConfig(CannonsConfig cannonsConfig) {
        this.cannonsConfig = cannonsConfig;
    }
}

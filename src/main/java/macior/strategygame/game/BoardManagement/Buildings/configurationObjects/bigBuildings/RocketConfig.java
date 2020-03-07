package macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings;

import macior.strategygame.game.Utilities.Time;
import org.springframework.stereotype.Component;

@Component
public class RocketConfig extends BigBuildingConfig {

    public Time LEVEL2_TOWER_RELOADING_TIME;
    public Time LEVEL3_TOWER_RELOADING_TIME;
    public Time LEVEL4_TOWER_RELOADING_TIME;
}

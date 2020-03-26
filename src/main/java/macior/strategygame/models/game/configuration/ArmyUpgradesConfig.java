package macior.strategygame.models.game.configuration;

import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ArmyUpgrades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ArmyUpgradesConfig {

    @Autowired
    @Qualifier("powerInSimplicityBean")
    private PowerInSimplicity powerInSimplicity;

    @Autowired
    @Qualifier("advancedDroidsBean")
    private AdvancedDroids advancedDroids;

    @Autowired
    @Qualifier("assemblyLinesBean")
    private AssemblyLines assemblyLines;

    @Autowired
    @Qualifier("massProductionBean")
    private MassProduction massProduction;

    @Autowired
    @Qualifier("tanksBean")
    private Tanks tanksBean;

    @Autowired
    @Qualifier("mobilityBean")
    private Mobility mobility;

    @Autowired
    @Qualifier("shootingWindowBean")
    private ShootingWindow shootingWindow;

    @Autowired
    @Qualifier("cannonsBean")
    private Cannons cannons;

    @Autowired
    @Qualifier("cannonPlatformBean")
    private CannonPlatform cannonPlatform;

    @Autowired
    @Qualifier("battleFormationBean")
    private BattleFormation battleFormation;

    @Autowired
    @Qualifier("solarPanelsBean")
    private SolarPanels solarPanels;


    public PowerInSimplicity getPowerInSimplicity() {
        return powerInSimplicity;
    }

    public void setPowerInSimplicity(PowerInSimplicity powerInSimplicity) {
        this.powerInSimplicity = powerInSimplicity;
    }

    public AdvancedDroids getAdvancedDroids() {
        return advancedDroids;
    }

    public void setAdvancedDroids(AdvancedDroids advancedDroids) {
        this.advancedDroids = advancedDroids;
    }

    public AssemblyLines getAssemblyLines() {
        return assemblyLines;
    }

    public void setAssemblyLines(AssemblyLines assemblyLines) {
        this.assemblyLines = assemblyLines;
    }

    public MassProduction getMassProduction() {
        return massProduction;
    }

    public void setMassProduction(MassProduction massProduction) {
        this.massProduction = massProduction;
    }

    public Tanks getTanksBean() {
        return tanksBean;
    }

    public void setTanksBean(Tanks tanksBean) {
        this.tanksBean = tanksBean;
    }

    public Mobility getMobility() {
        return mobility;
    }

    public void setMobility(Mobility mobility) {
        this.mobility = mobility;
    }

    public ShootingWindow getShootingWindow() {
        return shootingWindow;
    }

    public void setShootingWindow(ShootingWindow shootingWindow) {
        this.shootingWindow = shootingWindow;
    }

    public Cannons getCannons() {
        return cannons;
    }

    public void setCannons(Cannons cannons) {
        this.cannons = cannons;
    }

    public CannonPlatform getCannonPlatform() {
        return cannonPlatform;
    }

    public void setCannonPlatform(CannonPlatform cannonPlatform) {
        this.cannonPlatform = cannonPlatform;
    }

    public BattleFormation getBattleFormation() {
        return battleFormation;
    }

    public void setBattleFormation(BattleFormation battleFormation) {
        this.battleFormation = battleFormation;
    }

    public SolarPanels getSolarPanels() {
        return solarPanels;
    }

    public void setSolarPanels(SolarPanels solarPanels) {
        this.solarPanels = solarPanels;
    }
}

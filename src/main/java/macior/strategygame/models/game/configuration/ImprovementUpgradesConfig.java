package macior.strategygame.models.game.configuration;

import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ImprovementUpgradesConfig {

    @Autowired
    @Qualifier("productionManagersBean")
    private ProductionManagers1 productionManagers1;

    @Autowired
    @Qualifier("productionManagers2Bean")
    private ProductionManagers2 productionManagers2;

    @Autowired
    @Qualifier("miningDrillBean")
    private MiningDrill miningDrill;

    @Autowired
    @Qualifier("excavatorBean")
    private Excavator excavator;

    @Autowired
    @Qualifier("advancedPhysicsBean")
    private AdvancedPhysics advancedPhysics;

    @Autowired
    @Qualifier("engineeringPatternsBean")
    private EngineeringPatterns engineeringPatterns;

    @Autowired
    @Qualifier("spaceManagementBean")
    private SpaceManagement spaceManagement;

    @Autowired
    @Qualifier("transportTrainsBean")
    private TransportTrains transportTrains;

    @Autowired
    @Qualifier("geologyBean")
    private Geology geology;

    @Autowired
    @Qualifier("architectureBean")
    private Architecture architecture;

    @Autowired
    @Qualifier("managersAIBean")
    private ManagersAI managersAI;

    public ProductionManagers1 getProductionManagers1() {
        return productionManagers1;
    }

    public void setProductionManagers1(ProductionManagers1 productionManagers1) {
        this.productionManagers1 = productionManagers1;
    }

    public ProductionManagers2 getProductionManagers2() {
        return productionManagers2;
    }

    public void setProductionManagers2(ProductionManagers2 productionManagers2) {
        this.productionManagers2 = productionManagers2;
    }

    public MiningDrill getMiningDrill() {
        return miningDrill;
    }

    public void setMiningDrill(MiningDrill miningDrill) {
        this.miningDrill = miningDrill;
    }

    public Excavator getExcavator() {
        return excavator;
    }

    public void setExcavator(Excavator excavator) {
        this.excavator = excavator;
    }

    public AdvancedPhysics getAdvancedPhysics() {
        return advancedPhysics;
    }

    public void setAdvancedPhysics(AdvancedPhysics advancedPhysics) {
        this.advancedPhysics = advancedPhysics;
    }

    public EngineeringPatterns getEngineeringPatterns() {
        return engineeringPatterns;
    }

    public void setEngineeringPatterns(EngineeringPatterns engineeringPatterns) {
        this.engineeringPatterns = engineeringPatterns;
    }

    public SpaceManagement getSpaceManagement() {
        return spaceManagement;
    }

    public void setSpaceManagement(SpaceManagement spaceManagement) {
        this.spaceManagement = spaceManagement;
    }

    public TransportTrains getTransportTrains() {
        return transportTrains;
    }

    public void setTransportTrains(TransportTrains transportTrains) {
        this.transportTrains = transportTrains;
    }

    public Geology getGeology() {
        return geology;
    }

    public void setGeology(Geology geology) {
        this.geology = geology;
    }

    public Architecture getArchitecture() {
        return architecture;
    }

    public void setArchitecture(Architecture architecture) {
        this.architecture = architecture;
    }

    public ManagersAI getManagersAI() {
        return managersAI;
    }

    public void setManagersAI(ManagersAI managersAI) {
        this.managersAI = managersAI;
    }
}

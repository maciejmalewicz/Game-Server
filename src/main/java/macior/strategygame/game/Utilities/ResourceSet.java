package macior.strategygame.game.Utilities;

import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.RatioSet;

public class ResourceSet {

    public int METAL;
    public int BUILDING_MATERIALS;
    public int ELECTRICITY;

    public ResourceSet(int metal, int buildingMaterials, int electricity){
        METAL = metal;
        BUILDING_MATERIALS = buildingMaterials;
        ELECTRICITY = electricity;
    }

    public void addResources(int metal, int buildingMaterials, int electricity){
        METAL += metal;
        BUILDING_MATERIALS += buildingMaterials;
        ELECTRICITY += electricity;
    }

    public void addResources(ResourceSet setToAdd){
        addResources(setToAdd.METAL, setToAdd.BUILDING_MATERIALS, setToAdd.ELECTRICITY);
    }

    public void multiplyResources(double ratio){
        METAL = (int)(METAL*ratio);
        BUILDING_MATERIALS = (int)(BUILDING_MATERIALS*ratio);
        ELECTRICITY = (int)(ELECTRICITY*ratio);
    }

    public void multiplyResources(RatioSet ratios){
        METAL = (int)(METAL*ratios.metalRatio);
        BUILDING_MATERIALS = (int)(BUILDING_MATERIALS*ratios.buildingMaterialsRatio);
        ELECTRICITY = (int)(ELECTRICITY*ratios.electricityRatio);
    }

    public void substractResources(ResourceSet setToSubstract){
        METAL -= setToSubstract.METAL;
        BUILDING_MATERIALS -= setToSubstract.BUILDING_MATERIALS;
        ELECTRICITY -= setToSubstract.ELECTRICITY;
    }

    @Override
    public String toString(){
        return "METAL: " + METAL + " BM: " + BUILDING_MATERIALS + " ELECTRICITY: " + ELECTRICITY;
    }

    //dependent on: Geology with index: 30
    public ResourceSet canPurchase(Player buyer){ //return cost of item or null if can't purchase
        if (buyer.getUpgradesSet().upgraded(Upgrades.GEOLOGY)){
            return canBePurchasedWithGeology(buyer.getResources());
        } else {
            return canBePurchasedWithoutGeology(buyer.getResources());
        }
    }

    private ResourceSet canBePurchasedWithoutGeology(ResourceSet resourceSet){
        boolean hasEnough =  resourceSet.METAL >= this.METAL &&
                resourceSet.BUILDING_MATERIALS >= this.BUILDING_MATERIALS &&
                resourceSet.ELECTRICITY >= this.ELECTRICITY;
        if (hasEnough){
            return this;
        } else {
            return null;
        }
    }

    private ResourceSet canBePurchasedWithGeology(ResourceSet resourceSet){
        return canBePurchasedWithoutGeology(resourceSet);
    }
}

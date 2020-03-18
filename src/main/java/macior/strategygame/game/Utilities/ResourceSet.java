package macior.strategygame.game.Utilities;

import macior.strategygame.game.PlayersManagement.Player;

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

    @Override
    public String toString(){
        return "METAL: " + METAL + " BM: " + BUILDING_MATERIALS + " ELECTRICITY: " + ELECTRICITY;
    }

    //dependent on: Geology with index: 30
    public ResourceSet canPurchase(Player buyer){ //return cost of item or null if can't purchase
        if (buyer.getUpgradesSet().upgraded(30)){
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

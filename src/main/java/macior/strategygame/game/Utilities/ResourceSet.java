package macior.strategygame.game.Utilities;

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
}

package macior.strategygame.game.BattlesManagement;

public class Army {

    private int droids = 10;
    private int tanks = 20;
    private int cannons = 50;

    public void addDroids(int number){
        droids += number;
    }

    public void addTanks(int number){
        tanks += number;
    }

    public void addCannons(int number){
        cannons += number;
    }

    public int getDroids() {
        return droids;
    }

    public void setDroids(int droids) {
        this.droids = droids;
    }

    public int getTanks() {
        return tanks;
    }

    public void setTanks(int tanks) {
        this.tanks = tanks;
    }

    public int getCannons() {
        return cannons;
    }

    public void setCannons(int cannons) {
        this.cannons = cannons;
    }
}

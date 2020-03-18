package macior.strategygame.game.PostponedEvents;

//test object
public class PostponedPrinter extends PostponedEvent {

    public PostponedPrinter(int time){
        super(time);
    }

    @Override
    public void happen() {
        System.out.println("Dupa " + this.getFinishingTime());
    }

    @Override
    public String toString(){
        return this.getFinishingTime() + "";
    }
}

package macior.strategygame;

import macior.strategygame.game.BoardManagement.BuildingQueue;
import macior.strategygame.game.PostponedEvents.PostponedPrinter;
import org.junit.jupiter.api.Test;

public class EventsTests {

    @Test
    public void testQueues(){
        BuildingQueue buildingQueue = new BuildingQueue();
        PostponedPrinter printer1 = new PostponedPrinter(20);
        PostponedPrinter printer2 = new PostponedPrinter(10);
        PostponedPrinter printer3 = new PostponedPrinter(30);
        PostponedPrinter printer4 = new PostponedPrinter(15);

        System.out.println();
    }
}

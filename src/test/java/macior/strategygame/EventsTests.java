package macior.strategygame;

import macior.strategygame.game.BoardManagement.AreaEventsQueue;
import macior.strategygame.game.PostponedEvents.PostponedPrinter;
import org.junit.jupiter.api.Test;

public class EventsTests {

    @Test
    public void testQueues(){
        AreaEventsQueue buildingQueue = new AreaEventsQueue();
        PostponedPrinter printer1 = new PostponedPrinter(20);
        PostponedPrinter printer2 = new PostponedPrinter(10);
        PostponedPrinter printer3 = new PostponedPrinter(30);
        PostponedPrinter printer4 = new PostponedPrinter(15);

        System.out.println();
    }


}

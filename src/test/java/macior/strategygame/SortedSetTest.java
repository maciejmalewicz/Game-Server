package macior.strategygame;

import macior.strategygame.game.PostponedEvents.PostponedEvent;
import macior.strategygame.game.PostponedEvents.PostponedPrinter;
import org.junit.jupiter.api.Test;

import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetTest {

    @Test
    public void test(){
        PostponedPrinter printer1 = new PostponedPrinter(3);
        PostponedPrinter printer2 = new PostponedPrinter(1);
        PostponedPrinter printer3 = new PostponedPrinter(4);
        PostponedPrinter printer4 = new PostponedPrinter(2);

        TreeSet<PostponedEvent> events = new TreeSet<>();
        events.add(printer1);
        events.add(printer2);
        events.add(printer3);
        events.add(printer4);
        System.out.println(events);
        System.out.println(events.first());
        System.out.println(events.last());

        events.pollFirst();
        events.pollLast();
        System.out.println(events);
    }
}

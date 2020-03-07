package macior.strategygame;

import macior.strategygame.game.Utilities.BeanFactory;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class BeanFactoryTests {

    @Test
    public void testCamel(){
        String s = "MaciorMalewicz";
        String s2 = BeanFactory.convertToCamel(s);
        System.out.println(s2);
    }
}

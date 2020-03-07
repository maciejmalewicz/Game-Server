package macior.strategygame.service.utilities.mapper;

import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperCleaningThread extends Thread{

    @Autowired
    private PlayerGameMapperService mapper;

    //once for how many minutes does it refresh mapper
    private int refreshingPauseMinutes = 3;
    private long refreshingPauseDuration = refreshingPauseMinutes*60000; //to miliseconds

    public MapperCleaningThread(){
        this.start();
    }

    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(refreshingPauseDuration);
            } catch (InterruptedException exc){
                //none
            }
            mapper.clearOldCodes();
        }
    }
}

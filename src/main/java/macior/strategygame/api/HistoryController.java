package macior.strategygame.api;

import macior.strategygame.models.game_history_management.HistoryResponse;
import macior.strategygame.service.menu.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/history")
@RestController()
public class HistoryController {

    @Autowired
    private HistoryService service;

    @GetMapping(path = "{code}")
    public HistoryResponse getHistory(@PathVariable("code")String code){
        System.out.println(code);
        return service.getHistory(code);
    }
}

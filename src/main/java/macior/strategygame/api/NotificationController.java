package macior.strategygame.api;

import macior.strategygame.models.NotificationResponse;
import macior.strategygame.service.utilities.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/notifications")
@RestController
public class NotificationController {

    @Autowired
    private NotificationService service;

    @CrossOrigin
    @PutMapping(path = "{code}")
    public NotificationResponse notify(@PathVariable("code") String code){
        return service.notify(code);
    }
}

package macior.strategygame.api;

import macior.strategygame.models.ActivationLink;
import macior.strategygame.service.ActivationLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/activationLink")
@RestController
public class ActivationLinkController {

    private ActivationLinkService service;

    @Autowired
    public ActivationLinkController(ActivationLinkService activationLinkService){
        this.service = activationLinkService;
    }

    @PostMapping
    @CrossOrigin
    public int add(@RequestBody ActivationLink link){
        System.out.println("message red: " + link);
        int result = service.addActivationLink(link);
        return result;
    }

    @GetMapping(path = "{link}")
    @CrossOrigin
    public int activate(@PathVariable("link") String link){
        return service.activate(link);
    }


}

package macior.strategygame.api.account;

import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.models.account_management.ChangeInput;
import macior.strategygame.service.account.ChangeLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/changeLogin")
@RestController
public class ChangeLoginController {

    @Autowired
    private ChangeLoginService service;

    @PostMapping(path = "{code}")
    @CrossOrigin
    public StatusResponse addCode(@RequestBody ChangeInput login, @PathVariable("code") String code){
        return service.addCode(login.getText(), code);
    }

    @PutMapping(path = "{code}")
    @CrossOrigin
    public StatusResponse activateCode(@RequestBody ChangeInput input, @PathVariable("code") String code){
        return service.activateCode(input.getText(), code);
    }
}

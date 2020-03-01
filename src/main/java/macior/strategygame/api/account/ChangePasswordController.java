package macior.strategygame.api.account;

import macior.strategygame.models.account_management.ChangeInput;
import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.service.account.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/changePassword")
@RestController
public class ChangePasswordController {

    @Autowired
    private ChangePasswordService passwordService;

    @PostMapping(path = "{code}")
    @CrossOrigin
    public StatusResponse addCode(@RequestBody ChangeInput password, @PathVariable("code") String code) {
        return passwordService.addCode(password.getText(), code);
    }

    @PutMapping(path = "{code}")
    @CrossOrigin
    public StatusResponse activateCode(@RequestBody ChangeInput activationCode, @PathVariable("code") String code){
        return passwordService.activateCode(activationCode.getText(), code);
    }
}

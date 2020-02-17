package macior.strategygame.api;

import macior.strategygame.models.ActivationLink;
import macior.strategygame.service.ActivationLinkService;
import macior.strategygame.service.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        String mail = link.getEmail();
        int result = service.addActivationLink(link);
        return result;

//        switch (result){
//            case 1:
//                return "Login already occupied!";
//            case 2:
//                return "There already exists account with this e-mail address!";
//            case 3:
//                return "Password too weak! It should contain at least 8 characters, including:\n"
//                        + "- at least 1 upper case letter\n"
//                        + "- at least 1 lower case letter\n"
//                        + "- at least 1 digit";
//            default:
//                return "Well done! Activation link has been sent to " + mail + "!";
//        }
    }


}

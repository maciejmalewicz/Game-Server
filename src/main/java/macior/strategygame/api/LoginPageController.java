package macior.strategygame.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/login")
@Controller
public class LoginPageController {

    @GetMapping
    public String getLoginPage(){
        return "templates/LoggingPage/Index.html";
    }
}

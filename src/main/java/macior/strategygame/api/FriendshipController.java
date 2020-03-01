package macior.strategygame.api;

import macior.strategygame.dao.friendship.FriendsResponse;
import macior.strategygame.models.account_management.ChangeInput;
import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.models.friends.ProfileResponse;
import macior.strategygame.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/friendship")
@RestController
public class FriendshipController {

    @Autowired
    private FriendshipService service;

    @GetMapping(path = "{code}")
    @CrossOrigin
    public FriendsResponse getFriends(@PathVariable("code") String code){
        return service.getFriends(code);
    }

    @PutMapping(path = "{code}")
    @CrossOrigin
    public ProfileResponse getProfile(@PathVariable("code") String code, @RequestBody ChangeInput input){
        System.out.println(code);
        System.out.println(input.getText());
        return service.getByLogin(code, input.getText());
    }

    @PostMapping(path = "{code}")
    @CrossOrigin
    public StatusResponse invitePlayer(@PathVariable("code") String code, @RequestBody ChangeInput input){
        return service.invitePlayer(code, input.getText());
    }


}

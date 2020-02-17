package macior.strategygame.api;

import macior.strategygame.models.User;
import macior.strategygame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/user")

@RestController
public class UserController {

    private final UserService service;

    @Autowired //?
    public UserController(UserService userService){
        service = userService;
    }

    @PostMapping
    public void add(@RequestBody User user){
        service.add(user);
    }

//    @GetMapping(path = "{id}")
//    @CrossOrigin
//    public User get(@PathVariable("id") int id){
//        return service.getById(id).orElse(null);
//    }

    @GetMapping(path = "{login}|{password}")
    @CrossOrigin
    public User getAccount(@PathVariable("login") String login, @PathVariable("password") String password){ //for logging in
        System.out.println(login + " " + password);
        return service.getAccount(login, password).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") int id){
        service.delete(id);
    }

    @PutMapping
    public void update(@RequestBody User user){
        int id = user.getId();
        service.update(id, user);
    }

}

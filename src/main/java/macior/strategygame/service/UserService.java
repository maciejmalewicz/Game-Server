package macior.strategygame.service;

import macior.strategygame.dao.users.UserDAO;
import macior.strategygame.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(@Qualifier("userDAO") UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public void add(User user){
        userDAO.add(user);
    }

    public Optional<User> getById(int id){
        return userDAO.getById(id);
    }

    public void delete(int id){
        userDAO.delete(id);
    }

    public void update(int id, User user){
        userDAO.update(id, user);
    }

    public Optional<User> getAccount(String login, String password){
        return userDAO.getAccount(login, password);
    }
}

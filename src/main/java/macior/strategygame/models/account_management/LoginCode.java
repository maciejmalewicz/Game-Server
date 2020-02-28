package macior.strategygame.models.account_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import macior.strategygame.models.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

//changing login
@Entity(name = "login_code")
public class LoginCode implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "code")
    private String code;

    @Column(name = "login")
    private String login;

    public LoginCode(){

    }

    public static LoginCode buildLoginCode(String login){
        if (login == null || login.isEmpty()){
            return null;
        }
        LoginCode loginCode = new LoginCode();
        loginCode.setLogin(login);
        loginCode.setCode(UUID.randomUUID().toString());
        return loginCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

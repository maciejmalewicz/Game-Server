package macior.strategygame.models.account_management;

import macior.strategygame.models.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "password_code")
public class PasswordCode implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "password")
    private String password;

    @Column(name = "code")
    private String code;

    public static PasswordCode buildPasswordCode(String password){
        if (password == null || password.isEmpty()){
            return null;
        }
        PasswordCode passwordCode = new PasswordCode();
        passwordCode.setPassword(password);
        passwordCode.setCode(UUID.randomUUID().toString());
        return passwordCode;
    }

    public PasswordCode clone(){
        PasswordCode out = new PasswordCode();
        out.setUser(this.user);
        out.setCode(this.code);
        out.setPassword(this.password);
        return out;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

package macior.strategygame.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity(name = "user_registered")

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "experience")
    private Long experience;

    public User (@JsonProperty("login") String login, @JsonProperty("password") String password){
        this.login = login;
        this.password = password;
    }

    public User(){}

    public User(ActivationLink link){
        login = link.getLogin();
        password = link.getPassword();
        email = link.getEmail();
        experience = 0L;
    }

    public User cloneWithLogin(String login){
        User out = new User();
        out.setId(this.id);
        out.setLogin(login);
        out.setPassword(this.password);
        out.setEmail(this.email);
        out.setExperience(this.experience);
        return out;
    }

    public User cloneWithPassword(String password){
        User out = new User();
        out.setId(this.id);
        out.setLogin(this.login);
        out.setPassword(password);
        out.setEmail(this.email);
        out.setExperience(this.experience);
        return out;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        if (experience == null){
            this.experience = 0L;
        } else {
            this.experience = experience;
        }

    }

    public String toString(){
        return id + "-" + login + "-" + password;
    }
}

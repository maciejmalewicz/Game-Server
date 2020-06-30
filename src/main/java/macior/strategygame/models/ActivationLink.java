package macior.strategygame.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name = "activation_link")
public class ActivationLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_link")
    private Integer id;

    @Column(name = "activation_link")
    private String activationLink;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public ActivationLink(){

    }

    public ActivationLink(String login){
        this.login = login;
    }

    public ActivationLink(@JsonProperty("login") String login,
                          @JsonProperty("password") String password,
                          @JsonProperty("email") String email){
        this.login = login;
        this.password = password;
        this.email = email;
    }



    public String toString(){
        return login + " " + password + " " + email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivationLink() {
        return activationLink;
    }

    public void setActivationLink(String activationLink) {
        this.activationLink = activationLink;
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


}

package macior.strategygame.models.settings_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import macior.strategygame.models.User;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;

@Entity(name = "settings")
public class Settings implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_user_settings")
    private User user;

    @Column(name = "go_up")
    private int goUp;

    @Column(name = "go_down")
    private int goDown;

    @Column(name = "go_left")
    private int goLeft;

    @Column(name = "go_right")
    private int goRight;

    public Settings(@JsonProperty("goUp") int up, @JsonProperty("goDown") int down,
                    @JsonProperty("goLeft") int left, @JsonProperty("goRight") int right){
        goUp = up;
        goDown = down;
        goLeft = left;
        goRight = right;
    }

    public Settings(){

    }

    public void changeInto(Settings other){
        this.goUp = other.goUp;
        this.goDown = other.goDown;
        this.goLeft = other.goLeft;
        this.goRight = other.goRight;
    }

    @Override
    public String toString(){
        return goUp + " " + goDown + " " + goLeft + " " + goRight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getGoUp() {
        return goUp;
    }

    public void setGoUp(int goUp) {
        this.goUp = goUp;
    }

    public int getGoDown() {
        return goDown;
    }

    public void setGoDown(int goDown) {
        this.goDown = goDown;
    }

    public int getGoLeft() {
        return goLeft;
    }

    public void setGoLeft(int goLeft) {
        this.goLeft = goLeft;
    }

    public int getGoRight() {
        return goRight;
    }

    public void setGoRight(int goRight) {
        this.goRight = goRight;
    }
}

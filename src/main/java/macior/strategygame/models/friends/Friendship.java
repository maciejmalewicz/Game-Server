package macior.strategygame.models.friends;

import macior.strategygame.models.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "friendship")
public class Friendship implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_friendship")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_friend")
    private User firstFriend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_friend")
    private User secondFriend;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getFirstFriend() {
        return firstFriend;
    }

    public void setFirstFriend(User firstFriend) {
        this.firstFriend = firstFriend;
    }

    public User getSecondFriend() {
        return secondFriend;
    }

    public void setSecondFriend(User secondFriend) {
        this.secondFriend = secondFriend;
    }
}

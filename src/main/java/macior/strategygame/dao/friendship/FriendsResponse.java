package macior.strategygame.dao.friendship;

import macior.strategygame.models.ResponseBase;

import java.util.List;

public class FriendsResponse extends ResponseBase {

    private List<Friend> friends;

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}

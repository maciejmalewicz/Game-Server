package macior.strategygame.dao.friendship;

import macior.strategygame.models.StatusResponse;

import java.util.List;

public class FriendsResponse extends StatusResponse {

    private List<Friend> friends;

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}

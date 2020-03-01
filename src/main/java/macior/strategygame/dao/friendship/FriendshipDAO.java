package macior.strategygame.dao.friendship;

import macior.strategygame.dao.Context;
import macior.strategygame.models.User;
import macior.strategygame.service.utilities.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository("friendshipDAO")
public class FriendshipDAO {

    @Autowired
    private Context context;

    @Autowired
    private PlayerGameMapperService mapper;

    private String queryText = "select user_registered.id_user, user_registered.login\n" +
            "from user_registered, friendship\n" +
            "where (user_registered.id_user = friendship.first_friend\n" +
            "or user_registered.id_user = friendship.second_friend)\n" +
            "and (friendship.first_friend = ?\n" +
            "or friendship.second_friend = ?)\n" +
            "and user_registered.id_user != ?;";

    public List<Friend> getFriends(int id){
        System.out.println("Starting query");
        EntityManager manager = context.entityManager();
        Query query = manager.createNativeQuery(queryText);
        query.setParameter(1, id);
        query.setParameter(2, id);
        query.setParameter(3, id);
        List<Object[]> list = query.getResultList();
        List<Friend> friends = new ArrayList<>();
        for (Object[] arr: list){
            Friend friend = new Friend();
            friend.setLogin((String)arr[1]);
            int friendsId = (int)arr[0];
            friend.setActive(mapper.isActive(friendsId));
            friends.add(friend);
        }
        return friends;
    }
}

package macior.strategygame.dao.friendship;

import macior.strategygame.dao.Context;
import macior.strategygame.dao.users.UserDAO;
import macior.strategygame.models.User;
import macior.strategygame.models.friends.Friendship;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository("friendshipDAO")
public class FriendshipDAO {

    @Autowired
    private Context context;

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    private UserDAO userDAO;

    private String queryText = "select user_registered.id_user, user_registered.login\n" +
            "from user_registered, friendship\n" +
            "where (user_registered.id_user = friendship.first_friend\n" +
            "or user_registered.id_user = friendship.second_friend)\n" +
            "and (friendship.first_friend = ?\n" +
            "or friendship.second_friend = ?)\n" +
            "and user_registered.id_user != ?;";

    public List<Friend> joinFriends(User user){
        EntityManager manager = context.entityManager();
        Query query = manager.createNativeQuery(queryText);
        int id = user.getId();
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
            //here i notify other players that i'm active
            System.out.println(friendsId);
            System.out.println(user);
            mapper.notifyAboutLoggingIn(friendsId, user.getLogin());
            friends.add(friend);
        }
        return friends;
    }

    private String query2Text = "select count(id_friendship)\n" +
            "from friendship\n" +
            "where (first_friend = ? and second_friend = ?)\n" +
            "or (first_friend = ? and second_friend = ?);";

    public boolean areFriends(User first, User second){
        EntityManager manager = context.entityManager();
        Query query = manager.createNativeQuery(query2Text);
        int firstId = first.getId();
        int secondId = second.getId();
        query.setParameter(1, firstId);
        query.setParameter(2, secondId);
        query.setParameter(3, secondId);
        query.setParameter(4, firstId);
        BigInteger result = (BigInteger) query.getSingleResult();
        System.out.println(result);
        return result.intValue() > 0;
    }

    public int addFriends(User user1, User user2){
        EntityManager manager = context.entityManager();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        Friendship friendship = new Friendship();
        friendship.setFirstFriend(user1);
        friendship.setSecondFriend(user2);
        try {
            manager.persist(friendship);
            manager.flush();
            transaction.commit();
            manager.clear();
            return 0;
        } catch (Exception exc){
            exc.printStackTrace();
            transaction.rollback();
            manager.clear();
            return -1;
        }
    }
}

package macior.strategygame.dao.users;

import macior.strategygame.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDAO extends JpaRepository<User, Integer>, QueryByExampleExecutor<User> {
}

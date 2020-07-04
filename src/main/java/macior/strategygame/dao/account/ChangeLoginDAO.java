package macior.strategygame.dao.account;


import macior.strategygame.models.account_management.LoginCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeLoginDAO extends
        JpaRepository<LoginCode, Integer>,
        QueryByExampleExecutor<LoginCode> {

}

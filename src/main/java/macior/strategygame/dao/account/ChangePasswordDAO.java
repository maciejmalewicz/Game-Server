package macior.strategygame.dao.account;

import macior.strategygame.models.account_management.PasswordCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangePasswordDAO extends
        JpaRepository<PasswordCode, Integer>,
        QueryByExampleExecutor<PasswordCode> {
}

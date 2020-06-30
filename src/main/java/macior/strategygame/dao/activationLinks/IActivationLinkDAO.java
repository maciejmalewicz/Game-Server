package macior.strategygame.dao.activationLinks;

import macior.strategygame.models.ActivationLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActivationLinkDAO extends JpaRepository<ActivationLink, Integer> {
}

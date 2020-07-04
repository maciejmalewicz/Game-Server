package macior.strategygame.dao.activationLinks;

import macior.strategygame.models.ActivationLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IActivationLinkDAO extends JpaRepository<ActivationLink, Integer> {

    Optional<ActivationLink> findFirstByActivationLink(String activationLink);
}

package macior.strategygame.dao.activationLinks;

import macior.strategygame.models.ActivationLink;

import java.util.UUID;

public interface IActivationLinkDAO {

    void add(UUID id, ActivationLink link);
}

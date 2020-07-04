package macior.strategygame.service.registration;

import macior.strategygame.models.ActivationLink;
import macior.strategygame.service.account.models.UserConcernedModel;

public class ActivationLinkAddingModel extends UserConcernedModel {
    public String EMAIL;
    public String LOGIN;
    public String PASSWORD;
    public ActivationLink ACTIVATION_LINK;
}

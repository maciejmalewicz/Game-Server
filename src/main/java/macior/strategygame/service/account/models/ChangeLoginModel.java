package macior.strategygame.service.account.models;

import macior.strategygame.models.User;
import macior.strategygame.models.account_management.LoginCode;
import macior.strategygame.service.playerCommandPipelines.models.ChainModel;

public class ChangeLoginModel extends ChainModel {
    public LoginCode LOGIN_CODE;
    public String LOGIN;
    public User USER;
}

package macior.strategygame.dao.friendship;

public class Friend {

    private String login;
    private boolean isActive;

    @Override
    public String toString(){
        return login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

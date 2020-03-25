package macior.strategygame.game.PlayersManagement.Notifications;


import java.util.ArrayList;
import java.util.List;

public class NotificationsInbox {

    private List<NotificationBase> notifications = new ArrayList<>();

    public List<NotificationBase> getNotifications() {
        return notifications;
    }

    public void addNotification(NotificationBase notification){
        notifications.add(notification);
    }

    public void clearNotifications(){
        notifications.clear();
    }

    public NotificationsInbox clone(){
        NotificationsInbox inbox = new NotificationsInbox();
        inbox.notifications.addAll(this.notifications);
        return inbox;
    }
}

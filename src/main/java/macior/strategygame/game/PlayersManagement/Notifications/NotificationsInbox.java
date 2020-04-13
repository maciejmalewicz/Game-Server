package macior.strategygame.game.PlayersManagement.Notifications;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NotificationsInbox {

    private final List<NotificationBase> notifications = new LinkedList<>();

    public List<NotificationBase> getNotifications() {
        return notifications;
    }

    public void addNotification(NotificationBase notification){
        synchronized (notifications){
            notifications.add(notification);
        }
    }

    public NotificationsInbox cloneAndClear(){
        NotificationsInbox inbox = new NotificationsInbox();
        while (true){
            synchronized (notifications){
                if (notifications.isEmpty()){
                    break;
                }
                NotificationBase notification = notifications.get(0);
                inbox.notifications.add(notification);
                notifications.remove(notification);
            }
        }
        if (inbox.notifications.size() != 0){
            return inbox;
        }
        return inbox;
    }
}

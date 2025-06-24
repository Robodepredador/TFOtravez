package model.repository;
import model.models.*;

import java.util.List;

public interface NotificationRepository {
    Notification saveNotification(Notification notification);
    List<Notification> findNotificationByUser(User user);
    List<Notification> findNotificationUnreadByUser(User user);
    void markNotificationAsRead(int notificationId);
    void markAllNotificationsAsRead(User user);
    void deleteNotification(int id);
    int countUnreadNotifications(User user);
}

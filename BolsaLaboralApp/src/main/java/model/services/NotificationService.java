package model.services;
import model.models.*;
import model.repository.*;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationService {
    private final NotificationRepository notificationRepo;

    public NotificationService(NotificationRepository notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    public Notification createNotification(Notification notification) {
        return notificationRepo.saveNotification(notification);
    }

    public Notification sendJobNotification(User user, JobVacancy job, String message) {
        Notification notification = new Notification(
                0,
                user.getId(),
                "Nueva oportunidad: " + job.getTitle(),
                message,
                LocalDateTime.now(),
                false
        );
        notificationRepo.saveNotification(notification);
        return notification;
    }

    public List<Notification> getUserNotifications(User user) {
        return notificationRepo.findNotificationByUser(user);
    }

    public List<Notification> getUnreadNotifications(User user) {
        return notificationRepo.findNotificationUnreadByUser(user);
    }

    public void markNotificationAsRead(int notificationId) {
        notificationRepo.markNotificationAsRead(notificationId);
    }

    public void markAllNotificationsAsRead(User user) {
        notificationRepo.markAllNotificationsAsRead(user);
    }

    public void deleteNotification(int id) {
        notificationRepo.deleteNotification(id);
    }

    public int getUnreadNotificationCount(User user) {
        return notificationRepo.countUnreadNotifications(user);
    }

}

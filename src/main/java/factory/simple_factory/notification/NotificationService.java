package factory.simple_factory.notification;

public class NotificationService {

    public static void main(String[] args) {
        NotificationFactory notificationFactory = new NotificationFactory();
        Notification notification = notificationFactory.createNotification(("sms"));
        notification.notifyUser();

        notification = notificationFactory.createNotification("email");
        notification.notifyUser();
    }
}

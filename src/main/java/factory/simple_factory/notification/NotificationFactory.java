package factory.simple_factory.notification;

import java.util.Locale;

public class NotificationFactory {

    public Notification createNotification(String channel) {
        String cmd = channel.toUpperCase();

        switch (cmd) {
            case "SMS":
                return new SMSNotification();
            case "EMAIL":
                return new EmailNotification();
            case "PUSH":
                return new PushNotification();
            default:
                return null;
        }
    }
}

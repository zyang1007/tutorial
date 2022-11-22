package factory.simple_factory.notification;

public class PushNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("sending a push notification...");
    }
}

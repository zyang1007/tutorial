package factory.simple_factory.notification;

public class SMSNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("send a SMS notification...");
    }
}

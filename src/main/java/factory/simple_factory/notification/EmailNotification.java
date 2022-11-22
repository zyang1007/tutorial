package factory.simple_factory.notification;

public class EmailNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("sending an email notification...");
    }
}

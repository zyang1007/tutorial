package Observer;

public class Client {

    public static void main(String[] args) {
        SubscriptionSubject subject = new SubscriptionSubject();

        subject.attach(new WeiXinUser("Aaa"));
        subject.attach(new WeiXinUser("Bbb"));
        subject.attach(new WeiXinUser("Ccc"));

        subject.notify("wake up!!!");
    }
}

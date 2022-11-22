package builder.demp_phone;

public class Client {

    public static void main(String[] args) {
        Phone phone = new Phone.Builder()
                .cpu("intel")
                .screen("samsung")
                .memory("kingston")
                .mainBoard("asus")
                .build();

        System.out.println(phone);
    }
}

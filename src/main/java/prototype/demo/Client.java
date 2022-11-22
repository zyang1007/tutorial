package prototype.demo;

public class Client {

    public static void main(String[] args) throws CloneNotSupportedException {
        RealizeType realizeType = new RealizeType();

        RealizeType clone = realizeType.clone();

        System.out.println(realizeType);
        System.out.println(clone);
        System.out.println(realizeType == clone);
    }
}

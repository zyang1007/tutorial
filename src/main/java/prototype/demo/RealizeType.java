package prototype.demo;

public class RealizeType implements Cloneable {

    public RealizeType() {
        System.out.println("Object is created");
    }

    @Override
    protected RealizeType clone() throws CloneNotSupportedException {
        System.out.println("copy a new object of prototype");
        return (RealizeType) super.clone();
    }
}

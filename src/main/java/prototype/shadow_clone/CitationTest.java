package prototype.shadow_clone;

public class CitationTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Student student = new Student();
        student.setName("Zhang San");

        Citation citation1 = new Citation();
        citation1.setStudent(student);

        Citation citation2 = citation1.clone();
        citation2.getStudent().setName("Li Si");

        citation1.show();  // name becomes the same after modifying
        citation2.show();
    }
}

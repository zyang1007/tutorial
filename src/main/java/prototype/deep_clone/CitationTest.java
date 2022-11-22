package prototype.deep_clone;

import java.io.*;

public class CitationTest {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Student student = new Student();
        student.setName("Zhang San");

        Citation citation1 = new Citation();
        citation1.setStudent(student);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/yang/Downloads/a.txt"));
        oos.writeObject(citation1);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/yang/Downloads/a.txt"));
        Citation citation2 = (Citation) ois.readObject();
        ois.close();

        citation2.getStudent().setName("Li Si");

        citation1.show();  // different names because different references
        citation2.show();
    }
}

package prototype.deep_clone;

import java.io.Serializable;

public class Citation implements Cloneable, Serializable {

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Citation{" +
                "student=" + student +
                '}';
    }

    @Override
    protected Citation clone() throws CloneNotSupportedException {
        return (Citation) super.clone();
    }

    public void show() {
        System.out.println(student.getName() + ": get a citation...");
    }
}

import java.util.ArrayList;
import java.util.List;

public class Advisor extends Instructor {
    private List<Student> students;

    public Advisor(String firstName, String lastName) {
        super(firstName, lastName);
        this.students = new ArrayList<Student>();
        System.out.println("Advisor created");
    }

    public Advisor(String firstName, String lastName, List<Course> givenCourses) {
        super(firstName, lastName, givenCourses);
        this.students = new ArrayList<Student>();
        System.out.println("Advisor created");
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        System.out.println("Advisor students set");
    }

    public void addStudent(Student student) {
        this.students.add(student);
        System.out.println("Advisor student added");
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
        System.out.println("Advisor student removed");
    }

    public void adviseStudent(Student student) {
        this.addStudent(student);
        //student.setAdvisor(this);
        System.out.println("Advisor advised student");
    }
}

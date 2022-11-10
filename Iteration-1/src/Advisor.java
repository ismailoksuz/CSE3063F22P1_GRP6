import java.util.List;

public class Advisor {
    private List<Student> students;

    public Advisor() {
    }
    public Advisor(List<Student> students) {
        this.students = students;
    }
    public void approveRequest(Student student) {
        System.out.println("Request approved");
    }
}

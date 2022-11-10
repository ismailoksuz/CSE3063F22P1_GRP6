import java.util.ArrayList;

public class Student extends Person {
    private StudentId studentId;
    private Transcript transcript;
    private Advisor advisor;
    private ArrayList<Course> requestedCourses;
    private int registrationYear;
    private int order;
    private int currentYear;
    private int semesterNumber;

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
        this.transcript = new Transcript();
        this.studentId = new StudentId(registrationYear, order);
    }

    public ArrayList<Course> sendRequest() {
        return requestedCourses;
    }

    public Transcript getTranscript() {
        return this.transcript;
    }
}

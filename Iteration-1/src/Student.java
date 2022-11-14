import java.time.Year;
import java.util.ArrayList;

public class Student extends Person {
    private StudentId studentId;
    private Transcript transcript;
    private Advisor advisor;
    private ArrayList<Course> requestedCourses;
    private int registrationYear;
    private int order;
    private int currentYear;
    private int semester;
    private ArrayList<String> studentOutput;

    public Student(String firstName, String lastName, int registrationYear, int order) {
        super(firstName, lastName);
        this.registrationYear = registrationYear;
        this.order = order;
        this.requestedCourses = new ArrayList<Course>();
        this.currentYear = Year.now().getValue();
        this.transcript = new Transcript();
        this.studentId = new StudentId(registrationYear, order);
        this.studentOutput = new ArrayList<String>();
    }

    public ArrayList<String> getStudentOutput() {
        return studentOutput;
    }

    public void setStudentOutput(ArrayList<String> studentOutput) {
        this.studentOutput = studentOutput;
    }

    public ArrayList<Course> sendRequest() {
        return requestedCourses;
    }

    public String getStudentName() {
        return getFirstName() + " " + getLastName();
    }

    public Transcript getTranscript() {
        return this.transcript;
    }

    public StudentId getStudentId() {
        return this.studentId;
    }

    public void setStudentId(StudentId studentId) {
        this.studentId = studentId;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public Advisor getAdvisor() {
        return this.advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public ArrayList<Course> getRequestedCourses() {
        return this.requestedCourses;
    }

    public void setRequestedCourses(ArrayList<Course> requestedCourses) {
        this.requestedCourses = requestedCourses;
    }

    public int getRegistrationYear() {
        return this.registrationYear;
    }

    public void setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getCurrentYear() {
        return this.currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public int getSemester() {
        return this.semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }


}

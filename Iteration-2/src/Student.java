import java.time.Year;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class Student extends Person {
    static Logger log = Logger.getLogger(Student.class);
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
        log.info("Student created =>  " + "Name: " + this.getStudentName() + " RegistrationYear: "
                + this.getRegistrationYear() + " Order: " + this.getOrder());
    }

    public ArrayList<String> getStudentOutput() {
        return studentOutput;
    }

    public void setStudentOutput(ArrayList<String> studentOutput) {
        this.studentOutput = studentOutput;
        /* log.info(this.getStudentName() + ": student output changed."); */
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
        /* log.info(this.getStudentName() + ": student id changed."); */
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
        /* log.info(this.getStudentName() + ": student transcript changed."); */
    }

    public Advisor getAdvisor() {
        return this.advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
        /* log.info(this.getStudentName() + ": student advisor changed."); */
    }

    public ArrayList<Course> getRequestedCourses() {
        return this.requestedCourses;
    }

    public void setRequestedCourses(ArrayList<Course> requestedCourses) {
        this.requestedCourses = requestedCourses;
        log.info(this.getStudentName() + ": student requested courses changed.");
    }

    public int getRegistrationYear() {
        return this.registrationYear;
    }

    public void setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
        /* log.info(this.getStudentName() + ": student registration year changed."); */
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
        /* log.info(this.getStudentName() + ": student registration order changed."); */
    }

    public int getCurrentYear() {
        return this.currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
        /* log.info(this.getStudentName() + ": student current year changed."); */
    }

    public int getSemester() {
        return this.semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
        log.info(this.getStudentName() + ": student semester changed.");
    }
}

import java.util.ArrayList;
import org.apache.log4j.Logger;

public abstract class Course {
    private Logger log = Logger.getLogger(Course.class);
    private String courseCode;
    private String courseName;
    private int courseCredit;
    private Schedule courseSchedule;
    private int quota;
    private Instructor courseInstructor;
    private ArrayList<Student> students;
    private int quotaProblem;
    private int collisionProblem;
    private int failedCredits;
    private int failedPreq;

    public Course(String courseName, String courseCode, int courseCredit, int courseDay, String courseHour, int quota) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCredit = courseCredit;
        this.courseSchedule = new Schedule(courseDay, courseHour);
        this.quota = quota;
        this.students = new ArrayList<Student>();
        this.quotaProblem = 0;
        this.collisionProblem = 0;
        this.failedCredits = 0;
        this.failedPreq = 0;
        log.info(this.getCourseName() + " (" + this.getCourseCode() + ")" + " named course created.");
    }

    public abstract boolean isEligibleToRequest(Student student);

    // GETTER & SETTER
    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getQuato() {
        return quota;
    }

    public Instructor getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(Instructor courseInstructor) {
        this.courseInstructor = courseInstructor;
        /*
         * log.info(this.getCourseName() + " now has a instructor: " +
         * this.getCourseInstructor());
         */
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public Schedule getCourseSchedule() {
        return this.courseSchedule;
    }

    public int getQuotaProblem() {
        return this.quotaProblem;
    }

    public void setQuotaProblem(int quotaProblem) {
        this.quotaProblem = quotaProblem;
        log.info(this.getCourseName() + ": Number of quota problem changed." + "(" + "New: " + this.getQuotaProblem()
                + ")");
    }

    public int getCollisionProblem() {
        return this.collisionProblem;
    }

    public void setCollisionProblem(int collisionProblem) {
        this.collisionProblem = collisionProblem;
        log.info(
                this.getCourseName() + ": Number of collision problem changed." + "(" + "New: "
                        + this.getCollisionProblem()
                        + ")");
    }

    public int getFailedCredits() {
        return this.failedCredits;
    }

    public void setFailedCredits(int failedCredits) {
        this.failedCredits = failedCredits;
        log.info(this.getCourseName() + ": Number of failed credits changed." + "(" + "New: " + this.getFailedCredits()
                + ")");
    }

    public int getFailedPreq() {
        return this.failedPreq;
    }

    public void setFailedPreq(int failedPreq) {
        this.failedPreq = failedPreq;
        log.info(this.getCourseName() + ": Number of failed prerequisite changed." + "(" + "New: "
                + this.getFailedPreq()
                + ")");
    }
}
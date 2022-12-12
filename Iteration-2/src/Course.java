import java.util.ArrayList;
import org.apache.log4j.Logger;

public abstract class Course {
    static Logger log = Logger.getLogger(Course.class);
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
        /* log.info("Course name changed."); */
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
        /* log.info("Course code changed."); */
    }

    public int getQuato() {
        return quota;
    }

    public void setQuato(int quota) {
        this.quota = quota;
        log.info(this.getCourseName() + ": Course quota changed." + "(" + "New: " + this.getQuato() + ")");
    }

    public Instructor getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(Instructor courseInstructor) {
        this.courseInstructor = courseInstructor;
        /* log.info(this.getCourseName() + " now has a instructor: " + this.getCourseInstructor()); */
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
        /* log.info(this.getCourseName() + ": Student list changed."); */
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
        log.info(this.getCourseName() + ": Course credits changed." + "(" + "New: " + this.getCourseCredit() + ")");
    }

    public Schedule getCourseSchedule() {
        return this.courseSchedule;
    }

    public void setCourseSchedule(Schedule courseSchedule) {
        this.courseSchedule = courseSchedule;
        /* log.info(this.getCourseName() + ": Course schedule changed." + "(" + "New: " + courseSchedule.toString() + ")"); */
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

/* 
import java.util.ArrayList;

public abstract class Course {
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
    /* private ArrayList<Student> studentsFailedPreq;
    private ArrayList<Student> studentsFailedCredits;
    private ArrayList<Student> studentsQuotaProblem;
    private ArrayList<Student> studentsCollisionProblem; */

    /*
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
        /* this.studentsFailedPreq = new ArrayList<Student>();
        this.studentsQuotaProblem = new ArrayList<Student>();
        this.studentsCollisionProblem = new ArrayList<Student>();
        this.studentsFailedCredits = new ArrayList<Student>(); 

    }

    public abstract boolean isEligibleToRequest(Student student);

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getQuato() {
        return quota;
    }

    public void setQuato(int quota) {
        this.quota = quota;
    }

    public Instructor getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(Instructor courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }

    public Schedule getCourseSchedule() {
        return this.courseSchedule;
    }

    public void setCourseSchedule(Schedule courseSchedule) {
        this.courseSchedule = courseSchedule;
    }

    public int getQuotaProblem() {
        return this.quotaProblem;
    }

    public void setQuotaProblem(int quotaProblem) {
        this.quotaProblem = quotaProblem;
    }

    public int getCollisionProblem() {
        return this.collisionProblem;
    }

    public void setCollisionProblem(int collisionProblem) {
        this.collisionProblem = collisionProblem;
    }

    public int getFailedCredits() {
        return this.failedCredits;
    }

    public void setFailedCredits(int failedCredits) {
        this.failedCredits = failedCredits;
    }

    public int getFailedPreq() {
        return this.failedPreq;
    }

    public void setFailedPreq(int failedPreq) {
        this.failedPreq = failedPreq;
    }

    /* public ArrayList<Student> getStudentsFailedPreq() {
        return studentsFailedPreq;
    }
    
    public void setStudentsFailedPreq(ArrayList<Student> studentsFailedPreq) {
        this.studentsFailedPreq = studentsFailedPreq;
    }
    
    public ArrayList<Student> getStudentsFailedCredits() {
        return studentsFailedCredits;
    }
    
    public void setStudentsFailedCredits(ArrayList<Student> studentsFailedCredits) {
        this.studentsFailedCredits = studentsFailedCredits;
    }
    
    public ArrayList<Student> getStudentsQuotaProblem() {
        return studentsQuotaProblem;
    }
    
    public void setStudentsQuotaProblem(ArrayList<Student> studentsQuotaProblem) {
        this.studentsQuotaProblem = studentsQuotaProblem;
    }
    
    public ArrayList<Student> getStudentsCollisionProblem() {
        return studentsCollisionProblem;
    }
    
    public void setStudentsCollisionProblem(ArrayList<Student> studentsCollisionProblem) {
        this.studentsCollisionProblem = studentsCollisionProblem;
    } */
}

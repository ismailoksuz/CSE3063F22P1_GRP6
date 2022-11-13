import java.util.ArrayList;

public abstract class Course {
    private String courseCode;
    private String courseName;
    private int courseCredit;
    private Schedule courseSchedule;
    private int quotaProblem;
    private int collisionProblem;
    private int failedCredits;
    private int failedPreq;
    /* private ArrayList<Student> studentsFailedPreq;
    private ArrayList<Student> studentsFailedCredits;++
    private ArrayList<Student> studentsQuotaProblem;++
    private ArrayList<Student> studentsCollisionProblem;++ */
    private int quota;
    private Instructor courseInstructor;
    private ArrayList<Student> students;

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

    }

    public abstract boolean isEligibleToRequest(Student student);

    /* public boolean checkPrerequistiesOfStudentForRegistration(Student student) {
        System.out.println(
                "Checking prerequisties courses of " + student.getStudentId().toString() + " for registration...");
        for (Course course : this.prerequisites) {
            if (student.getTranscript().getFailedCourses().contains(course)) {
                System.out.println("Student Id of" + student.getStudentId().toString() + " can not enroll "
                        + this.getCourseCode() + " course.");
                return false;
            }
        }
        return true;
    } */

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

}

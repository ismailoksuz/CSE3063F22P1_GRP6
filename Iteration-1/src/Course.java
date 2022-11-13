import java.util.ArrayList;

public abstract class Course {
    private String courseCode;
    private String courseName;
    private int courseCredit;
    private int courseDay;
    private String courseHour;
    private ArrayList<Student> studentsEnrolledCourse;
    private Schedule courseSchedule;
    private ArrayList<Student> studentsFailedPreq;
    private ArrayList<Student> studentsFailedCredits;
    private ArrayList<Student> studentsQuotaProblem;
    private ArrayList<Student> studentsCollisionProblem;

    private int quota;
    private Instructor courseInstructor;

    private ArrayList<Student> students;

    public Course() {
    }

    public Course(String courseName, String courseCode, int courseCredit, int courseDay, String courseHour, int quota) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCredit = courseCredit;
        this.courseDay = courseDay;
        this.courseHour = courseHour;
        this.quota = quota;

    }

    public boolean checkQuotaForRegistration() {
        System.out.println("Checking course quota for registration...");
        if (this.studentsEnrolledCourse.size() < this.quota) {
            return true;
        }
        System.out.println("Quota is full for " + this.courseCode + "(" + this.courseName + ")");
        return false;
    }

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
        return students;
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

    public int getCourseDay() {
        return courseDay;
    }

    public void setCourseDay(int courseDay) {
        this.courseDay = courseDay;
    }

    public String getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(String courseHour) {
        this.courseHour = courseHour;
    }
}

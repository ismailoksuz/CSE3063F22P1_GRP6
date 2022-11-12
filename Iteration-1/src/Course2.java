import java.util.ArrayList;

public abstract class Course {
    private String courseCode;
    private String courseName;
    private int courseCredit;
    private int courseDay;
    private String courseHour;


    private int quato;
    private Instructor courseInstructor;


    private ArrayList<Student> students;

    public Course(){
    }

    public Course(String courseName, String courseCode, int courseCredit, int courseDay, String courseHour, int quato){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCredit = courseCredit;
        this.courseDay = courseDay;
        this.courseHour = courseHour;
        this.quato = quato;

    }

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
        return quato;
    }

    public void setQuato(int quato) {
        this.quato = quato;
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

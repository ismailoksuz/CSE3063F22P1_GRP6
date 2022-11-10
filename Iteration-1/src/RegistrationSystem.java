import java.util.ArrayList;

public class RegistrationSystem {

    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<Advisor> advisors;

    public RegistrationSystem() {
    }

    public void addMandatoryCourses() {
        //input.jsondan çek
    }

    public void failRandomCourses() {
    }

    public void storeStudents() {
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Advisor> getAdvisors() {
        return this.advisors;
    }

    public void setAdvisors(ArrayList<Advisor> advisors) {
        this.advisors = advisors;
    }
}

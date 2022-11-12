import java.util.ArrayList;

public class RegistrationSystem {

    private ArrayList<Student> studentsList;
    private ArrayList<Course> coursesList;
    private ArrayList<Advisor> advisorsList;

    public RegistrationSystem() throws Exception {
        Parser parser = new Parser();
        studentsList = parser.parseStudents();
        coursesList = parser.parseCourses();
        advisorsList = parser.parseAdvisors();
    }

    public void failRandomCourses() {
    }

    public void storeStudents() {
    }

    public ArrayList<Student> getStudentsList() {
        return this.studentsList;
    }

    public void setStudentsList(ArrayList<Student> studentsList) {
        this.studentsList = studentsList;
    }

    public ArrayList<Course> getCoursesList() {
        return this.coursesList;
    }

    public void setCoursesList(ArrayList<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public ArrayList<Advisor> getAdvisorsList() {
        return this.advisorsList;
    }

    public void setAdvisorsList(ArrayList<Advisor> advisorsList) {
        this.advisorsList = advisorsList;
    }
}

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class RegistrationSystem {

    private ArrayList<Student> studentsList;
    private ArrayList<Course> coursesList;
    private ArrayList<Advisor> advisorsList;

    public RegistrationSystem() throws Exception {
        studentsList = new ArrayList<Student>();
        coursesList = new ArrayList<Course>();
        advisorsList = new ArrayList<Advisor>();
        createStudents();
        createCourses();
    }

    public void createCourses() throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject input = (JSONObject) parser.parse(new FileReader("Iteration-1\\src\\courses.json"));
        JSONArray inputCourses = (JSONArray) input.get("courses");

        for (Object c : inputCourses) {
            JSONObject course = (JSONObject) c;
            String courseCode = (String) course.get("courseCode");
            int courseSemester = (int) (long) course.get("semester");
            int credits = (int) (long) course.get("credits");
            int quota = (int) (long) course.get("quota");
            int courseDay = (int) (long) course.get("courseDay");
            String courseHour = (String) course.get("courseHour");
            ArrayList<Course> preRequisiteCourses = new ArrayList<>();

            Course newCourse = new Course(courseCode, credits, courseSemester, quota, preRequisiteCourses,
                    new Schedule(courseDay, courseHour));
            System.out.println(newCourse.toString());
            coursesList.add(newCourse);
        }
    }

    public void createStudents() throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject input = (JSONObject) parser.parse(new FileReader("Iteration-1\\src\\students.json"));
        JSONArray students = (JSONArray) input.get("students");

        for (Object s : students) {
            JSONObject student = (JSONObject) s;
            String firstName = (String) student.get("firstName");
            String lastName = (String) student.get("lastName");
            int registrationYear = (int) (long) student.get("registrationYear");
            int order = (int) (long) student.get("order");

            Student newStudent = new Student(firstName, lastName, registrationYear, order);
            System.out.println(newStudent.toString());
            studentsList.add(newStudent);

        }
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

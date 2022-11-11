import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RegistrationSystem {

    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<Advisor> advisors;

    public RegistrationSystem() throws Exception {
        createStudents();
    }

    public Course findCourse(String courseCode) {
        for (Course c : courses) {
            if (c.getCourseCode().equals(courseCode)) {
                return c;
            }
        }
        return null;
    }

    public void addMandatoryCourses() throws FileNotFoundException, IOException, ParseException {
        //çalışmıyor
        /* JSONParser parser = new JSONParser();
        JSONObject input = (JSONObject) parser.parse(new FileReader("input.json"));
        JSONArray inputCourses = (JSONArray) input.get("MandatoryCourses");
        
        for (Object c : inputCourses) { //Read mandatory courses and initialize
            JSONObject course = (JSONObject) c;
            String courseCode = (String) course.get("courseCode");
            int courseSemester = ((Number) course.get("semester")).intValue();
            int credits = (int) (long) course.get("credits");
            int quota = (int) (long) course.get("quota");
            ArrayList<Course> preRequisiteCourses = new ArrayList<>();
            JSONArray preRequisites = (JSONArray) course.get("preRequisites");
            for (Object p : preRequisites) {
                preRequisiteCourses.add(findCourse((String) p));
            }
        
            Course newCourse = new Course(courseCode, credits, courseSemester, quota, preRequisiteCourses);
            courses.add(newCourse);
        } */

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
        }
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

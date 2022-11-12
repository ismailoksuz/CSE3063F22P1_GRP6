import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Parser {
    static Parser parser = new Parser();

    public Parser() {

    }

    public ArrayList<Advisor> parseAdvisors() throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        File file = new File("Iteration-1/src/test_advisor.json");
        FileReader reader = new FileReader(file);
        JSONObject advisorsList = (JSONObject) parser.parse(reader);

        JSONArray advisors = (JSONArray) advisorsList.get("advisors");
        ArrayList<Advisor> advisorList = new ArrayList<Advisor>();

        for (Object anAdvisor : advisors) {
            JSONObject advisor = (JSONObject) anAdvisor;
            String firstName = (String) advisor.get("firstName");
            String lastName = (String) advisor.get("lastName");
            Advisor newAdvisor = new Advisor(firstName, lastName);
            advisorList.add(newAdvisor);
        }
        return advisorList;
    }

    public ArrayList<Student> parseStudents() throws FileNotFoundException, IOException, ParseException {
        ArrayList<Student> studentsList = new ArrayList<Student>();

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
        return studentsList;
    }

    public ArrayList<Course> parseCourses() throws FileNotFoundException, IOException, ParseException {
        ArrayList<Course> coursesList = new ArrayList<Course>();

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
        return coursesList;
    }
}
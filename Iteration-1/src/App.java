import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class App {

    public static void main(String[] args) throws Exception {
        // RegistrationSystem rs = new RegistrationSystem();

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
        }
    }

}

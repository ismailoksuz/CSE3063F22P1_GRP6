import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class App {

    public static void main(String[] args) throws Exception {
        RegistrationSystem rs = new RegistrationSystem();
        System.out.println("\n\n");

        for (Student s : rs.getStudentsList()) {
            System.out.println(s.toString());
        }

        for (Course c : rs.getCoursesList()) {
            System.out.println(c.toString());
        }
    }

}

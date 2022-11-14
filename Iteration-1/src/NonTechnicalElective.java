import java.util.ArrayList;

public class NonTechnicalElective extends ElectiveCourse {

    public NonTechnicalElective(String courseName, String courseCode, int courseCredit, int courseDay,
            String courseHour, int quato, ArrayList<Integer> semesters) {
        super(courseName, courseCode, courseCredit, courseDay, courseHour, quato, semesters);

    }

    public boolean isEligibleToRequest(Student student) {
        return semesterControl(student);
    }

}

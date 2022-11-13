import java.util.ArrayList;

public class ElectiveCourse extends Course{

    private ArrayList<Integer> semesters;

    public ElectiveCourse(){}


    public ElectiveCourse(String courseName, String courseCode, int courseCredit, int courseDay, String courseHour,
                          int quato, ArrayList<Integer> semesters){
        super(courseName, courseCode, courseCredit, courseDay, courseHour, quato);
        this.semesters = semesters;
    }

    public boolean semesterControl(Student student){
        for (int semester : semesters){
            return student.getSemester() == semester;
        }
        return true;
    }
    public ArrayList<Integer> getSemester() {
        return semesters;
    }

    public void setSemester(ArrayList<Integer> semesters) {
        this.semesters = semesters;
    }
}

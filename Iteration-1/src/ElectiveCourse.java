import java.util.ArrayList;

public abstract class ElectiveCourse extends Course {

    private ArrayList<Integer> semesters;

    public ElectiveCourse(String courseName, String courseCode, int courseCredit, int courseDay, String courseHour,
            int quato, ArrayList<Integer> semesters) {
        super(courseName, courseCode, courseCredit, courseDay, courseHour, quato);
        this.semesters = semesters;
    }

    public boolean semesterControl(Student student) {
        boolean isTrue = false;
        for (int semester : this.getSemesters()) {
            if (student.getSemester() == semester) {
                isTrue = true;
                break;
            }
        }
        return isTrue;
    }

    public boolean semesterCheck(int semester) {
        boolean isTrue = false;
        for (int s : this.getSemesters()) {
            if (s == semester) {
                isTrue = true;
                break;
            }
        }
        return isTrue;
    }

    public ArrayList<Integer> getSemesters() {
        return semesters;
    }

    public void setSemesters(ArrayList<Integer> semesters) {
        this.semesters = semesters;
    }
}

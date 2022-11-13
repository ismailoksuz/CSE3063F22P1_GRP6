import java.util.ArrayList;

public class MandatoryCourse extends Course {

    private int semester;
    private ArrayList<Course> prerequisites;

    public MandatoryCourse(String courseName, String courseCode, int courseCredit, int courseDay, String courseHour,
            int quota, int semester, ArrayList<Course> prerequisites) {
        super(courseName, courseCode, courseCredit, courseDay, courseHour, quota);

        this.semester = semester;
        this.prerequisites = prerequisites;
    }

    public boolean isEligibleToRequest(Student student) {
        return student.getSemester() >= this.getSemester()
                && student.getTranscript().hasBeenPassedCourses(this.getPrequisites());
    }

    // GETTER & SETTERPRE
    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public ArrayList<Course> getPrequisites() {
        return prerequisites;
    }

    public void setPrequisites(ArrayList<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

}

import java.util.ArrayList;

public class MandatoryCourse extends Course {

    private int semester;
    private ArrayList<Course> prequisites;



    public MandatoryCourse(){
    }


    public MandatoryCourse(String courseName, String courseCode, int courseCredit, int courseDay, String courseHour,
                           int quato, int semester, ArrayList<Course> prequisites){
        super(courseName, courseCode, courseCredit, courseDay, courseHour, quato);

        this.semester = semester;
        this.prequisites = prequisites;
    }

    public boolean isEligibleToRequest(Student student){
        return student.getSemester() >= this.getSemester() && student.getTranscript().hasBeenPassedCourses(this.getPrequisites());
    }

    // GETTER & SETTERPRE
    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
    public ArrayList<Course> getPrequisites() {
        return prequisites;
    }

    public void setPrequisites(ArrayList<Course> prequisites) {
        this.prequisites = prequisites;
    }


}

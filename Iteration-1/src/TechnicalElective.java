import java.util.ArrayList;

public class TechnicalElective extends ElectiveCourse {
    private int requiredCredits;
    private ArrayList<Course> prequisites;

    TechnicalElective(){
    }
    TechnicalElective(String courseName, String courseCode, int courseCredit, int courseDay, String courseHour,
                      int quato, ArrayList<Integer> semester, int requiredCredits, ArrayList<Course> prequisites){
        super(courseName, courseCode, courseCredit, courseDay, courseHour, quato, semester);
        this.requiredCredits = requiredCredits;
        this.prequisites = prequisites;
    }

    public boolean isEligibleToRequest(Student student){
        return semesterControl(student) && student.getTranscript().hasBeenPassedCourses(this.getPrequisites())
                && checkRequiredCredit(student);
    }

    public boolean checkRequiredCredit(Student student) {
        return student.getTranscript().getCreditCompleted() >= requiredCredits;
    }

        //GETTER & SETTER

    public int getRequiredCredits() {
        return requiredCredits;
    }

    public void setRequiredCredits(int requiredCredits) {
        this.requiredCredits = requiredCredits;
    }

    public ArrayList<Course> getPrequisites() {
        return prequisites;
    }

    public void setPrequisites(ArrayList<Course> prequisites) {
        this.prequisites = prequisites;
    }
}



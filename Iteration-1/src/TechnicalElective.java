import java.util.ArrayList;

public class TechnicalElective extends ElectiveCourse {
    private int requiredCredits;
    private ArrayList<Course> prequisites;

    TechnicalElective(String courseName, String courseCode, int courseCredit, int courseDay, String courseHour,
            int quato, ArrayList<Integer> semester, int requiredCredits, ArrayList<Course> prequisites) {
        super(courseName, courseCode, courseCredit, courseDay, courseHour, quato, semester);
        this.requiredCredits = requiredCredits;
        this.prequisites = prequisites;
    }

    @Override
    public boolean isEligibleToRequest(Student student) {
        /* if (semesterControl(student)) {
            if (student.getTranscript().hasBeenPassedCourses(this.getPrequisites())) {
                return true;
            } else
                return false;
        } else {
            this.setFailedPreq(this.getFailedPreq() + 1);
            return false;
        } */
        return semesterControl(student) && student.getTranscript().hasBeenPassedCourses(this.getPrequisites())
                && checkRequiredCredit(student);
    }

    public boolean checkRequiredCredit(Student student) {
        if (student.getTranscript().getCreditCompleted() >= requiredCredits) {
            this.setFailedCredits(getFailedCredits() + 1);
            return true;
        } else
            return false;
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

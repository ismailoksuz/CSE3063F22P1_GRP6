import java.util.ArrayList;

public class GraduationProject extends MandatoryCourse {

    private int requiredCredits;

    public GraduationProject(String courseName, String courseCode, int courseCredit, int courseDay, String courseHour,
            int quato, int semester, ArrayList<Course> prequisites, int requiredCredits) {
        super(courseName, courseCode, courseCredit, courseDay, courseHour, quato, semester, prequisites);
        this.requiredCredits = requiredCredits;
    }

    @Override
    public boolean isEligibleToRequest(Student student) {

        return super.isEligibleToRequest(student) && checkRequiredCredit(student);

    }

    public boolean checkRequiredCredit(Student student) {
        if (student.getTranscript().getCreditCompleted() >= requiredCredits) {
            return true;
        } else {
            this.setFailedCredits(getFailedCredits() + 1);
            this.getStudentsFailedCredits().add(student);
            student.getStudentOutput().add("The advisor didn't approve graduation project " + this.getCourseCode() +
                    " because student completed credits < " + this.getRequiredCredits());
            return false;
        }

    }

    // GETTER & SETTER
    public int getRequiredCredits() {
        return requiredCredits;
    }

    public void setRequiredCredits(int requiredCredits) {
        this.requiredCredits = requiredCredits;
    }
}

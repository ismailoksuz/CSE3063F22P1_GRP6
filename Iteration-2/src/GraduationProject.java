import java.util.ArrayList;
import org.apache.log4j.Logger;

public class GraduationProject extends MandatoryCourse implements ICreditRequirement {
    private Logger log = Logger.getLogger(GraduationProject.class);
    private int requiredCredits;

    public GraduationProject(String courseName, String courseCode, int courseCredit, int courseDay, String courseHour,
            int quato, int semester, ArrayList<Course> prequisites, int requiredCredits) {
        super(courseName, courseCode, courseCredit, courseDay, courseHour, quato, semester, prequisites);
        this.requiredCredits = requiredCredits;
        log.info(this.getCourseName() + " (" + this.getCourseCode() + ")"
                + " named graduation project created.");
    }

    @Override
    public boolean isEligibleToRequest(Student student) {
        /* log.info(student.getStudentName() + " can "
                + ((super.isEligibleToRequest(student) && checkRequiredCredit(student))
                        ? ""
                        : "not")
                + "enroll this course: "
                + this.getCourseName()); */
        return super.isEligibleToRequest(student) && checkRequiredCredit(student);
    }

    @Override
    public boolean checkRequiredCredit(Student student) {
        if (student.getTranscript().getCreditCompleted() >= requiredCredits) {
            /* log.info(
                    "Student has enough credits. " + student.getStudentName() + " can enroll " + this.getCourseName()
                            + "."); */
            return true;
        } else {
            this.setFailedCredits(getFailedCredits() + 1);
            student.getStudentOutput().add("The advisor didn't approve graduation project " + this.getCourseCode() +
                    " because student completed credits < " + this.getRequiredCredits());
            log.info(
                    "Student has not enough credits. " + student.getStudentName() + " cannot enroll "
                            + this.getCourseName()
                            + ".");
            return false;
        }
    }

    // GETTER & SETTER
    public int getRequiredCredits() {
        return requiredCredits;
    }
}

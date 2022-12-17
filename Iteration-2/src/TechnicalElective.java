import java.util.ArrayList;
import org.apache.log4j.Logger;

public class TechnicalElective extends ElectiveCourse implements ICreditRequirement {
    private Logger log = Logger.getLogger(TechnicalElective.class);
    private int requiredCredits;
    private ArrayList<Course> prequisites;

    TechnicalElective(String courseName, String courseCode, int courseCredit, int courseDay, String courseHour,
            int quato, ArrayList<Integer> semester, int requiredCredits, ArrayList<Course> prequisites) {
        super(courseName, courseCode, courseCredit, courseDay, courseHour, quato, semester);
        this.requiredCredits = requiredCredits;
        this.prequisites = prequisites;
        log.info(this.getCourseName() + " (" + this.getCourseCode() + ")"
                + " named technical elective course created.");
    }

    @Override
    public boolean isEligibleToRequest(Student student) {
        if (semesterControl(student)) {
            if (!student.getTranscript().hasBeenPassedCourses(this.getPrequisites())) {
                this.setFailedPreq(this.getFailedPreq() + 1);
                student.getStudentOutput().add("The system didn't allow " + this.getCourseCode() +
                        " because student failed prereq. " + this.getPrequisites().get(0).getCourseCode());
                log.info(
                        "Student couldn't pass prerequisite course(s). " + student.getStudentName() + " cannot enroll "
                                + this.getCourseName()
                                + ".");
                return false;
            } else {
                /* log.info(
                        "Student passed prerequisite course(s) successfully. " + student.getStudentName()
                                + " can enroll "
                                + this.getCourseName()
                                + "."); */
                return true;
            }
        } else {
            log.info(
                    "Student is not eligible to take this course this semester. " + student.getStudentName()
                            + " cannot enroll "
                            + this.getCourseName()
                            + ".");
            return false;
        }
    }

    @Override
    public boolean checkRequiredCredit(Student student) {
        if (student.getTranscript().getCreditCompleted() >= requiredCredits) {
            /* log.info(
                    "Student has enough credits. " + student.getStudentName() + " can take " + this.getCourseName()
                            + "."); */
            return true;
        } else {
            this.setFailedCredits(getFailedCredits() + 1);
            student.getStudentOutput().add("The advisor didn't approve TE" + this.getCourseCode() +
                    " because student completed credits < " + this.getRequiredCredits());
            log.info(
                    "Student has not enough credits. " + student.getStudentName() + " cannot take "
                            + this.getCourseName()
                            + ".");
            return false;
        }
    }

    //GETTER & SETTER
    public int getRequiredCredits() {
        return requiredCredits;
    }

    public ArrayList<Course> getPrequisites() {
        return prequisites;
    }
}

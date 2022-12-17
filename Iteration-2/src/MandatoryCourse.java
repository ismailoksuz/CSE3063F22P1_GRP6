import java.util.ArrayList;
import org.apache.log4j.Logger;

public class MandatoryCourse extends Course {
    private Logger log = Logger.getLogger(MandatoryCourse.class);
    private int semester;
    private ArrayList<Course> prerequisites;

    public MandatoryCourse(String courseName, String courseCode, int courseCredit, int courseDay, String courseHour,
            int quota, int semester, ArrayList<Course> prerequisites) {
        super(courseName, courseCode, courseCredit, courseDay, courseHour, quota);
        this.semester = semester;
        this.prerequisites = prerequisites;
        log.info(this.getCourseName() + " (" + this.getCourseCode() + ")"
                + " named mandatory course created.");
    }

    @Override
    public boolean isEligibleToRequest(Student student) {
        if (student.getSemester() == this.getSemester()) {
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

    public boolean isEligibleToBePreviouslyTaken(Student student) {
        /* log.info(student.getStudentName() + " could "
                + ((student.getSemester() > this.getSemester()
                        && student.getTranscript().hasBeenPassedCourses(this.getPrequisites()))
                                ? ""
                                : "not")
                + "take this course for his previous semesters: "
                + this.getCourseName()); */
        return student.getSemester() > this.getSemester()
                && student.getTranscript().hasBeenPassedCourses(this.getPrequisites());
    }

    // GETTER & SETTER
    public int getSemester() {
        return semester;
    }

    public ArrayList<Course> getPrequisites() {
        return prerequisites;
    }
}

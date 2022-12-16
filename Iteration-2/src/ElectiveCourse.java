import java.util.ArrayList;
import org.apache.log4j.Logger;

public abstract class ElectiveCourse extends Course {
    private Logger log = Logger.getLogger(ElectiveCourse.class);
    private ArrayList<Integer> semesters;

    public ElectiveCourse(String courseName, String courseCode, int courseCredit, int courseDay, String courseHour,
            int quato, ArrayList<Integer> semesters) {
        super(courseName, courseCode, courseCredit, courseDay, courseHour, quato);
        this.semesters = semesters;
        log.info(this.getCourseName() + " (" + this.getCourseCode() + ")" + " named elective course created.");
    }

    public boolean semesterControl(Student student) {
        boolean isTrue = false;
        for (int semester : this.getSemesters()) {
            if (student.getSemester() == semester) {
                isTrue = true;
                /* log.info(this.getCourseName() + ": Student " + student.getStudentName()
                        + " can take the course this semester."); */
                break;
            }
        }
        /* log.info(this.getCourseName() + ": Student " + student.getStudentName()
                + " can not take the course this semester."); */
        return isTrue;
    }

    public boolean semesterCheck(int semester) {
        boolean isTrue = false;
        for (Integer s : this.getSemesters()) {
            if (s.intValue() == semester) {
                isTrue = true;
                /* log.info(this.getCourseName() + ": The course can be taken in the given semester" + "(" + semester + ")"
                        + "."); */
                break;
            }
        }
        /* log.info(this.getCourseName() + ": The course can not be taken in the given semester" + "(" + semester + ")"
                + "."); */
        return isTrue;
    }

    // GETTER & SETTER
    public ArrayList<Integer> getSemesters() {
        return semesters;
    }
}

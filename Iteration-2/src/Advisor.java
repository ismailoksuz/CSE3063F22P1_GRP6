import java.util.ArrayList;
import org.apache.log4j.Logger;

public class Advisor extends Instructor {
    static Logger log = Logger.getLogger(Advisor.class);
    private ArrayList<Student> students;

    public Advisor(String firstName, String lastName) {
        super(firstName, lastName);
        this.students = new ArrayList<Student>();
        log.info(this.getFirstName() + " " + this.getLastName() + " named advisor created.");
    }

    public Advisor(String firstName, String lastName, ArrayList<Course> givenCourses) {
        super(firstName, lastName, givenCourses);
        this.students = new ArrayList<Student>();
        log.info(this.getFirstName() + " " + this.getLastName() + " named advisor created.");
    }

    public void completeRegistration(Student student) {
        for (int i = 0; i < student.getRequestedCourses().size(); i++)
            if (checkQuotaForRegistration(student.getRequestedCourses().get(i), student)
                    && checkCollision(student, student.getRequestedCourses().get(i))) {
                student.getTranscript().getEnrolledCourses().add(student.getRequestedCourses().get(i));
                student.getRequestedCourses().get(i).getStudents().add(student);
                /* log.info(student.getStudentName() + " enrolled this course: "
                        + student.getRequestedCourses().get(i).getCourseName()); */
            }
        log.info(student.getStudentName() + "'s registration successfully completed.");
    }

    public boolean checkCollision(Student student, Course course) {
        boolean isTrue = true;
        for (Course c : student.getTranscript().getEnrolledCourses()) {
            if (course.getCourseSchedule().isCollision(c.getCourseSchedule())) {
                course.setCollisionProblem(course.getCollisionProblem() + 1);
                student.getStudentOutput().add("Advisor didn't approve " + course.getCourseCode() +
                        " because of two hours collision with " + c.getCourseCode() + " in schedule");
                log.info(student.getStudentName() + " couldn't take " + course.getCourseName()
                        + " because of collision with " + c.getCourseName() + ".");
                isTrue = false;
                break;
            }
        }
        /* log.info("No Collision found. " + student.getStudentName() + " can take " + course.getCourseName() + "."); */
        return isTrue;
    }

    public boolean checkQuotaForRegistration(Course course, Student student) {
        if (course.getStudents().size() < course.getQuato()) {
            /* log.info(
                    "Course quota not full. " + student.getStudentName() + " can take " + course.getCourseName() + "."); */
            return true;
        } else {
            course.setQuotaProblem(course.getQuotaProblem() + 1);
            student.getStudentOutput().add("The student couldn't register for " + course.getCourseCode() +
                    " because of a quota problem");
            log.info(student.getStudentName() + " couldn't take " + course.getCourseName()
                    + " because course quota is full ( Quota: " + course.getQuato() + ").");
            return false;
        }
    }

    // GETTER & SETTER
    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
        /* log.info(this.getAdvisorName() + ": advisor's student list changed."); */
    }

    public void addStudent(Student student) {
        this.students.add(student);
        /* log.info("Student " + student.getStudentName() + " added to advisor " + this.getAdvisorName() + "'s list."); */
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
        /* log.info("Student " + student.getStudentName() + " removed from advisor " + this.getAdvisorName() + "'s list."); */
    }

    public String getAdvisorName() {
        return getFirstName() + " " + getLastName();
    }
}

import java.util.ArrayList;
import org.apache.log4j.Logger;

public class Instructor extends Person {
    private Logger log = Logger.getLogger(Instructor.class);
    private ArrayList<Course> givenCourses;

    public Instructor(String firstName, String lastName) {
        super(firstName, lastName);
        this.givenCourses = new ArrayList<Course>();
        log.info(this.toString() + " named instructor created.");
    }

    public Instructor(String firstName, String lastName, ArrayList<Course> givenCourses) {
        super(firstName, lastName);
        this.givenCourses = givenCourses;
        log.info(this.toString() + " named instructor created.");
    }

    public void addGivenCourse(Course course) {
        this.givenCourses.add(course);
        log.info(this.toString() + ": instructor now gives " + course.getCourseName());
    }

    public void removeGivenCourse(Course course) {
        this.givenCourses.remove(course);
        /* log.info(this.toString() + ": instructor no longer gives " + course.getCourseName()); */
    }

    public ArrayList<Course> getGivenCourses() {
        return this.givenCourses;
    }
}

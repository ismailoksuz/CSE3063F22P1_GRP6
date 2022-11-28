import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {
    private List<Course> givenCourses;
    private Schedule schedule;

    public Instructor(String firstName, String lastName) {
        super(firstName, lastName);
        this.givenCourses = new ArrayList<Course>();
        System.out.println("Instructor created");
    }

    public Instructor(String firstName, String lastName, List<Course> givenCourses) {
        super(firstName, lastName);
        this.givenCourses = givenCourses;
        System.out.println("Instructor created");
    }

    public List<Course> getGivenCourses() {
        return this.givenCourses;
    }

    public void setGivenCourses(List<Course> givenCourses) {
        this.givenCourses = givenCourses;
        System.out.println("Instructor given courses set");
    }

    public void addGivenCourse(Course course) {
        this.givenCourses.add(course);
        System.out.println("Instructor given course added");
    }

    public void removeGivenCourse(Course course) {
        this.givenCourses.remove(course);
        System.out.println("Instructor given course removed");
    }

    public Schedule getSchedule() {
        return this.schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
        System.out.println("Instructor schedule set");
    }

    public void giveCourse(Course course) {
        this.addGivenCourse(course);
        //course.setInstructor(this);
        System.out.println("Instructor gave course");
    }
}

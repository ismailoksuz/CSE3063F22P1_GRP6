import java.util.ArrayList;

public class Transcript {
    private int creditCompleted = 0;
    private int creditTaken = 0;
    private ArrayList<Course> takenCourses = new ArrayList<>();
    private ArrayList<Course> completedCourses = new ArrayList<>();
    private ArrayList<Course> failedCourses = new ArrayList<>();

    public Transcript() {
    }

    public void addCompletedCourse(Course course) {
        completedCourses.add(course);
        this.creditCompleted += course.getCourseCredit();
    }

    public void addTakenCourse(Course course) {
        takenCourses.add(course);
        this.creditTaken += course.getCourseCredit();
    }

    public void addFailedCourse(Course course) {
        failedCourses.add(course);
    }

    public int getCreditCompleted() {
        return this.creditCompleted;
    }

    public void setCreditCompleted(int creditCompleted) {
        this.creditCompleted = creditCompleted;
    }

    public int getCreditTaken() {
        return this.creditTaken;
    }

    public void setCreditTaken(int creditTaken) {
        this.creditTaken = creditTaken;
    }
}

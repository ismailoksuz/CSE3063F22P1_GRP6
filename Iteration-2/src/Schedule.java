import org.apache.log4j.Logger;

public class Schedule {
    private Logger log = Logger.getLogger(Schedule.class);
    private int courseDay;
    private String courseHour;

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public Schedule(int courseDay, String courseHour) {
        this.courseDay = courseDay;
        this.courseHour = courseHour;
        log.info("Course schedule created.");
    }

    public boolean isCollision(Schedule scheduleOther) {
        return this.toString().equals(scheduleOther.toString());
    }

    // GETTER & SETTER
    public Day getCourseDay() {
        return Day.values()[this.courseDay];
    }

    public String getCourseHour() {
        return this.courseHour;
    }

    @Override
    public String toString() {
        return "Day: " + this.getCourseDay() + " Hour: " + this.getCourseHour();
    }
}

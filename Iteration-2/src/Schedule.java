import org.apache.log4j.Logger;

public class Schedule {
    static Logger log = Logger.getLogger(Schedule.class);
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

    public Day getCourseDay() {
        return Day.values()[this.courseDay];
    }

    public void setCourseDay(int courseDay) {
        this.courseDay = courseDay;
        /* log.info("Course day changed."); */
    }

    public String getCourseHour() {
        return this.courseHour;
    }

    public void setCourseHour(String courseHour) {
        this.courseHour = courseHour;
        /* log.info("Course hour changed."); */
    }

    public String toString() {
        return "Day: " + getCourseDay() + " Hour: " + getCourseHour();
    }
}

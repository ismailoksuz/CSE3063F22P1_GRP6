public class Schedule {
    private Day courseDay;
    private String courseHour;

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public Schedule(Day courseDay, String courseHour) {
        this.courseDay = courseDay;
        this.courseHour = courseHour;
    }

    public Day getCourseDay() {
        return this.courseDay;
    }

    public void setCourseDay(Day courseDay) {
        this.courseDay = courseDay;
    }

    public String getCourseHour() {
        return this.courseHour;
    }

    public void setCourseHour(String courseHour) {
        this.courseHour = courseHour;
    }
}

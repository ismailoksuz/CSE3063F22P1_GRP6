public class Schedule {
    private int courseDay;
    private String courseHour;

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public Schedule(int courseDay, String courseHour) {
        this.courseDay = courseDay;
        this.courseHour = courseHour;
    }

    /* public Day getCourseDay() {
        return this.courseDay;
    } */

    /* public void setCourseDay(Day courseDay) {
        this.courseDay = courseDay;
    } */

    public String getCourseHour() {
        return this.courseHour;
    }

    public void setCourseHour(String courseHour) {
        this.courseHour = courseHour;
    }
}

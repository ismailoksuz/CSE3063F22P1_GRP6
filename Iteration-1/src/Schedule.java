public class Schedule {
    private String courseDay;
    private int courseHour;

    public Schedule(String courseDay, int courseHour) {
        this.courseDay = courseDay;
        this.courseHour = courseHour;
    }

    public String getCourseDay() {
        return this.courseDay;
    }

    public void setCourseDay(String courseDay) {
        this.courseDay = courseDay;
    }

    public int getCourseHour() {
        return this.courseHour;
    }

    public void setCourseHour(int courseHour) {
        this.courseHour = courseHour;
    }
}

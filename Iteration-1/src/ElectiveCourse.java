public class ElectiveCourse extends Course{
    private String electiveCourseCode;
    private String electiveCourseName;

    public String getElectiveCourseCode() {
        return electiveCourseCode;
    }

    public void setElectiveCourseCode(String electiveCourseCode) {
        this.electiveCourseCode = electiveCourseCode;
    }

    public String getElectiveCourseName() {
        return electiveCourseName;
    }

    public void setElectiveCourseName(String electiveCourseName) {
        this.electiveCourseName = electiveCourseName;
    }

    public ElectiveCourse(){
    }
    public ElectiveCourse(String courseCode, String courseName, int courseCredit, String semester, int quota, int section,
                          ArrayList<Course> prequisties, Instructor courseInstructor, ArrayList<Student> students,
                          Schedule courseSchedule, String electiveCourseCode, String electiveCourseName){
        super(courseCode, courseName, courseCredit, semester, quota, section, prequisties, courseSchedule, courseInstructor,
                students, courseSchedule);
        this.electiveCourseCode = electiveCourseCode;
        this.electiveCourseName =electiveCourseName;
        
    }
}


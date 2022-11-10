import java.util.ArrayList;

public class TechnicalElectiveCourse extends Course{

    private String technicalElectiveCourseCode;
    private String technicalElectiveCourseName;
    private Transcript minimumCompletedCredit;

    public String getTechnicalElectiveCourseCode() {
        return technicalElectiveCourseCode;
    }
    public void setTechnicalElectiveCourseCode(String technicalElectiveCourseCode) {
        this.technicalElectiveCourseCode = technicalElectiveCourseCode;
    }
    public String getTechnicalElectiveCourseName() {
        return technicalElectiveCourseName;
    }
    public void setTechnicalElectiveCourseName(String technicalElectiveCourseName) {
        this.technicalElectiveCourseName = technicalElectiveCourseName;
    }
    public Transcript getMinimumCompletedCredit() {
        return minimumCompletedCredit;
    }
    public void setMinimumCompletedCredit(Transcript minimumCompletedCredit) {
        this.minimumCompletedCredit = minimumCompletedCredit;
    }


    public TechnicalElectiveCourse() {
    }

    public TechnicalElectiveCourse(String courseCode, String courseName, Semester givenSemester, Schedule courseSchedule,
                          ArrayList<Student> students, int courseCredit, ArrayList<Course> prerequsities, int section, int quota,
                          String type, String technicalElectiveCourseCode, String technicalElectiveCourseName) {
        super(courseCode, courseName, givenSemester, courseSchedule, students, courseCredit, prerequsities, section,
                quota, type);
        this.technicalElectiveCourseCode = technicalElectiveCourseCode;
        this.technicalElectiveCourseName = technicalElectiveCourseName;
    }

}
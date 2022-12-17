import java.util.ArrayList;
import org.apache.log4j.Logger;

public class FacultyTechnicalElective extends ElectiveCourse {
        private Logger log = Logger.getLogger(FacultyTechnicalElective.class);
        private ArrayList<Course> prequisites;

        public FacultyTechnicalElective(String courseName, String courseCode, int courseCredit, int courseDay,
                        String courseHour, int quato, ArrayList<Integer> semesters,
                        ArrayList<Course> prequisites) {
                super(courseName, courseCode, courseCredit, courseDay, courseHour, quato, semesters);
                this.prequisites = prequisites;
                log.info(this.getCourseName() + " (" + this.getCourseCode() + ")"
                                + " named faculty technical elective course created.");
        }

        @Override
        public boolean isEligibleToRequest(Student student) {
                /* log.info(student.getStudentName() + " can "
                                + ((semesterControl(student)
                                                && student.getTranscript().hasBeenPassedCourses(this.getPrequisites()))
                                                                ? ""
                                                                : "not")
                                + "enroll this course: "
                                + this.getCourseName()); */
                return semesterControl(student) && student.getTranscript().hasBeenPassedCourses(this.getPrequisites());
        }

        //GETTER & SETTER
        public ArrayList<Course> getPrequisites() {
                return prequisites;
        }
}

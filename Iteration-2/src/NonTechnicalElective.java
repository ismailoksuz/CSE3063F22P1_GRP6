import java.util.ArrayList;
import org.apache.log4j.Logger;

public class NonTechnicalElective extends ElectiveCourse {
        private Logger log = Logger.getLogger(NonTechnicalElective.class);

        public NonTechnicalElective(String courseName, String courseCode, int courseCredit, int courseDay,
                        String courseHour, int quato, ArrayList<Integer> semesters) {
                super(courseName, courseCode, courseCredit, courseDay, courseHour, quato, semesters);
                log.info(this.getCourseName() + " (" + this.getCourseCode() + ")"
                                + " named nontechnical elective course created.");
        }

        @Override
        public boolean isEligibleToRequest(Student student) {
                /* log.info(student.getStudentName() + " can "
                                + ((semesterControl(student))
                                                ? ""
                                                : "not")
                                + "enroll this course: "
                                + this.getCourseName()); */
                return semesterControl(student);
        }
}

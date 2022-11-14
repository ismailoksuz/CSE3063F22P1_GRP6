import java.util.ArrayList;

public class FacultyTechnicalElective extends ElectiveCourse {
        private ArrayList<Course> prequisites;

        public FacultyTechnicalElective(String courseName, String courseCode, int courseCredit, int courseDay,
                        String courseHour, int quato, ArrayList<Integer> semesters,
                        ArrayList<Course> prequisites) {
                super(courseName, courseCode, courseCredit, courseDay, courseHour, quato, semesters);
                this.prequisites = prequisites;
        }

        @Override
        public boolean isEligibleToRequest(Student student) {
                return semesterControl(student) && student.getTranscript().hasBeenPassedCourses(this.getPrequisites());
        }

        //GETTER & SETTER

        public ArrayList<Course> getPrequisites() {
                return prequisites;
        }

        public void setPrequisites(ArrayList<Course> prequisites) {
                this.prequisites = prequisites;
        }
}

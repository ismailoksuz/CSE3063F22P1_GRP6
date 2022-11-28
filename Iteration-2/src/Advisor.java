import java.util.ArrayList;

public class Advisor extends Instructor {
    private ArrayList<Student> students;

    public Advisor(String firstName, String lastName) {
        super(firstName, lastName);
        this.students = new ArrayList<Student>();
        /* System.out.println("Advisor created"); */
    }

    public Advisor(String firstName, String lastName, ArrayList<Course> givenCourses) {
        super(firstName, lastName, givenCourses);
        this.students = new ArrayList<Student>();
        /* System.out.println("Advisor created"); */
    }

    public void completeRegistration(Student student) {
        for (int i = 0; i < student.getRequestedCourses().size(); i++)
            if (checkCollision(student, student.getRequestedCourses().get(i))
                    && checkQuotaForRegistration(student.getRequestedCourses().get(i), student)) {
                student.getTranscript().getEnrolledCourses().add(student.getRequestedCourses().get(i));
                student.getRequestedCourses().get(i).getStudents().add(student);
            }
    }

    public boolean checkCollision(Student student, Course course) {
        boolean isTrue = true;
        for (Course c : student.getTranscript().getEnrolledCourses()) {
            if (course.getCourseSchedule().toString().equals(c.getCourseSchedule().toString())) {
                course.setCollisionProblem(course.getCollisionProblem() + 1);//******************************** */
                course.getStudentsCollisionProblem().add(student);
                student.getStudentOutput().add("Advisor didn't approve " + course.getCourseCode() +
                        " because of two hours collision with " + c.getCourseCode() + " in schedule");
                isTrue = false;
                break;
            }
        }
        return isTrue;
    }

    public boolean checkQuotaForRegistration(Course course, Student student) {
        /* System.out.println("Checking course quota for registration..."); */
        if (course.getStudents().size() < course.getQuato()) {
            return true;
        } else {
            /*  System.out.println("Quota is full for " + course.getCourseCode() + "(" + course.getCourseName() + ")"); */
            course.setQuotaProblem(course.getQuotaProblem() + 1);//******************************************** */
            course.getStudentsQuotaProblem().add(student);
            student.getStudentOutput().add("The student couldn't register for " + course.getCourseCode() +
                    " because of a quota problem");
            return false;
        }
        /* System.out.println(this.students.size() + "**********" + this.quota); */
        /* return true; */
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
        /* System.out.println("Advisor students set"); */
    }

    public void addStudent(Student student) {
        this.students.add(student);
        /* System.out.println("Advisor student added"); */
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
        /* System.out.println("Advisor student removed"); */
    }

    public void adviseStudent(Student student) {
        this.addStudent(student);
        //student.setAdvisor(this);
        /* System.out.println("Advisor advised student"); */
    }

}

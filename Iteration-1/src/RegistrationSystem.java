public class RegistrationSystem {

    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private int quota = 10;

    private Instructor instructor = new Instructor("Ahmet", "Mehmet");
    private Schedule schedule = new Schedule();

    public RegistrationSystem() {

    public RegistrationSystem(Student student, Course course) {
    }

    public void getCourses() {
        MandatoryCourse mbg1201 = new MandatoryCourse("MBG1201", "Introduction to Modern Biology", 2, 1, quota, null,
                instructor, schedule);

    }

    public void failRandomCourses() {
    }

    public void storeStudents() {
    }
}

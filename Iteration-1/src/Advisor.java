import java.util.ArrayList;

public class Advisor extends Person {

    private ArrayList<Student> students = new ArrayList<Student>();

    public Advisor(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public boolean addStudent(Student student) {
        return students.add(student);
    }

    public void approveAllRequests(ArrayList<Student> students) {
        for (Student i : students) {
            this.approveRequest(i);
        }
    }

    public void approveRequest(Student student) {
        for (Course i : student.sendRequest()) {
            this.checkCourses(student, i);
        }
        System.out.println("Request approved");
    }

    public void checkCourses(Student student, Course course) {
        if (course.getStudentNumber() == course.getQuota()) {
            course.setQuotaProblem(course.getQuotaProblem() + 1);
        } else if (course.getMinCredit() > student.getTranscript().getCreditCompleted()) {
            course.setFailedCredits(course.getFailedCredits() + 1);
        }
        // else if failed prereq
        //else if collision
        else {
            course.registerStudent(student);
            student.getTranscript().addTakenCourse(course);
        }
    }
}
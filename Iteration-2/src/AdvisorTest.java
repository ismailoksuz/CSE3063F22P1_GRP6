import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AdvisorTest {
    private Advisor advisor = new Advisor("Advisor", "Test");
    private Student student = new Student("Student", "Test", 2020, 1);
    @Test
    public void testAddStudent() {
        advisor.addStudent(student);
        assertEquals(advisor.getStudents().get(0), student);
    }

    @Test
    public void testCheckCollision() {
        MandatoryCourse course1 = new MandatoryCourse("Math", "MATH101", 3, 1, "08:00-09:00", 30, 1, null);
        MandatoryCourse course2 = new MandatoryCourse("Physics", "PHYS102", 3, 1, "08:00-09:00", 30, 1, null);
        student.getTranscript().getCompletedCourses().add(course1);
        assertEquals(true, advisor.checkCollision(student, course2));
    }

    @Test
    public void testCheckQuotaForRegistration() {
        MandatoryCourse course1 = new MandatoryCourse("Math", "MATH101", 3, 1, "08:00-09:00", 30, 1, null);
        student.getTranscript().getCompletedCourses().add(course1);
        assertEquals(true, advisor.checkQuotaForRegistration(course1, student));
    }
}

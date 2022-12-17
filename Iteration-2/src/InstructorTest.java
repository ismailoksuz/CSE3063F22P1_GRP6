import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InstructorTest {
    private Instructor instructor = new Instructor("Instructor", "Test");
    private MandatoryCourse course = new MandatoryCourse("Math", "MATH101", 3, 1, "08:00-09:00", 30, 1, null);

    @Test
    public void testAddGivenCourse() {
        instructor.addGivenCourse(course);
        assertEquals(instructor.getGivenCourses().get(0), course);
    }

    @Test
    public void testRemoveGivenCourse() {
        instructor.addGivenCourse(course);
        instructor.removeGivenCourse(course);
        assertEquals(instructor.getGivenCourses().size(), 0);
    }
}

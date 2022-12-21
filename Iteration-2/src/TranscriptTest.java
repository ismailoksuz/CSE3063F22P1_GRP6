import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class TranscriptTest {
    private Transcript transcript = new Transcript();

    @Test
    public void testCalculateComplateCredit() {
        ArrayList<Course> mandatoryCourses = new ArrayList<Course>();
        mandatoryCourses.add(new MandatoryCourse("Math", "MATH101", 3, 1, "08:00-09:00", 30, 1, null));
        mandatoryCourses.add(new MandatoryCourse("English", "ENG101", 3, 1, "08:00-09:00", 30, 1, null));
        transcript.getCompletedCourses().addAll(mandatoryCourses);
        assertEquals(6, transcript.calculateComplateCredit());
    }

    @Test
    public void testCalculateGpa() {
        HashMap<Course, Grade> mandatoryCourses = new HashMap<Course, Grade>();
        mandatoryCourses.put(new MandatoryCourse("Math", "MATH101", 3, 1, "08:00-09:00", 30, 1, null),
                new Grade(new MandatoryCourse("Math", "MATH101", 3, 1, "08:00-09:00", 30, 1, null), 100));
        transcript.getTakenCouerses().putAll(mandatoryCourses);
        assertEquals(4.0, transcript.calculateGpa(), 0.0);
    }

    @Test
    public void testHasBeenPassedCourse() {
        ArrayList<Course> mandatoryCourses = new ArrayList<Course>();
        MandatoryCourse mandatoryCourse1 = new MandatoryCourse("Math", "MATH101", 3, 1, "08:00-09:00", 30, 1, null);
        mandatoryCourses.add(mandatoryCourse1);
        transcript.getCompletedCourses().addAll(mandatoryCourses);
        assertEquals(true, transcript.hasBeenPassedCourse(mandatoryCourse1));
    }

    @Test
    public void testHasBeenPassedCourses() {
        MandatoryCourse mandatoryCourse1 = new MandatoryCourse("Math", "MATH101", 3, 1, "08:00-09:00", 30, 1, null);
        ArrayList<Course> mandatoryCourses = new ArrayList<Course>();
        mandatoryCourses.add(mandatoryCourse1);
        ArrayList<Course> mandatoryCourses2 = new ArrayList<Course>();
        mandatoryCourses2.add(mandatoryCourse1);
        transcript.getCompletedCourses().addAll(mandatoryCourses);
        assertEquals(true, transcript.hasBeenPassedCourses(mandatoryCourses2));
    }

    @Test
    public void testIsCourseComplatedOrFailed() {
        MandatoryCourse mandatoryCourse1 = new MandatoryCourse("Math", "MATH101", 3, 1, "08:00-09:00", 30, 1, null);
        transcript.isCourseComplatedOrFailed(mandatoryCourse1, "AA");
        assertEquals(true, (transcript.getCompletedCourses().size() == 1));
    }
}

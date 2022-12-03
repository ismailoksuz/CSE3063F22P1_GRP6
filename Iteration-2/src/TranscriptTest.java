import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class TranscriptTest {
    @Test
    public void testCalculateComplateCredit() {
        Transcript transcript = new Transcript();
        ArrayList<Course> mandatoryCourses = new ArrayList<Course>();
        mandatoryCourses.add(new MandatoryCourse("Math", "MATH101", 3, 1, "08:00-09:00", 30, 1, null));
        mandatoryCourses.add(new MandatoryCourse("English", "ENG101", 3, 1, "08:00-09:00", 30, 1, null));
        transcript.setCompletedCourses(mandatoryCourses);
        assertEquals(6, transcript.calculateComplateCredit());
    }

    @Test
    public void testCalculateGpa() {
        Transcript transcript = new Transcript();
        HashMap<Course,String> mandatoryCourses = new HashMap<Course,String>();
        mandatoryCourses.put(new MandatoryCourse("Math", "MATH101", 3, 1, "08:00-09:00", 30, 1, null), "AA");
        mandatoryCourses.put(new MandatoryCourse("English", "ENG101", 3, 1, "08:00-09:00", 30, 1, null), "AA");
        transcript.setTakenCouerses(mandatoryCourses);
        transcript.setCreditCompleted(6);
        assertEquals(4.0, transcript.calculateGpa(), 0.0);
    }

    @Test
    public void testCalculateTakenCredit() {
        Transcript transcript = new Transcript();
        HashMap<Course,String> mandatoryCourses = new HashMap<Course,String>();
        mandatoryCourses.put(new MandatoryCourse("Math", "MATH101", 3, 1, "08:00-09:00", 30, 1, null), "AA");
        mandatoryCourses.put(new MandatoryCourse("English", "ENG101", 3, 1, "08:00-09:00", 30, 1, null), "AA");
        transcript.setTakenCouerses(mandatoryCourses);
        assertEquals(6, transcript.calculateTakenCredit());
    }

    @Test
    public void testHasBeenPassedCourse() {
        Transcript transcript = new Transcript();
        ArrayList<Course> mandatoryCourses = new ArrayList<Course>();
        MandatoryCourse mandatoryCourse1 = new MandatoryCourse("Math", "MATH101", 3, 1, "08:00-09:00", 30, 1, null);
        mandatoryCourses.add(mandatoryCourse1);
        transcript.setCompletedCourses(mandatoryCourses);
        assertEquals(true, transcript.hasBeenPassedCourse(mandatoryCourse1));
    }

    @Test
    public void testHasBeenPassedCourses() {
        Transcript transcript = new Transcript();
        MandatoryCourse mandatoryCourse1 = new MandatoryCourse("Math", "MATH101", 3, 1, "08:00-09:00", 30, 1, null);
        ArrayList<Course> mandatoryCourses = new ArrayList<Course>();
        mandatoryCourses.add(mandatoryCourse1);
        transcript.setCompletedCourses(mandatoryCourses);
        ArrayList<Course> mandatoryCourses2 = new ArrayList<Course>();
        mandatoryCourses2.add(mandatoryCourse1);
        assertEquals(true, transcript.hasBeenPassedCourses(mandatoryCourses2));
    }

    @Test
    public void testIsCourseComplatedOrFailed() {
        Transcript transcript = new Transcript();
        MandatoryCourse mandatoryCourse1 = new MandatoryCourse("Math", "MATH101", 3, 1, "08:00-09:00", 30, 1, null);
        transcript.isCourseComplatedOrFailed(mandatoryCourse1,"AA");
        assertEquals(true, (transcript.getCompletedCourses().size() == 1));
    }
}

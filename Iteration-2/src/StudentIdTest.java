import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StudentIdTest {
    @Test
    public void testCreateStudentId() {
        StudentId studentId = new StudentId(2015, 1);
        assertEquals("150115001", studentId.toString());
    }

    @Test
    public void testToString() {
        StudentId studentId = new StudentId(2015, 1);
        assertEquals("150115001", studentId.toString());
    }
}

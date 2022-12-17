import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StudentIdTest {

    private StudentId studentId = new StudentId(2015, 1);

    @Test
    public void testCreateStudentId() {
        assertEquals("150115001", studentId.toString());
    }

    @Test
    public void testToString() {
        assertEquals("150115001", studentId.toString());
    }
}


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ScheduleTest {

    private Schedule schedule = new Schedule(1, "08:00-09:00");
    @Test
    public void testIsCollision() {
        assertEquals(schedule.isCollision(new Schedule(1, "08:00-09:00")), true);
    }
}

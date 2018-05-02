package Lab5;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TimeParserTest {

    @Test
    public void testzero() throws Exception {
        assertEquals(TimeParser.parseTimeToSeconds("00:00:00"), 0);
        assertEquals(TimeParser.parseTimeToSeconds("00:00:00 am"), 0);
    }

    @Test
    public void testnoon() throws Exception {
        assertEquals(TimeParser.parseTimeToSeconds("12:00:00"), 43200);
        assertEquals(TimeParser.parseTimeToSeconds("12:00:00 am"), 43200);
        assertEquals(TimeParser.parseTimeToSeconds("11:59:59"), 43199);
        assertEquals(TimeParser.parseTimeToSeconds("11:59:59 am"), 43199);
    }

    @Test
    public void testmidnight() throws Exception {
        assertEquals(TimeParser.parseTimeToSeconds("11:59:59 pm"), 86399);
        assertEquals(TimeParser.parseTimeToSeconds("23:59:59"), 86399);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testBranch() {
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("Unrecognized time format");
        assertEquals(5025, TimeParser.parseTimeToSeconds("12345 am"));
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Unacceptable time specified");
        assertEquals(5025, TimeParser.parseTimeToSeconds("1:23:99 am"));
    }

    @Test
    public void testPath() {
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("Unrecognized time format");
        assertEquals(5025, TimeParser.parseTimeToSeconds("12345 pm"));
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("Unrecognized time format");
        assertEquals(5025, TimeParser.parseTimeToSeconds("1:2345 pm"));
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Unacceptable time specified");
        assertEquals(5025, TimeParser.parseTimeToSeconds("1:23:99 pm"));
    }

} 

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

// unit tests for event class
public class EventTest {
    private Event event;
    private Date date;

    //NOTE: line 1 and line 2 must run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        event = new Event("added fido(dog) for adoption"); //1
        date = Calendar.getInstance().getTime(); //2
    }

    @Test
    public void testEvent() {
        assertEquals("added fido(dog) for adoption", event.getDescription());
        assertEquals(date, event.getDate());
    }
}

package edu.duke.ece651.team2.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UniversityTest {
    University u = new University("Duke", true);

    University u2 = new University("SomeU", false);

    @Test
    public void testAll(){
        u.setId(179);
        assertEquals(179, u.getId());
        assertTrue(u.canChangeName());
        assertEquals("Duke", u.getName());
        assertFalse(u2.canChangeName());
    }
}

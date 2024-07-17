package edu.duke.ece651.team2.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EnrollmentTest {
    
    Enrollment e = new Enrollment(145, 289, true);
    Enrollment e2 = new Enrollment(145, 289, false);

    @Test
    public void testAll(){
        assertEquals(145, e.getSectionId());
        assertEquals(289, e.getStudentId());
        assertTrue(e.isNotify());
        assertFalse(e2.isNotify());
        
    }
}

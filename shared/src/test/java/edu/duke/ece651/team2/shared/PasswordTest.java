package edu.duke.ece651.team2.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PasswordTest {
    
    @Test
    public void testAll(){
        Password p1 = new Password();
        p1.setId(27);
        assertEquals(27, p1.getId());
        p1.setPassword("this is a Password");
        assertEquals("this is a Password", p1.getPassword());
        p1.setStudent(true);
        assertTrue(p1.isStudent());
        Password p2 = new Password(86, "a professor");
        assertEquals(86, p2.getId());
        assertEquals("a professor", p2.getPassword());
        p1.setStudent(false);
        assertFalse(p2.isStudent());
        Password p3 = new Password(123, "a faculty",false);
        assertEquals(123, p3.getId());
        assertEquals("a faculty", p3.getPassword());
        assertFalse(p3.isStudent());
    }

}

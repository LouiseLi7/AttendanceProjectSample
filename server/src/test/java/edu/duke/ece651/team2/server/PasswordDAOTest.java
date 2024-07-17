package edu.duke.ece651.team2.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.team2.shared.Password;

public class PasswordDAOTest {

    DAOFactory factory = new DAOFactory();
    PasswordDAO passwordDAO = new PasswordDAO(factory);


    @Test
    public void testCreate(){
        passwordDAO.deleteAll();
        Password p = new Password(1, "123456", true);
        passwordDAO.create(p);
        assertEquals("123456", passwordDAO.get(1).getPassword());
        passwordDAO.remove(p);
    }

    @Test
    public void testupdate(){
        passwordDAO.deleteAll();
        Password p = new Password(1, "23456", true);
        passwordDAO.create(p);
        passwordDAO.update(p);
        assertEquals("23456", passwordDAO.get(1).getPassword());
        passwordDAO.remove(p);
        assertEquals(0, passwordDAO.list().size());
    }


}

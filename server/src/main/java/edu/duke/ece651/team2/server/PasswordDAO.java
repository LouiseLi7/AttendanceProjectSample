package edu.duke.ece651.team2.server;

import edu.duke.ece651.team2.shared.Password;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PasswordDAO extends DAO<Password> {

    private final DAOFactory daoFactory;

    public PasswordDAO(DAOFactory daoFactory) {
        super();
        this.daoFactory = daoFactory;
    }

    @Override
    Password map(ResultSet resultSet) throws SQLException {
        Password password = new Password(
                resultSet.getInt("id"), // TODO fix when column is renamed
                resultSet.getString("password"),
                resultSet.getBoolean("isStudent")
        );
        return password;
    }

    public void create(Password password) {
        List<Object> values = Arrays.asList(
                password.getId(),
                password.getPassword(),
                password.isStudent()
        );

        try {
            executeUpdate(daoFactory,
                    "INSERT INTO Passwords (id, password, isStudent) VALUES (?, ?, ?)",
                    values); // TODO Fix
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Password password) {
        List<Object> values = Arrays.asList(
                password.getPassword(),
                password.getId()
        );

        try {
            executeUpdate(daoFactory,
                    "UPDATE Passwords SET password = ? WHERE id = ?",
                    values); // TODO Fix
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Password password) {
        List<Object> values = Collections.singletonList(
                password.getId()
        );

        try {
            executeUpdate(daoFactory,
                    "DELETE FROM Passwords WHERE id = ?",
                    values); // TODO Fix
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Password> list() {
        return super.list(daoFactory, "SELECT * FROM Passwords ORDER BY id", new ArrayList<>());
    }

    public Password get(Integer studentId) {
        List<Object> values = Collections.singletonList(studentId);
        return super.get(daoFactory, "SELECT * FROM Passwords WHERE id = ?", values);
    }

    public void deleteAll() {
        super.deleteAll(daoFactory, "Passwords");
    }
}

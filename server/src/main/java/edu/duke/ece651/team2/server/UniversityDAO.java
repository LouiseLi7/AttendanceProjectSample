package edu.duke.ece651.team2.server;

import edu.duke.ece651.team2.shared.University;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UniversityDAO extends DAO<University> {
    private final DAOFactory daoFactory;

    public UniversityDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    @Override
    University map(ResultSet resultSet) throws SQLException {
        University university = new University(resultSet.getString("name"),
                resultSet.getBoolean("changeName"));
        university.setId(resultSet.getInt("id"));
        return university;
    }

    public void create(University university) {
        if (university.getId() != null) {
            throw new IllegalArgumentException("University object already exists in database");
        }

        List<Object> values = Arrays.asList(
                university.getName(),
                university.canChangeName()
        );

        try {
            ResultSet generatedKeys = executeUpdate(daoFactory,
                    "INSERT INTO University (name, changeName) VALUES (?, ?)",
                    values);
            if (generatedKeys.next()) {
                university.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(University university) {
        if (university.getId() == null) {
            throw new IllegalArgumentException("University object does not exist in database");
        }


        List<Object> values = Arrays.asList(
                university.getName(),
                university.canChangeName(),
                university.getId()
        );
        try {
            executeUpdate(daoFactory, "UPDATE University SET name = ?, changeName = ? WHERE id = ?", values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(University university) {
        if (university.getId() == null) {
            throw new IllegalArgumentException("University object does not exist in database");
        }

        List<Object> values = Collections.singletonList(university.getId());

        try {
            executeUpdate(daoFactory, "DELETE FROM University WHERE id = ?", values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        university.setId(null);
    }

    public List<University> list() {
        return super.list(daoFactory, "SELECT * FROM University ORDER BY id", new ArrayList<>());
    }

    public University get(Integer id) {
        List<Object> values = Collections.singletonList(id);
        return super.get(daoFactory, "SELECT * FROM University WHERE id = ?", values);
    }

    public boolean exist(String name){
        List<Object> values = Collections.singletonList(name);
        University u = super.get(daoFactory, "SELECT * FROM University WHERE name = ?", values);
        if(u!=null){
            return true;
        }
        return false;
    }

    public void deleteAll() {
        super.deleteAll(daoFactory, "University");
    }
}

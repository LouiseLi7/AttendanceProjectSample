package edu.duke.ece651.team2.server;

import edu.duke.ece651.team2.shared.Professor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProfessorDAO extends DAO<Professor> {

    private final DAOFactory daoFactory;

    public ProfessorDAO(DAOFactory daoFactory) {
        super();
        this.daoFactory = daoFactory;
    }

    @Override
    public Professor map(ResultSet resultSet) throws SQLException {
        Professor professor = new Professor(
                 resultSet.getString("legalName"),
                resultSet.getString("email"),
                resultSet.getInt("universityId")
        );
        professor.setProfessorID(resultSet.getInt("id"));
        return professor;
    }

    public void create(Professor professor) {
        if (professor.getProfessorID() != null) {
            // Object already exists in database
            throw new IllegalArgumentException("Professor object already exists in database");
        }

        List<Object> values = Arrays.asList(
                professor.getName(),
                professor.getEmail(),
                professor.getUniversityId()
        );

        try {
            ResultSet generatedKeys = executeUpdate(daoFactory,
                    "INSERT INTO Users (legalName, email, universityId, isStudent) VALUES (?, ?, ?, FALSE)",
                    values); // TODO Fix
            if (generatedKeys.next()) {
                professor.setProfessorID(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Professor professor) {
        if (professor.getProfessorID() == null) {
            // Object does not exist in database
            throw new IllegalArgumentException("Professor object does not exist in database");
        }

        List<Object> values = Arrays.asList(
                professor.getName(),
                professor.getEmail(),
                professor.getUniversityId(),
                professor.getProfessorID()
        );

        try {
            executeUpdate(daoFactory,
                    "UPDATE Users SET legalName = ?, email = ?, universityId = ? WHERE id = ?",
                    values); // TODO Fix
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Professor professor) {
        if (professor.getProfessorID() == null) {
            // Object does not exist in database
            throw new IllegalArgumentException("Professor object does not exist in database");
        }

        List<Object> values = Collections.singletonList(
                professor.getProfessorID()
        );

        try {
            executeUpdate(daoFactory,
                    "DELETE FROM Users WHERE id = ?",
                    values); // TODO Fix
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        professor.setProfessorID(null);
    }

    public List<Professor> list() {
        return super.list(daoFactory, "SELECT * FROM Users WHERE isStudent=FALSE ORDER BY id", new ArrayList<>());
    }

    public Professor get(Integer id) {
        List<Object> values = Collections.singletonList(id);
        return super.get(daoFactory, "SELECT * FROM Users WHERE id = ?", values);
    }


    public List<Professor> listByUniversity(Integer universityId) {
        List<Object> values = Collections.singletonList(universityId);
        return super.list(daoFactory, "SELECT * FROM Users WHERE universityId = ? AND isStudent=FALSE ORDER BY id", values);
    }

    public Integer getUniversityID(Integer id){
        List<Object> values = Collections.singletonList(id);
        String sql = "SELECT universityId FROM Users WHERE id = ?";
        try(ResultSet resultSet = executeQuery(daoFactory, sql, values)){
            if (resultSet.next()){
                return resultSet.getInt("universityId");
            }
            else{
                return null;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Failed to fetch attendance for UniversityID for student: " + id, e);
        }
    }

    public void deleteAll(){
        super.deleteAll(daoFactory, "Users");
    }
}

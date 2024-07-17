package edu.duke.ece651.team2.shared;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The Professor class represents a professor with a name, professor ID, and a
 * list of courses they teach.
 */
public class Professor {
    /**
     * The name of the professor.
     */
    private String name;
    /**
     * The email address of the professor.
     */
    private String email;
    private Integer universityId;
    /**
     * The unique ID of the professor.
     */
    private Integer professorID;

    /**
     * Constructs a new Professor object with the specified name and professor ID.
     *
     * @param professorID The unique ID of the professor.
     * @param name        The name of the professor.
     * @param email       The email address of the professor.
     */
    public Professor(String name, String email, Integer universityId) {
        this.name = name;
        this.email = email;
        this.universityId = universityId;
    }

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(name, professor.name) && Objects.equals(professorID, professor.professorID) && Objects.equals(email, professor.email) && Objects.equals(universityId, professor.universityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, professorID, email, universityId);
    }

    /**
     * Returns the name of the professor.
     *
     * @return The name of the professor.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the email address of the professor.
     *
     * @return The email address of the professor.
     */
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the professor ID of the professor.
     *
     * @return The professor ID of the professor.
     */
    public Integer getProfessorID() {
        return professorID;
    }

    public void setProfessorID(Integer professorID) {
        this.professorID = professorID;
    }

}

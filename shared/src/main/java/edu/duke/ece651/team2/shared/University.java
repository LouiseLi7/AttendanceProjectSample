package edu.duke.ece651.team2.shared;

/**
 * The University class represents a university with a name
 * and a boolean value indicating whether the university supports changing the
 * display name of students.
 */
public class University {
    /**
     * The name of the university.
     */
    private final String name;
    /**
     * Whether the university supports changing the display name of students.
     */
    private final boolean changeName;
    // TODO? may add functions: This class will be asked whether it supports to
    // change the display name
    private Integer id;

    /**
     * Constructs a new University object with the specified name and whether it
     * supports changing the display name of students.
     *
     * @param name   The name of the university.
     * @param changeName Whether the university supports changing the display name of
     *               students.
     */
    public University(String name, boolean changeName) {
        this.name = name;
        this.changeName = changeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the name of the university.
     *
     * @return The name of the university.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns whether the university supports changing the display name of
     * students.
     *
     * @return Whether the university supports changing the display name of
     *         students.
     */
    public boolean canChangeName() {
        return this.changeName;
    }
}

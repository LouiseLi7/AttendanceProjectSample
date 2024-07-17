package edu.duke.ece651.team2.shared;

public class Password {
    Integer id;
    String password;

    boolean isStudent;

    public Password(){}

    public Password(Integer id, String password){
        this.id = id;
        this.password = password;
    }

    public Password(Integer id, String password, boolean isStudent) {
        this.id = id;
        this.password = password;
        this.isStudent = isStudent;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

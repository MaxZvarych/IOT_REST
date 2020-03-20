package ua.lviv.iot.spring.first.rest.model;

public class Student {
    private String firstName;
    private String lastName;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Student() {

    }

    public Student(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }
}
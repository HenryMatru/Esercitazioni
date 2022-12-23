package it.course.servletuseresempio2.models;

public class User {
    private int id;
    private String name;
    private String email;
    private String country;
    private int age;

    public User() {}

    public User(String name, String email, String country, int age) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.age = age;
    }

    public User(int id, String name, String email, String country, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
        this.age = age;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}

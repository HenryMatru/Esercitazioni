package it.course.esercitazionespringboot_coursesdatabase.models;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max=20)
    @Column(name="username")
    private String username;


    @NotBlank
    @Size(max=50)
    @Column(name="email")
    private String email;

    @NotBlank
    @Size(max=120)
    @Column(name="password")
    private String password;

    /*
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="courses_users",
            // joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="course_id"))
     */
    @ManyToMany(mappedBy = "users")
    private Set<Course> courses = new LinkedHashSet<>();

    public User() {}

    public User(long id, String username, String email, String password) {
        this.setId(id);
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

}

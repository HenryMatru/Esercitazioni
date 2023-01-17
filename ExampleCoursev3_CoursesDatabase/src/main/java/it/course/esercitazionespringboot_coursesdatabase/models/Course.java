package it.course.esercitazionespringboot_coursesdatabase.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="course")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Course.class)
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Getter
    @Setter
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(length=100000)
    private byte[] data;

    @ManyToMany(cascade={CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
    // @JsonIgnore
    @JoinTable(name="courses_users",
            joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy="course", orphanRemoval=true, cascade=CascadeType.MERGE)
    private Set<Exam> exams = new HashSet<>();

    public Course() {}

    public Course(long id, String name, String description) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Exam> getExams() {
        return this.exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return this.name.equals(course.name) && this.description.equals(course.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}

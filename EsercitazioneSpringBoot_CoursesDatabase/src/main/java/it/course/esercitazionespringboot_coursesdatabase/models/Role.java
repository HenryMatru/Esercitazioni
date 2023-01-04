package it.course.esercitazionespringboot_coursesdatabase.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    @ManyToMany(mappedBy="roles")
    private Set<User> users;

    public Role() {}

    public Role(Integer id, ERole name) {
        this.setName(name);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return this.name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}

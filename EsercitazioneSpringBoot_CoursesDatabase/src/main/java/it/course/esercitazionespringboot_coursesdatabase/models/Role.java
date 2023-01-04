package it.course.esercitazionespringboot_coursesdatabase.models;

import jakarta.persistence.*;

@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {}

    public Role(ERole name) {
        this.setName(name);
    }

    public ERole getName() {
        return this.name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

}

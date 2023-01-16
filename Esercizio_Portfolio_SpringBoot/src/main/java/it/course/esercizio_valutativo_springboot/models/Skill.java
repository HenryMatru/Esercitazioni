package it.course.esercizio_valutativo_springboot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "skill")
public class Skill extends BaseEntity {

    private String name;

    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name="category", referencedColumnName="id")
    private Category category;

}

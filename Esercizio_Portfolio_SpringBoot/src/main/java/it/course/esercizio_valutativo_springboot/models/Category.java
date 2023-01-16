package it.course.esercizio_valutativo_springboot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "category")
public class Category extends BaseEntity{

    private String name;

    @OneToMany(mappedBy="category", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
    private Set<Skill> skills;

}

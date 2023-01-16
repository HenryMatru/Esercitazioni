package it.course.esercizio_valutativo_springboot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "project")
public class Project extends BaseEntity {

    private String name;

    private String description;

    private String link;

}

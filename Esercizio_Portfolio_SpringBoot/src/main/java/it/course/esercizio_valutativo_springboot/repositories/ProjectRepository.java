package it.course.esercizio_valutativo_springboot.repositories;

import it.course.esercizio_valutativo_springboot.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

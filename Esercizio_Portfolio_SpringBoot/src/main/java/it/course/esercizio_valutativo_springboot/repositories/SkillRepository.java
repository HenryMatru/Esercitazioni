package it.course.esercizio_valutativo_springboot.repositories;

import it.course.esercizio_valutativo_springboot.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}

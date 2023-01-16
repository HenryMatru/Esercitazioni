package it.course.esercizio_valutativo_springboot.businesses.interfaces;

import it.course.esercizio_valutativo_springboot.models.Skill;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SkillBO {

    Skill getSkill(long id) throws DataAccessException;

    List<Skill> getSkills();

    Skill insertSkill(Skill skill) throws DataAccessException;

    void updateSkill(long id, Skill skill) throws DataAccessException;

    void deleteSkill(long id) throws DataAccessException;

}

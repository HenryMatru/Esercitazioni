package it.course.esercizio_valutativo_springboot.businesses.impl;

import it.course.esercizio_valutativo_springboot.businesses.interfaces.SkillBO;
import it.course.esercizio_valutativo_springboot.models.Skill;
import it.course.esercizio_valutativo_springboot.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillBOImpl implements SkillBO {

    @Autowired
    SkillRepository skillRepository;

    @Override
    public Skill getSkill(long id) throws DataAccessException {
        return this.skillRepository.findById(id).get();
    }

    @Override
    public List<Skill> getSkills() {
        return this.skillRepository.findAll();
    }

    @Override
    public Skill insertSkill(Skill skill) throws DataAccessException {
        return this.skillRepository.save(skill);
    }

    @Override
    public void updateSkill(long id, Skill skill) throws DataAccessException {
        if (this.skillRepository.findById(id).isPresent()) {
            skill.setId(id);
            this.skillRepository.save(skill);
        }
    }

    @Override
    public void deleteSkill(long id) throws DataAccessException {
        if (this.skillRepository.findById(id).isPresent()) {
            this.skillRepository.deleteById(id);
        }
    }

}

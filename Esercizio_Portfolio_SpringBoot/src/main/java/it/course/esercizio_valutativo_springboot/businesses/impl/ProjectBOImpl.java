package it.course.esercizio_valutativo_springboot.businesses.impl;

import it.course.esercizio_valutativo_springboot.businesses.interfaces.ProjectBO;
import it.course.esercizio_valutativo_springboot.models.Project;
import it.course.esercizio_valutativo_springboot.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectBOImpl implements ProjectBO {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Project getProject(long id) throws DataAccessException {
        return this.projectRepository.findById(id).get();

    }

    @Override
    public List<Project> getProjects() {
        return this.projectRepository.findAll();
    }

    @Override
    public Project insertProject(Project project) throws DataAccessException {
        return this.projectRepository.save(project);
    }

    @Override
    public void updateProject(long id, Project project) throws DataAccessException {
        if (this.projectRepository.findById(id).isPresent()) {
            project.setId(id);
            this.projectRepository.save(project);
        }
    }

    @Override
    public void deleteProject(long id) throws DataAccessException {
        if (this.projectRepository.findById(id).isPresent()) {
            this.projectRepository.deleteById(id);
        }
    }

}

package it.course.esercizio_valutativo_springboot.businesses.interfaces;

import it.course.esercizio_valutativo_springboot.models.Project;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ProjectBO {

    Project getProject(long id) throws DataAccessException;

    List<Project> getProjects();

    Project insertProject(Project project) throws DataAccessException;

    void updateProject(long id, Project project) throws DataAccessException;

    void deleteProject(long id) throws DataAccessException;

}

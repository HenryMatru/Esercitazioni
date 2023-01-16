package it.course.esercizio_valutativo_springboot.businesses.interfaces;

import it.course.esercizio_valutativo_springboot.models.Category;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CategoryBO {

    Category getCategory(long id);

    List<Category> getCategories();

    Category insertCategory(Category category) throws DataAccessException;

    void updateCategory(long id, Category category) throws DataAccessException;

    void deleteCategory(long id) throws DataAccessException;

}

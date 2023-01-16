package it.course.esercizio_valutativo_springboot.businesses.impl;

import it.course.esercizio_valutativo_springboot.businesses.interfaces.CategoryBO;
import it.course.esercizio_valutativo_springboot.models.Category;
import it.course.esercizio_valutativo_springboot.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBOImpl implements CategoryBO {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category getCategory(long id) {
        return this.categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category insertCategory(Category category) throws DataAccessException {
        return this.categoryRepository.save(category);
    }

    @Override
    public void updateCategory(long id, Category category) throws DataAccessException {
        if (this.categoryRepository.findById(id).isPresent()) {
            category.setId(id);
            this.categoryRepository.save(category);
        }
    }

    @Override
    public void deleteCategory(long id) throws DataAccessException {
        if (this.categoryRepository.findById(id).isPresent()) {
            this.categoryRepository.deleteById(id);
        }
    }

}

package it.course.esercizio_valutativo_springboot.repositories;

import it.course.esercizio_valutativo_springboot.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

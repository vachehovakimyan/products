package com.vache.products.repository;

import com.vache.products.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий категорий продуктов
 */
@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /**
     * Поик все категорий продуктов
     * @return Список всех категорий продуктов
     */
    List<Category> findAll();


}

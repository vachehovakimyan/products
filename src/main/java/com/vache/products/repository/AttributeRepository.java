package com.vache.products.repository;

import com.vache.products.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Репозиторий свойств продуктов
 */
@Repository("attributeRepository")
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

    /**
     * Поиск свойств продукта по индетификаторам продукта
     * @param product {идентификатор продукта}
     * @return Список свойст продукта
     */
    List<Attribute> findAllByProduct(Integer product);

    /**
     * Удаление всех атрибутор, которые относятся к определенному продукту
     * @param product {идентификатор продукта}
     */
    @Transactional
    void deleteAllByProduct(Integer product);

}

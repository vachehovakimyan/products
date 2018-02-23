package com.vache.products.repository;

import com.vache.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Репозиторий продуктов
 */
@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Поиск всех продуктов по заданной категории
     * @param categoryId {идентификатор категории продуктов}
     * @return список продуктов
     */
    List<Product> findAllByCategoryId(Integer categoryId);

    /**
     * Поиск конкретного продукта по его идентификатору
     * @param id {идентификатор продукта}
     * @return обьект продукта
     */
    Product findProductById(Integer id);

    /**
     * @return идентификатор поседнего продукта
     */
    @Query(value = "SELECT coalesce(max(prod.id), 0) FROM products prod", nativeQuery = true)
    Integer getMaxId();

    @Transactional
    void deleteProductById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "update products prod set prod.name = ?, prod.category = ? where prod.id = ?", nativeQuery = true)
    void update(String name, Integer category, Integer id);

}

package com.vache.products.service;

import com.vache.products.model.Product;

import java.util.List;

/**
 * Данный сервис отвечает за работу с обьектами продуктов
 */
public interface ProductService {

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
     * Создание нового продукта
     * @param product {обьект продукта}
     */
    void createNew(Product product);

    /**
     * Удаление продукта по его идентифкатору
     * @param id {идентификатор продукта}
     */
    void delete(Integer id);

    /**
     * Модификация продукта в базе данных
     * @param product {обьект продукта}
     */
    void update(Product product);
}

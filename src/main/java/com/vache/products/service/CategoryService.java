package com.vache.products.service;

import com.vache.products.model.Category;

import java.util.List;

/**
 * Данный сервис отвечает за работу с обьектами категорий
 */
public interface CategoryService {

    /**
     * Поиск всех категорий
     * @return {список категорий}
     */
    List<Category> findAll();
}

package com.vache.products.service;

import com.vache.products.model.Category;
import com.vache.products.model.Product;
import com.vache.products.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    public CategoryServiceImpl(@Qualifier("categoryRepository") CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @Override
    public List<Category> findAll() {

        // Получаем список всех категорий
        List<Category> categoryList = categoryRepository.findAll();

        // Пробегаем по списку категорий и добавляем в каждую список продуктов, которые к ней относятся
        categoryList.forEach(category -> {
            List<Product> productList = productService.findAllByCategoryId(category.getId());
            category.setProductList(productList);
        });

        return categoryList;
    }
}

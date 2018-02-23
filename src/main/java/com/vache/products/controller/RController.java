package com.vache.products.controller;

import com.vache.products.model.Category;
import com.vache.products.model.Product;
import com.vache.products.repository.ProductRepository;
import com.vache.products.service.CategoryService;
import com.vache.products.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Данный контроллер служит для передачи данных в веб-интерфейс
 */
@RestController
public class RController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public RController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    /**
     * @return {список всех категорий}
     */
    @RequestMapping("/get_all_categories")
    public List<Category> getCategoriesList() {
        return categoryService.findAll();
    }


    /**
     * @return {обьект продукта}
     */
    @RequestMapping("/get_product")
    public Product getProduct(@RequestParam(name = "product_id") Integer id) {
        return productService.findProductById(id);
    }

    /**
     * Создание нового продукта
     */
    @RequestMapping(value = "/create_product", method = RequestMethod.POST)
    public void createProduct(@RequestBody Product product) {
        productService.createNew(product);
    }

    /**
     * Удаление продукта
     */
    @RequestMapping(value = "/delete_prod", method = RequestMethod.GET)
    public void deleteProduct(@RequestParam(name = "id") Integer id) {
        productService.delete(id);
    }

    /**
     * Изменение продукта
     */
    @RequestMapping(value = "/modify_prod", method = RequestMethod.POST)
    public void modifyProduct(@RequestBody Product product) {
        productService.update(product);
    }




}

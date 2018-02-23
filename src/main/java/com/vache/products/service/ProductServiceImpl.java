package com.vache.products.service;

import com.vache.products.model.Attribute;
import com.vache.products.model.Product;
import com.vache.products.repository.AttributeRepository;
import com.vache.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final AttributeRepository attributeRepository;

    public ProductServiceImpl(@Qualifier("productRepository") ProductRepository productRepository, @Qualifier("attributeRepository") AttributeRepository attributeRepository) {
        this.productRepository = productRepository;
        this.attributeRepository = attributeRepository;
    }

    @Override
    public List<Product> findAllByCategoryId(Integer categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public Product findProductById(Integer id) {

        // Получаем обьект продукта по идентификатору
        Product product = productRepository.findProductById(id);

        // Получаем список свойств, которое имеет продукт
        List<Attribute> attributeList = attributeRepository.findAllByProduct(product.getId());

        // Добавляем список свойств в обьект продукта
        product.setAttributeList(attributeList);

        return product;
    }

    @Override
    public void createNew(Product product) {

        // Получаем список свойств продукта из полученного обьекта
        List<Attribute> attributeList = product.getAttributeList();

        // Добавляем в базу данных обьект продукта
        productRepository.save(product);

        // Получаем идентификатор только что созданного в базе обьекта продукта
        Integer lastAddedProductId = productRepository.getMaxId();

        // Пробегаем по всем свойствам продукта и добавляем в них идентификатор продукта
        attributeList.forEach(attribute -> {
            attribute.setProduct(lastAddedProductId);
            // сохраняем каждое свойство в базу
            attributeRepository.save(attribute);
        });

    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteProductById(id);
    }

    @Override
    public void update(Product product) {

        // Обновляем продукт
        productRepository.update(product.getName(), product.getCategoryId(), product.getId());

        // Удаляем старые атрибуты продукта
        attributeRepository.deleteAllByProduct(product.getId());

        // Подучаем новые атрибуты
        List<Attribute> attributeList = product.getAttributeList();

        // Созраняем в базу новые атрибуты
        attributeList.forEach(attribute -> attribute.setProduct(product.getId()));

        attributeRepository.save(attributeList);



    }
}

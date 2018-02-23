package com.vache.products.service;

import com.vache.products.model.Attribute;
import com.vache.products.model.Product;
import com.vache.products.repository.AttributeRepository;
import com.vache.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return productRepository.findProductById(id);
    }

    @Override
    public void createNew(Product product) {

        // Получаем список свойств продукта из полученного обьекта
        List<Attribute> attributeList = product.getAttributeList();

        // Добавляем в базу данных обьект продукта и получаем конечные продукт с выставленным идентификатором
        product.setAttributeList(new ArrayList<>());
        Product finalProduct = productRepository.save(product);

        // Пробегаем по всем свойствам продукта и добавляем в них идентификатор продукта
        attributeList.forEach(attribute -> attribute.setProduct(finalProduct.getId()));

        // Добавляем все свойства в конечный продукт
        finalProduct.setAttributeList(attributeList);

        productRepository.save(finalProduct);

    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteProductById(id);
    }

    @Override
    public void update(Product product) {

        // Удаляем старые атрибуты продукта
        attributeRepository.deleteAllByProduct(product.getId());

        // Выставляем идентификаторы продукта
        product.getAttributeList().forEach(attribute -> attribute.setProduct(product.getId()));

        productRepository.save(product);

    }
}

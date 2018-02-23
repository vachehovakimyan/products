package com.vache.products.model;

import javax.persistence.*;
import java.util.List;

/**
 * Обьект продукта
 */
@Entity
@Table(name = "products")
public class Product {

    // Идентификатор продукта
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    // Название продукта
    @Column(name = "name")
    private String name;

    // Категория к которой относится продукт
    @Column(name = "category")
    private Integer categoryId;

    // Список свойств, которыми обладает продукт
    @Transient
    private List<Attribute> attributeList;

    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

}

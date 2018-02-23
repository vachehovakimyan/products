package com.vache.products.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product", referencedColumnName = "id")
    private List<Attribute> attributeList = new ArrayList<>();

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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", attributeList=" + Arrays.toString(attributeList.toArray()) +
                '}';
    }
}

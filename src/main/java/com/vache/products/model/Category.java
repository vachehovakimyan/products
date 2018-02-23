package com.vache.products.model;


import javax.persistence.*;
import java.util.List;

/**
 * Категория продукта
 */
@Entity
@Table(name = "categories")
public class Category {

    // Идентификатор категории продуктов
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    // Название категории
    @Column(name = "name")
    private String name;

    // Список всех продуктов в категории
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category", referencedColumnName = "id")
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
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
}

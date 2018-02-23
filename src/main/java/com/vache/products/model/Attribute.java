package com.vache.products.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.omg.CORBA.Object;

import javax.persistence.*;
import java.util.List;

/**
 * Свойство продукта
 */
@Entity
@Table(name = "attributes")
public class Attribute {

    // Идентификатор свойства
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonIgnore
    private Integer id;

    // Название свойства
    @Column(name = "name")
    private String name;

    // Неокое число указывающее на содержании в продукте
    @Column(name = "capacity")
    private Float capacity;

    // Идентификатор продукта
    @Column(name = "product")
    @JsonIgnore
    private Integer product;

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

    public Float getCapacity() {
        return capacity;
    }

    public void setCapacity(Float capacity) {
        this.capacity = capacity;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", product=" + product +
                '}';
    }
}

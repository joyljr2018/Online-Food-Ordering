package com.jjsushi.sell.dao;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * By Jiarong
 * 2019/3/31
 */

@Entity
@Data
@DynamicUpdate
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    public ProductCategory() {
    }
    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }



}

package com.wineshop.model;

import java.io.Serializable;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "product_category")
@XmlRootElement
@ManagedBean
@NamedQueries({
    @NamedQuery(name = "ProductCategory.findAll" , query ="SELECT p FROM ProductCategory p"),
    @NamedQuery(name = "ProductCategory.findByProductId" , query ="SELECT p FROM ProductCategory p WHERE p.productId = :productId"),
    @NamedQuery(name = "ProductCategory.findByCategoryId" , query ="SELECT p FROM ProductCategory p WHERE p.categoryId = :categoryId")})
public class ProductCategory implements Serializable {

    @Version
    @NotNull
    public static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    private Integer productCategoryId;
    
    @Column(name = "product_id")
    private int productId;
    
    @Column(name = "category_id")
    private int categoryId;

    public ProductCategory() {}

    public ProductCategory( Integer productId, Integer categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

   
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.productCategoryId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductCategory other = (ProductCategory) obj;
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        if (!Objects.equals(this.categoryId, other.categoryId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductCategory{" + "productCategoryId=" + productCategoryId + ", productId=" + productId + ", categoryId=" + categoryId + '}';
    }    
    
}

package com.wineshop.dao;

import com.wineshop.model.ProductCategory;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

@Model
public class ProductCategoryDAO implements Serializable {

    @PersistenceContext(unitName = "WineShopPU")
    private EntityManager em;

    public List<ProductCategory> findAll() {
        Query query = em.createNamedQuery("ProductCategory.findAll");
        return query.getResultList();
    }

    public ProductCategory findById(Integer productCategoryId) {
        return em.find(ProductCategory.class, productCategoryId);
    }

    public List<ProductCategory> findByProductId(Integer productId) {
        Query query = em.createNamedQuery("ProductCategory.findByProductId");
        return query.getResultList();
    }

    public List<ProductCategory> findByCategoryId(Integer categoryID) {
        Query query = em.createNamedQuery("ProductCategory.findByCategoryId");
        return query.getResultList();
    }

    public void create(ProductCategory productCategory) {
        em.persist(productCategory);
        em.flush();
    }
    
//    public void create(ProductCategory productCategory) {
//        try {
//            Query query = em.createNativeQuery("INSERT INTO PRODUCT_CATEGORY (Product_Id, Category_Id) VALUES (?, ?)");
//            query.setParameter(1, productCategory.getProductId());
//            query.setParameter(2, productCategory.getCategoryId());
//            query.executeUpdate();
//            em.flush();
//
//        } catch (Exception e) {
//            Logger.getLogger(ProductCategoryDAO.class.getName()).log(Level.SEVERE, "Error creating new Product Category in Database", e.getMessage());
//        }
//        
// 
//    }

    public void update(ProductCategory productCategory) {
        em.merge(productCategory);
    }

    public void delete(@Valid ProductCategory productCategory) {
        em.remove(productCategory);
    }
}

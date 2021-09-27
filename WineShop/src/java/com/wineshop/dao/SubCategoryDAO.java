package com.wineshop.dao;

import com.wineshop.model.SubCategory;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

@Model
public class SubCategoryDAO implements Serializable {
    
    @PersistenceContext(unitName = "WineShopPU")
    private EntityManager em;
    
    public List<SubCategory> findAll() {
        Query query = em.createNamedQuery("SubCategory.findAll");
        return query.getResultList();
    }
    
    public long subCategoryCount() {
        return findAll().stream().count();
    }
    
    public SubCategory findById(Integer subCategoryId) {
        return em.find(SubCategory.class, subCategoryId);
    }
    
    public List<SubCategory> findByCategoryId(Integer categoryId) {
        Query query = em.createNamedQuery("SubCategory.findByCategoryId");
        return query.getResultList();
    }
    
    public List<SubCategory> findByDescription(String description) {
        Query query = em.createNamedQuery("SubCategory.findByDescription");
        return query.getResultList();
    }
    
    public void create(SubCategory subCategory) {
        em.persist(subCategory);
        em.flush();
    }
    
//    public void create(SubCategory subCategory) {
//        try {
//            Query query = em.createNativeQuery("INSERT INTO SUB_CATEGORY (Category_Id, Description) VALUES (?, ?)");
//            query.setParameter(1, subCategory.getCategoryId());
//            query.setParameter(2, subCategory.getDescription());
//            query.executeUpdate();
//        } catch (Exception e) {
//            Logger.getLogger(SubCategoryDAO.class.getName()).log(Level.SEVERE, "Error creating Sub Category in Database", e.getMessage());
//        }
//    }
    
    public void update(@Valid SubCategory subCategory) {
        em.merge(subCategory);
    }

    public void delete(int subCategoryId) {
        SubCategory subCategory = em.find(SubCategory.class, subCategoryId);
        em.remove(subCategory);
    }
}

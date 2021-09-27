package com.wineshop.dao;

import com.wineshop.model.Category;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

//@Stateless
//@LocalBean
@Model
public class CategoryDAO {
    
    @PersistenceContext(unitName = "WineShopPU")
    private EntityManager em;
    
    
    
    public List<Category> findAll() {
        Query query = em.createNamedQuery("Category.findAll");
        return query.getResultList();
    }
    
    public Category findById(Integer categoryId) {
        return em.find(Category.class, categoryId);
    }
    
    public Category findByDescription(String categoryDescription) {
        return em.find(Category.class, categoryDescription);
    }
    public void create(Category category) {
        em.persist(category);
        em.flush();
    }
    
    public void update(Category category) {
        em.merge(category);
    }
    
    public void delete(int categoryId) {
        Category category = em.find(Category.class, categoryId);
        em.remove(category);
    }
    
    
}

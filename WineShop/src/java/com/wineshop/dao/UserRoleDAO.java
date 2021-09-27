package com.wineshop.dao;

import com.wineshop.model.UserRole;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

@Model
public class UserRoleDAO implements Serializable {
    
    @PersistenceContext(unitName="WineShopPU")
    private EntityManager em;
    
    public List<UserRole> findAll() {
        Query query = em.createNamedQuery("UserRole.findAll");
        return query.getResultList();
    }
    
    public long roleCount() {
        return findAll().stream().count();
    }
    
    public UserRole findById(Integer userRoleId) {
        return em.find(UserRole.class, userRoleId);
    }
    
    public List<UserRole> findByDescription(String description) {
        Query query = em.createNamedQuery("UserRole.findByDescription");
        return query.getResultList();
    }
    
    public void create(UserRole userRole) {
        em.persist(userRole);
        em.flush();
    }
    
    public void update(UserRole userRole) {
        em.merge(userRole);
    }
    
    public void delete(UserRole userRole) {
        em.remove(userRole);
    }
}

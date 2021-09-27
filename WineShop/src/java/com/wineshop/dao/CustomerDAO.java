package com.wineshop.dao;

import com.wineshop.model.Customer;
import java.util.Date;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//@Stateless
//@LocalBean
@Model
public class CustomerDAO {
    
    @PersistenceContext(unitName = "WineShopPU")
    private EntityManager em;
    
    
    public List<Customer> findAll() {
        Query query = em.createNamedQuery("Customer.findAll");
        return query.getResultList();
    }
    
    public List<Customer> findByLogin(String username, String password) {
        Query query = em.createNamedQuery("Customer.findByLogin");
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getResultList();
    }
    
    
    public long customerCount() {
        return findAll().stream().count();
    }
    
    public Customer findById(Integer customerId) {
        return em.find(Customer.class, customerId);
    }
    
    public List<Customer> findByFirstName(String firstName) {
        Query query = em.createNamedQuery("Customer.findByFirstName");
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }
    
    public List<Customer> findByLastName(String lastName) {
        Query query = em.createNamedQuery("Customer.findByLastName");
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }
    
    public Customer findByTaxId(String taxId) {
        return em.find(Customer.class, taxId);
    }
    
    public List<Customer> findByDateOfBirth(Date dateOfBirth) {
        Query query = em.createNamedQuery("Customer.findByDateOfBirth");
        query.setParameter("dateOfBirth", dateOfBirth);
        return query.getResultList();
    }
    
    public List<Customer> findByPostalCode(String postalCode) {
        Query query = em.createNamedQuery("Customer.findByPostalCode");
        query.setParameter("postalCode", postalCode);
        return query.getResultList();
    }
    
    public List<Customer> findByRoleId(Integer roleId) {
        Query query = em.createNamedQuery("Customer.findByRoleId");
        query.setParameter("roleId", roleId);
        return query.getResultList();
    }
    
    public void create(Customer customer) {
        em.persist(customer);
        em.flush();
    }
    
    public void update(Customer customer) {
        em.merge(customer);
    }
    
    public void delete(Customer customer) {
        em.remove(customer);
    }
    
    
}

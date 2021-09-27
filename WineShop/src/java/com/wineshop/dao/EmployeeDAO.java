package com.wineshop.dao;

import com.wineshop.model.Employee;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Model
public class EmployeeDAO {
    
    @PersistenceContext(unitName = "WineShopPU")
    private EntityManager em;
    
    
    public List<Employee> findAll() {
        Query query = em.createNamedQuery("Employee.findAll");
        return query.getResultList();
    }
    
    public long employeeCount() {
        return findAll().stream().count();
    }
    
    public void create(Employee employee) {
        em.persist(employee);
        em.flush();
    }
    
//    public void create(Employee employee) {
//        
//        try {
//            Query query = em.createNativeQuery("INSERT INTO EMPLOYEE (Customer_Id) VALUES (?)");
//            query.setParameter(1, employee.getCustomerId());
//            query.executeUpdate();
//            
//        } catch (Exception e) {
//            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, "Error creating employee record", e);
//        }
//    }
    
    public void update(Employee employee) {
        em.merge(employee);
    }
    
    public void delete(Employee employee) {
        em.remove(employee);
    }   
}

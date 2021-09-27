package com.wineshop.dao;

import com.wineshop.model.Invoice;
import java.io.Serializable;
import java.util.List;
import javax.ejb.LocalBean;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

@LocalBean
@Model
public class InvoiceDAO implements Serializable {

    @PersistenceContext(unitName = "WineShopPU")
    private EntityManager em;
    
    public List<Invoice> findAll() {
        Query query = em.createNamedQuery("Invoice.findAll");
        return query.getResultList();
    }

    public long invoiceCount() {
        return findAll().stream().count();
    }

    public Invoice findById(Integer invoiceId) {
        return em.find(Invoice.class, invoiceId);
    }

    public Invoice create(Invoice invoice) {
        em.persist(invoice);
        em.flush();
        return invoice;
    }
    
    public Invoice createNull() {
        Invoice newInvoice = new Invoice();
        em.persist(newInvoice);
        em.flush();
        return newInvoice;
    }

//    public void create(Invoice invoice) {
//        try {
//            Query query = em.createNativeQuery("INSERT INTO INVOICE (Operation_Date, Total_Sale, Employee_Id) VALUES (?, ?, ?)");
//            
//            query.setParameter(1, invoice.getOperationDate());            
//            query.setParameter(2, invoice.getTotalSale());
//            query.setParameter(3, invoice.getEmployeeId());
//            query.executeUpdate();
//            Logger.getLogger(InvoiceDAO.class.getName()).log(Level.INFO, "Added to database Invoice ID: " + invoice.getInvoiceId());
//
//        } catch (Exception e) {
//            Logger.getLogger(InvoiceDAO.class.getName()).log(Level.SEVERE, e.getCause().getMessage());
//        }
//    }
    

    public void update(@Valid Invoice invoice) {
        em.merge(invoice);
    }

    public void delete(int invoiceId) {
        Invoice invoice = em.find(Invoice.class, invoiceId);
        em.remove(invoice);
    }
}

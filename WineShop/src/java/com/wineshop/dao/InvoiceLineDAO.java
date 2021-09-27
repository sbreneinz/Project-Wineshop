package com.wineshop.dao;

import com.wineshop.model.InvoiceLine;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

@Model
public class InvoiceLineDAO implements Serializable {

    @PersistenceContext(unitName = "WineShopPU")
    private EntityManager em;

    public List<InvoiceLine> findAll() {
        Query query = em.createNamedQuery("InvoiceLine.findAll");
        return query.getResultList();
    }

    public InvoiceLine findById(Integer invoiceLineId) {
        return em.find(InvoiceLine.class, invoiceLineId);
    }

    public List<InvoiceLine> findByInvoiceId(Integer invoiceId) {
        Query query = em.createNamedQuery("InvoiceLine.findByInvoiceId");
        return query.getResultList();
    }

    public List<InvoiceLine> findByProductId(Integer productId) {
        Query query = em.createNamedQuery("InvoiceLine.findByProductId");
        return query.getResultList();
    }

    public List<InvoiceLine> findByProductPrice(Double price) {
        Query query = em.createNamedQuery("InvoiceLine.findByProductPrice");
        return query.getResultList();
    }

    public void create(@Valid InvoiceLine invoiceLine) {
        em.persist(invoiceLine);
        em.flush();
    }
    
//    public void create(InvoiceLine invoiceLine) {
//        try {
//            Query query = em.createNativeQuery("INSERT INTO INVOICE_LINE (Invoice_Id, Product_Id, Product_Price, Product_Quantity) VALUES (?, ?, ?, ?)");
//            query.setParameter(1, invoiceLine.getInvoiceId());
//            query.setParameter(2, invoiceLine.getProductId());
//            query.setParameter(3, invoiceLine.getProductPrice());
//            query.setParameter(4, invoiceLine.getProductQuantity());
//            query.executeUpdate();
//            
//            Logger.getLogger(InvoiceLineDAO.class.getName()).log(Level.INFO, "Added to database Invoice Line ID: " + invoiceLine.getInvoiceLineId());
//        } catch (Exception e) {
//            Logger.getLogger(InvoiceLineDAO.class.getName()).log(Level.SEVERE, e.getCause().getMessage());
//        }
//    }

    public void update(@Valid InvoiceLine invoiceLine) {
        em.merge(invoiceLine);
    }

    public void delete(@Valid InvoiceLine invoiceLine) {
        em.remove(invoiceLine);
    }
}

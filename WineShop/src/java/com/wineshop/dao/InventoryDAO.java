package com.wineshop.dao;

import com.wineshop.model.Inventory;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Model
public class InventoryDAO {

    @PersistenceContext(unitName = "WineShopPU")
    private EntityManager em;

    public List<Inventory> findAll() {
        Query query = em.createNamedQuery("Inventory.findAll");
        return query.getResultList();
    }

    public Inventory findByProductId(int productId) {
        return em.find(Inventory.class, productId);
    }
    

//    public void create(Inventory inventory) {
//        try {
//            Query query = em.createNativeQuery("INSERT INTO INVENTORY (Product_Id, Quantity) VALUES (?, ?)");
//            query.setParameter(1, inventory.getProductId());
//            query.setParameter(2, inventory.getQuantity());
//            query.executeUpdate();
//
//            Logger.getLogger(InventoryDAO.class.getName()).log(Level.INFO, "Added to database inventory ID: " + inventory.getProductId());
//        } catch (Exception e) {
//            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, e.getCause().getMessage());
//        }
//
//    }
    
    public Inventory create(Inventory inventory) {
        em.persist(inventory);
        em.flush();
        return inventory;
    }

    public void update(Inventory inventory) {
        em.merge(inventory);
    }
    
    public void delete(int inventoryId) {
        Inventory tempInventory = em.find(Inventory.class, inventoryId);
        em.remove(tempInventory);
    }

}

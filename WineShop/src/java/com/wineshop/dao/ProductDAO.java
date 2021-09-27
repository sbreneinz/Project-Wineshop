package com.wineshop.dao;

import com.wineshop.model.Product;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Model
public class ProductDAO {

    @PersistenceContext(unitName = "WineShopPU")
    private EntityManager em;

    public List<Product> findAll() {
        Query query = em.createNamedQuery("Product.findAll");
        return query.getResultList();
    }

    public long productCount() {
        return findAll().stream().count();
    }
    
    public Product findById(Integer productId) {
        return em.find(Product.class, productId);
    }
    
    public List<Product> findByName(String name) {
        Query query = em.createNamedQuery("Product.findByName");
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Product> findByDescription(String description) {
        Query query = em.createNamedQuery("Product.findByDescription");
        query.setParameter("description", description);
        return query.getResultList();
    }

    public List<Product> findByPrice(Double price) {
        Query query = em.createNamedQuery("Product.findByPrice");
        query.setParameter("price", price);
        return query.getResultList();
    }

    public List<Product> findByPriceSmallerThan(Double price) {
        Query query = em.createNamedQuery("Product.findByPriceSmallerThan");
        query.setParameter("price", price);
        return query.getResultList();
    }

    public List<Product> findByPriceBiggerThan(Double price) {
        Query query = em.createNamedQuery("Product.findByPriceBiggerThan");
        query.setParameter("price", price);
        return query.getResultList();
    }

    public List<Product> findByType(String type) {
        Query query = em.createNamedQuery("Product.findByType");
        query.setParameter("type", type);
        return query.getResultList();
    }

    public List<Product> findByYear(Integer year) {
        Query query = em.createNamedQuery("Product.findByYear");
        query.setParameter("year", year);
        return query.getResultList();
    }

    public List<Product> findByCountry(String country) {
        Query query = em.createNamedQuery("Product.findByCountry");
        query.setParameter("country", country);
        return query.getResultList();
    }

    public List<Product> findByRegion(String region) {
        Query query = em.createNamedQuery("Product.findByRegion");
        query.setParameter("region", region);
        return query.getResultList();
    }

    public List<Product> findByProducer(String producer) {
        Query query = em.createNamedQuery("Product.findByProducer");
        query.setParameter("producer", producer);
        return query.getResultList();
    }

    public void create(Product product) {
        em.persist(product);
        em.flush();
    }
    
    // Save button in Add Product Page
//    public Product  create(Product product) {
//        try {
//            Query query = em.createNativeQuery("INSERT INTO PRODUCT (Prod_Name, Description, Price, Prod_Type, Prod_Year, Country, Region, Producer) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
//            query.setParameter(1, product.getName());
//            query.setParameter(2, product.getDescription());
//            query.setParameter(3, product.getPrice());
//            query.setParameter(4, product.getType());
//            query.setParameter(5, product.getYear());
//            query.setParameter(6, product.getCountry());
//            query.setParameter(7, product.getRegion());
//            query.setParameter(8, product.getProducer());
//            query.executeUpdate();
//            em.flush();
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.INFO, "Added to database product ID: " + product.getProductId());
//            Product tempProduct = em.find(Product.class, product.getProductId());
//            return tempProduct;
//            
//            
//        } catch (Exception e) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, e.getCause().getMessage());
//        }
//        return new Product("bad return","bad return",0.0,"bad return",0,"bad return","bad return","bad return");
//    }
    
    
    // save button in Update Product Page
    public void update(Product product) {
        em.merge(product);
    }
    
      public Product updateWithReturn(Product product) {
        em.merge(product);
        return product;
    }
   
    // delete button in Product Manager Page
    public void delete(int  productId) {
        Product product = em.find(Product.class, productId);
        em.remove(product);
    }
}

//    --------------------------------------------------------------------------
//        


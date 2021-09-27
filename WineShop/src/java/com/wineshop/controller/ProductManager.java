package com.wineshop.controller;

import com.wineshop.util.ErrorMessage;
import com.wineshop.dao.CategoryDAO;
import com.wineshop.dao.InventoryDAO;
import com.wineshop.dao.ProductCategoryDAO;
import com.wineshop.dao.ProductDAO;
import com.wineshop.dao.SubCategoryDAO;
import com.wineshop.model.Category;
import com.wineshop.model.Inventory;
import com.wineshop.model.Product;
import com.wineshop.model.ProductCategory;
import com.wineshop.model.SubCategory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@ManagedBean
@SessionScoped
public class ProductManager implements Serializable, ErrorMessage {

    @Resource private UserTransaction ut; 
    @Inject private ProductDAO productDao;
    @Inject private InventoryDAO inventoryDao;
    @Inject private CategoryDAO categoryDao;
    @Inject private SubCategoryDAO subCategoryDao;
    @Inject private ProductCategoryDAO productCategoryDao;

    private List<Product> products;
    private List<Inventory> inventoryList;
    
    private List<Integer> categoryIdList;
    private List<String> categoryDescriptionList;
    private List<Category> categoryList;

    
    private List<SubCategory> subCategoryList;
    private List<String> subCategoryDescriptionList;
    private List<Integer> subCategoryIdList;
    
    private Logger logger = Logger.getLogger(getClass().getName());

    public ProductManager() {
        products = new ArrayList<>();
        inventoryList = new ArrayList<>();
        categoryList = new ArrayList<>();
        subCategoryList = new ArrayList<>();
    }

    @PostConstruct
    public void loadAll() {
        loadCategories();
        loadSubCategories();
    
    }

    // load the list with all products at render time
    public void loadProducts() {
        // clear products list
        products.clear();
        try {
            // get products from Database
            products = productDao.findAll();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading products from Database", e);
            addErrorMessage(e);
        }
    }

    // Category Drop down list 
    public void loadCategories() {
        // Category list from DB
        categoryList = categoryDao.findAll();
        
        // Category Drop down list only with description as value
        categoryDescriptionList = categoryDao.findAll().stream()
                .map(m -> m.getDescription())
                .collect(Collectors.toList());
        
        // Category Drop down list only with Id as value
        categoryIdList = categoryDao.findAll().stream()
                                            .map(m -> m.getCategoryId())
                                            .collect(Collectors.toList());
    }

    // Subcategory Drop down list 
    public void loadSubCategories() {
        subCategoryList = subCategoryDao.findAll();
         // Subcategory Drop down list only with description as value
        subCategoryDescriptionList = subCategoryDao.findAll().stream()
                                                             .map(m -> m.getDescription())
                                                             .collect(Collectors.toList());
    }
    
    public void loadInventory() {
        inventoryList = inventoryDao.findAll();
    }
    
/*
--------------------------------------------------------------------------------
                        PRODUCT
--------------------------------------------------------------------------------
*/

    public String addProduct(Product product, Inventory inventory, int categoryId) {
        
        try {
            ut.begin();
            // If product already exists (rollback) check Product equals() to set up equality
            if(products.contains(product)) {
                addErrorMessage(new Exception("Product " + product.getName() + " already exists"));
                ut.rollback();
            }
            // Persist new product
            productDao.create(product);
            // set Inventory 
            inventory.setProductId(product.getProductId());
            inventoryDao.create(inventory);
            // Add Product_Category 
            productCategoryDao.create(new ProductCategory(product.getProductId(), categoryId));
            ut.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding new product ", e.getCause().getMessage());
            addErrorMessage(e);
      
        }
        return "ProductManager?faces-redirect=true";
    }  
    
    
    // for update product button
    public String loadProduct(int productId) {

        logger.info("Loading product: " + productId);

        try {
            // find product in DB
            Product tempProduct = productDao.findById(productId);

            // put in the request attribute ... so we can use it on the form page
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> requestMap = externalContext.getRequestMap();
            requestMap.put("product", tempProduct);

        } catch (Exception e) {
            // log error message
            logger.log(Level.SEVERE, "Error loading product id: " + productId, e);

            // error message to JSF page
            addErrorMessage(e);
            return null;
        }

        return "UpdateProduct";
    }

    public String updateProduct(Product product) {
        try {
            ut.begin();
            productDao.update(product);
            
            ut.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting product: " + product, e);
            addErrorMessage(e);
            try {
                ut.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        return "ProductManager?faces-redirect=true";
    }
    
    public String deleteProduct(int productId) {

        try {
            ut.begin();
            productDao.delete(productId);
            ut.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting product id: " + productId, e);
            addErrorMessage(e);
            try {
                ut.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "ProductManager?faces-redirect=true";
    }
/*
--------------------------------------------------------------------------------
                        CATEGORY
--------------------------------------------------------------------------------
*/
    
    public String addCategory(Category category) {
        try {
            ut.begin();
            categoryDao.create(category);
            ut.commit();
            logger.log(Level.INFO, "Category added to DB");
        } catch (Exception e) {
            addErrorMessage(e);
            try {
                ut.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        return "AddCategory?faces-redirect=true";
    }
    
    // for update category button
    public String loadCategory(int categoryId) {

        try {
            // find category in DB
            Category tempCategory = categoryDao.findById(categoryId);

            // put in the request attribute ... so we can use it on the form page
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> requestMap = externalContext.getRequestMap();
            requestMap.put("category", tempCategory);

        } catch (Exception e) {
            // log error message
            logger.log(Level.SEVERE, "Error loading category id: " + categoryId, e);
            addErrorMessage(e);
            return null;
        }

        return "UpdateCategory";
    }
    
    public String updateCategory(Category category) {
        try {
            ut.begin();
            categoryDao.update(category);
            ut.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating category: " + category, e);
            addErrorMessage(e);
            try {
                ut.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "AddCategory?faces-redirect=true";
    }
    
    public String deleteCategory(int categoryId) {
        try {
            ut.begin();
            categoryDao.delete(categoryId);
            ut.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting category id: " + categoryId, e);
            addErrorMessage(e);
            try {
                ut.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "AddCategory?faces-redirect=true";
    }
/*
--------------------------------------------------------------------------------
                        SUB CATEGORY
--------------------------------------------------------------------------------
*/
    
    public String addSubCategory(SubCategory subCategory) {
        try {
            ut.begin();
            subCategoryDao.create(subCategory);
            ut.commit();
            logger.log(Level.INFO, "Sub Category added to DB");
        } catch (Exception e) {
            addErrorMessage(e);
            logger.log(Level.INFO, "Error creating Sub Category to DB" + subCategory, e.getMessage());
            try {
                ut.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        return "AddSubCategory?faces-redirect=true";
    }
    
    // for update sub category button
    public String loadSubCategory(int subCategoryId) {

        try {
            // find category in DB
            SubCategory tempSubCategory = subCategoryDao.findById(subCategoryId);

            // put in the request attribute ... so we can use it on the form page
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> requestMap = externalContext.getRequestMap();
            requestMap.put("subCategory", tempSubCategory);

        } catch (Exception e) {
            // log error message
            logger.log(Level.SEVERE, "Error loading sub category id: " + subCategoryId, e);
            addErrorMessage(e);
            return null;
        }

        return "UpdateSubCategory";
    }
    
    
    public String updateSubCategory(SubCategory subCategory) {
        try {
            ut.begin();
            subCategoryDao.update(subCategory);
            ut.commit();
            logger.log(Level.INFO, "Sub Category updated to DB");
        } catch (Exception e) {
            addErrorMessage(e);
            logger.log(Level.INFO, "Error updating Sub Category to DB" + subCategory, e.getMessage());
            try {
                ut.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        return "AddSubCategory?faces-redirect=true";
    }

    public String deleteSubCategory(int subCategoryId) {
        try {
            ut.begin();
            subCategoryDao.delete(subCategoryId);
            ut.commit();
            logger.log(Level.INFO, "Sub Category deleted in DB");
        } catch (Exception e) {
            addErrorMessage(e);
            logger.log(Level.INFO, "Error deleting Sub Category in DB" + subCategoryId, e.getMessage());
            try {
                ut.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        return "AddSubCategory?faces-redirect=true";
    }
    
/*
--------------------------------------------------------------------------------
                        INVENTORY
--------------------------------------------------------------------------------
*/
    // for add stock button
    public String loadInventory(int productId) {

        try {
            // find inventory in DB
            Inventory tempInventory = inventoryDao.findByProductId(productId);

            // put in the request attribute ... so we can use it on the form page
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> requestMap = externalContext.getRequestMap();
            requestMap.put("inventory", tempInventory);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading inventory of product id: " + productId, e);
            addErrorMessage(e);
            return null;
        }

        return "AddStock";
    }

    public String updateStock(Inventory productId) {
            try {
                ut.begin();
                inventoryDao.update(productId);
                ut.commit();
            } catch (Exception e) {
                addErrorMessage(e);
                try {
                    ut.rollback();
                } catch (IllegalStateException ex) {
                    Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SystemException ex) {
                    Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return "AddStock?faces-redirect=true";
    }


    public String deleteInventory(int productId) {
        try {
            ut.begin();
            inventoryDao.delete(productId);
            ut.commit();
            logger.log(Level.INFO, "Inventory deleted in DB");
        } catch (Exception e) {
            addErrorMessage(e);
            logger.log(Level.INFO, "Error deleting inventory in DB" + productId, e.getMessage());
            try {
                ut.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        return "AddStock?faces-redirect=true";
    }
    
/*
--------------------------------------------------------------------------------
                        
--------------------------------------------------------------------------------
*/
            
    @Override
    public void addErrorMessage(Exception e) {
        FacesMessage message = new FacesMessage("Error: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
    public List<Product> getProducts() {
        return products;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<Integer> getCategoryIdList() {
        return categoryIdList;
    }

    public List<String> getCategoryDescriptionList() {
        return categoryDescriptionList;
    }

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public List<Integer> getSubCategoryIdList() {
        return subCategoryIdList;
    }

    public List<String> getSubCategoryDescriptionList() {
        return subCategoryDescriptionList;
    }
   
    
}

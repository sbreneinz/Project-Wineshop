package com.wineshop.controller;

import com.wineshop.dao.CustomerDAO;
import com.wineshop.dao.EmployeeDAO;
import com.wineshop.dao.InventoryDAO;
import com.wineshop.dao.InvoiceDAO;
import com.wineshop.dao.InvoiceLineDAO;
import com.wineshop.dao.ProductDAO;
import com.wineshop.model.Inventory;
import com.wineshop.model.Invoice;
import com.wineshop.model.InvoiceLine;
import com.wineshop.model.Product;
import com.wineshop.util.ErrorMessage;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@ManagedBean
@SessionScoped
public class ShoppingCart implements Serializable, ErrorMessage {

    @Inject private ProductDAO productDao;
    @Inject private InventoryDAO inventoryDao;
    @Inject private InvoiceDAO invoiceDao;
    @Inject private InvoiceLineDAO invoiceLineDao;
    @Inject private CustomerDAO customerDao;
    @Inject private EmployeeDAO employeeDao;
    @Resource private UserTransaction ut;
    
    private List<InvoiceLine> receiptInvoiceLines;
    private Invoice receiptInvoice;
    
    private Map<Product, Integer> cart;
    private Double total;
    private Logger logger = Logger.getLogger(getClass().getName());

    public ShoppingCart() {
        cart = new HashMap<>();
        receiptInvoiceLines = new ArrayList<>();
    }

    // display product in ShoppingCart
    public String displayCartProduct(Product product) {
        return product.getName() + " - " + product.getType() + " - " + product.getYear();
    }

    // get ID from JSF Product page (Add product button parameter)
    public String getIdParameter(FacesContext fc) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("id");
    }

    // add product to cart
    public String addProduct() {

        FacesContext fc = FacesContext.getCurrentInstance();
        int productId = Integer.parseInt(getIdParameter(fc));
        String uri = ((HttpServletRequest) fc.getExternalContext().getRequest()).getRequestURI();

        // get product from product list
        Product tempProd = productDao.findById(productId);

        // if Product already in Cart, increase quantity by 1, else add 1 product to cart 
        if (cart.containsKey(tempProd)) {
            increaseQuantity(tempProd);
        } else {
            cart.put(tempProd, 1);
        }
        return uri + "?faces-redirect=true";
    }

    // action button to add 1 more product quantity
    public String increaseQuantity(Product product) {
        try {
            cart.put(product, cart.get(product) + 1);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding Product " + product + " to cart", e);
            addErrorMessage(e);
            return null;
        }
        return "ShoppingCart?faces-redirect=true";
    }

    // action button to subtract 1 product quantity
    public String decreaseQuantity(Product product) {
        try {
            // if quantity = 1, remove product
            if (cart.get(product) <= 1) {
                cart.remove(product);
            }

            cart.put(product, cart.get(product) - 1);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error subtracting Product " + product + " from cart", e);
            addErrorMessage(e);
            return null;
        }
        return "ShoppingCart?faces-redirect=true";
    }

    // action button to remove product from cart
    public String removeProduct(Product product) {
        try {
            cart.remove(product);
            logger.log(Level.INFO, "Removed product " + product + " from cart");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error removing product " + product + " from cart");
            return null;
        }

        return "ShoppingCart?faces-redirect=true";
    }

//    // total value of cart
    public Double getTotal() {
        // resetting total for calculation
        total = 0.0;

        // multiplying cost of 1 product by its quantity and adding to total
        cart.entrySet().stream().forEach(e -> total += e.getKey().getPrice() * e.getValue());
        return total;
    }

    public String purchaseCart(int customerId) {
        receiptInvoice = null;
        receiptInvoiceLines.clear();
        try {
            ut.begin();
            // Create Invoice Object
            Invoice invoice = null;
            invoice = invoiceDao.createNull();
            // Set of products to iterate over
            Set<Product> productList = cart.keySet();

            for (Product p : productList) {
                // get inventory quantity for specified Product Id
                Inventory inventory = inventoryDao.findByProductId(p.getProductId());
                int inventoryQuantity = inventory.getQuantity();
                // get the value (quantity)
                int productQuantity = cart.get(p);

                // Subtract cart quantity to inventory quantity
                if (!(inventoryQuantity - productQuantity >= 0) || cart.isEmpty())  {
                    logger.log(Level.SEVERE, "Couldn't buy " + p.getName() + " : Available quantity: " + inventoryQuantity);
                    addErrorMessage(new Exception("Error: couldn't buy " + p.getName() + " : Available quantity: " + inventoryQuantity));
                    ut.rollback();
                    return null;
                } else {
                    // update inventory quantity
                    inventory.setQuantity(inventoryQuantity - productQuantity);
                    // get Invoice Line Cost
                    double invoiceLineCost = p.getPrice() * productQuantity;
                    // create Invoice Line and persist
                    InvoiceLine newInvoiceLine = new InvoiceLine(invoice.getInvoiceId(), p.getProductId(), p.getPrice(), productQuantity);
                    invoiceLineDao.create(newInvoiceLine);
                    
                    // Add InvoiceLine to Receipt
                    receiptInvoiceLines.add(newInvoiceLine);
                }
            }
            // Update Invoice Object
            invoice.setOperationDate(LocalDate.now().toString());
            invoice.setTotalSale(getTotal());
// TODO: Find Employee ID with customer ID in EMPLOYEE Table
            invoice.setEmployeeId(customerId); // get CustomerId from Login
//            invoiceDao.update(invoice);
            // Invoice to present at the end of the purchase
            receiptInvoice = invoice;
            try {
                ut.commit();
                
                
            } catch (javax.transaction.RollbackException ex) {
                Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalStateException ex) {
                Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | SystemException e) {
            logger.log(Level.SEVERE, "Error trying to purchase cart: " + e);

        } finally {
            // reset cart
            cart = new HashMap<>();
        }
        // go to Receipt / Invoice
        return "Receipt";
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void loadCart() {
        cart = getCart();
    }

    @Override
    public void addErrorMessage(Exception e) {
        FacesMessage message = new FacesMessage("Error: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public ProductDAO getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDAO productDao) {
        this.productDao = productDao;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Invoice getReceiptInvoice() {
        return receiptInvoice;
    }

    public List<InvoiceLine> getReceiptInvoiceLines() {
        return receiptInvoiceLines;
    }
    
}

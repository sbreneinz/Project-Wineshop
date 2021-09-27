package com.wineshop.controller;

import com.wineshop.util.ErrorMessage;
import com.wineshop.dao.CustomerDAO;
import com.wineshop.dao.EmployeeDAO;
import com.wineshop.model.Customer;
import com.wineshop.model.Employee;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Login implements Serializable, ErrorMessage {

    @Inject
    private CustomerDAO customerDao;

    @Inject
    private EmployeeDAO employeeDao;

    @Resource
    private UserTransaction ut;

    private Customer loggedInCustomer;
    private String username;
    private String password;
    private List<Customer> customerList;
    private Logger logger = Logger.getLogger(getClass().getName());

    public Login() {
        customerList = new ArrayList<>();
        loggedInCustomer = null;
    }

    // Get all customer records from DB 
    public void loadCustomerList() {
        try {
            customerList = customerDao.findAll();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading Customer list", e.getMessage());
            addErrorMessage(e);
        }
    }

    /* Account button - if customer logged in go to "Update Customer Details" page / else go to "Login" page */
    public String login() {
        if (loggedInCustomer == null) {
            return "Login";
        }

        // load customerID
        return loadCustomer(loggedInCustomer.getCustomerId());
    }

    /* If customer logged in, "logout" / else deactivate button "logout" */
    public String logout() {
        if (loggedInCustomer == null) {
            return "Login?faces-redirect=true";
        }

        loggedInCustomer = null;
        return "index";
    }

    /* Product Manager button - If customer is NOT employee or admin deactivate button */
    public String checkRoleForProductManager() {
        if (loggedInCustomer != null && loggedInCustomer.getRoleId() != 3) {
            return "ProductManager/ProductManager?faces-redirect=true";
        }

        return null;
    }

    // If user is admin can add Category
    public String checkRoleForAddCategory() {
        if (loggedInCustomer.getRoleId() != 1) {
            return "ProductManager?faces-redirect=true";
        }

        return "AddCategory?faces-redirect=true";
    }

    // If user is admin can add Category
    public String checkRoleForAddSubCategory() {
        if (loggedInCustomer.getRoleId() != 1) {
            return "ProductManager?faces-redirect=true";
        }

        return "AddSubCategory?faces-redirect=true";
    }

    // if customer is logged in deactivate "Create Account" button
    public String isLoggedCreateAccountButton() {
        if (loggedInCustomer != null) {
            return null;
        }

        return "CreateAccount";
    }

    // if customer logged in deactivate "Sign In" button
    public String isLoggedSignInButton() {
        if (loggedInCustomer != null) {
            return null;
        }

        return "SignIn";
    }

    // Check if Username and Tax ID already exist in any Customer Account
    public boolean checkUsernameAndTaxId(Customer customer) {
        customerList.clear();
        loadCustomerList();

        boolean userNameExists = customerList.stream().filter(c -> c.getUsername().equals(customer.getUsername())).findFirst().isPresent();
        boolean taxIdExists = customerList.stream().filter(c -> c.getTaxId().equals(customer.getTaxId())).findFirst().isPresent();

        try {
            if (userNameExists || taxIdExists) {
                throw new Exception("Username / Tax Id already exists");
            }           

        } catch (Exception e) {
            addErrorMessage(e);
            return true;
        }
        return false;
    }

//    /* Create Account button */
    public String createAccount(Customer customer) {
        
        try {
            ut.begin();
            // check if Username or Tax Id already exist in DB
            if (checkUsernameAndTaxId(customer))
                ut.rollback();
            
            // create customer account
            customerDao.create(customer);
            // If customer is employee or admin create new Employee record
            if (customer.getRoleId() != 3) {
                employeeDao.create(new Employee(customer.getCustomerId()));
            }
            ut.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating account", e.getMessage());
            addErrorMessage(e);
            return null;
        }
        return "SignIn?faces-redirect=true";
    }
   

    // Update Account button if Username or Tax Id not found in DB
    public String updateAccount(Customer customer) {
        try {

            customerDao.update(customer);
            loggedInCustomer = customerDao.findById(customer.getCustomerId());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating customer: " + customer, e);
            addErrorMessage(e);
            return null;
        }

        return "UpdateAccount?faces-redirect=true";
    }

    /* Check if Login details are correct, return Customer as LoggedInCustomer 
        and redirect to Sign In page                           */
    public String signIn(String username, String password) {
        customerList.clear();
        loadCustomerList();

        for (Customer c : customerList) {
            if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
                loggedInCustomer = customerDao.findById(c.getCustomerId());
                return "index";
            }
        }
        return "SignIn?faces-redirect=true";
    }

    /* If Customer Logged in - Account button will redirect to Update Account Page */
    public String loadCustomer(int customerId) {

        try {
            // find customer in DB
            Customer tempCustomer = customerDao.findById(customerId);

            // put in the request attribute ... so we can use it on the form page
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> requestMap = externalContext.getRequestMap();
            requestMap.put("customer", tempCustomer);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading customer id: " + customerId, e);
            addErrorMessage(e);
            return null;
        }
        return "UpdateAccount";
    }

    // error message to JSF page
    @Override
    public void addErrorMessage(Exception e) {
        FacesMessage message = new FacesMessage("Error: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public Customer getLoggedInCustomer() {
        return loggedInCustomer;
    }

    public void setLoggedInCustomer(Customer loggedInCustomer) {
        this.loggedInCustomer = loggedInCustomer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

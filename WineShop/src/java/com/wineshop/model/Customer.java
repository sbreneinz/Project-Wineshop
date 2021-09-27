package com.wineshop.model;

import java.io.Serializable;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "customer")
@XmlRootElement
@ManagedBean
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByLogin", query = "SELECT c FROM Customer c WHERE c.username = :username AND c.password = :password"),
    @NamedQuery(name = "Customer.findByFirstName", query = "SELECT c FROM Customer c WHERE c.firstName = :firstName"),
    @NamedQuery(name = "Customer.findByLastName", query = "SELECT c FROM Customer c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "Customer.findByDateOfBirth", query = "SELECT c FROM Customer c WHERE c.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Customer.findByPostalCode", query = "SELECT c FROM Customer c WHERE c.postalCode = :postalCode"),
    @NamedQuery(name = "Customer.findByRoleId", query = "SELECT c FROM Customer c WHERE c.roleId = :roleId")})
public class Customer implements Serializable {

    @Version
    @NotNull
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Size(min=1, max=45)
    @Column(name="username")
    private String username;
    
    @Size(min=1, max=45)
    @Column(name="password")
    private String password;
    
    
    @Size(min = 1, max = 45)
    @Column(name = "first_name")
    private String firstName;
    

    @Size(min = 1, max = 45)
    @Column(name = "last_name")
    private String lastName;
    

    @Size(min = 1, max = 9)
    @Column(name = "tax_id")
    private String taxId;
    
    
    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Size(min = 1, max = 45)
    @Column(name = "address")
    private String address;
    
 
    @Size(min = 1, max = 45)
    @Column(name = "postal_code")
    private String postalCode;
    
    @Column(name = "role_id")
    private Integer roleId;

    
    public Customer() {}

    public Customer(Integer customerId, String username, String password, String firstName, String lastName, String taxId, String dateOfBirth, String address, String postalCode, Integer roleId) {
        this.customerId = customerId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.taxId = taxId;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.postalCode = postalCode;
        this.roleId = roleId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.taxId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.customerId, other.customerId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", taxId=" + taxId + ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", postalCode=" + postalCode + ", roleId=" + roleId + '}';
    }

    


    

}

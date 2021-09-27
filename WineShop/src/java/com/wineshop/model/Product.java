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
@Table(name = "PRODUCT")
@XmlRootElement
@ManagedBean
@NamedQueries({
    @NamedQuery(name="Product.findAll", query="SELECT p FROM Product p"),
    @NamedQuery(name="Product.findById", query="SELECT p FROM Product p WHERE p.productId = :id"),
    @NamedQuery(name="Product.findByName", query="SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name="Product.findByDescription", query="SELECT p FROM Product p WHERE p.description = :description"),
    @NamedQuery(name="Product.findByPrice", query="SELECT p FROM Product p WHERE p.price = :price"),
    @NamedQuery(name="Product.findByPriceSmallerThan", query="SELECT p FROM Product p WHERE p.price < :price"),
    @NamedQuery(name="Product.findByPriceBiggerThan", query="SELECT p FROM Product p WHERE p.price > :price"),
    @NamedQuery(name="Product.findByType", query="SELECT p FROM Product p WHERE p.type = :type"),
    @NamedQuery(name="Product.findByYear", query="SELECT p FROM Product p WHERE p.year = :year"),
    @NamedQuery(name="Product.findByCountry", query="SELECT p FROM Product p WHERE p.country = :country"),
    @NamedQuery(name="Product.findByRegion", query="SELECT p FROM Product p WHERE p.region = :region"),
    @NamedQuery(name="Product.findByProducer", query="SELECT p FROM Product p WHERE p.producer = :producer")})
public class Product implements Serializable {

    @Version
    @NotNull
    public static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min=1, max=45)
    @Column(name = "prod_name")
    private String name;
    
    @Basic(optional = false)
    @NotNull
    @Size(min=1, max=3000)
    @Column(name = "description")
    private String description;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    
    @Basic(optional = false)
    @NotNull
    @Size(min=1, max=45)
    @Column(name = "prod_type")
    private String type;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "prod_year")
    private int year;
    
    @Basic(optional = false)
    @NotNull
    @Size(min=1, max=45)
    @Column(name = "country")
    private String country;
    
    @Basic(optional = false)
    @NotNull
    @Size(min=1, max=45)
    @Column(name = "region")
    private String region;
    
    @Basic(optional = false)
    @NotNull
    @Size(min=1, max=45)
    @Column(name = "producer")
    private String producer;

    public Product() {}

    public Product(String name, String description, Double price, String type, Integer year, String country, String region, String producer) {
        
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.year = year;
        this.country = country;
        this.region = region;
        this.producer = producer;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.productId);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.region, other.region)) {
            return false;
        }
        if (!Objects.equals(this.producer, other.producer)) {
            return false;
        }
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", name=" + name + ", description=" + description + ", price=" + price + ", type=" + type + ", year=" + year + ", country=" + country + ", region=" + region + ", producer=" + producer + '}';
    
    
    }
//    @Override
//    public String toString() {
//        String s = String.format("Product id: %s\n"
//                                + "Name: %s\n"
//                                + "Description: %s\n"
//                                + "Price: $%.2f", productId, name, description, price);
//        return s;
//    }

    }

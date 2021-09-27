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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "invoice_line")
@XmlRootElement
@ManagedBean
@NamedQueries({
    @NamedQuery(name = "InvoiceLine.findAll", query = "SELECT i FROM InvoiceLine i")
    ,
    @NamedQuery(name = "InvoiceLine.findByInvoiceId", query = "SELECT i FROM InvoiceLine i WHERE i.invoiceId = :invoiceId")
    ,
    @NamedQuery(name = "InvoiceLine.findByProductId", query = "SELECT i FROM InvoiceLine i WHERE i.productId = :productId")
    ,
    @NamedQuery(name = "InvoiceLine.findByProductPrice", query = "SELECT i FROM InvoiceLine i WHERE i.productPrice = :productPrice")})
public class InvoiceLine implements Serializable {

    @Version
    @NotNull
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_line_id")
    private Integer invoiceLineId;

    @Column(name = "invoice_id")
    private Integer invoiceId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_price")
    private Double productPrice;

    @Column(name = "product_quantity")
    private Integer productQuantity;

    public InvoiceLine() {}

    public InvoiceLine(Integer invoiceId, Integer productId, Double productPrice, Integer productQuantity) {

        this.invoiceId = invoiceId;
        this.productId = productId;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public Integer getInvoiceLineId() {
        return invoiceLineId;
    }

    public void setInvoiceLineId(Integer invoiceLineId) {
        this.invoiceLineId = invoiceLineId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.invoiceLineId);
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
        final InvoiceLine other = (InvoiceLine) obj;
        if (!Objects.equals(this.invoiceLineId, other.invoiceLineId)) {
            return false;
        }
        if (!Objects.equals(this.invoiceId, other.invoiceId)) {
            return false;
        }
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        if (!Objects.equals(this.productPrice, other.productPrice)) {
            return false;
        }
        if (!Objects.equals(this.productQuantity, other.productQuantity)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InvoiceLine{" + "invoiceLineId=" + invoiceLineId + ", invoiceId=" + invoiceId + ", productId=" + productId + ", productPrice=" + productPrice + ", productQuantity=" + productQuantity + '}';
    }

}

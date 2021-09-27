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
@Table(name = "invoice")
@XmlRootElement
@ManagedBean
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findByInvoiceId", query = "SELECT i FROM Invoice i WHERE i.invoiceId = :invoiceId"),
    @NamedQuery(name = "Invoice.findByOperationDate", query = "SELECT i FROM Invoice i WHERE i.operationDate = :operationDate"),
    @NamedQuery(name = "Invoice.findByTotalMoreThan", query = "SELECT i FROM Invoice i WHERE i.totalSale > :totalSale"),
    @NamedQuery(name = "Invoice.findByTotalLessThan", query = "SELECT i FROM Invoice i WHERE i.totalSale < :totalSale")})
public class Invoice implements Serializable {

    @Version
    @NotNull
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer invoiceId;

    @Column(name = "operation_date")
    private String operationDate;

    @Column(name = "total_sale")
    private Double totalSale;

    @Column(name = "employee_id")
    private Integer employeeId;

    public Invoice() {}

    public Invoice(String operationDate, Double totalSale, Integer employeeId) {
        
        this.operationDate = operationDate;
        this.totalSale = totalSale;
        this.employeeId = employeeId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    public Double getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(Double totalSale) {
        this.totalSale = totalSale;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.operationDate);
        hash = 23 * hash + Objects.hashCode(this.totalSale);
        hash = 23 * hash + Objects.hashCode(this.employeeId);
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
        final Invoice other = (Invoice) obj;
        if (!Objects.equals(this.invoiceId, other.invoiceId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Invoice{" + "invoiceId=" + invoiceId + ", operationDate=" + operationDate + ", totalSale=" + totalSale + ", employeeId=" + employeeId + '}';
    }

}

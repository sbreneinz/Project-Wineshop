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
@Table(name = "sub_category")
@XmlRootElement
@ManagedBean
@NamedQueries({
    @NamedQuery(name = "SubCategory.findAll", query = "SELECT s FROM SubCategory s")
    ,
    @NamedQuery(name = "SubCategory.findByCategoryId", query = "SELECT s FROM SubCategory s WHERE s.categoryId = :categoryId")
    ,
    @NamedQuery(name = "SubCategory.findByDescription", query = "SELECT s FROM SubCategory s WHERE s.description = :description")})
public class SubCategory implements Serializable {

    @Version
    @NotNull
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_category_id")
    private Integer subCategoryId;

    @Column(name = "category_id")
    private int categoryId;

    @Size(min = 1, max = 45)
    @Column(name = "description")
    private String description;

    public SubCategory() {
    }

    public SubCategory(Integer categoryId, String description) {
        this.categoryId = categoryId;
        this.description = description;

    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.subCategoryId);
        hash = 61 * hash + Objects.hashCode(this.description);
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
        final SubCategory other = (SubCategory) obj;
        if (!Objects.equals(this.subCategoryId, other.subCategoryId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SubCategory{" + "subCategoryId=" + subCategoryId + ", categoryId=" + categoryId + ", description=" + description + '}';
    }
}

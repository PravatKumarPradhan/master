package com.sdgt.medcare.master.entity;

import com.core.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.NonEditableByUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Pravat Kumar Pradhan
 * @author Karthik Chandra
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractBaseEntity implements BaseEntity {
    @NonEditableByUser
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name="seq_gen", sequenceName = "seq_", allocationSize=1, initialValue = 2000)
    @MastersFieldCustomAnnotation(visibleToUser = false,editableByUser = false)
    protected Long id;

    @MastersFieldCustomAnnotation(displayName = "Active",sequence = 222,nullable = false)
    @NotNull
    protected Boolean active = true;

    @MastersFieldCustomAnnotation(sequence = 223,editableByUser = false,visibleToUser = false)
    @Column(name = "created_by")
    protected String createdBy;

    @MastersFieldCustomAnnotation(sequence = 224,editableByUser = false,visibleToUser = false)
    @Column(name = "updated_by")
    protected String updatedBy;

    @MastersFieldCustomAnnotation(sequence = 225,editableByUser = false,visibleToUser = false)
    @Column(name = "created_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    @MastersFieldCustomAnnotation(sequence = 226,editableByUser = false,visibleToUser = false)
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedDate;



    @PrePersist
    void createdAt() {
        this.createdDate = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedDate = new Date();
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    @Override
    public void setId(String s) {
        this.id = Long.valueOf(s);
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

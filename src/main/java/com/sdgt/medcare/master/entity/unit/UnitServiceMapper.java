package com.sdgt.medcare.master.entity.unit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.VisitTypeMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.ServiceCategoryMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.TaxMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "unit_service_mapper", schema = "service")
public class UnitServiceMapper extends BaseMaster {
    @Column(name = "is_outSourced")
    private Boolean isOutSourced;

    @Column(name = "is_package", nullable = false)
    private Boolean isPackage = false;

    @Column(name = "is_procedure")
    private Boolean isProcedure;

    @Column(name = "is_rate_editable")
    private Boolean isRateEditable;

    @Column(name = "is_facility")
    private Boolean isFacility;

    @Column(name = "min_rate", precision = 8, scale = 2)
    private Double minRate;

    @Column(name = "max_rate", precision = 8, scale = 2)
    private Double maxRate;

    @Column(name = "is_doctor_required")
    private Boolean isDoctorRequired;

    @Column(name = "is_discount_max")
    private Boolean isDiscountMax;

    @Column(name = "min_discount", precision = 8, scale = 2)
    private Double minDiscount;
    @Column(name = "max_discount", precision = 8, scale = 2)
    private Double maxDiscount;
    @Column(name = "is_tax_applicable")
    private Boolean isTaxApplicable;

    @Column(name="is_discount_req")
    private Boolean isDiscountReq;

    @ManyToOne
    @JoinColumn(name="tax_master_id")
    protected TaxMaster  gstTax;

   @ManyToOne
    @JoinColumn(name="tax_master")
    protected TaxMaster otcTax;

    @Column(name = "tax_type_id")
    private Long taxTypeId;

    @Column(name = "otc_tax_type_id")
    private Long otcTaxTypeId;

    @Column(name = "is_allow_multiple_quantity")
    private Boolean isAllowMultipleQuantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    private OrganizationMaster organizationMaster;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_master_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id")
    private ServiceMaster serviceMaster;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "service_category_id")
    private ServiceCategoryMaster serviceCategoryMaster;

    @Column(name = "effective_start_date")
    private Date effectiveStartDate;

    @Column(name = "effective_end_date")
    private Date effectiveEndDate;

    @Column(name="is_auto_render")
    protected Boolean isAutoRender;

    @Column(name="is_poc")
    protected  Boolean isPoc;

    @Column(name="is_panel")
    protected  Boolean isPanel;

    @Column(name="is_periodicity_service")
    protected   Boolean isPeriodicityService;

    @Column(name="is_appointment_service")
    protected Boolean isAppointmentService;


    @ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL )
    @JoinTable (name = "visit_unit_service_mapper", joinColumns = @JoinColumn(name = "unit_service_mapper_id"), inverseJoinColumns = @JoinColumn(name = "visit_type_master_id"))
    private List<VisitTypeMaster> visitTypeMasterList;



    @ManyToMany
    @JoinColumn
    @JsonIgnoreProperties({"parentUnitServiceMapper"})
    private  List<DependentUnitServiceMapper>    dependentUnitServiceMapper;

    public Date getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(Date effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public Date getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(Date effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }


    public List<VisitTypeMaster> getVisitTypeMasterList() {
        return visitTypeMasterList;
    }
    public void setVisitTypeMasterList(List<VisitTypeMaster> visitTypeMasterList) {
        this.visitTypeMasterList = visitTypeMasterList;
    }


}

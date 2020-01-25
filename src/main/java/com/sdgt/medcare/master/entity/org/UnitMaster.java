package com.sdgt.medcare.master.entity.org;


import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.CityMaster;
import com.sdgt.medcare.master.entity.global.CountryMaster;
import com.sdgt.medcare.master.entity.global.StateMaster;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Pravat Kumar Pradhan
 */
@Data
@Entity
@Table(name = "m_unit")
@DynamicUpdate
@MastersEntityCustomAnnotation (showOnFrontEnd = true)
public class UnitMaster extends BaseMaster implements Serializable{


    @Column(name = "unit_Code", unique = true, length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String unitCode;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
    private OrganizationMaster organizationMaster;

    //  @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @MastersFieldCustomAnnotation(displayName = "Country",sequence = 4)
    private CountryMaster countryMaster;
    //@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    @MastersFieldCustomAnnotation(displayName = "State",sequence = 5)
    private StateMaster stateMaster;

    // @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityMaster_id")
    @MastersFieldCustomAnnotation(displayName = "City",sequence = 6)
    private CityMaster cityMaster;

    @Column(name = "pin_code", length = 10)
    @MastersFieldCustomAnnotation(displayName = "Pincode",sequence = 7)
    private Integer pinCode;

    @Column(name = "fax_no")
    @MastersFieldCustomAnnotation(displayName = "Fax",sequence = 8)
    private Integer faxNo;

    @Column(name = "contact_no", length = 20)
    @MastersFieldCustomAnnotation(displayName = "Contact No",sequence = 9)
    private String contactNo;

    @Column(name = "email_id")
    @MastersFieldCustomAnnotation(displayName = "Email Id",sequence = 10)
    private String emailId;

   /* public Set<UnitDepartmentMapper> getUnitDepartmentMappers() {
        return unitDepartmentMappers;
    }*/

    /*public void setUnitDepartmentMappers(Set<UnitDepartmentMapper> unitDepartmentMappers) {
        this.unitDepartmentMappers = unitDepartmentMappers;
    }*/

    /*@Column(name="unit_logo")
    @MastersFieldCustomAnnotation(displayName = "Logo",sequence = 11)
    private Blob unitLogo;*/
    /*@JsonProperty
    @OneToMany(mappedBy = "unitMaster")
    Set<UnitDepartmentMapper> unitDepartmentMappers;*/

}

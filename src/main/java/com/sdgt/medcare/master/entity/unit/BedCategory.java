package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.FinancialClassMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Table(name="m_bed_category",schema = "adt")
@Entity
public class BedCategory  extends BaseMaster {

    @Column(name="bed_category_code",unique = true,length = 50)
    private String bedCategoryCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="financial_class_id")
    private FinancialClassMaster financialClassMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="occupancy_unit_id")
    private OccupancyUnitMaster occupancyUnitMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="room_service_id")
    private UnitServiceMapper unitServiceMapper;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="meal_service_id")
    private UnitServiceMapper unitServiceMapperForMeal;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="nursing_service_id")
    private UnitServiceMapper unitServiceMapperForNursing;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="lodger_service_id")
    private LodgerServiceMaster lodgerServiceMaster;

    @OneToMany(mappedBy = "bedCategory", fetch = FetchType.LAZY)
    private List<BedMaster> bedMaster = new ArrayList<>();

    public String getBedCategoryCode() {
        return bedCategoryCode;
    }

    public void setBedCategoryCode(String bedCategoryCode) {
        this.bedCategoryCode = bedCategoryCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public BedCategory() {
    }

    public UnitMaster getUnitMaster() {
        return unitMaster;
    }

    public void setUnitMaster(UnitMaster unitMaster) {
        this.unitMaster = unitMaster;
    }
    public BedCategory(String bedCategoryCode, OrganizationMaster organizationMaster, UnitMaster unitMaster, FinancialClassMaster financialClassMaster, OccupancyUnitMaster occupancyUnitMaster, UnitServiceMapper unitServiceMapper, UnitServiceMapper unitServiceMapperForMeal, LodgerServiceMaster lodgerServiceMaster,UnitServiceMapper unitServiceMapperForNursing) {
        this.bedCategoryCode = bedCategoryCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
        this.financialClassMaster = financialClassMaster;
        this.occupancyUnitMaster = occupancyUnitMaster;
        this.unitServiceMapper = unitServiceMapper;
        this.unitServiceMapperForMeal = unitServiceMapperForMeal;
        this.lodgerServiceMaster = lodgerServiceMaster;
        this.unitServiceMapperForNursing = unitServiceMapperForNursing;
    }

    public FinancialClassMaster getFinancialClassMaster() {
        return financialClassMaster;
    }

    public void setFinancialClassMaster(FinancialClassMaster financialClassMaster) {
        this.financialClassMaster = financialClassMaster;
    }

    public OccupancyUnitMaster getOccupancyUnitMaster() {
        return occupancyUnitMaster;
    }

    public void setOccupancyUnitMaster(OccupancyUnitMaster occupancyUnitMaster) {
        this.occupancyUnitMaster = occupancyUnitMaster;
    }

    public UnitServiceMapper getRoomServiceMaster() {
        return unitServiceMapper;
    }

    public void setRoomServiceMaster(RoomServiceMaster roomServiceMaster) {
        this.unitServiceMapper = unitServiceMapper;
    }

    public UnitServiceMapper getMealServiceMaster() {
        return unitServiceMapperForMeal;
    }

    public void setMealServiceMaster(UnitServiceMapper unitServiceMapperForMeal) {
        this.unitServiceMapperForMeal = unitServiceMapperForMeal;
    }

    public LodgerServiceMaster getLodgerServiceMaster() {
        return lodgerServiceMaster;
    }

    public void setLodgerServiceMaster(LodgerServiceMaster lodgerServiceMaster) {
        this.lodgerServiceMaster = lodgerServiceMaster;
    }

    public UnitServiceMapper getUnitServiceMapperForNursing() {
        return unitServiceMapperForNursing;
    }

    public void setUnitServiceMapperForNursing(UnitServiceMapper unitServiceMapperForNursing) {
        this.unitServiceMapperForNursing = unitServiceMapperForNursing;
    }

}
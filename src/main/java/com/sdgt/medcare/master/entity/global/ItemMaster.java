package com.sdgt.medcare.master.entity.global;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.billing.OrderSetDetails;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.unit.ItemUnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitOfMeasurementMaster;
import com.sdgt.medcare.master.model.Uom;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "m_item", schema = "inventory")
@SequenceGenerator(name = "item_seq", sequenceName = "inventory.item_seq", allocationSize = 1)
@DynamicUpdate
public class ItemMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_master_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analysis_type_id", nullable = true)
    private AnalysisTypeMaster analysisTypeMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_category_id", nullable = true)
    private ItemCategoryMaster itemCategoryMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_type_id", nullable = true)
    private ItemTypeMaster itemTypeMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_group_id", nullable = true)
    private ItemGroupMaster itemGroupMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generic_id", nullable = true)
    private GenericMaster genericMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formulation_type_id", nullable = true)
    private FormulationTypeMaster formulationTypeMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RouteOfAdministrationMaster_id", nullable = true)
    private RouteOfAdministrationMaster routeOfAdministrationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmalogy_classification_id", nullable = true)
    private PharmacologyClassificationMaster pharmacologyClassificationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dosage_unit_id", nullable = true)
    private StrengthUnitMaster strengthUnitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_tax_id", nullable = true)
    private TaxMaster purchaseTaxMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_tax_id", nullable = true)
    private TaxMaster saleTaxMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "markup_id", nullable = true)
    private MarkupMaster markupMaster = new MarkupMaster(1L);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_unit_id", nullable = true)
    private StorageUnitMaster storageUnitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usage_type_id", nullable = true)
    private UsageType usageType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pregnancy_id", nullable = true)
    private PregnancyClassMaster pregnancyClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumption_type_master", nullable = true)
    private ConsumptionTypeMaster consumptionTypeMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linen_type", nullable = true)
    private LinenTypeMaster linenTypeMaster;

    @Column(name = "sale_price", length = 50)
    private Double salePrice = 1.0;

    @Column(name = "product_code", length = 50)
    private String productCode;

    @Column(name = "strength", length = 50)
    private Double strength;

    @Column(name = "cost_price", length = 50)
    private Double costPrice = 1.0;

    @Column(name = "no_time_reuse", length = 50)
    private Integer noTimeReuse;

    @Column(name = "dosage")
    private Double dosage;

    @Column(name = "mims_my")
    private String mimsMy;

    @Column(name = "mims_type")
    private String mimsType;

    @Column(name = "guid")
    private String guid;

    @Column(name = "is_drug", nullable = false)
    private Boolean drug;

    @Column(name = "is_drug_recall")
    private Boolean drugRecall=false;

    @Column(name = "is_non_stock", nullable = false)
    private Boolean nonStock = false;

    @Column(name = "is_consumable", nullable = false)
    private Boolean consumable = false;

    @Column(name = "is_high_risk", nullable = false)
    private Boolean highRisk = false;

    @Column(name = "is_narcotics", nullable = false)
    private Boolean narcotics = false;

    @Column(name = "is_vaccine", nullable = false)
    private Boolean vaccine = false;

    @Column(name = "is_otc", nullable = false)
    private Boolean otc = false;

    @Column(name = "is_psychotropic", nullable = false)
    private Boolean psychotropic = false;

    @Column(name = "is_infusion", nullable = false)
    private Boolean infusion = false;

    @Column(name = "is_batch_required", nullable = false)
    private Boolean batchRequired = false;

    @Column(name = "is_consignment", nullable = false)
    private Boolean consignment = false;

    @Column(name = "is_replenishment", nullable = false)
    private Boolean replenishment = false;

    @Column(name = "is_pharmacy", nullable = false)
    private Boolean pharmacy = false;

    @Column(name = "is_indent", nullable = false)
    private Boolean indent = false;

    @Column(name = "is_fixed_price", nullable = false)
    private Boolean fixedPrice;

    @Column(name = "is_reusable", nullable = false)
    private Boolean reusable;

    @Column(name = "is_kit", nullable = false)
    private Boolean kit = false;

    @Column(name = "manufactured_by")
    private String manufacturedBy;

    @Column(name = "marketed_by")
    private String marketedBy;

    @Column(name = "suggestions")
    private String suggestions;

    @Column(name = "prequation")
    private String prequation;

    @Column(name = "is_cssd")
    private Boolean cssd = false;

    @Transient
    private List<Uom> uom;

    public ItemMaster() {
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public AnalysisTypeMaster getAnalysisTypeMaster() {
        return analysisTypeMaster;
    }

    public void setAnalysisTypeMaster(AnalysisTypeMaster analysisTypeMaster) {
        this.analysisTypeMaster = analysisTypeMaster;
    }

    public ItemCategoryMaster getItemCategoryMaster() {
        return itemCategoryMaster;
    }

    public void setItemCategoryMaster(ItemCategoryMaster itemCategoryMaster) {
        this.itemCategoryMaster = itemCategoryMaster;
    }

    public Boolean getDrugRecall() {
        return drugRecall;
    }

    public void setDrugRecall(Boolean drugRecall) {
        this.drugRecall = drugRecall;
    }

    public GenericMaster getGenericMaster() {
        return genericMaster;
    }

    public void setGenericMaster(GenericMaster genericMaster) {
        this.genericMaster = genericMaster;
    }

    public FormulationTypeMaster getFormulationTypeMaster() {
        return formulationTypeMaster;
    }

    public void setFormulationTypeMaster(FormulationTypeMaster formulationTypeMaster) {
        this.formulationTypeMaster = formulationTypeMaster;
    }

    public RouteOfAdministrationMaster getRouteOfAdministrationMaster() {
        return routeOfAdministrationMaster;
    }

    public void setRouteOfAdministrationMaster(RouteOfAdministrationMaster routeOfAdministrationMaster) {
        this.routeOfAdministrationMaster = routeOfAdministrationMaster;
    }

    public PharmacologyClassificationMaster getPharmacologyClassificationMaster() {
        return pharmacologyClassificationMaster;
    }

    public void setPharmacologyClassificationMaster(PharmacologyClassificationMaster pharmacologyClassificationMaster) {
        this.pharmacologyClassificationMaster = pharmacologyClassificationMaster;
    }

    public StrengthUnitMaster getStrengthUnitMaster() {
        return strengthUnitMaster;
    }

    public void setStrengthUnitMaster(StrengthUnitMaster strengthUnitMaster) {
        this.strengthUnitMaster = strengthUnitMaster;
    }

    public TaxMaster getPurchaseTaxMaster() {
        return purchaseTaxMaster;
    }

    public void setPurchaseTaxMaster(TaxMaster purchaseTaxMaster) {
        this.purchaseTaxMaster = purchaseTaxMaster;
    }

    public TaxMaster getSaleTaxMaster() {
        return saleTaxMaster;
    }

    public void setSaleTaxMaster(TaxMaster saleTaxMaster) {
        this.saleTaxMaster = saleTaxMaster;
    }

    public MarkupMaster getMarkupMaster() {
        return markupMaster;
    }

    public void setMarkupMaster(MarkupMaster markupMaster) {
        this.markupMaster = markupMaster;
    }

    public StorageUnitMaster getStorageUnitMaster() {
        return storageUnitMaster;
    }

    public void setStorageUnitMaster(StorageUnitMaster storageUnitMaster) {
        this.storageUnitMaster = storageUnitMaster;
    }

    public UsageType getUsageType() {
        return usageType;
    }

    public void setUsageType(UsageType usageType) {
        this.usageType = usageType;
    }

    public ConsumptionTypeMaster getConsumptionTypeMaster() {
        return consumptionTypeMaster;
    }

    public void setConsumptionTypeMaster(ConsumptionTypeMaster consumptionTypeMaster) {
        this.consumptionTypeMaster = consumptionTypeMaster;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Double getDosage() {
        return dosage;
    }

    public void setDosage(Double dosage) {
        this.dosage = dosage;
    }

    public String getMimsMy() {
        return mimsMy;
    }

    public void setMimsMy(String mimsMy) {
        this.mimsMy = mimsMy;
    }

    public String getMimsType() {
        return mimsType;
    }

    public void setMimsType(String mimsType) {
        this.mimsType = mimsType;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean getDrug() {
        return drug;
    }

    public void setDrug(Boolean drug) {
        this.drug = drug;
    }

    public Boolean getNonStock() {
        return nonStock;
    }

    public void setNonStock(Boolean nonStock) {
        this.nonStock = nonStock;
    }

    public Boolean getConsumable() {
        return consumable;
    }

    public void setConsumable(Boolean consumable) {
        this.consumable = consumable;
    }

    public Boolean getHighRisk() {
        return highRisk;
    }

    public void setHighRisk(Boolean highRisk) {
        this.highRisk = highRisk;
    }

    public Boolean getNarcotics() {
        return narcotics;
    }

    public void setNarcotics(Boolean narcotics) {
        this.narcotics = narcotics;
    }

    public Boolean getVaccine() {
        return vaccine;
    }

    public void setVaccine(Boolean vaccine) {
        this.vaccine = vaccine;
    }

    public Boolean getOtc() {
        return otc;
    }

    public void setOtc(Boolean otc) {
        this.otc = otc;
    }

    public Boolean getPsychotropic() {
        return psychotropic;
    }

    public void setPsychotropic(Boolean psychotropic) {
        this.psychotropic = psychotropic;
    }

    public Boolean getInfusion() {
        return infusion;
    }

    public void setInfusion(Boolean infusion) {
        this.infusion = infusion;
    }

    public Boolean getBatchRequired() {
        return batchRequired;
    }

    public void setBatchRequired(Boolean batchRequired) {
        this.batchRequired = batchRequired;
    }

    public Boolean getConsignment() {
        return consignment;
    }

    public void setConsignment(Boolean consignment) {
        this.consignment = consignment;
    }

    public Boolean getReplenishment() {
        return replenishment;
    }

    public void setReplenishment(Boolean replenishment) {
        this.replenishment = replenishment;
    }

    public Boolean getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Boolean pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Boolean getIndent() {
        return indent;
    }

    public void setIndent(Boolean indent) {
        this.indent = indent;
    }

    public ItemTypeMaster getItemTypeMaster() {
        return itemTypeMaster;
    }

    public void setItemTypeMaster(ItemTypeMaster itemTypeMaster) {
        this.itemTypeMaster = itemTypeMaster;
    }

    public ItemGroupMaster getItemGroupMaster() {
        return itemGroupMaster;
    }

    public void setItemGroupMaster(ItemGroupMaster itemGroupMaster) {
        this.itemGroupMaster = itemGroupMaster;
    }

    public PregnancyClassMaster getPregnancyClass() {
        return pregnancyClass;
    }

    public void setPregnancyClass(PregnancyClassMaster pregnancyClass) {
        this.pregnancyClass = pregnancyClass;
    }

    public Double getStrength() {
        return strength;
    }

    public void setStrength(Double strength) {
        this.strength = strength;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getNoTimeReuse() {
        return noTimeReuse;
    }

    public void setNoTimeReuse(Integer noTimeReuse) {
        this.noTimeReuse = noTimeReuse;
    }

    public Boolean getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(Boolean fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public Boolean getReusable() {
        return reusable;
    }

    public void setReusable(Boolean reusable) {
        this.reusable = reusable;
    }

    public Boolean getKit() {
        return kit;
    }

    public void setKit(Boolean kit) {
        this.kit = kit;
    }

    public String getManufacturedBy() {
        return manufacturedBy;
    }

    public void setManufacturedBy(String manufacturedBy) {
        this.manufacturedBy = manufacturedBy;
    }

    public String getMarketedBy() {
        return marketedBy;
    }

    public void setMarketedBy(String marketedBy) {
        this.marketedBy = marketedBy;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public String getPrequation() {
        return prequation;
    }

    public void setPrequation(String prequation) {
        this.prequation = prequation;
    }

    public List<Uom> getUom() {
        return uom;
    }

    public void setUom(List<Uom> uom) {
        this.uom = uom;
    }

    public LinenTypeMaster getLinenTypeMaster() {
        return linenTypeMaster;
    }

    public void setLinenTypeMaster(LinenTypeMaster linenTypeMaster) {
        this.linenTypeMaster = linenTypeMaster;
    }

    public Boolean getCssd() {
        return cssd;
    }

    public void setCssd(Boolean cssd) {
        this.cssd = cssd;
    }
}

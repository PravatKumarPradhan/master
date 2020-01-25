package com.sdgt.medcare.master.translator.billing;

import com.sdgt.medcare.master.dto.billing.ContractDTO;
import com.sdgt.medcare.master.dto.billing.common.CommonDiscountContractConfigurationDTO;
import com.sdgt.medcare.master.dto.billing.common.CommonDiscountContractDiscountConfigurationDTO;
import com.sdgt.medcare.master.dto.billing.common.CommonDiscountContractExclusionConfigurationDTO;
import com.sdgt.medcare.master.dto.billing.common.CommonUnitContractDiscountMapperDTO;
import com.sdgt.medcare.master.dto.billing.common.CommonVisitTypeConfigurationDTO;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.billing.ChargeCategoryMaster;
import com.sdgt.medcare.master.entity.billing.contract.ContractConfiguration;
import com.sdgt.medcare.master.entity.billing.contract.ContractDiscountDetailsConfiguration;
import com.sdgt.medcare.master.entity.billing.contract.ContractExclusionConfiguration;
import com.sdgt.medcare.master.entity.billing.contract.ContractMaster;
import com.sdgt.medcare.master.entity.billing.contract.ContractVisitConfiguration;
import com.sdgt.medcare.master.entity.billing.contract.UnitContractMapper;
import com.sdgt.medcare.master.entity.org.CompanyMaster;
import com.sdgt.medcare.master.entity.org.CompanyTypeMaster;
import com.sdgt.medcare.master.entity.org.GradeMaster;
import com.sdgt.medcare.master.entity.unit.EmployeeMasterDetails;
import com.sdgt.medcare.master.repository.billing.GradeMasterRepository;
import com.sdgt.medcare.master.repository.common.ChargeCategoryMasterRepository;
import com.sdgt.medcare.master.repository.common.CompanyMasterRepository;
import com.sdgt.medcare.master.repository.common.CompanyTypeMasterRepository;
import com.sdgt.medcare.master.repository.common.OrganizationMasterRepository;
import com.sdgt.medcare.master.repository.common.UnitMasterRepository;
import com.sdgt.medcare.master.repository.common.VisitTypeMasterRepository;
import com.sdgt.medcare.master.service.common.EmployeeMasterService;
import com.sdgt.medcare.master.service.common.TariffMasterService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Component
public class ContractDTOEntityTranslator {
    private Logger logger = LoggerFactory.getLogger(ContractDTOEntityTranslator.class);
    @Autowired
    private CompanyTypeMasterRepository companyTypeMasterRepository;

    @Autowired
    private CompanyMasterRepository companyMasterRepository;

    @Autowired
    private EmployeeMasterService employeeMasterService;

    @Autowired
    private UnitMasterRepository unitMasterRepository;

    @Autowired
    private ChargeCategoryMasterRepository chargeCategoryMasterRepository;

    @Autowired
    private TariffMasterService tariffMasterService;

    @Autowired
    private VisitTypeMasterRepository visitTypeMasterRepository;

    @Autowired
    private OrganizationMasterRepository organizationMasterRepository;

    @Autowired
    private GradeMasterRepository gradeMasterRepository;

    public ContractMaster translate(final ContractDTO contractDTO) {
        final ContractMaster contractMaster = new ContractMaster();
        BaseMaster.initialPersistenceSetup(contractMaster, contractDTO);
        contractMaster.setDate(contractDTO.getDate());
        contractMaster.setCode(contractDTO.getCode());
        contractMaster.setDesc(contractDTO.getDesc());
        contractMaster.setReferenceNumber(contractDTO.getReferenceNo());
        contractMaster.setEffectiveStartDate(contractDTO.getEffectiveStartDate());
        contractMaster.setEffectiveEndDate(contractDTO.getEffectiveEndDate());
        contractMaster.setPersistStatus(contractDTO.getPersistStatus());
        contractMaster.setOrganization(organizationMasterRepository.findByCode(contractDTO.getOrgCode()));

        final CompanyTypeMaster companyTypeMaster = companyTypeMasterRepository.findByCode(contractDTO.getCompanyTypeCode());
        contractMaster.setCompanyType(companyTypeMaster);
        final CompanyMaster companyMaster = companyMasterRepository.findByCode(contractDTO.getCompanyCode());
        contractMaster.setCompany(companyMaster);
        final EmployeeMasterDetails employeeMasterDetails = employeeMasterService.findByCode(contractDTO.getEmployeeCode());
        contractMaster.setEmployeeDetails(employeeMasterDetails);

        if (CollectionUtils.isNotEmpty(contractDTO.getApplicableUnitCodeList())) {
            final Collection<UnitContractMapper> mapperCollection = new HashSet<>(contractDTO.getApplicableUnitCodeList().size());
            for(final CommonUnitContractDiscountMapperDTO mapperDTO : contractDTO.getApplicableUnitCodeList()) {
                final UnitContractMapper mapper = new UnitContractMapper();
                mapper.setUnitMaster(unitMasterRepository.findByCode(mapperDTO.getUnitCode()));

                mapperCollection.add(mapper);
            }

            contractMaster.setUnitContractMapperList(mapperCollection);
        } else if (StringUtils.isBlank(contractDTO.getMasterId())) {
            logger.error("translate; At least one unit to be mapped when contract is created for the first time.");
            throw new IllegalStateException("At least one applicable unit mapping expected.");
        }

        final Collection<ContractConfiguration>  configurationCollection = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(contractDTO.getConfigurationList())) {
            for(final CommonDiscountContractConfigurationDTO configurationDTO : contractDTO.getConfigurationList()) {
                final ContractConfiguration configuration = new ContractConfiguration();
                BaseMaster.initialPersistenceSetup(configuration, contractDTO);
                configuration.setCoSharePercentage(configurationDTO.getCoSharePercentage());
                configuration.setFallBackTariff(tariffMasterService.findByCode(configurationDTO.getFallBackTariffCode()));
                configuration.setExcludeDiscount(configurationDTO.getExcludeDiscount());

                final GradeMaster gradeMaster = gradeMasterRepository.findByCode(configurationDTO.getGradeCode());
                if(gradeMaster == null) {
                    logger.error("grade information not found for code = {}", configurationDTO.getGradeCode());
                    throw new IllegalArgumentException("Invalid grade details. Code =" + configurationDTO.getGradeCode());
                }
                configuration.setGrade(gradeMaster);
                configuration.setDesc(gradeMaster.getDesc());

                if (!configurationDTO.getExcludeDiscount()) {
                    final Collection<ContractDiscountDetailsConfiguration> discountDetailsConfigurationCollection = new ArrayList<>();
                    for(final CommonDiscountContractDiscountConfigurationDTO discountConfigurationDTO : configurationDTO.getDiscountConfigurationDTOList()) {
                        final ContractDiscountDetailsConfiguration discountDetailsConfiguration = new ContractDiscountDetailsConfiguration();
                        BaseMaster.initialPersistenceSetup(discountDetailsConfiguration, contractDTO);

                        discountDetailsConfiguration.setChargeCode(discountConfigurationDTO.getChargeCode());
                        final ChargeCategoryMaster chargeCategoryMaster = chargeCategoryMasterRepository.findByCode(discountConfigurationDTO.getChargeCategoryCode());
                        discountDetailsConfiguration.setChargeCategory(chargeCategoryMaster);
                        discountDetailsConfiguration.setDiscountPercentage(discountConfigurationDTO.getDiscountPercentage());
                        discountDetailsConfiguration.setDiscountAmount(discountConfigurationDTO.getDiscountAmount());
                        discountDetailsConfiguration.setMaxDiscountLimitAmount(discountConfigurationDTO.getMaxDiscountLimitAmount());

                        discountDetailsConfigurationCollection.add(discountDetailsConfiguration);
                    }
                    configuration.setDiscountDetailsCollection(discountDetailsConfigurationCollection);
                } else {
                    configuration.setTotalNoOfVisit(configurationDTO.getTotalNoOfVisit());
                    configuration.setTotalVisitLimit(configurationDTO.getTotalVisitLimit());
                    configuration.setPreApprovedVisitLimit(configurationDTO.getPreApprovedVisitLimit());
                }

                if (CollectionUtils.isNotEmpty(configurationDTO.getExclusionConfigurationDTOList())) {
                    final Collection<ContractExclusionConfiguration> exclusionConfigurationCollection = new ArrayList<>();
                    for(final CommonDiscountContractExclusionConfigurationDTO exclusionConfigurationDTO: configurationDTO.getExclusionConfigurationDTOList()) {
                        final ContractExclusionConfiguration exclusionConfiguration = new ContractExclusionConfiguration();
                        exclusionConfiguration.setExclusionChargeCode(exclusionConfigurationDTO.getExclusionChargeCode());
                        exclusionConfiguration.setChargeCategoryMaster(chargeCategoryMasterRepository.findByCode(exclusionConfigurationDTO.getChargeCategoryCode()));

                        BaseMaster.initialPersistenceSetup(exclusionConfiguration, contractDTO);
                        exclusionConfigurationCollection.add(exclusionConfiguration);
                    }

                    configuration.setExclusionConfigurationCollection(exclusionConfigurationCollection);
                }

                if (CollectionUtils.isNotEmpty(configurationDTO.getVisitTypeConfigurationDTOList())) {
                    final Collection<ContractVisitConfiguration> visitConfigurationCollection = new ArrayList<>(configurationDTO.getVisitTypeConfigurationDTOList().size());
                    for(final CommonVisitTypeConfigurationDTO visitTypeConfigurationDTO : configurationDTO.getVisitTypeConfigurationDTOList()) {
                        final ContractVisitConfiguration visitConfiguration = new ContractVisitConfiguration();
                        BaseMaster.initialPersistenceSetup(visitConfiguration, contractDTO);
                        visitConfiguration.setVisitTypeMaster(visitTypeMasterRepository.findByCode(visitTypeConfigurationDTO.getVisitTypeCode()));
                        visitConfiguration.setNoOfVisit(visitTypeConfigurationDTO.getNoOfVisit());
                        visitConfiguration.setVisitLimit(visitTypeConfigurationDTO.getVisitLimit());
                        visitConfiguration.setPreApprovedVisitLimit(visitTypeConfigurationDTO.getPreApprovedVisitLimit());

                        visitConfigurationCollection.add(visitConfiguration);
                    }

                    configuration.setVisitConfigurationCollection(visitConfigurationCollection);
                }

                configuration.setContract(contractMaster);

                configurationCollection.add(configuration);
            }

            contractMaster.setContractConfigurationCollection(configurationCollection);
        }

        return contractMaster;
    }

    /**
     * Translate {@link ContractMaster} to DTO.
     * @param contractMaster given.
     * @return {@link ContractDTO} object.
     */
    public ContractDTO translateContractDTO(final ContractMaster contractMaster) {
        final ContractDTO contractDTO = new ContractDTO();
        contractDTO.setMasterId(contractMaster.getId());
        contractDTO.setCreatedBy(contractMaster.getCreatedBy());
        contractDTO.setCode(contractMaster.getCode());
        contractDTO.setDesc(contractMaster.getDesc());

        contractDTO.setCompanyTypeCode(BaseMaster.getCode(contractMaster.getCompanyType()));
        contractDTO.setCompanyTypeDesc(BaseMaster.getDesc(contractMaster.getCompanyType()));
        contractDTO.setCompanyCode(BaseMaster.getCode(contractMaster.getCompany()));
        contractDTO.setCompanyDesc(BaseMaster.getDesc(contractMaster.getCompany()));
        contractDTO.setDate(contractMaster.getDate());
        contractDTO.setEmployeeCode(BaseMaster.getCode(contractMaster.getEmployeeDetails()));
        contractDTO.setReferenceNo(contractMaster.getReferenceNumber());
        contractDTO.setEffectiveStartDate(contractMaster.getEffectiveStartDate());
        contractDTO.setEffectiveEndDate(contractMaster.getEffectiveEndDate());
        contractDTO.setOrgCode(BaseMaster.getCode(contractMaster.getOrganization()));
        contractDTO.setApplicableUnitCodeList(translateUnitMapperDTOList(contractMaster.getUnitContractMapperList()));
        contractDTO.setConfigurationList(translateConfigurationDTOList(contractMaster.getContractConfigurationCollection()));
        contractDTO.setPersistStatus(contractMaster.getPersistStatus());

        return contractDTO;
    }

    /**
     * Translate from Entity to DTO.
     * @param mapperCollection given
     * @return list of {@link CommonUnitContractDiscountMapperDTO} object.
     */
    private Collection<CommonUnitContractDiscountMapperDTO> translateUnitMapperDTOList(final Collection<UnitContractMapper> mapperCollection) {
        if(CollectionUtils.isEmpty(mapperCollection)) {
            return Collections.emptyList();
        }

        final Collection<CommonUnitContractDiscountMapperDTO>  mapperDTOCollection = new ArrayList<>(mapperCollection.size());
        for(UnitContractMapper mapper : mapperCollection) {
            final CommonUnitContractDiscountMapperDTO mapperDTO = new CommonUnitContractDiscountMapperDTO();
            mapperDTO.setUnitCode(BaseMaster.getCode(mapper.getUnitMaster()));

            mapperDTOCollection.add(mapperDTO);
        }

        return mapperDTOCollection;
    }

    /**
     * Translate from Entity to DTO
     *
     * @param configurationCollection given
     * @return list of {@link CommonDiscountContractConfigurationDTO} object.
     */
    public Collection<CommonDiscountContractConfigurationDTO> translateConfigurationDTOList(final Collection<ContractConfiguration> configurationCollection) {
        if (CollectionUtils.isEmpty(configurationCollection)) {
            return Collections.emptyList();
        }

        final Collection<CommonDiscountContractConfigurationDTO> ConfigurationDTOCollection = new ArrayList<>(configurationCollection.size());
        for(ContractConfiguration configuration : configurationCollection) {
            final CommonDiscountContractConfigurationDTO configurationDTO = new CommonDiscountContractConfigurationDTO();
            configurationDTO.setConfigurationId(configuration.getId());
            configurationDTO.setGradeCode(BaseMaster.getCode(configuration.getGrade()));
            configurationDTO.setGradeDesc(BaseMaster.getDesc(configuration.getGrade()));
            configurationDTO.setFallBackTariffCode(BaseMaster.getCode(configuration.getFallBackTariff()));
            configurationDTO.setFallBackTariffDesc(BaseMaster.getDesc(configuration.getFallBackTariff()));
            configurationDTO.setCoSharePercentage(configuration.getCoSharePercentage());
            configurationDTO.setExcludeDiscount(configuration.getExcludeDiscount());
            configurationDTO.setIncludeAllVisitType(configuration.getIncludeAllVisitType());
            configurationDTO.setTotalNoOfVisit(configuration.getTotalNoOfVisit());
            configurationDTO.setTotalVisitLimit(configuration.getTotalVisitLimit());
            configurationDTO.setPreApprovedVisitLimit(configuration.getPreApprovedVisitLimit());
            configurationDTO.setVisitTypeConfigurationDTOList(translateVisitConfigurationDTOSet(configuration.getVisitConfigurationCollection()));
            configurationDTO.setDiscountConfigurationDTOList(translateDiscountDetailsConfigurationDTOSet(configuration.getDiscountDetailsCollection()));
            configurationDTO.setExclusionConfigurationDTOList(translateExclusionConfigurationDTOSet(configuration.getExclusionConfigurationCollection()));

            ConfigurationDTOCollection.add(configurationDTO);
        }

        return ConfigurationDTOCollection;
    }

    /**
     * Translate from Entity to DTO
     *
     * @param configurationCollection given
     * @return unique list of {@link CommonDiscountContractExclusionConfigurationDTO}
     */
    public Set<CommonDiscountContractExclusionConfigurationDTO> translateExclusionConfigurationDTOSet(final Collection<ContractExclusionConfiguration> configurationCollection) {
        if (CollectionUtils.isEmpty(configurationCollection)) {
            return Collections.emptySet();
        }

        final Set<CommonDiscountContractExclusionConfigurationDTO> configurationDTOSet = new HashSet<>(configurationCollection.size());
        for(ContractExclusionConfiguration configuration : configurationCollection) {
            final CommonDiscountContractExclusionConfigurationDTO configurationDTO = new CommonDiscountContractExclusionConfigurationDTO();
            configurationDTO.setExclusionConfigurationId(configuration.getId());

            configurationDTO.setChargeCategoryCode(BaseMaster.getCode(configuration.getChargeCategoryMaster()));
            configurationDTO.setExclusionChargeCode(configuration.getExclusionChargeCode());

            configurationDTOSet.add(configurationDTO);
        }

        return configurationDTOSet;
    }

    /**
     * Translate from Entity to DTO
     *
     * @param configurationCollection given.
     * @return unique list of {@link CommonDiscountContractDiscountConfigurationDTO}
     */
    public Set<CommonDiscountContractDiscountConfigurationDTO> translateDiscountDetailsConfigurationDTOSet(final Collection<ContractDiscountDetailsConfiguration> configurationCollection) {
        if (CollectionUtils.isEmpty(configurationCollection)) {
            return Collections.emptySet();
        }

        final Set<CommonDiscountContractDiscountConfigurationDTO> configurationDTOSet = new HashSet<>(configurationCollection.size());
        for(ContractDiscountDetailsConfiguration configuration : configurationCollection) {
            final CommonDiscountContractDiscountConfigurationDTO configurationDTO = new CommonDiscountContractDiscountConfigurationDTO();
            configurationDTO.setDiscountDetailsConfigurationId(configuration.getId());

            configurationDTO.setChargeCategoryCode(BaseMaster.getCode(configuration.getChargeCategory()));
            configurationDTO.setChargeCode(configuration.getChargeCode());
            configurationDTO.setDiscountPercentage(configuration.getDiscountPercentage());
            configurationDTO.setDiscountAmount(configuration.getDiscountAmount());
            configurationDTO.setMaxDiscountLimitAmount(configuration.getMaxDiscountLimitAmount());

            configurationDTOSet.add(configurationDTO);
        }

        return configurationDTOSet;
    }

    /**
     * Translate from Entity to DTO
     *
     * @param configurationCollection given.
     * @return unique list of {@link CommonVisitTypeConfigurationDTO}
     */
    public Set<CommonVisitTypeConfigurationDTO> translateVisitConfigurationDTOSet(final Collection<ContractVisitConfiguration> configurationCollection) {
        if (CollectionUtils.isEmpty(configurationCollection)) {
            return Collections.emptySet();
        }

        final Set<CommonVisitTypeConfigurationDTO> configurationDTOSet = new HashSet<>(configurationCollection.size());
        for (ContractVisitConfiguration configuration : configurationCollection) {
            CommonVisitTypeConfigurationDTO configurationDTO = new CommonVisitTypeConfigurationDTO();
            configurationDTO.setVisitTypeConfigurationId(configuration.getId());

            configurationDTO.setVisitTypeCode(BaseMaster.getCode(configuration.getVisitTypeMaster()));
            configurationDTO.setNoOfVisit(configuration.getNoOfVisit());
            configurationDTO.setVisitLimit(configuration.getVisitLimit());
            configurationDTO.setPreApprovedVisitLimit(configuration.getPreApprovedVisitLimit());

            configurationDTOSet.add(configurationDTO);
        }

        return configurationDTOSet;
    }
}

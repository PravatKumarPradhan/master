package com.sdgt.medcare.master.service.billing;

import com.sdgt.medcare.master.dto.billing.Configuration;
import com.sdgt.medcare.master.dto.billing.PackageCap;
import com.sdgt.medcare.master.dto.billing.PackageCapDetailDTO;
import com.sdgt.medcare.master.dto.billing.PackageConfiguration;
import com.sdgt.medcare.master.dto.billing.PackageExceptionDetailsDTO;
import com.sdgt.medcare.master.dto.billing.PackageExclusion;
import com.sdgt.medcare.master.dto.billing.PackageFilterRequest;
import com.sdgt.medcare.master.dto.billing.PackageFilterResponse;
import com.sdgt.medcare.master.dto.billing.PackageItemService;
import com.sdgt.medcare.master.dto.billing.PackageItemServiceDetailDTO;
import com.sdgt.medcare.master.dto.billing.PackageRequest;
import com.sdgt.medcare.master.dto.billing.PackageResponse;
import com.sdgt.medcare.master.dto.billing.PackageTypeResponse;
import com.sdgt.medcare.master.dto.billing.SavePackageRequest;
import com.sdgt.medcare.master.dto.billing.ServiceFilterRequest;
import com.sdgt.medcare.master.dto.billing.ServiceFilterResponse;
import com.sdgt.medcare.master.dto.billing.ServiceRateRequest;
import com.sdgt.medcare.master.dto.billing.UnitWiseBillingChargesDTO;
import com.sdgt.medcare.master.dto.billing.UnitWiseService;
import com.sdgt.medcare.master.entity.billing.packageservice.PackageCapDetail;
import com.sdgt.medcare.master.entity.billing.packageservice.PackageExceptionDetails;
import com.sdgt.medcare.master.entity.billing.packageservice.PackageItemServiceDetail;
import com.sdgt.medcare.master.entity.billing.packageservice.PackageMaster;
import com.sdgt.medcare.master.entity.billing.packageservice.PackageTariffConfigurationMaster;
import com.sdgt.medcare.master.entity.billing.packageservice.PackageTypeMaster;
import com.sdgt.medcare.master.entity.global.VisitTypeMaster;
import com.sdgt.medcare.master.entity.org.FinancialClassMaster;
import com.sdgt.medcare.master.entity.org.GroupMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.SubGroupMaster;
import com.sdgt.medcare.master.entity.org.TariffMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import com.sdgt.medcare.master.entity.unit.UnitTariffMapper;
import com.sdgt.medcare.master.entity.unit.UnitTariffServiceRateMaster;
import com.sdgt.medcare.master.repository.billing.PackageCapDetailRepository;
import com.sdgt.medcare.master.repository.billing.PackageExceptionDetailsRepository;
import com.sdgt.medcare.master.repository.billing.PackageItemServiceDetailRepository;
import com.sdgt.medcare.master.repository.billing.PackageMasterRepository;
import com.sdgt.medcare.master.repository.billing.PackageTariffConfigurationMasterRepository;
import com.sdgt.medcare.master.repository.billing.PackageTypeRepository;
import com.sdgt.medcare.master.repository.common.DiscountTypeMasterRepository;
import com.sdgt.medcare.master.repository.common.GenderMasterRepository;
import com.sdgt.medcare.master.repository.common.GroupMasterRepository;
import com.sdgt.medcare.master.repository.common.ItemCategoryMasterRepository;
import com.sdgt.medcare.master.repository.common.ItemGroupMasterRepository;
import com.sdgt.medcare.master.repository.common.ItemMasterRepository;
import com.sdgt.medcare.master.repository.common.OrganizationMasterRepository;
import com.sdgt.medcare.master.repository.common.ServiceMasterRepository;
import com.sdgt.medcare.master.repository.common.SubGroupMasterRepository;
import com.sdgt.medcare.master.repository.common.UnitMasterRepository;
import com.sdgt.medcare.master.repository.common.UnitServiceMapperRepository;
import com.sdgt.medcare.master.repository.common.UnitTariffMapperRepository;
import com.sdgt.medcare.master.service.common.FinancialClassMasterService;
import com.sdgt.medcare.master.service.common.TariffMasterService;
import com.sdgt.medcare.master.service.common.UnitServiceMapperService;
import com.sdgt.medcare.master.service.common.UnitTariffServiceRateMasterService;
import com.sdgt.medcare.master.service.common.VisitTypeMasterService;
import com.sdgt.medcare.master.util.NumberParseAssistant;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Service
public class PackageService {
    private Logger logger = LoggerFactory.getLogger(PackageService.class);

    @Autowired private UnitServiceMapperService unitServiceMapperService;
    @Autowired private VisitTypeMasterService visitTypeMasterService;
    @Autowired private FinancialClassMasterService financialClassMasterService;
    @Autowired private TariffMasterService tariffMasterService;
    @Autowired private UnitTariffServiceRateMasterService unitTariffServiceRateMasterService;

    @Autowired private UnitTariffMapperRepository unitTariffMapperRepository;
    @Autowired private PackageTypeRepository packageTypeRepository;
    @Autowired private PackageMasterRepository packageMasterRepository;
    @Autowired private GenderMasterRepository genderMasterRepository;
    @Autowired private OrganizationMasterRepository organizationMasterRepository;
    @Autowired private UnitMasterRepository unitMasterRepository;
    @Autowired private PackageTariffConfigurationMasterRepository packageTariffConfigurationMasterRepository;
    @Autowired private DiscountTypeMasterRepository discountTypeMasterRepository;
    @Autowired private PackageItemServiceDetailRepository packageItemServiceDetailRepository;
    @Autowired private ServiceMasterRepository serviceMasterRepository;
    @Autowired private GroupMasterRepository groupMasterRepository;
    @Autowired private SubGroupMasterRepository subGroupMasterRepository;
    @Autowired private PackageCapDetailRepository packageCapDetailRepository;
    @Autowired private ItemGroupMasterRepository itemGroupMasterRepository;
    @Autowired private ItemCategoryMasterRepository itemCategoryMasterRepository;
    @Autowired private ItemMasterRepository itemMasterRepository;
    @Autowired private UnitServiceMapperRepository unitServiceMapperRepository;
    @Autowired private PackageExceptionDetailsRepository packageExceptionDetailsRepository;

    /**
     * Get all {@link PackageMaster} by given unit code.
     *
     * @param unitCode a valid unit code.
     * @return list of {@link PackageMaster} objects.
     */
    public List<PackageMaster> findAllPackageMaster(final String unitCode) {
        final UnitMaster unitMaster = findAndValidateUnitCode(unitCode);
        if (unitMaster == null) {
            logger.warn("unitMaster not found.");
            return Collections.emptyList();
        }
        return packageMasterRepository.findAllByUnitMaster(unitMaster);
    }

    /**
     * Validate if packageName already exist.
     *
     * @param packageName given package name.
     * @param packageCode given package code.
     * @return true if unique. Else false
     */
    public Boolean isUnique(final String packageName, final String packageCode) {
        if (StringUtils.isNotBlank(packageCode)) {
            if (packageMasterRepository.findByCode(packageCode) != null) {
                return true;
            }
        }

        if (packageMasterRepository.findByDescIgnoreCase(packageName) != null) {
            return true;
        }

        return false;
    }

    /**
     * Save ECH Package.
     *
     * @param request a valid object of type {@link SavePackageRequest}
     */
    @Transactional
    public void savePackage(final SavePackageRequest request) {
        verifyNotNull(request);
        verifyIfPackageCanBeCreated(request);

        final OrganizationMaster organizationMaster = organizationMasterRepository.findByCode(request.getOrgCode());
        final UnitMaster unitMaster = findAndValidateUnitCode(request.getUnitCode());

        final PackageMaster packageMaster = new PackageMaster();
        packageMaster.setPackageTypeMaster(packageTypeRepository.findByCode(request.getPackageTypeCode()));
        packageMaster.setGenderMaster(genderMasterRepository.findByCode(request.getGenderCode()));
        packageMaster.setOrganizationMaster(organizationMaster);
        packageMaster.setUnitMaster(unitMaster);
        packageMaster.setMaximumAgeCriteria(request.getMaxAge());
        packageMaster.setMinimumAgeCriteria(request.getMinAge());
        packageMaster.setUnitServiceMapper(unitServiceMapperService.getUnitServiceMapper(request.getUnitCode(), request.getPackageCode()));
        packageMaster.setCode(packageMaster.getUnitServiceMapper().getServiceMaster().getCode());
        packageMaster.setEffectiveStartDate(request.getEffectiveStartDate());
        packageMaster.setEffectiveEndDate(request.getEffectiveEndDate());
        packageMaster.setDesc(request.getPackageName());
        packageMaster.setCreatedBy(request.getCreatedBy());
        packageMaster.setActive(true);
        packageMaster.setCreatedDate(new Date());

        final PackageMaster savedPackageMaster = packageMasterRepository.save(packageMaster);



        for(final PackageConfiguration packageConfiguration : request.getPackageConfigurationList()) {
            final FinancialClassMaster financialClassMaster = financialClassMasterService.findByCode(packageConfiguration.getFinancialClassCode());
            final VisitTypeMaster visitTypeMaster = visitTypeMasterService.findByCode(packageConfiguration.getVisitTypeCode());
            final PackageTariffConfigurationMaster packageTariffConfigurationMaster = new PackageTariffConfigurationMaster();
            packageTariffConfigurationMaster.setPackageMaster(savedPackageMaster);
            packageTariffConfigurationMaster.setFinancialClassMaster(financialClassMaster);
            packageTariffConfigurationMaster.setVisitTypeMaster(visitTypeMaster);
            packageTariffConfigurationMaster.setOrganizationMaster(organizationMaster);
            packageTariffConfigurationMaster.setUnitMaster(unitMaster);
            packageTariffConfigurationMaster.setEffectiveStartDate(request.getEffectiveStartDate());
            packageTariffConfigurationMaster.setPackageRate(packageConfiguration.getPackageRate());
            packageTariffConfigurationMaster.setTariffMaster(tariffMasterService.findByCode(packageConfiguration.getTariffCode()));
            packageTariffConfigurationMaster.setPackageDiscountRate(packageConfiguration.getPackageDiscountRate());
            packageTariffConfigurationMaster.setPackageRevisedRate(packageConfiguration.getPackageRevisedRate());
            packageTariffConfigurationMaster.setPackageRoundOffRate(packageConfiguration.getPackageRoundOffRate());
            packageTariffConfigurationMaster.setActive(true);
            packageTariffConfigurationMaster.setCreatedBy(request.getCreatedBy());
            packageTariffConfigurationMaster.setCreatedDate(new Date());

            final PackageTariffConfigurationMaster savedPackageTariffConfigurationMaster =
                    packageTariffConfigurationMasterRepository.save(packageTariffConfigurationMaster);

            final List<PackageCapDetailDTO> packageCapDetailList = packageConfiguration.getPackageCapDetailList();
            if (CollectionUtils.isNotEmpty(packageCapDetailList)) {
                packageCapDetailRepository.saveAll(preparePackageCapDetailList(packageCapDetailList, savedPackageTariffConfigurationMaster));
            }


            if (!CollectionUtils.sizeIsEmpty(packageConfiguration.getPackageItemServiceDetailList())) {
                final List<PackageItemServiceDetail> packageItemServiceDetailList = new ArrayList<>(packageConfiguration.getPackageItemServiceDetailList().size());
                for(final PackageItemServiceDetailDTO detail : packageConfiguration.getPackageItemServiceDetailList()) {
                    final PackageItemServiceDetail packageItemServiceDetail = new PackageItemServiceDetail();
                    packageItemServiceDetail.setOrganizationMaster(organizationMaster);
                    packageItemServiceDetail.setUnitMaster(unitMaster);
                    packageItemServiceDetail.setPackageTariffConfigurationMaster(savedPackageTariffConfigurationMaster);
                    packageItemServiceDetail.setPackageUnitDiscountRate(detail.getPackageUnitDiscountRate());
                    packageItemServiceDetail.setRate(detail.getServiceRate());
                    packageItemServiceDetail.setServiceQty(detail.getServiceQty());
                    verifyIfValidItemOrServiceDetail(detail);

                    if (StringUtils.isBlank(detail.getUnitServiceMapperId()) && StringUtils.isBlank(detail.getServiceCode())) {
                        packageItemServiceDetail.setItemMaster(itemMasterRepository.findByCodeIgnoreCase(detail.getItemCode()));
                    } else {
                        packageItemServiceDetail.setUnitServiceMapper(lookupUnitServiceMapper(detail.getUnitServiceMapperId(), detail.getServiceCode(), unitMaster));
                    }
                    if (packageItemServiceDetail.getItemMaster() == null && packageItemServiceDetail.getUnitServiceMapper() == null) {
                        logger.error("preparePackageCapDetailList; Exception list cannot be empty when group master is null. Or otherwise.");
                        throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "preparePackageCapDetailList; Exception list cannot be empty when group master is null. Or otherwise.");
                    }

                    packageItemServiceDetail.setDiscountTypeMaster(discountTypeMasterRepository.findByCode(detail.getPackageDiscountTypeCode()));
                    packageItemServiceDetail.setActive(true);
                    packageItemServiceDetail.setCreatedBy(request.getCreatedBy());
                    packageItemServiceDetail.setCreatedDate(new Date());

                    packageItemServiceDetailList.add(packageItemServiceDetail);
                }

                packageItemServiceDetailRepository.saveAll(packageItemServiceDetailList);
            }

        }
    }

    /**
     * Verifies if the requested package can be created.
     *
     * @param request given valid request.
     */
    private void verifyIfPackageCanBeCreated(SavePackageRequest request) {
        final PackageMaster existingPackageMaster = packageMasterRepository.findByCode(request.getPackageCode());

        if (existingPackageMaster != null && existingPackageMaster.getEffectiveEndDate().after(request.getEffectiveStartDate())) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Un-Expired Package of code = " +
                    request.getPackageCode() + " already exists.");
        }
    }

    private UnitServiceMapper lookupUnitServiceMapper(final String unitServiceMapperId, final String serviceCode, final UnitMaster unitMaster) {
        final UnitServiceMapper unitServiceMapper;
        if (StringUtils.isNotBlank(unitServiceMapperId)) {
            Optional<UnitServiceMapper> unitServiceMapperOptional = unitServiceMapperRepository.findById(NumberParseAssistant.parseLong(unitServiceMapperId));

            unitServiceMapper = unitServiceMapperOptional.orElseThrow(() -> new IllegalStateException("preparePackageCapDetailList; exception list missing service code."));
        } else {
            unitServiceMapper = unitServiceMapperRepository.findByServiceMasterAndUnitMaster(
                    serviceMasterRepository.findByCodeIgnoreCase(serviceCode), unitMaster);
        }

        return unitServiceMapper;
    }

    /**
     * Prepare {@link PackageCapDetail} list from list of  {@link PackageCapDetailDTO}
     *
     * @param packageCapDetailDTOList valid.
     * @param savedPackageTariffConfigurationMaster valid.
     * @return list of  {@link PackageCapDetail} objects.
     */
    private List<PackageCapDetail> preparePackageCapDetailList(final List<PackageCapDetailDTO> packageCapDetailDTOList, final PackageTariffConfigurationMaster savedPackageTariffConfigurationMaster) {
        final List<PackageCapDetail> packageCapDetailList = new ArrayList<>(packageCapDetailDTOList.size());
        for(final PackageCapDetailDTO capDTO : packageCapDetailDTOList) {
            final PackageCapDetail packageCapDetail = new PackageCapDetail();
            packageCapDetail.setCapRate(capDTO.getCapRate());
            packageCapDetail.setGroupConsumable(capDTO.getGroupConsumable());
            packageCapDetail.setGroupMaster(groupMasterRepository.findByCodeIgnoreCase(capDTO.getGroupCode()));
            packageCapDetail.setSubGroupMaster(subGroupMasterRepository.findByCodeIgnoreCase(capDTO.getSubGroupCode()));
            packageCapDetail.setItemGroupMaster(itemGroupMasterRepository.findByCodeIgnoreCase(capDTO.getItemGroupCode()));
            packageCapDetail.setItemCategoryMaster(itemCategoryMasterRepository.findByCodeIgnoreCase(capDTO.getItemCategoryCode()));
            final OrganizationMaster organizationMaster = organizationMasterRepository.findByCode(capDTO.getOrgCode());
            packageCapDetail.setOrganizationMaster(organizationMaster);
            final UnitMaster unitMaster = unitMasterRepository.findByCode(capDTO.getUnitCode());
            packageCapDetail.setUnitMaster(unitMaster);
            packageCapDetail.setPackageTariffConfigurationMaster(savedPackageTariffConfigurationMaster);
            packageCapDetail.setActive(true);
            packageCapDetail.setCreatedBy(capDTO.getCreatedBy());
            packageCapDetail.setCreatedDate(new Date());

            final List<PackageExceptionDetailsDTO> packageExceptionDetailsDTOList = capDTO.getPackageExceptionDetailsList();
            if (CollectionUtils.isNotEmpty(packageExceptionDetailsDTOList)) {
                final List<PackageExceptionDetails> packageExceptionDetailsList = new ArrayList<>(packageExceptionDetailsDTOList.size());
                for(final PackageExceptionDetailsDTO exceptionDto : packageExceptionDetailsDTOList) {
                    PackageExceptionDetails packageExceptionDetails = new PackageExceptionDetails();
                    if (StringUtils.isNotBlank(exceptionDto.getItemId()) || StringUtils.isNotBlank(exceptionDto.getItemCode())) {
                        packageExceptionDetails.setItemMaster(itemMasterRepository.findByIdOrCode(NumberParseAssistant.parseLong(exceptionDto.getItemId()), exceptionDto.getItemCode()));
                    }
                    if (StringUtils.isNotBlank(exceptionDto.getServiceCode()) || StringUtils.isNotBlank(exceptionDto.getUnitServiceMapperId())) {
                        final UnitServiceMapper unitServiceMapper = lookupUnitServiceMapper(exceptionDto.getUnitServiceMapperId(), exceptionDto.getServiceCode(), unitMaster);
                        packageExceptionDetails.setUnitServiceMapper(unitServiceMapper);
                    }

                    if (StringUtils.isNotBlank(exceptionDto.getGroupCode())) {
                        packageExceptionDetails.setGroupMaster(groupMasterRepository.findByCodeIgnoreCase(exceptionDto.getGroupCode()));
                    }
                    if (StringUtils.isNotBlank(exceptionDto.getSubGroupCode())) {
                        packageExceptionDetails.setSubGroupMaster(subGroupMasterRepository.findByCodeIgnoreCase(exceptionDto.getSubGroupCode()));
                    }

                    if (StringUtils.isNotBlank(exceptionDto.getItemGroupCode())) {
                        packageExceptionDetails.setItemGroupMaster(itemGroupMasterRepository.findByCodeIgnoreCase(exceptionDto.getItemGroupCode()));
                    }

                    if (StringUtils.isNotBlank(exceptionDto.getItemCategoryCode())) {
                        packageExceptionDetails.setItemCategoryMaster(itemCategoryMasterRepository.findByCodeIgnoreCase(exceptionDto.getItemCategoryCode()));
                    }

                    packageExceptionDetails.setExceptionType(exceptionDto.getExceptionType());
                    packageExceptionDetails.setOrganizationMaster(organizationMaster);
                    packageExceptionDetails.setUnitMaster(unitMaster);
                    packageExceptionDetails.setActive(true);
                    packageExceptionDetails.setCreatedBy(exceptionDto.getCreatedBy());
                    packageExceptionDetails.setCreatedDate(new Date());

                    if (packageExceptionDetails.getItemMaster() == null &&
                            packageExceptionDetails.getUnitServiceMapper() == null &&
                            packageExceptionDetails.getGroupMaster() == null &&
                            packageExceptionDetails.getItemGroupMaster() == null) {
                        logger.error("preparePackageCapDetailList; Item, service, service group and item group code codes, cannot, all be blank in single exception item.");
                        throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "preparePackageCapDetailList; Item, service, service group and item group code codes, cannot, all be blank in single exception item.");
                    }

                    packageExceptionDetails = packageExceptionDetailsRepository.save(packageExceptionDetails);

                    packageExceptionDetailsList.add(packageExceptionDetails);
                }

                packageCapDetail.setPackageExceptionDetailsList(packageExceptionDetailsList);
            }

            packageCapDetailList.add(packageCapDetail);
        }

        return packageCapDetailList;
    }

    /**
     * Functional validation to {@link PackageItemServiceDetailDTO}
     *
     * @param detail {@link PackageItemServiceDetailDTO} object.
     * @throws IllegalArgumentException if invalid input.
     */
    private void verifyIfValidItemOrServiceDetail(final PackageItemServiceDetailDTO detail) {
        if (StringUtils.isNotBlank(detail.getItemCode()) && StringUtils.isNotBlank(detail.getUnitServiceMapperId())) {
            throw new IllegalArgumentException("Same package details cannot have item code and unitServiceMapper id."
                    + " Details = " + detail);
        }
    }

    /**
     * Get complete package details for given package code and unit code.
     *
     * @param request a valid {@link PackageRequest} object.
     * @return {@link PackageResponse} object.
     */
    public PackageResponse getPackageDetail(final PackageRequest request) {
        verifyNotNull(request);

        final PackageResponse response = new PackageResponse();
        final PackageMaster packageMaster = packageMasterRepository.findByCode(request.getPackageCode());
        response.setPackageMaster(packageMaster);
        final List<PackageTariffConfigurationMaster> packageTariffConfigurationMasterList = packageTariffConfigurationMasterRepository.
                findAllByPackageMaster(packageMaster);

        final List<Configuration> configurationList = new ArrayList<>(packageTariffConfigurationMasterList.size());
        for (final PackageTariffConfigurationMaster existingPackageConfiguration : packageTariffConfigurationMasterList) {
            final Configuration configuration = new Configuration();
            configuration.setPackageTariffConfigurationMaster(existingPackageConfiguration);
            final List<PackageItemServiceDetail> packageItemServiceDetailList = packageItemServiceDetailRepository.
                    findAllByPackageTariffConfigurationMaster(existingPackageConfiguration);
            configuration.setPackageItemServiceDetailList(packageItemServiceDetailList);
            configurationList.add(configuration);
        }
        response.setConfigurationList(configurationList);

        return response;
    }

    /**
     * Get all service rates for the given request {@link ServiceRateRequest}
     *
     * @param request a valid {@link ServiceRateRequest}
     * @return a list of {@link UnitTariffServiceRateMaster} objects.
     */
    public List<UnitTariffServiceRateMaster> findAllServiceRate(final ServiceRateRequest request) {
        verifyNotNull(request);

        if(StringUtils.isBlank(request.getFinancialClassCode()) &&
                StringUtils.isBlank(request.getTariffCode()) &&
                        StringUtils.isBlank(request.getUnitCode()) &&
                        StringUtils.isBlank(request.getVisitTypeCode())) {
            logger.error("required fields are blank. {}", request);
            throw new IllegalArgumentException("required fields are blank.");
        }

        return unitTariffServiceRateMasterService.findByRequest(request);
    }

    /**
     * Get all {@link TariffMaster} by give unit code.
     *
     * @param unitCode a valid unit code.
     * @return a list of {@link TariffMaster} objects/
     */
    public List<TariffMaster> findAllTariffByUnitCode(final String unitCode) {
        final UnitMaster unitMaster = findAndValidateUnitCode(unitCode);
        if (unitMaster == null) {
            logger.warn("unitMaster not found.");
            return Collections.emptyList();
        }

        final List<UnitTariffMapper> unitTariffMapperList =  unitTariffMapperRepository.findAllByUnitMaster(unitMaster);
        final List<TariffMaster> tariffMasterList = new ArrayList<>(unitTariffMapperList.size());

        for (final UnitTariffMapper unitTariffMapper : unitTariffMapperList) {
            tariffMasterList.add(unitTariffMapper.getTariffMaster());
        }

        return tariffMasterList;
    }
    /**
     * Gets all {@link PackageTypeResponse}.
     *
     * @return list of {@link PackageTypeResponse}
     */
    public List<PackageTypeResponse> findAllPackageType() {
        final List<PackageTypeMaster> packageTypeMasterList = packageTypeRepository.findAll();
        final List<PackageTypeResponse> responseList = new ArrayList<>(packageTypeMasterList.size());
        for(final PackageTypeMaster packageTypeMaster : packageTypeMasterList) {
            final PackageTypeResponse response = new PackageTypeResponse();
            response.setCode(packageTypeMaster.getCode());
            response.setDesc(packageTypeMaster.getDesc());

            responseList.add(response);
        }

        return responseList;
    }

    /**
     * service to find list of {@link UnitWiseService} by given filter {@link UnitWiseBillingChargesDTO}.
     * Gets the list of active services for given unit and also if in valid date range and the service is of type package.
     *
     * @param chargesDTO a valid {@link UnitWiseBillingChargesDTO} object.
     * @return a list of {@link UnitWiseService}.
     * @throws IllegalArgumentException if request data is not valid.
     */
    public List<UnitWiseService> findAllUnitServiceMapperByFilter(final UnitWiseBillingChargesDTO chargesDTO) {
        if (!validateUnitWiseBillingChargesDTO(chargesDTO)) {
            logger.error("Unit header missing. {}", chargesDTO);
            throw new IllegalArgumentException("Unit header missing");
        }

        final UnitMaster unitMaster = findAndValidateUnitCode(chargesDTO.getUnitCode());
        if (unitMaster == null) {
            logger.error("unitMaster not found.");
            return Collections.emptyList();
        }

        List<UnitServiceMapper> unitServiceMapperList = unitServiceMapperService.findAllByUnitMaster(unitMaster, chargesDTO.getSearchKey(), chargesDTO.getPageSize());
        if (unitServiceMapperList == null || CollectionUtils.isEmpty(unitServiceMapperList)) {
            logger.error("No unit wise service for the given unitCode {}", chargesDTO.getUnitCode());
            return Collections.emptyList();
        }

        //FIXME: implement streams
        final List<UnitWiseService> unitWiseServiceList = new ArrayList<>();
        for (final UnitServiceMapper unitServiceMapper : unitServiceMapperList) {
            if (unitServiceMapper.getActive() && unitServiceMapper.getServiceMaster().getActive()
                && (unitServiceMapper.getIsPackage() != null && unitServiceMapper.getIsPackage())) {
                final UnitWiseService unitWiseService = new UnitWiseService();
                unitWiseService.setServiceCode(unitServiceMapper.getServiceMaster().getCode());
                unitWiseService.setServiceName(unitServiceMapper.getServiceMaster().getDesc());
                unitWiseService.setIsDoctorRequired(unitServiceMapper.getIsDoctorRequired());
                unitWiseService.setUnitServiceMapperId(unitServiceMapper.getId());
                final GroupMaster groupMaster = unitServiceMapper.getServiceMaster().getGroupMaster();
                if (groupMaster != null) {
                    unitWiseService.setGroupCode(groupMaster.getCode());
                    unitWiseService.setGroupDesc(groupMaster.getDesc());
                }

                final SubGroupMaster subGroupMaster = unitServiceMapper.getServiceMaster().getSubGroupMaster();
                if (subGroupMaster != null) {
                    unitWiseService.setSubGroupCode(subGroupMaster.getCode());
                    unitWiseService.setSubGroupCode(subGroupMaster.getDesc());
                }

                unitWiseServiceList.add(unitWiseService);
            }
        }
        unitWiseServiceList.sort(Comparator.comparing(UnitWiseService::getServiceName));
        return unitWiseServiceList;
    }

    /**
     * Find all services by given filter of type {@link ServiceFilterRequest}
     *
     * @param request a valid {@link ServiceFilterRequest}
     * @return list of {@link ServiceFilterResponse}
     */
    public List<ServiceFilterResponse> findAllService(final ServiceFilterRequest request) {
        UnitMaster unitMaster = null;
        if(StringUtils.isNotBlank(request.getUnitCode())) {
            unitMaster = unitMasterRepository.findByCode(request.getUnitCode());
        }

        List<ServiceFilterResponse> responseList = new ArrayList<>();
        if (StringUtils.isBlank(request.getSubGroupCode()) && StringUtils.isBlank(request.getSubGroupId())) {
            List<ServiceMaster> groupWiseServiceList;
            GroupMaster groupMaster = getGroupMaster(request.getGroupId(), request.getGroupCode());

            if(groupMaster != null) {
                groupWiseServiceList = serviceMasterRepository.findByGroupMaster(groupMaster);

                responseList = populatePackageFilterResponse(groupWiseServiceList, unitMaster);
            }
        } else {
            List<ServiceMaster> groupSubGroupWiseServiceList;
            GroupMaster groupMaster = getGroupMaster(request.getGroupId(), request.getGroupCode());
            SubGroupMaster subGroupMaster = getSubGroupMaster(request.getSubGroupId(), request.getSubGroupCode());

            if (groupMaster != null && subGroupMaster != null) {
                groupSubGroupWiseServiceList = serviceMasterRepository.findByGroupMasterAndSubGroupMaster(groupMaster, subGroupMaster);

                responseList = populatePackageFilterResponse(groupSubGroupWiseServiceList, unitMaster);
            }

        }

        return responseList;
    }

    /**
     * Polulate {@link ServiceFilterResponse}.
     *
     * @param serviceMasterList list of {@link ServiceMaster}
     * @param unit given unit.
     * @return list of {@link ServiceFilterResponse}
     */
    private List<ServiceFilterResponse> populatePackageFilterResponse(final List<ServiceMaster> serviceMasterList, final UnitMaster unit) {
        List<ServiceFilterResponse> serviceFilterResponseArrayList = new ArrayList<>();
        for(final ServiceMaster serviceMaster : serviceMasterList) {
            final ServiceFilterResponse groupServiceListResponse = new ServiceFilterResponse();
            groupServiceListResponse.setServiceId(serviceMaster.getId());
            groupServiceListResponse.setServiceCode(serviceMaster.getCode());
            groupServiceListResponse.setServiceDesc(serviceMaster.getDesc());

            String unitServiceMapperId = null;
            if (unit == null) {
                unitServiceMapperId = unitServiceMapperRepository.findByServiceMasterAndUnitMaster(serviceMaster, unit).getId();
            }

            groupServiceListResponse.setUnitServiceMapperId(unitServiceMapperId);

            serviceFilterResponseArrayList.add(groupServiceListResponse);
        }
        return serviceFilterResponseArrayList;
    }

    /**
     * Get Id from Given list of {@link UnitServiceMapper}
     * @param unitServiceMapperList given.
     * @return List of Identifiers.
     */
    private List<String> getIds(List<UnitServiceMapper> unitServiceMapperList) {
        final List<String> idList = new ArrayList<>(unitServiceMapperList.size());

        if (CollectionUtils.isEmpty(unitServiceMapperList)) {
            return idList;
        }
        for(final UnitServiceMapper unitServiceMapper : unitServiceMapperList) {
            idList.add(unitServiceMapper.getId());
        }

        return idList;
    }

    /**
     * Get {@link GroupMaster} by given identifier or code.
     *
     * @param id a valid identifier. Can be null.
     * @param code a valid code.
     * @return the {@link GroupMaster} object.
     */
    private GroupMaster getGroupMaster(final String id, final String code) {
        GroupMaster groupMaster = null;

        if (StringUtils.isNotBlank(code)) {
            groupMaster = groupMasterRepository.findByCodeIgnoreCase(code);
        } else if (StringUtils.isNotBlank(id)) {
            groupMaster = groupMasterRepository.getOne(Long.parseLong(id));
        }

        return groupMaster;
    }

    /**
     * Get {@link SubGroupMaster} by given identifier or code.
     *
     * @param id a valid identifier. Can be null.
     * @param code a valid code.
     * @return the {@link SubGroupMaster} object.
     */
    private SubGroupMaster getSubGroupMaster(final String id, final String code) {
        SubGroupMaster subGroupMaster = null;

        if (StringUtils.isNotBlank(code)) {
            subGroupMaster = subGroupMasterRepository.findByCodeIgnoreCase(code);
        } else if (StringUtils.isNotBlank(id)) {
            subGroupMaster = subGroupMasterRepository.getOne(Long.parseLong(id));
        }

        return subGroupMaster;
    }

    /**
     * Validates the {@link UnitWiseBillingChargesDTO}
     *
     * @param serviceDTO given {@link UnitWiseBillingChargesDTO} object.
     * @return true if valid. Else false.
     */
    private boolean validateUnitWiseBillingChargesDTO(final UnitWiseBillingChargesDTO serviceDTO) {
        verifyNotNull(serviceDTO);

        if (StringUtils.isBlank(serviceDTO.getUnitCode())) {
            return false;
        }

        return true;
    }

    /**
     * Validates the given DTO.
     *
     * @param object a dto.
     */
    private void verifyNotNull(final Object object) {
        if(object == null) {
            logger.error("invalid request.");
            throw new IllegalArgumentException("invalid request.");
        }
    }

    /**
     * Validates and finds the {@link UnitMaster} for the given unit code,
     *
     * @param unitCode a valid unit code.
     * @return {@link UnitMaster} if valid. Else null.
     * @throws IllegalArgumentException if unit header missing.
     */
    private UnitMaster findAndValidateUnitCode(final String unitCode) {
        if (StringUtils.isBlank(unitCode)) {
            logger.error("Unit header missing. {}", unitCode);
            throw new IllegalArgumentException("Unit header missing");
        }

        return unitMasterRepository.findByCode(unitCode);
    }

    /**
     * Find {@link PackageMaster} for given filter.
     *
     * @param request a valid {@link PackageFilterRequest}
     * @return {@link PackageFilterResponse} object.
     */
    public List<PackageFilterResponse> findAllPackages(final PackageFilterRequest request) {
        final UnitMaster unitMaster = unitMasterRepository.findByCode(request.getUnitCode());
        final ServiceMaster serviceMaster = serviceMasterRepository.findByCodeIgnoreCase(request.getPackageServiceCode());
        final UnitServiceMapper unitServiceMapper = unitServiceMapperRepository.findByServiceMasterAndUnitMaster(serviceMaster, unitMaster);
        final FinancialClassMaster financialClassMaster = financialClassMasterService.findByCode(request.getFinancialClassCode());
        final PackageTypeMaster packageTypeMaster = packageTypeRepository.findByCode(request.getPackageTypeCode());
        final TariffMaster tariffMaster = tariffMasterService.findByCode(request.getTariffCode());
        final VisitTypeMaster visitTypeMaster = visitTypeMasterService.findByCode(request.getVisitTypeCode());
        List<PackageTariffConfigurationMaster> tariffConfigurationMasterList;
        if (unitServiceMapper != null && financialClassMaster == null && tariffMaster == null && visitTypeMaster == null) {
            final PackageMaster packageMaster = packageMasterRepository.findByUnitServiceMapper(unitServiceMapper);
            tariffConfigurationMasterList = packageTariffConfigurationMasterRepository.findAllByPackageMaster(packageMaster);
        } else {
            final List<PackageMaster> packageMasterList = new ArrayList<>();
            if (unitServiceMapper != null) {
                packageMasterList.add(packageMasterRepository.findByUnitServiceMapper(unitServiceMapper));
            } else {
                packageMasterList.addAll(packageMasterRepository.findAllByPackageTypeMaster(packageTypeMaster));
            }

            if (CollectionUtils.isEmpty(packageMasterList)) {
                logger.warn("No Package data found for the given request.");
                return Collections.emptyList();
            }

            tariffConfigurationMasterList =
                    packageTariffConfigurationMasterRepository.findByFilter(financialClassMaster, tariffMaster, visitTypeMaster, packageMasterList);
        }

        if (CollectionUtils.isEmpty(tariffConfigurationMasterList)) {
            logger.warn("No configuration found for package.");
            return Collections.emptyList();
        }

        List<PackageFilterResponse> responseList = new ArrayList<>(tariffConfigurationMasterList.size());
        for (final PackageTariffConfigurationMaster configurationMaster : tariffConfigurationMasterList) {
            List<PackageItemServiceDetail> itemServiceDetailsList = packageItemServiceDetailRepository.findAllByPackageTariffConfigurationMaster(configurationMaster);
            List<PackageCapDetail> packageCapDetailList = packageCapDetailRepository.findAllByPackageTariffConfigurationMaster(configurationMaster);

            PackageFilterResponse response = new PackageFilterResponse();
            response.setPackageItemServiceList(preparePackageItemServiceDetail(itemServiceDetailsList));
            response.setPackageCapList(preparePackageCapDetailList(packageCapDetailList));

            response.setUnitServiceMapperId(configurationMaster.getPackageMaster().getUnitServiceMapper().getId());
            response.setPackageServiceCode(configurationMaster.getPackageMaster().getUnitServiceMapper().getServiceMaster().getCode());
            response.setPackageDesc(configurationMaster.getPackageMaster().getDesc());
            response.setPackageCode(configurationMaster.getPackageMaster().getCode());
            response.setEffectiveEndDate(configurationMaster.getPackageMaster().getEffectiveEndDate());
            response.setEffectiveStartDate(configurationMaster.getPackageMaster().getEffectiveStartDate());
            response.setGenderCode(configurationMaster.getPackageMaster().getGenderMaster().getGenderCode());
            response.setPackageTypeCode(configurationMaster.getPackageMaster().getPackageTypeMaster().getCode());
            response.setMinimumAgeCriteria(configurationMaster.getPackageMaster().getMinimumAgeCriteria());
            response.setFinancialClassCode(configurationMaster.getFinancialClassMaster().getCode());
            response.setPackageDiscountRate(configurationMaster.getPackageDiscountRate());
            response.setPackageRate(configurationMaster.getPackageRate());
            response.setPackageRevisedRate(configurationMaster.getPackageRevisedRate());
            response.setPackageRoundOffRate(configurationMaster.getPackageRoundOffRate());
            response.setTariffCode(configurationMaster.getTariffMaster().getCode());
            response.setVisitTypeCode(configurationMaster.getVisitTypeMaster().getCode());
            if (configurationMaster.getOrganizationMaster() != null) {
                response.setOrgCode(configurationMaster.getOrganizationMaster().getCode());
            }
            response.setUnitCode(configurationMaster.getUnitMaster().getCode());

            responseList.add(response);

        }
        return responseList;
    }

    /**
     * Prepare {@link PackageCap} list from given,
     *
     * @param packageCapDetailList given list of type {@link PackageCapDetail}
     * @return list of {@link PackageCap}
     */
    private List<PackageCap> preparePackageCapDetailList(final List<PackageCapDetail> packageCapDetailList) {
        final List<PackageCap> packageCapList = new ArrayList<>(packageCapDetailList.size());

        if (CollectionUtils.isEmpty(packageCapDetailList)) {
            return Collections.emptyList();
        }

        for(final PackageCapDetail packageCapDetail : packageCapDetailList) {
            final PackageCap packageCap = new PackageCap();
            packageCap.setCapRate(packageCapDetail.getCapRate());
            if (packageCapDetail.getGroupMaster() != null) {
                packageCap.setGroupCode(packageCapDetail.getGroupMaster().getCode());
                packageCap.setGroupDesc(packageCapDetail.getGroupMaster().getDesc());
            }
            if (packageCapDetail.getGroupConsumable() != null) {
                packageCap.setGroupConsumable(packageCapDetail.getGroupConsumable());
            }
            if (packageCapDetail.getItemCategoryMaster() != null) {
                packageCap.setItemCategoryCode(packageCapDetail.getItemCategoryMaster().getCode());
                packageCap.setItemCategoryDesc(packageCapDetail.getItemCategoryMaster().getDesc());
            }
            if (packageCapDetail.getItemGroupMaster() != null) {
                packageCap.setItemGroupCode(packageCapDetail.getItemGroupMaster().getCode());
                packageCap.setItemGroupDesc(packageCapDetail.getItemGroupMaster().getDesc());
            }
            packageCap.setPackageExclusionList(preparePackageExclusionList(packageCapDetail.getPackageExceptionDetailsList()));

            if (packageCapDetail.getSubGroupMaster() != null) {
                packageCap.setSubGroupCode(packageCapDetail.getSubGroupMaster().getCode());
                packageCap.setSubGroupDesc(packageCapDetail.getSubGroupMaster().getDesc());
            }

            if (packageCapDetail.getOrganizationMaster() != null) {
                packageCap.setOrgCode(packageCapDetail.getOrganizationMaster().getCode());
            }
            packageCap.setUnitCode(packageCapDetail.getUnitMaster().getCode());

            packageCapList.add(packageCap);
        }

        return packageCapList;
    }

    /**
     * Prepare {@link PackageExclusion} list from given.
     *
     * @param packageExceptionDetailsList given list of type {@link PackageExceptionDetails}
     * @return list of {@link PackageExclusion} object.
     */
    private List<PackageExclusion> preparePackageExclusionList(List<PackageExceptionDetails> packageExceptionDetailsList) {
        final List<PackageExclusion> packageExclusionList = new ArrayList<>(packageExceptionDetailsList.size());

        if (CollectionUtils.isEmpty(packageExceptionDetailsList)) {
            return Collections.emptyList();
        }

        for(final PackageExceptionDetails packageExceptionDetails : packageExceptionDetailsList) {
            final PackageExclusion packageExclusion = new PackageExclusion();
            packageExclusion.setExceptionType(packageExceptionDetails.getExceptionType());
            if (packageExceptionDetails.getItemMaster() != null) {
                packageExclusion.setItemCode(packageExceptionDetails.getItemMaster().getCode());
            } else if (packageExceptionDetails.getUnitServiceMapper() != null) {
                packageExclusion.setServiceCode(packageExceptionDetails.getUnitServiceMapper().getServiceMaster().getCode());
                packageExclusion.setUnitServiceMapperId(packageExceptionDetails.getUnitServiceMapper().getId());
            }
            if (packageExceptionDetails.getOrganizationMaster() != null) {
                packageExclusion.setOrgCode(packageExceptionDetails.getOrganizationMaster().getCode());
            }

            if (packageExceptionDetails.getUnitMaster() != null) {
                packageExclusion.setUnitCode(packageExceptionDetails.getUnitMaster().getCode());
            }

            packageExclusionList.add(packageExclusion);
        }
        return packageExclusionList;
    }

    /**
     * Prepare list of {@link PackageItemService} from given.
     *
     * @param itemServiceDetailsList given {@link PackageItemServiceDetail} list.
     * @return list.
     */
    private List<PackageItemService> preparePackageItemServiceDetail(final List<PackageItemServiceDetail> itemServiceDetailsList) {
        final List<PackageItemService> packageItemServiceList = new ArrayList<>(itemServiceDetailsList.size());

        if (CollectionUtils.isEmpty(itemServiceDetailsList)) {
            return Collections.emptyList();
        }

        for (final PackageItemServiceDetail itemServiceDetail: itemServiceDetailsList) {
            final PackageItemService packageItemService = new PackageItemService();
            if (itemServiceDetail.getDiscountTypeMaster() != null) {
                packageItemService.setDiscountTypeStatus(itemServiceDetail.getDiscountTypeMaster().getDiscountTypeStatus());
            }
            packageItemService.setPackageUnitDiscountRate(itemServiceDetail.getPackageUnitDiscountRate());
            if (itemServiceDetail.getItemMaster() != null) {
                packageItemService.setItemCode(itemServiceDetail.getItemMaster().getCode());
                packageItemService.setItemDesc(itemServiceDetail.getItemMaster().getDesc());
            } else if (itemServiceDetail.getUnitServiceMapper() != null) {
                packageItemService.setUnitServiceMapperId(itemServiceDetail.getUnitServiceMapper().getId());
                packageItemService.setServiceCode(itemServiceDetail.getUnitServiceMapper().getServiceMaster().getCode());
                packageItemService.setServiceDesc(itemServiceDetail.getUnitServiceMapper().getServiceMaster().getDesc());
            }
            packageItemService.setPackageUnitRate(itemServiceDetail.getPackageUnitRate());
            packageItemService.setRate(itemServiceDetail.getRate());
            packageItemService.setServiceQty(itemServiceDetail.getServiceQty());
            if (itemServiceDetail.getUnitMaster() != null) {
                packageItemService.setUnitCode(itemServiceDetail.getUnitMaster().getCode());
            }
            if (itemServiceDetail.getOrganizationMaster() != null) {
                packageItemService.setOrgCode(itemServiceDetail.getOrganizationMaster().getCode());
            }

            packageItemServiceList.add(packageItemService);
        }

        return packageItemServiceList;
    }
}

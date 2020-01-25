package com.sdgt.medcare.master.service.billing;

import com.sdgt.medcare.master.dto.billing.DependentService;
import com.sdgt.medcare.master.dto.billing.FindAllDependentServicesDTO;
import com.sdgt.medcare.master.dto.billing.ServiceDetailsDTO;
import com.sdgt.medcare.master.dto.billing.UnitWiseBillingChargesDTO;
import com.sdgt.medcare.master.dto.billing.UnitWiseCostCenter;
import com.sdgt.medcare.master.dto.billing.UnitWiseService;
import com.sdgt.medcare.master.dto.billing.UnitWiseTariffRequest;
import com.sdgt.medcare.master.dto.common.CompanyAndServiceRequest;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.ItemMaster;
import com.sdgt.medcare.master.entity.org.GroupMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.SubGroupMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.DependentUnitServiceMapper;
import com.sdgt.medcare.master.entity.unit.UnitServiceCostCenterMapper;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import com.sdgt.medcare.master.entity.unit.UnitTariffServiceRateMaster;
import com.sdgt.medcare.master.repository.common.DependentUnitServiceMapperRepository;
import com.sdgt.medcare.master.repository.common.ItemMasterRepository;
import com.sdgt.medcare.master.repository.common.ServiceMasterRepository;
import com.sdgt.medcare.master.service.common.CompanyServiceMasterService;
import com.sdgt.medcare.master.service.common.FinancialClassMasterService;
import com.sdgt.medcare.master.service.common.UnitMasterService;
import com.sdgt.medcare.master.service.common.UnitServiceCostCenterMapperService;
import com.sdgt.medcare.master.service.common.UnitServiceMapperService;
import com.sdgt.medcare.master.service.common.UnitTariffServiceRateMasterService;
import com.sdgt.medcare.master.util.NumberParseAssistant;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Service
public class BillingMastersService {
    @Autowired
    private UnitServiceMapperService unitServiceMapperService;
    @Autowired
    private UnitMasterService unitMasterService;
    @Autowired
    private UnitServiceCostCenterMapperService unitServiceCostCenterMapperService;
    @Autowired
    private FinancialClassMasterService financialClassMasterService;
    @Autowired
    private UnitTariffServiceRateMasterService unitTariffServiceRateMasterService;
    @Autowired
    private ServiceMasterRepository serviceMasterRepository;
    @Autowired
    private DependentUnitServiceMapperRepository dependentUnitServiceMapperRepository;
    @Autowired
    private CompanyServiceMasterService companyServiceMasterService;

    @Autowired
    private ItemMasterRepository itemMasterRepository;

    private Logger logger = LoggerFactory.getLogger(BillingMastersService.class);
    private String errorMessage;

    /**
     * Get all information of a service by service code. This is a batch operation
     *
     * @param dtos given.
     * @param unitCode given.
     * @return list of {@link UnitWiseService}
     */
    public Set<UnitWiseService> findAllServiceInfoByUnit(Set<UnitWiseService> dtos, String unitCode) {
        if (dtos.isEmpty()) {
            logger.error("Empty request.");
            return Collections.emptySet();
        }

        dtos.forEach(unitWiseService -> {
            UnitServiceMapper unitServiceMapper = unitServiceMapperService.getUnitServiceMapper(unitCode, unitWiseService.getServiceCode());
            if (unitServiceMapper == null) {
                ItemMaster itemMaster = itemMasterRepository.findByCodeIgnoreCase(unitWiseService.getServiceCode());
                if (itemMaster == null) {
                    return; //continue
                }

                unitWiseService.setItemMaster(itemMaster);
                return;
            }
            unitWiseService.setUnitServiceMapper(unitServiceMapper);
            ServiceMaster serviceMaster = unitServiceMapper.getServiceMaster();
            unitWiseService.setServiceMaster(serviceMaster);
            unitWiseService.setServiceCode(BaseMaster.getCode(serviceMaster));
            unitWiseService.setGroupCode(BaseMaster.getCode(serviceMaster.getGroupMaster()));
            unitWiseService.setSubGroupCode(BaseMaster.getCode(serviceMaster.getSubGroupMaster()));
            //FIXME need to bind the rest based on request
        });

        return dtos;
    }

    /**
     * Find all dependent services based on given
     * @param dto given dto of tyoe {@link FindAllDependentServicesDTO}
     * @return list of {@link DependentService} objects
     */
    public Collection<DependentService> findAllDependentServices(final FindAllDependentServicesDTO dto) {

        if (dto == null) {
            logger.error("findAllDependentServices; null request body");
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "findAllDependentServices; null request body");
        }
        if (CollectionUtils.isEmpty(dto.getServiceCodeCollection()) && CollectionUtils.isEmpty(dto.getUnitServiceMapperIdCollection())) {
             logger.warn("findAllDependentServices; No services found in the request.");
             return Collections.emptyList();
         }

        final Collection<UnitServiceMapper> unitServiceMapperCollection = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(dto.getUnitServiceMapperIdCollection())) {
            for (Long id: dto.getUnitServiceMapperIdCollection()) {
                final UnitServiceMapper unitServiceMapper = unitServiceMapperService.findById(id);
                unitServiceMapperCollection.add(unitServiceMapper);
            }
        }
        if (CollectionUtils.isEmpty(dto.getUnitServiceMapperIdCollection()) && CollectionUtils.isNotEmpty(dto.getServiceCodeCollection())) {
            for (String serviceCode : dto.getServiceCodeCollection()) {
                final UnitServiceMapper unitServiceMapper = unitServiceMapperService.getUnitServiceMapper(dto.getUnitCode(), serviceCode);
                unitServiceMapperCollection.add(unitServiceMapper);
            }
        }

        final Collection<DependentUnitServiceMapper> dependentUnitServiceMapperCollection = dependentUnitServiceMapperRepository.findAllByParentUnitServiceMapperIn(unitServiceMapperCollection);

        if (CollectionUtils.isEmpty(dependentUnitServiceMapperCollection)) {
            logger.warn("findAllDependentServices; no dependent services found.");
            return Collections.emptyList();
        }
        return translate(dependentUnitServiceMapperCollection);
    }

    /**
     * Translate Entity to DTO
     *
     * @param dependentUnitServiceMapperCollection given dto {@link DependentUnitServiceMapper} list.
     * @return list of {@link DependentService} objects.
     */
    private Collection<DependentService> translate(Collection<DependentUnitServiceMapper> dependentUnitServiceMapperCollection) {
        final Collection<DependentService> responseCollection = new ArrayList<>(dependentUnitServiceMapperCollection.size());

        for (DependentUnitServiceMapper dependentUnitServiceMapper : dependentUnitServiceMapperCollection) {
            final DependentService response = new DependentService();
            ServiceMaster serviceMaster = dependentUnitServiceMapper.getParentUnitServiceMapper().getServiceMaster();
            response.setServiceCode(BaseMaster.getCode(serviceMaster));
            response.setServiceName(BaseMaster.getDesc(serviceMaster));
            response.setServiceGroupCode(BaseMaster.getCode(serviceMaster.getGroupMaster()));
            response.setServiceGroupName(BaseMaster.getDesc(serviceMaster.getGroupMaster()));
            response.setServiceSubGroupCode(BaseMaster.getCode(serviceMaster.getSubGroupMaster()));
            response.setServiceSubGroupName(BaseMaster.getDesc(serviceMaster.getSubGroupMaster()));
            response.setCompanyServiceCode(response.getServiceCode()); //FIXME need to change once company wise code is set.
            response.setCompanyServiceName(response.getServiceName());
            ServiceMaster dependentServiceMaster = dependentUnitServiceMapper.getChildService();
            response.setDependentServiceCode(BaseMaster.getCode(dependentServiceMaster));
            response.setDependentServiceName(BaseMaster.getDesc(dependentServiceMaster));
            response.setDependentServiceGroupCode(BaseMaster.getCode(dependentServiceMaster.getGroupMaster()));
            response.setDependentServiceGroupName(BaseMaster.getDesc(dependentServiceMaster.getGroupMaster()));
            response.setDependentServiceSubGroupCode(BaseMaster.getCode(dependentServiceMaster.getSubGroupMaster()));
            response.setDependentServiceSubGroupName(BaseMaster.getDesc(dependentServiceMaster.getSubGroupMaster()));
            response.setDependentCompanyServiceCode(response.getDependentServiceCode()); //FIXME need to change once company wise code is set.
            response.setDependentCompanyServiceName(response.getDependentServiceName());
            response.setIsOverriddenRate(dependentUnitServiceMapper.getOverrideConfiguredRate());
            response.setPercentageOnActualServiceRate(dependentUnitServiceMapper.getPercentageOnParentServiceRate());
            response.setFlatRate(dependentUnitServiceMapper.getFlatServiceRate());

            responseCollection.add(response);
        }

        return responseCollection;
    }

    /**
     * service to find list of {@link UnitWiseService} by given filter {@link UnitWiseBillingChargesDTO}.
     * Gets the list of active services for given unit and also if in valid date range.
     *
     * @param chargesDTO a valid {@link UnitWiseBillingChargesDTO} object.
     * @return a list of {@link UnitWiseService}.
     * @throws {@link IllegalArgumentException} if request data is not valid.
     */
    public List<UnitWiseService> findAllUnitServiceMapperByFilter(final UnitWiseBillingChargesDTO chargesDTO) {
        if (!validateUnitWiseBillingChargesDTO(chargesDTO)) {
            logger.error("Unit header missing.");
            throw new IllegalArgumentException("Unit header missing");
        }

        final UnitMaster unitMaster = unitMasterService.findByCode(chargesDTO.getUnitCode());
        if (unitMaster == null) {
            logger.warn("unitMaster not found.");
            return Collections.emptyList();
        }

        List<UnitServiceMapper> unitServiceMapperList;
        if(StringUtils.isNotBlank(chargesDTO.getServiceCode())) {
            unitServiceMapperList = Collections.singletonList(unitServiceMapperService.findByCode(chargesDTO.getServiceCode()));
        } else if (StringUtils.isNotBlank(chargesDTO.getSearchKey())) {
            unitServiceMapperList = unitServiceMapperService.findAllByUnitMaster(unitMaster, chargesDTO.getSearchKey(), chargesDTO.getPageSize());
        } else {
            logger.error("serviceCode and search key are blank. One of them must be valid.");
            throw new HttpMessageNotReadableException("Bad request.");
        }

        if (unitServiceMapperList == null || CollectionUtils.isEmpty(unitServiceMapperList)) {
            logger.warn("No unit wise service for the given unitCode {}", chargesDTO.getUnitCode());
            return Collections.emptyList();
        }

        //FIXME: implement streams
        final List<UnitWiseService> unitWiseServiceList = new ArrayList<>();
        for (final UnitServiceMapper unitServiceMapper : unitServiceMapperList) {
            if (unitServiceMapper.getActive() && unitServiceMapper.getServiceMaster().getActive()) {
                if (BooleanUtils.isTrue(unitServiceMapper.getIsPackage())) {
                    continue;
                }
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
     * service to find list of {@link UnitServiceCostCenterMapper} by given filter {@link UnitWiseBillingChargesDTO}
     *
     * @param chargesDTO a valid {@link UnitWiseBillingChargesDTO} object.
     * @return a list of {@link UnitWiseCostCenter}.
     * @throws {@link IllegalArgumentException} if request data is not valid.
     */
    public List<UnitWiseCostCenter> findAllUnitServiceCostCenterMapperByFilter(final UnitWiseBillingChargesDTO chargesDTO) {
        if (!validateUnitWiseCostCenterBillingChargesDTO(chargesDTO)) {
            logger.error("Bad request body. {}", chargesDTO);
            throw new IllegalArgumentException("Bad request body.");
        }

        UnitServiceMapper unitServiceMapper;
        final String unitServiceMapperId = chargesDTO.getUnitServiceMapperId();
        final String serviceCode = chargesDTO.getServiceCode();
        if (StringUtils.isNotBlank(unitServiceMapperId)) {
            unitServiceMapper = unitServiceMapperService.findById(Long.valueOf(chargesDTO.getUnitServiceMapperId()));
        } else if (StringUtils.isNotBlank(serviceCode)) {
            unitServiceMapper = unitServiceMapperService.getUnitServiceMapper(chargesDTO.getUnitCode(), serviceCode);
        } else {
            throw new IllegalArgumentException("findAllUnitServiceCostCenterMapperByFilter; Neither unitServiceMapperId, nor serviceCode are provided.");
        }

        if (unitServiceMapper == null) {
            logger.warn("findAllUnitServiceCostCenterMapperByFilter; unitServiceMapper not found.");
            return Collections.emptyList();
        }

        final List<UnitServiceCostCenterMapper> unitServiceCostCenterMapperList = unitServiceCostCenterMapperService.findAllByUnitServiceMapper(unitServiceMapper);
        final List<UnitWiseCostCenter> unitWiseCostCenterList = new ArrayList<>(unitServiceCostCenterMapperList.size());
        for (final UnitServiceCostCenterMapper unitServiceCostCenterMapper : unitServiceCostCenterMapperList) {
            final UnitWiseCostCenter unitWiseCostCenter = new UnitWiseCostCenter();
            unitWiseCostCenter.setUnitServiceCostCenterMapperId(unitServiceCostCenterMapper.getId());
            unitWiseCostCenter.setUnitServiceMapperId(unitServiceMapper.getId());
            unitWiseCostCenter.setServiceCode(unitServiceMapper.getServiceMaster().getCode());
            unitWiseCostCenter.setCostCenterDescription(unitServiceCostCenterMapper.getCostCenterMaster().getDesc());
            unitWiseCostCenter.setCostCenterCode(unitServiceCostCenterMapper.getCostCenterMaster().getCode());
            unitWiseCostCenter.setIsDefault(unitServiceCostCenterMapper.getDefaultCostCenter());

            if (unitServiceCostCenterMapper.getEmployeeMasterDetails() != null) {
                unitWiseCostCenter.setDoctorId(unitServiceCostCenterMapper.getEmployeeMasterDetails().getId());
                unitWiseCostCenter.setDoctorEmpNo(unitServiceCostCenterMapper.getEmployeeMasterDetails().getEmployeeNo());
            }

            if (unitServiceCostCenterMapper.getDepartmentMaster() != null) {
                unitWiseCostCenter.setDepartmentCode(unitServiceCostCenterMapper.getDepartmentMaster().getCode());
                unitWiseCostCenter.setDepartmentName(unitServiceCostCenterMapper.getDepartmentMaster().getDesc());
            }

            unitWiseCostCenterList.add(unitWiseCostCenter);
        }

        return unitWiseCostCenterList;
    }

    /**
     * Find service details {@link ServiceDetailsDTO} by given request.
     *
     * @param request a valid object of type {@link UnitWiseTariffRequest}
     * @return an object of type {@link ServiceDetailsDTO}. {@link ServiceDetailsDTO#isFetchSuccess()} is false if any error.
     */
    public ServiceDetailsDTO findServiceDetails(final UnitWiseTariffRequest request) {
        if (request == null) {
            errorMessage = "findServiceDetails; null request.";
            logger.error(errorMessage);
            final ServiceDetailsDTO serviceDetailsDTO = new ServiceDetailsDTO();
            serviceDetailsDTO.setErrorMessage(errorMessage);

            return serviceDetailsDTO;
        }
        final ServiceDetailsDTO response = new ServiceDetailsDTO();

        final UnitTariffServiceRateMaster unitTariffServiceRateMaster = findUnitTariffServiceRateMasterByRequest(request);
        if (unitTariffServiceRateMaster == null) {
            errorMessage = "findServiceDetails; rates not available for given request : ";
            logger.error(errorMessage + "{}", request);
            final ServiceDetailsDTO serviceDetailsDTO = new ServiceDetailsDTO();
            serviceDetailsDTO.setErrorMessage(errorMessage + request);

            return serviceDetailsDTO;
        }
        response.setUnitServiceMapper(unitTariffServiceRateMaster.getUnitServiceMapper());
        response.setRate(new Double(unitTariffServiceRateMaster.getRate()));

        final FindAllDependentServicesDTO findAllDependentServicesDTO = new FindAllDependentServicesDTO();
        findAllDependentServicesDTO.setServiceCodeCollection(Collections.singleton(request.getServiceCode()));
        response.setDependentServiceCollection(findAllDependentServices(findAllDependentServicesDTO));

        if (!request.getPayerCode().equalsIgnoreCase("self")) {
            final CompanyAndServiceRequest companyAndServiceRequest = new CompanyAndServiceRequest();
            companyAndServiceRequest.setCompanyCode(request.getPayerCode());
            companyAndServiceRequest.setServiceCode(request.getServiceCode());
            final UnitMaster unitMaster = unitTariffServiceRateMaster.getUnitMaster();
            if (unitMaster == null) {
                errorMessage = "unit code not found or empty.";
                logger.error(errorMessage);
                final ServiceDetailsDTO serviceDetailsDTO = new ServiceDetailsDTO();
                serviceDetailsDTO.setErrorMessage(errorMessage);

                return serviceDetailsDTO;
            }
            response.setCompanyServiceCode(BaseMaster.getCode(companyServiceMasterService.getByComAndSer(companyAndServiceRequest, NumberParseAssistant.parseLong(unitMaster.getId()))));
        }
        response.setUnitCode(BaseMaster.getCode(response.getUnitServiceMapper().getUnitMaster()));
        response.setOrgCode(BaseMaster.getCode(response.getUnitServiceMapper().getOrganizationMaster()));
        response.setFinancialClassCode(BaseMaster.getCode(unitTariffServiceRateMaster.getFinancialClassMaster()));
        response.setServiceCode(request.getServiceCode());
        response.setTariffCode(request.getTariffCode());
        response.setVisitTypeCode(request.getVisitTypeCode());
        response.setCreatedBy(response.getUnitServiceMapper().getServiceMaster().getCreatedBy());
        response.setFetchSuccess(true);
        response.setFreeze(true);

        return response;
    }

    /**
     * Get {@link UnitTariffServiceRateMaster} by given {@link UnitWiseTariffRequest}
     *
     * @param request a valid {@link UnitWiseTariffRequest} object.
     * @return the {@link UnitTariffServiceRateMaster} object.
     */
    public UnitTariffServiceRateMaster findUnitTariffServiceRateMasterByRequest(final UnitWiseTariffRequest request) {
        if (!validateUnitWiseTariffAgainstRequest(request)) {
            logger.error("Bad request body. {}", request);
            throw new IllegalArgumentException("Bad request body.");
        }

        final UnitServiceMapper unitServiceMapper = unitServiceMapperService.getUnitServiceMapper(request.getUnitCode(), request.getServiceCode());
        if(unitServiceMapper == null) {
            logger.error("unitServiceMapper not found for unitCode = {}, serviceCode = {}", request.getUnitCode(), request.getServiceCode());
            return null;
        }

        List<UnitTariffServiceRateMaster> unitTariffServiceRateMasterList = unitTariffServiceRateMasterService.findByRequest(request.getFinancialClassCode(), request.getTariffCode(),
                request.getVisitTypeCode(), unitServiceMapper);

        if (CollectionUtils.isEmpty(unitTariffServiceRateMasterList)) {
            return null;
        }

        Date recentDate = unitTariffServiceRateMasterList.get(0).getEffectiveDate();
        UnitTariffServiceRateMaster recentTariff = unitTariffServiceRateMasterList.get(0);
        for(final UnitTariffServiceRateMaster unitTariffServiceRateMaster : unitTariffServiceRateMasterList) {
            if (unitTariffServiceRateMaster.getEffectiveDate().before(new Date()) && unitTariffServiceRateMaster.getEffectiveDate().after(recentDate)) {
                recentDate = unitTariffServiceRateMaster.getEffectiveDate();
                recentTariff = unitTariffServiceRateMaster;
            }
        }

        return recentTariff;
    }

    /**
     * Validates the {@link UnitWiseTariffRequest}
     *
     * @param request given {@link UnitWiseTariffRequest} object.
     * @return true if valid. Else false.
     */
    private boolean validateUnitWiseTariffAgainstRequest(final UnitWiseTariffRequest request) {
        return StringUtils.isNotBlank(request.getServiceCode()) && StringUtils.isNotBlank(request.getUnitCode());
    }

    /**
     * Validates the {@link UnitWiseBillingChargesDTO}
     *
     * @param serviceDTO given {@link UnitWiseBillingChargesDTO} object.
     * @return true if valid. Else false.
     */
    private boolean validateUnitWiseCostCenterBillingChargesDTO(final UnitWiseBillingChargesDTO serviceDTO) {
        if (serviceDTO == null) {
            return false;
        }
        if (StringUtils.isBlank(serviceDTO.getUnitServiceMapperId()) &&
                StringUtils.isBlank(serviceDTO.getServiceCode())) {
            return false;
        }
        return true;
    }

    /**
     * Validates the {@link UnitWiseBillingChargesDTO}
     *
     * @param serviceDTO given {@link UnitWiseBillingChargesDTO} object.
     * @return true if valid. Else false.
     */
    private boolean validateUnitWiseBillingChargesDTO(final UnitWiseBillingChargesDTO serviceDTO) {
        if (serviceDTO == null) {
            return false;
        }
        return true;
    }
}

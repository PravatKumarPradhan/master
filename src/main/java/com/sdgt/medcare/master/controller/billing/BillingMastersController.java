package com.sdgt.medcare.master.controller.billing;

import com.core.base.rest.util.HttpUtils;
import com.core.base.util.RESTCommonAssistant;
import com.core.constants.HttpHeaders;
import com.sdgt.medcare.master.dto.billing.BatchTariffRate;
import com.sdgt.medcare.master.dto.billing.DependentService;
import com.sdgt.medcare.master.dto.billing.FindAllDependentServicesDTO;
import com.sdgt.medcare.master.dto.billing.ServiceDetailsDTO;
import com.sdgt.medcare.master.dto.billing.UnitWiseBillingChargesDTO;
import com.sdgt.medcare.master.dto.billing.UnitWiseCostCenter;
import com.sdgt.medcare.master.dto.billing.UnitWiseService;
import com.sdgt.medcare.master.dto.billing.UnitWiseTariffRequest;
import com.sdgt.medcare.master.entity.unit.UnitServiceCostCenterMapper;
import com.sdgt.medcare.master.entity.unit.UnitTariffServiceRateMaster;
import com.sdgt.medcare.master.service.billing.BillingMastersService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@CrossOrigin
@RestController
@RequestMapping("/billing-masters")
public class BillingMastersController {
    //TODO needs ControllerAdvise
	
    @Autowired
    private BillingMastersService billingMastersService;

    /**
     * Find all dependent services based on given {@link FindAllDependentServicesDTO}
     *
     * @return Status 200. List of {@link DependentService}.
     *         Status 204. If the no content found.
     */
    @PostMapping("/dependent/services")
    public ResponseEntity findAllDependentServices(@RequestBody FindAllDependentServicesDTO dto) {
        final Collection<DependentService> responseCollection = billingMastersService.findAllDependentServices(dto);

        return RESTCommonAssistant.buildListResponse(responseCollection);
    }

    /**
     * service to find list of {@link UnitWiseService} by given filter
     * {@link UnitWiseBillingChargesDTO}
     *
     * @return Status 200. List of {@link UnitWiseService}.
     *         Status 204. If the no content found.
     */
    @GetMapping("/unit-wise-services")
    public ResponseEntity<List<UnitWiseService>> findAllUnitWiseServicesByFilterForCharges (@RequestParam(value = "searchKey", required = false) String searchKey,
                                                                                            @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                                                            @RequestParam(value = "serviceCode", required = false) String code) {
        final String unitCode = HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
        final UnitWiseBillingChargesDTO chargesDTO = new UnitWiseBillingChargesDTO();
        chargesDTO.setSearchKey(StringUtils.isBlank(searchKey) ? "%" : searchKey);
        chargesDTO.setServiceCode(code);
        chargesDTO.setPageSize(pageSize);
        chargesDTO.setUnitCode(unitCode);


        final List<UnitWiseService> unitWiseServiceList = billingMastersService.findAllUnitServiceMapperByFilter(chargesDTO);
        if(CollectionUtils.isEmpty(unitWiseServiceList)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(unitWiseServiceList);
    }

    /**
     * service to find list of {@link UnitServiceCostCenterMapper} by given filter {@link UnitWiseBillingChargesDTO}
     *
     * @return Status 200. List of {@link UnitServiceCostCenterMapper}.
     *         Status 204. If the no content found.
     */
    @GetMapping("/unit-wise-service-cost-centers")
    public ResponseEntity<List<UnitWiseCostCenter>> findAllUnitServiceCostCenterMapperByFilterForCharges (final @RequestParam(value = "unitServiceMapperId", required = false) String unitServiceMapperId,
                                                                                                          final @RequestParam(value = "serviceCode", required = false) String serviceCode) {
        final UnitWiseBillingChargesDTO chargesDTO = new UnitWiseBillingChargesDTO();
        chargesDTO.setUnitServiceMapperId(unitServiceMapperId);
        chargesDTO.setServiceCode(serviceCode);

        final List<UnitWiseCostCenter> unitWiseCostCenterList = billingMastersService.findAllUnitServiceCostCenterMapperByFilter(chargesDTO);
        if(CollectionUtils.isEmpty(unitWiseCostCenterList)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(unitWiseCostCenterList);
    }

    public ResponseEntity findAllDependentServices() {
        return null;
    }

    /**
     * Get the {@link UnitTariffServiceRateMaster} for the given request.
     *
     * Requirements:
     *      33375 - Need a facility to define Company wise service code
     *      TODO - Identify other requirements
     *
     * @param financialClassCode a valid code from {@link com.sdgt.medcare.master.entity.org.FinancialClassMaster}.
     * @param tariffCode a valid code from {@link com.sdgt.medcare.master.entity.org.TariffMaster}. This is also company code.
     * @param visitTypeCode a valid code from {@link com.sdgt.medcare.master.entity.global.VisitTypeMaster}.
     * @param serviceCode a valid code from {@link com.sdgt.medcare.master.entity.unit.UnitServiceMapper}.
     * @return Status 200. The {@link UnitTariffServiceRateMaster} object.
     *         Status 204. If the {@link UnitTariffServiceRateMaster} is not found for the given.
     */
    @Deprecated
    @GetMapping("/unit-wise-tariff-rate")
    public ResponseEntity<UnitTariffServiceRateMaster> findTariffByService(final @RequestParam(value = "financialClassCode", required = false) String financialClassCode,
                                                      final @RequestParam(value = "tariffCode", required = false) String tariffCode,
                                                      final @RequestParam(value = "visitTypeCode", required = false) String visitTypeCode,
                                                      final @RequestParam(value = "serviceCode") String serviceCode) {
        final UnitWiseTariffRequest request = UnitWiseTariffRequest.builder()
                .financialClassCode(financialClassCode)
                .tariffCode(tariffCode)
                .visitTypeCode(visitTypeCode)
                .serviceCode(serviceCode)
                .unitCode(HttpUtils.getHeader(HttpHeaders.UNIT_CODE))
                .build();
        final UnitTariffServiceRateMaster unitTariffServiceRateMaster = billingMastersService.findUnitTariffServiceRateMasterByRequest(request);
        if (unitTariffServiceRateMaster == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(unitTariffServiceRateMaster);
    }

    /**
     * Get the {@link UnitTariffServiceRateMaster} for the given request.
     *
     * Requirements:
     *      33375 - Need a facility to define Company wise service code
     *      TODO - Identify other requirements
     *
     * @param financialClassCode a valid code from {@link com.sdgt.medcare.master.entity.org.FinancialClassMaster}.
     * @param tariffCode a valid code from {@link com.sdgt.medcare.master.entity.org.TariffMaster}. This is also company code.
     * @param visitTypeCode a valid code from {@link com.sdgt.medcare.master.entity.global.VisitTypeMaster}.
     * @param serviceCode a valid code from {@link com.sdgt.medcare.master.entity.unit.UnitServiceMapper}.
     * @return Status 200. The {@link UnitTariffServiceRateMaster} object.
     */
    @GetMapping("/services/{serviceCode}/details")
    public ServiceDetailsDTO findServiceDetails(final @RequestParam(value = "financialClassCode", required = false) String financialClassCode,
                                                                           final @RequestParam(value = "tariffCode", required = false) String tariffCode,
                                                                           final @RequestParam(value = "visitTypeCode", required = false) String visitTypeCode,
                                                                           final @RequestParam(value = "payerCode", required = false) String payerCode,
                                                                           final @PathVariable(value = "serviceCode") String serviceCode) {
        final UnitWiseTariffRequest request = UnitWiseTariffRequest.builder()
                .financialClassCode(financialClassCode)
                .tariffCode(tariffCode)
                .payerCode(payerCode)
                .visitTypeCode(visitTypeCode)
                .serviceCode(serviceCode)
                .unitCode(HttpUtils.getHeader(HttpHeaders.UNIT_CODE))
                .build();

        return billingMastersService.findServiceDetails(request);
    }

    @PostMapping("/batch-tariff-rates")
    public ResponseEntity<List<UnitTariffServiceRateMaster>> findTariffForServiceList(@RequestBody BatchTariffRate batchTariffRate) {
        final List<UnitTariffServiceRateMaster> unitTariffServiceRateMasterList = new ArrayList<>();

        for(final String serviceCode : batchTariffRate.getServiceCodeList()) {
            final UnitWiseTariffRequest request = UnitWiseTariffRequest.builder()
                    .financialClassCode(batchTariffRate.getFinancialClassCode())
                    .tariffCode(batchTariffRate.getTariffCode())
                    .visitTypeCode(batchTariffRate.getVisitTypeCode())
                    .serviceCode(serviceCode)
                    .unitCode(HttpUtils.getHeader(HttpHeaders.UNIT_CODE))
                    .build();
            unitTariffServiceRateMasterList.add(billingMastersService.findUnitTariffServiceRateMasterByRequest(request));
        }

        return ResponseEntity.ok(unitTariffServiceRateMasterList);
    }

    /**
     * Get service information for given.
     *
     * @apiNote  : As of now this only support serviceCode.
     * @param dtos given
     *
     * @return list of {@link UnitWiseService}
     */
    @PostMapping("/services/info/byUnit")
    public Set<UnitWiseService> findAllServiceInformationByUnit(@RequestBody Set<UnitWiseService> dtos) {
        String unitCode = HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
        return billingMastersService.findAllServiceInfoByUnit(dtos, unitCode);
    }
}
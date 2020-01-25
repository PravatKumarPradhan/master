package com.sdgt.medcare.master.controller.billing;

import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.sdgt.medcare.master.dto.billing.PackageRequest;
import com.sdgt.medcare.master.dto.billing.PackageResponse;
import com.sdgt.medcare.master.dto.billing.PackageTypeResponse;
import com.sdgt.medcare.master.dto.billing.SavePackageRequest;
import com.sdgt.medcare.master.dto.billing.ServiceRateRequest;
import com.sdgt.medcare.master.dto.billing.UnitWiseBillingChargesDTO;
import com.sdgt.medcare.master.dto.billing.UnitWiseService;
import com.sdgt.medcare.master.dto.billing.UnitWiseTariffRequest;
import com.sdgt.medcare.master.entity.billing.packageservice.PackageMaster;
import com.sdgt.medcare.master.entity.org.TariffMaster;
import com.sdgt.medcare.master.entity.unit.UnitTariffServiceRateMaster;
import com.sdgt.medcare.master.service.billing.BillingMastersService;
import com.sdgt.medcare.master.service.billing.PackageService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@CrossOrigin
@RestController
@RequestMapping("/package-masters")
public class PackageController {
    //TODO controller Advise
    @Autowired
    private PackageService packageService;
    @Autowired
    private BillingMastersService billingMastersService;

    /**
     * Get the {@link UnitTariffServiceRateMaster} for the given request.
     *
     * @param financialClassCode a valid code from {@link com.sdgt.medcare.master.entity.org.FinancialClassMaster}.
     * @param tariffCode a valid code from {@link com.sdgt.medcare.master.entity.org.TariffMaster}.
     * @param visitTypeCode a valid code from {@link com.sdgt.medcare.master.entity.global.VisitTypeMaster}.
     * @param serviceCode a valid code from {@link com.sdgt.medcare.master.entity.org.ServiceMaster}.
     * @return Status 200. The {@link UnitTariffServiceRateMaster} object.
     *         Status 204. If the {@link UnitTariffServiceRateMaster} is not found for the given.
     */
    @GetMapping("/unit-wise-tariff-rate")
    public ResponseEntity<UnitTariffServiceRateMaster> findTariffByService(final @RequestParam("financialClassCode") String financialClassCode,
                                                                           final @RequestParam("tariffCode") String tariffCode,
                                                                           final @RequestParam("visitTypeCode") String visitTypeCode,
                                                                           final @RequestParam("serviceCode") String serviceCode) {
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
     * Gets list of all {@link PackageMaster} for the given unit code from the HTTP header.
     *
     * @return 200 - list of {@link PackageMaster}
     *         204 - No content.
     */
    @CrossOrigin
    @GetMapping(value = "/find-all-package",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<PackageMaster>> findAllPackageMaster() {
        final List<PackageMaster> packageMasterList = packageService.findAllPackageMaster(
                HttpUtils.getHeader(HttpHeaders.UNIT_CODE));

        if (CollectionUtils.isEmpty(packageMasterList)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(packageMasterList);
    }

    /**
     * Testing purpose only
     *
     * @return
     */
    @Deprecated
    @GetMapping("/packages")
    public Collection<PackageMaster> findAllPackage() {
        final Collection<PackageMaster> packageMasterCollection = packageService.findAllPackageMaster(HttpUtils.getHeader(HttpHeaders.UNIT_CODE));

        return packageMasterCollection;
    }


    /**
     * Gets all {@link PackageTypeResponse}.
     *
     * @return Status 200. List of {@link PackageTypeResponse}.
     *         Status 204. If the no content found.
     */
    @GetMapping("/find-all-package-type")
    public ResponseEntity<List<PackageTypeResponse>> findAllPackageType() {
        final List<PackageTypeResponse> packageTypeResponseList = packageService.findAllPackageType();

        if (CollectionUtils.isEmpty(packageTypeResponseList)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(packageTypeResponseList);
    }

    /**
     * service to find list of {@link UnitWiseService} by given filter and if of type package.
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


        final List<UnitWiseService> unitWiseServiceList = packageService.findAllUnitServiceMapperByFilter(chargesDTO);
        if(CollectionUtils.isEmpty(unitWiseServiceList)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(unitWiseServiceList);
    }

    /**
     * Get all {@link TariffMaster} by given unit code.
     *
     * @return Status 200. List of {@link TariffMaster} object.
     *         Status 204. If the no content found.
     */
    @GetMapping("/tariff-list")
    public ResponseEntity<List<TariffMaster>> findAllTariffByUnitCode() {
        final String unitCode = HttpUtils.getHeader(HttpHeaders.UNIT_CODE);

        final List<TariffMaster> tariffMasterList = packageService.findAllTariffByUnitCode(unitCode);

        if (CollectionUtils.isEmpty(tariffMasterList)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tariffMasterList);
    }

    /**
     * Get all details related to package for given service code of package type.
     *
     * @param packageCode a valid service code of package type.
     * @return Status 200 {@link PackageResponse} object.
     *         Status 204 is not found.
     */
    @Deprecated
    public ResponseEntity<PackageResponse> getPackageDetail(@RequestParam("packageCode") String packageCode) {
        final PackageRequest request = new PackageRequest();
        request.setOrgCode(HttpUtils.getHeader(HttpHeaders.ORG_CODE));
        request.setUnitCode(HttpUtils.getHeader(HttpHeaders.UNIT_CODE));
        request.setPackageCode(packageCode);
        final PackageResponse response = packageService.getPackageDetail(request);

        if(response.getPackageMaster() == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Save EHC Package.
     *
     * @param request dto of type {@link SavePackageRequest}
     * @return {@link ResponseEntity}. 200 if save successfully.
     */
    @PostMapping("/save-package")
    public ResponseEntity savePackage(final @RequestBody SavePackageRequest request) {
        packageService.savePackage(request);
        return ResponseEntity.ok("{\"success\" : \"true\" }");
    }

    @GetMapping("/is-package-available")
        public ResponseEntity<Boolean> isUnique(final @RequestParam(value = "packageName", required = false) String packageName,
                                            final @RequestParam(value = "packageCode", required = false) String packageCode) {
        return ResponseEntity.ok(packageService.isUnique(packageName, packageCode));
    }

    /**
     * Get service rate by given {@link ServiceRateRequest}
     *
     * @param request a valid {@link ServiceRateRequest}
     * @return a list of {@link UnitTariffServiceRateMaster}
     */
    @PostMapping("/tariff-wise-service-rate")
    public ResponseEntity<List<UnitTariffServiceRateMaster>> getServiceRate(final @RequestBody ServiceRateRequest request) {
        request.setUnitCode(HttpUtils.getHeader(HttpHeaders.UNIT_CODE));
        final List<UnitTariffServiceRateMaster> unitTariffServiceRateMasterList = packageService.findAllServiceRate(request);

        if (CollectionUtils.isEmpty(unitTariffServiceRateMasterList)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(unitTariffServiceRateMasterList);
    }
}

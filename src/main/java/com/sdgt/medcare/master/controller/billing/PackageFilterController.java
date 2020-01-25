package com.sdgt.medcare.master.controller.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import com.sdgt.medcare.master.dto.billing.PackageFilterRequest;
import com.sdgt.medcare.master.dto.billing.PackageFilterResponse;
import com.sdgt.medcare.master.dto.billing.ServiceFilterRequest;
import com.sdgt.medcare.master.dto.billing.ServiceFilterResponse;
import com.sdgt.medcare.master.service.billing.PackageService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/package/filter/")
public class PackageFilterController extends BaseDTO {
    @Autowired private PackageService packageService;
    /**
     * Find all services by given {@link ServiceFilterRequest}
     * @param request given.
     * @return {@link ServiceFilterResponse} list.
     */
    @PostMapping(value = {"/service", "/service/"})
    public @ResponseBody
    ResponseEntity<List<ServiceFilterResponse>> findAllService(final @RequestBody ServiceFilterRequest request) {
        final List<ServiceFilterResponse> responseList = packageService.findAllService(request);

        if (CollectionUtils.isEmpty(responseList)) {
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(responseList);
    }

    /**
     * Find package by given filter
     *
     * @param request valid {@link PackageFilterRequest}
     * @return {@link ResponseEntity<List<PackageFilterResponse>>}
     */
    @PostMapping("/")
    public ResponseEntity<List<PackageFilterResponse>> findAllPackages(final @RequestBody PackageFilterRequest request) {
        final List<PackageFilterResponse> responseList = packageService.findAllPackages(request);

        if (CollectionUtils.isEmpty(responseList)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(responseList);
    }
}

package com.sdgt.medcare.master.controller.common;

import com.core.base.util.RESTCommonAssistant;
import com.sdgt.medcare.master.dto.common.ServiceDTO;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.search.GlobalSearchRequest;
import com.sdgt.medcare.master.search.ServiceSpecification;
import com.sdgt.medcare.master.service.common.ServicesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;

/**
 * {@link ServiceController} is used to get and post the service details
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *  @author Pravat Kumar Pradhan
 * @author Karthik Chandra
 *
 */
@Api(value = "services",description = "Fetch or save service provided.")
@CrossOrigin
@RestController
@RequestMapping("/services")
public class ServiceController {
    @Autowired
    private ServicesService servicesService;

    @Autowired
    ServiceSpecification specification;

    /**
     * Api to get service.
     *
     * @param key   given key [ID,CODE,GROUPCODE,SUBGROUPCODE,DESC, ]
     * @param value corresponding value
     *
     * @return list of {@link ServiceDTO}
     */
    @ApiOperation (value = "Fetch service details by given [id, code, desc, groupCode, subGroupCode,<empty>]", response = ResponseEntity.class)
    @ApiResponses (value = {
          @ApiResponse (code = 200, message = "Successfully retrieved list"),
          @ApiResponse (code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping ({"/", "/{key}/{value}"})
    public ResponseEntity findService(final @PathVariable (value = "key", required = false) String key,
                                      final @PathVariable (value = "value", required = false) String value) {

        Collection<ServiceDTO> serviceDTOCollection = new HashSet<>();
        if (StringUtils.isNotBlank(key)) {
            switch (key.toLowerCase()) {
                case "id":
                    serviceDTOCollection = servicesService.findById(value);
                    break;
                case "code":
                    serviceDTOCollection = servicesService.findByCode(value);
                    break;
                case "groupcode":
                    serviceDTOCollection = servicesService.findByGroupCode(value);
                    break;
                case "subgroupcode":
                    serviceDTOCollection = servicesService.findBySubGroupCode(value);
                    break;
                case "desc":
                    serviceDTOCollection = servicesService.findByDesc(value);
                    break;
                default:
                    serviceDTOCollection = servicesService.findAll();
            }
        } else {
            serviceDTOCollection = servicesService.findAll();
        }

        return RESTCommonAssistant.buildListResponse(serviceDTOCollection);
    }

    /**
     * Api to get list of service by given ids.
     *
     * @param ids given.
     *
     * @return list of {@link ServiceDTO}
     */
    @ApiOperation (value = "Fetch service details by given [id, code, desc, groupCode, subGroupCode,<empty>]", response = ResponseEntity.class)
    @ApiResponses (value = {
          @ApiResponse (code = 200, message = "Successfully retrieved list"),
          @ApiResponse (code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping ({"/"})
    public @ResponseBody
    ResponseEntity findByIds(final @RequestBody Collection<String> ids) {
        return RESTCommonAssistant.buildListResponse(servicesService.findByIds(ids));
    }

    /**
     * Api to get list of service by given ids.
     *
     * @param codes given.
     *
     * @return list of {@link ServiceDTO}
     */
    @ApiOperation (value = "Fetch service details by given [id, code, desc, groupCode, subGroupCode,<empty>]", response = ResponseEntity.class)
    @ApiResponses (value = {
          @ApiResponse (code = 200, message = "Successfully retrieved list"),
          @ApiResponse (code = 404, message = "The resource you were trying to reach is not found")
    })
    public @ResponseBody
    ResponseEntity findByCodes(final @RequestBody Collection<String> codes) {
        return RESTCommonAssistant.buildListResponse(servicesService.findByCodes(codes));
    }

    @PostMapping ("/search")
    public Page<ServiceMaster>search(@RequestBody GlobalSearchRequest filterRequest, Pageable pageable) {
        return  specification.searchGroup(filterRequest,pageable);
    }
    @PatchMapping("/deActive/{id}")
    public ServiceMaster updateStatus(@PathVariable Long id,@RequestBody String serviceStatus){
        return  servicesService.updateStatus(id,serviceStatus);
    }
}

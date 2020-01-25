package com.sdgt.medcare.master.controller.billing;

import com.core.base.util.JsonParserAssistant;
import com.google.gson.Gson;
import com.sdgt.medcare.master.dto.billing.FilterReadOrderSet;
import com.sdgt.medcare.master.dto.billing.ServiceDetailResponse;
import com.sdgt.medcare.master.service.billing.OrderService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@RequestMapping("/order")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired private OrderService orderService;

    @GetMapping("/order-sets/{code}")
    public ResponseEntity<FilterReadOrderSet> findAllOrderSet(@PathVariable("code") String code) {
        FilterReadOrderSet filterReadOrderSet = new FilterReadOrderSet();
        filterReadOrderSet.setCode(code);
        filterReadOrderSet = orderService.findAllOrderSet(filterReadOrderSet);

        return ResponseEntity.ok(filterReadOrderSet);
    }

    @GetMapping("/order-sets/")
    public ResponseEntity<FilterReadOrderSet> findAllOrderSet() {
        FilterReadOrderSet filterReadOrderSet = new FilterReadOrderSet();
        filterReadOrderSet = orderService.findAllOrderSetForSelection(filterReadOrderSet);

        return ResponseEntity.ok(filterReadOrderSet);
    }

    /**
     * Find service details for given service code list.
     *
     * @param object json representation of  list of service codes.
     * @return a list of {@link ServiceDetailResponse} objects.
     */
    @PostMapping({"/services/list", "/Services/list", "/services/List", "/Services/List"})
    public ResponseEntity<List<ServiceDetailResponse>> findServiceDetails(@RequestBody String object) {
        Gson gson = new Gson();
        final ArrayList<String> serviceCodeList = gson.fromJson(JsonParserAssistant.populateArrayFromObject(object), ArrayList.class);
        final List<ServiceDetailResponse>  response = orderService.findServiceDetails(serviceCodeList);

        if (CollectionUtils.isEmpty(response)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}

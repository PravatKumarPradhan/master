package com.sdgt.medcare.master.service.billing;

import com.sdgt.medcare.master.dto.billing.FilterReadOrderSet;
import com.sdgt.medcare.master.dto.billing.OrderSetMenuItem;
import com.sdgt.medcare.master.dto.billing.SaveOrderSet;
import com.sdgt.medcare.master.dto.billing.ServiceDetailResponse;
import com.sdgt.medcare.master.entity.billing.OrderSetMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.repository.common.OrganizationMasterRepository;
import com.sdgt.medcare.master.repository.common.ServiceMasterRepository;
import com.sdgt.medcare.master.repository.common.UnitMasterRepository;
import com.sdgt.medcare.master.repository.billing.OrderMasterSetRepository;
import com.sdgt.medcare.master.repository.billing.OrderSetDetailsRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Service
public class OrderService {
    private Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired private OrderMasterSetRepository orderMasterSetRepository;
    @Autowired private OrderSetDetailsRepository orderSetDetailsRepository;
    @Autowired private UnitMasterRepository unitMasterRepository;
    @Autowired private OrganizationMasterRepository organizationMasterRepository;
    @Autowired private ServiceMasterRepository serviceMasterRepository;

    /**
     * Save {@link OrderSetMaster}
     *
     * @param dto a valid DTO of type  {@link SaveOrderSet}
     * @return the saved data and the requested data.
     */
    public SaveOrderSet saveOrderSetMaster(final SaveOrderSet dto) {
        //TODO save entity
        return dto;
    }

    /**
     * Find all {@link OrderSetMaster} by given filter.
     *
     * @param filterReadOrderSet dto of type  {@link FilterReadOrderSet}
     * @return {@link FilterReadOrderSet} containing the list of {@link OrderSetMaster}.
     */
    public FilterReadOrderSet findAllOrderSet(final FilterReadOrderSet filterReadOrderSet) {
        filterReadOrderSet.verify();

        final String code = filterReadOrderSet.getCode();
        final UnitMaster unitMaster = unitMasterRepository.findByCode(filterReadOrderSet.getUnitCode());
        final OrganizationMaster organizationMaster = organizationMasterRepository.findByCode(filterReadOrderSet.getOrgCode());
        final OrderSetMaster orderSetMaster = orderMasterSetRepository.findByCodeByUnitMasterByOrganizationMaster(code,
                unitMaster, organizationMaster);
        filterReadOrderSet.setOrderSetMasterList(Collections.singletonList(orderSetMaster));

        return filterReadOrderSet;
    }

    /**
     * Find all {@link OrderSetMaster} by given filter.
     *
     * @param filterReadOrderSet dto of type  {@link FilterReadOrderSet}
     * @return {@link FilterReadOrderSet} containing the list of {@link OrderSetMaster#getDesc()}.
     */
    public FilterReadOrderSet findAllOrderSetForSelection(final FilterReadOrderSet filterReadOrderSet) {
        List<OrderSetMaster> orderSetMasterList = orderMasterSetRepository.findAllByUnitMaster(unitMasterRepository.findByCode(filterReadOrderSet.getUnitCode()));
        orderSetMasterList = (List<OrderSetMaster>) CollectionUtils.emptyIfNull(orderSetMasterList);

        List<OrderSetMenuItem> orderSetMenuItemList = new ArrayList<>(orderSetMasterList.size());
        for(final OrderSetMaster orderSetMaster : orderSetMasterList) {
            final OrderSetMenuItem orderSetMenuItem = new OrderSetMenuItem();
            orderSetMenuItem.setCode(orderSetMaster.getCode());
            orderSetMenuItem.setDesc(orderSetMaster.getDesc());
            orderSetMenuItem.setUnitCode(orderSetMaster.getUnitMaster().getCode());
            orderSetMenuItem.setOrgCode(orderSetMaster.getOrganizationMaster().getCode());

            orderSetMenuItemList.add(orderSetMenuItem);
        }

        filterReadOrderSet.setOrderSetMenuItemList(orderSetMenuItemList);
        return filterReadOrderSet;
    }

    /**
     * Get service details
     *
     * @param codeList a valid list of code representing {@link ServiceMaster}
     * @return an list of type {@link ServiceDetailResponse}
     */
    public List<ServiceDetailResponse> findServiceDetails(final List<String> codeList) {
        final List<ServiceMaster> serviceMasterList = serviceMasterRepository.findList(codeList);
        if (CollectionUtils.isEmpty(serviceMasterList)) {
            logger.warn("findServiceDetails; No service records found.");
            return Collections.emptyList();
        }

        final List<ServiceDetailResponse> serviceDetailResponseList = new ArrayList<>(serviceMasterList.size());
        for(final ServiceMaster serviceMaster : serviceMasterList) {
            final ServiceDetailResponse response = ServiceDetailResponse.builder()
                    .code(serviceMaster.getCode())
                    .description(serviceMaster.getDesc())
                    .groupCode(serviceMaster.getGroupMaster().getCode())
                    .groupDescription(serviceMaster.getGroupMaster().getDesc())
                    .subGroupCode(serviceMaster.getSubGroupMaster().getCode())
                    .subGroupDescription(serviceMaster.getSubGroupMaster().getDesc())
                    .build();

            serviceDetailResponseList.add(response);
        }

        return serviceDetailResponseList;
    }
}

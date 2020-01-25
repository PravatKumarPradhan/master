package com.sdgt.medcare.master.service.common;

import com.core.base.rest.controller.ErrorResponse;
import com.sdgt.medcare.master.entity.bmw.ServiceProviderMaster;
import com.sdgt.medcare.master.entity.global.ItemMaster;
import com.sdgt.medcare.master.entity.global.MarkupDetail;
import com.sdgt.medcare.master.entity.global.MarkupMaster;
import com.sdgt.medcare.master.entity.inventory.KitDetailMaster;
import com.sdgt.medcare.master.entity.inventory.KitMaster;
import com.sdgt.medcare.master.model.KitDetailResponse;
import com.sdgt.medcare.master.model.KitResponse;
import com.sdgt.medcare.master.model.MarkupDetailSaveRequest;
import com.sdgt.medcare.master.repository.common.ItemMasterRepository;
import com.sdgt.medcare.master.repository.common.KitMasterRepository;
import com.sdgt.medcare.master.repository.common.MarkupDetailRepository;
import com.sdgt.medcare.master.repository.common.ServiceProviderRepository;
import com.sdgt.medcare.master.util.AbstractJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("CommonService")
public class CommonService implements ICommonService {

    @Autowired
    MarkupDetailRepository markupMasterRepository;

    @Autowired
    private AbstractJpaDao dao;

    @Autowired
    ItemMasterRepository itemMasterRepository;

    @Autowired
    KitMasterRepository kitMasterRepository;

    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    @Override
    public Object addMarkupMaster(MarkupDetailSaveRequest markupDetailSaveRequest) {
        try {

            MarkupDetail markupDetail = new MarkupDetail(new MarkupMaster(markupDetailSaveRequest.getId()),
                    markupDetailSaveRequest.getMinimumRate(), markupDetailSaveRequest.getMaximumRate(),
                    markupDetailSaveRequest.getMarkupPercentage(), markupDetailSaveRequest.getMarkupRate(),
                    markupDetailSaveRequest.getFixedSellingPrice(), markupDetailSaveRequest.getAnyRate());

            markupMasterRepository.save(markupDetail);


        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorCode(500);
            errorResponse.setMessage(e.getMessage());
            return errorResponse;
        }
        return null;
    }

    @Override
    public Object updateItemMaster(String itemCode) {
        try {
            ItemMaster itemMaster = itemMasterRepository.getItemMasterByItemId(itemCode);
            itemMaster.setIndent(true);
            itemMaster.setPharmacy(true);
            itemMasterRepository.save(itemMaster);
        } catch (Exception e) {
            throw e;
        }
        return 1;
    }

    @Override
    public Object getServiceProvider() {
        List<ServiceProviderMaster> serviceProviderList = new ArrayList<>();
        try{
            serviceProviderList = serviceProviderRepository.findAll();
        }catch (Exception e){
            throw e;
        }
        return serviceProviderList;
    }

    @Override
    public Object getItemByItemTypeId(Long itemTypeId) {
        List<ItemMaster> itemMasterList = new ArrayList<>();
        try {
            itemMasterList = itemMasterRepository.getItemByItemTypeId(itemTypeId);
        } catch (Exception e) {
            throw e;
        }

        return itemMasterList;
    }

    @Override
    public Object getItemsByKitId(Long kitId) {
        KitResponse kitResponse = null;
        try {
            KitMaster kitMaster = kitMasterRepository.findById(kitId).get();

            kitResponse = new KitResponse(Long.parseLong(kitMaster.getId()), kitMaster.getCode(), kitMaster.getDesc());

            List<KitDetailResponse> kitDetailResponseList = new ArrayList<>();
            List<KitDetailMaster> kitDetailList = kitMasterRepository.getDetailsByKitId(kitId);
            if (kitDetailList != null && kitDetailList.size() > 0) {
                for (KitDetailMaster kitDetailMaster : kitDetailList) {

                    KitDetailResponse kitDetailResponse = new KitDetailResponse(Long.parseLong(kitDetailMaster.getId()), Long.parseLong(kitDetailMaster.getItemMaster().getId()),
                            kitDetailMaster.getItemMaster().getCode(), kitDetailMaster.getItemMaster().getDesc(), Long.parseLong(kitDetailMaster.getItemUnitMaster().getId()),
                            kitDetailMaster.getItemUnitMaster().getCode(), kitDetailMaster.getItemUnitMaster().getDesc(),
                            Long.parseLong(kitDetailMaster.getUomTypeMaster().getId()), kitDetailMaster.getUomTypeMaster().getCode(), kitDetailMaster.getUomTypeMaster().getDesc(),
                            kitDetailMaster.getQuantity());
                    kitDetailResponseList.add(kitDetailResponse);

                }
                kitResponse.setItems(kitDetailResponseList);
            }
        } catch (Exception e) {
            throw e;
        }
        return kitResponse;
    }

}

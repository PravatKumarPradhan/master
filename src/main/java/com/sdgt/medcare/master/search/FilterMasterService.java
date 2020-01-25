package com.sdgt.medcare.master.search;

import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.repository.common.GroupMasterRepository;
import com.sdgt.medcare.master.repository.common.ServiceMasterRepository;
import com.sdgt.medcare.master.repository.common.SubGroupMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilterMasterService extends BaseService<ServiceMaster> {


   @Autowired
   ServiceMasterRepository serviceMasterRepository;
   @Autowired
   GroupMasterRepository groupMasterRepository;
   @Autowired
   SubGroupMasterRepository subGroupMasterRepository;




}

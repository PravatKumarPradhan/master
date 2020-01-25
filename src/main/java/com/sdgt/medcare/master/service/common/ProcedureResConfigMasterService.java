package com.sdgt.medcare.master.service.common;


import com.core.service.BaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdgt.medcare.master.entity.global.ProcedureResourceConfigurationMaster;
import com.sdgt.medcare.master.repository.common.ProcedureResourceConfigurationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProcedureResConfigMasterService  extends BaseService<ProcedureResourceConfigurationMaster>
{
    @Autowired
    public ProcedureResConfigMasterService(ProcedureResourceConfigurationRepository dao) {
	super.setDao(dao);
    }

    @Autowired
    ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(ProcedureResConfigMasterService.class);



    @Override
    public  ProcedureResourceConfigurationMaster  save(ProcedureResourceConfigurationMaster pro) {

	ProcedureResourceConfigurationMaster procedureResourceConfigurationMaster = new ProcedureResourceConfigurationMaster();
	if(pro!=null) {
	    //  procedureResourceConfigurationMaster.setResourceDetails(pro.getResourceDetails());
	}
	return pro;
    }



    /*
     * ProcedureResourceConfigurationMaster
     * procedureResourceConfigurationMaster=null;
     * procedureResourceConfigurationMaster.setResourceDetails(pro.
     * getResourceDetails()); procedureResourceConfigurationMaster.set
     */

}






package com.sdgt.medcare.master.controller.common;

import com.core.base.rest.controller.RestWSController;
import com.sdgt.medcare.master.entity.global.ProcedureResourceConfigurationMaster;
import com.sdgt.medcare.master.repository.common.ProcedureResourceConfigurationRepository;
import com.sdgt.medcare.master.service.common.ProcedureResConfigMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/proresconf")
public class ProcedureResourceConfigurationMasterController extends RestWSController<ProcedureResourceConfigurationMaster>{


    //  @Autowired
    private ProcedureResConfigMasterService service;
    //  @Autowired
    private ProcedureResourceConfigurationRepository procedureResourceConfigurationRepository;

    @Autowired
    public ProcedureResourceConfigurationMasterController(ProcedureResConfigMasterService service,  ProcedureResourceConfigurationRepository procedureResourceConfigurationRepository ) {
	super(service);
	this.service = service;
	this.procedureResourceConfigurationRepository = procedureResourceConfigurationRepository;
    }

    @PostMapping("/save")
    public ProcedureResourceConfigurationMaster saveProcedure(@RequestBody ProcedureResourceConfigurationMaster proResoueceConfig){

	return service.save(proResoueceConfig);

    }

}

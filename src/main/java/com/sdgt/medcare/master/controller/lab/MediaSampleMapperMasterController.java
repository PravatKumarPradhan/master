package com.sdgt.medcare.master.controller.lab;

import com.core.base.rest.controller.RestWSController;
import com.sdgt.medcare.master.entity.lab.MediaSampleMapperMaster;
import com.sdgt.medcare.master.service.lab.MediaSampleMapperMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping({"/SampleWiseMedia","/sampleWiseMedia"})
public class MediaSampleMapperMasterController extends RestWSController<MediaSampleMapperMaster> {

    @Autowired
    MediaSampleMapperMasterService mediaSampleMapperMasterService;

    @GetMapping("/{id}")
    public List<MediaSampleMapperMaster> getTemlateByTestId(@PathVariable("id") Long id){
        return mediaSampleMapperMasterService.getMediaBySampleType(id,null,null);
    }

    @GetMapping()
    public List<MediaSampleMapperMaster> getTemlateByTestId(@QueryParam("code") String code
            ,@QueryParam("id") Long id,@QueryParam("desc") String desc){
        return mediaSampleMapperMasterService.getMediaBySampleType(id,code,desc);
    }
}

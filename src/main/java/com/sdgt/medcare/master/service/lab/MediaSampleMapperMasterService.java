package com.sdgt.medcare.master.service.lab;

import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.lab.MediaSampleMapperMaster;
import com.sdgt.medcare.master.repository.lab.MediaSampleMapperMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediaSampleMapperMasterService extends BaseService<MediaSampleMapperMaster> {

    MediaSampleMapperMasterRepository mediaSampleMapperMasterRepository;

    @Autowired
    public MediaSampleMapperMasterService(MediaSampleMapperMasterRepository mediaSampleMapperMasterRepository) {
        super(mediaSampleMapperMasterRepository);
        this.mediaSampleMapperMasterRepository = mediaSampleMapperMasterRepository;
    }

    public List<MediaSampleMapperMaster> getMediaBySampleType(Long sampleTypeMasterId,String code,String desc)
    {
        if(sampleTypeMasterId!=null) {
            return mediaSampleMapperMasterRepository.getAllBySampleTypeMasterId(sampleTypeMasterId);
        }
        else if(code!=null)
        {
            return mediaSampleMapperMasterRepository.getAllBySampleTypeMasterCode(code);
        }
        else if(desc!=null)
        {
            return mediaSampleMapperMasterRepository.getAllBySampleTypeMasterCode(desc);
        }
        return new ArrayList<>();
    }

}

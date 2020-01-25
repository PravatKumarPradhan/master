package com.sdgt.medcare.master.repository.lab;

import com.sdgt.medcare.master.entity.lab.MediaSampleMapperMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaSampleMapperMasterRepository extends JpaRepository<MediaSampleMapperMaster, Long> {

    public List<MediaSampleMapperMaster> getAllBySampleTypeMasterId(Long sampleTypeMasterId);

    public List<MediaSampleMapperMaster> getAllBySampleTypeMasterCode(String sampleTypeMasterCode);

    public List<MediaSampleMapperMaster> getAllBySampleTypeMasterDesc(String sampleTypeMasterDesc);
}

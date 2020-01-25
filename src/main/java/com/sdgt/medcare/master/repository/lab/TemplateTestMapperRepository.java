package com.sdgt.medcare.master.repository.lab;

import com.sdgt.medcare.master.entity.lab.TemplateTestMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateTestMapperRepository extends JpaRepository<TemplateTestMapper, Long> {

    public List<TemplateTestMapper> getTemplatesByTestMasterId(Long testId);

    public List<TemplateTestMapper> getTemplatesByTestMasterCode(String Code);

    /*void deleteByTest_master_id(Long testMasterId);*/

    public List<TemplateTestMapper> findAllByTestMaster(Long testMasterId);
   public List<TemplateTestMapper> findAllByTestMasterId(Long testMasterId);

}

package com.sdgt.medcare.master.service.lab;

import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.lab.TemplateTestMapper;
import com.sdgt.medcare.master.repository.lab.TemplateTestMapperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateTestMapperService extends BaseService<TemplateTestMapper> {

    TemplateTestMapperRepository templateTestMapperRepository;

    @Autowired
    public TemplateTestMapperService(TemplateTestMapperRepository templateTestMapperRepository) {
        super(templateTestMapperRepository);
        this.templateTestMapperRepository=templateTestMapperRepository;
    }

    public List<TemplateTestMapper> getTemplatesByTestMasterId(Long testId)
    {
        return templateTestMapperRepository.getTemplatesByTestMasterId(testId);
    }
}

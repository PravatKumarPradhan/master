package com.sdgt.medcare.master.controller.lab;

import com.core.base.rest.controller.RestWSController;
import com.sdgt.medcare.master.entity.lab.TemplateTestMapper;
import com.sdgt.medcare.master.service.lab.TemplateTestMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping({"/TestWiseTemplate","/testWiseTemplate"})
public class TemplateTestMapperController extends RestWSController<TemplateTestMapper> {

    @Autowired
    TemplateTestMapperService templateTestMapperService;

    @GetMapping("/{id}")
    public List<TemplateTestMapper> getTemlateByTestId(@PathVariable("id") Long id){
        return templateTestMapperService.getTemplatesByTestMasterId(id);
    }
}

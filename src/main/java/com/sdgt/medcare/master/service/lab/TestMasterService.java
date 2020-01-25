package com.sdgt.medcare.master.service.lab;

import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.core.exceptions.DataException;
import com.core.service.BaseService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sdgt.medcare.master.entity.lab.TemplateTestMapper;
import com.sdgt.medcare.master.entity.lab.TestMaster;
import com.sdgt.medcare.master.entity.lab.TestParameterMapper;
import com.sdgt.medcare.master.repository.lab.TemplateTestMapperRepository;
import com.sdgt.medcare.master.repository.lab.TestMasterRepository;
import com.sdgt.medcare.master.repository.lab.TestParameterMapperRepository;
import com.sdgt.medcare.master.util.BaseUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Pravat Kumar Pradhan
 */
@Service
public class TestMasterService extends BaseService<TestMaster> {
    @Autowired
    EntityManager entityManager;

    private TestMasterRepository dao;

    private TestMasterRepository testMasterRepository;
    @Autowired
    TestParameterMapperRepository testParameterMapperRepository;

    @Autowired
    TemplateTestMapperRepository templateTestMapperRepository;


    Logger logger = LoggerFactory.getLogger(TestMasterService.class);

    @Autowired
    public void setDao(TestMasterRepository dao) {
        super.setDao(dao);
        this.dao = dao;
    }
    public TestMasterService(TestMasterRepository dao) {
        super.setDao(dao);
        this.testMasterRepository = dao;
    }
    @Override
    @Transactional
    public TestMaster create(TestMaster tMaster) {
        if (BaseUtil.isNullOrEmptyObject(tMaster.getActive())) {
            tMaster.setActive(true);
        } else
            tMaster.setActive(tMaster.getActive());

        if(StringUtils.isBlank(tMaster.getAliasName())) {
            tMaster.setAliasName(tMaster.getDesc());
        }
        else{
            tMaster.setAliasName(tMaster.getAliasName());
        }
        TestMaster master = dao.save(tMaster);
        if(tMaster.getCode()==null) {
            master.setCode(master.getId());
        }
        else
            tMaster.setCode(master.getCode());
        if (BaseUtil.isNullOrEmptyObject(tMaster.getClinicalInterpitation())) {
            tMaster.setClinicalInterpitation(false);
        } else
            tMaster.setClinicalInterpitation(true);
        if (tMaster.getNoOfSample() != null)
            master.setNoOfSample(tMaster.getNoOfSample());
        else
            master.setNoOfSample(null);
        List<TestParameterMapper> testParameterMapperSet = null;
        List<TemplateTestMapper> templateTestMapperSet = null;
        if (master.getTestParameterMappers() != null && !master.getTestParameterMappers().isEmpty()) {
            for (TestParameterMapper testParameterMapper : master.getTestParameterMappers()) {
                testParameterMapper.setTestMaster(master);
                testParameterMapper.setParameterMaster(testParameterMapper.getParameterMaster());
                testParameterMapper.setCode(testParameterMapper.getId());
                if (testParameterMapper.getActive() == null)
                    testParameterMapper.setActive(true);
                else
                    testParameterMapper.setActive(testParameterMapper.getActive());
            }
        }
        if (master.getTemplateTestMappers() != null && !master.getTemplateTestMappers().isEmpty()) {
            for (TemplateTestMapper templateTestMapper : master.getTemplateTestMappers()) {
                templateTestMapper.setTestMaster(master);
                templateTestMapper.setTemplateMaster(templateTestMapper.getTemplateMaster());
                templateTestMapper.setCode(templateTestMapper.getId());
                if (templateTestMapper.getActive() == null)
                    templateTestMapper.setActive(true);
                else
                    templateTestMapper.setActive(templateTestMapper.getActive());
            }
        }
        master.setTemplateTestMappers(templateTestMapperSet);
        master.setTestParameterMappers(testParameterMapperSet);
        master.setSubDepartmentMaster(master.getSubDepartmentMaster());
        return master;
    }

    /**
     * @param pageable
     * @return
     */
    public Page<TestMaster> getAllTest(Pageable pageable) {
        return dao.findAll(pageable);
    }

    /**
     * @param id
     * @return
     */

    public Optional<TestMaster> findById(Long id) {
        return testMasterRepository.findById(id);
    }

    /**
     * @param id
     * @param testMasterJsonObject
     * @return
     */
    public TestMaster deActiveTest(Long id, String testMasterJsonObject) {
        Optional<TestMaster> findTestMasterObject = dao.findById(id);
        TestMaster testMaster = new TestMaster();
        JsonObject obj = new JsonParser().parse(testMasterJsonObject).getAsJsonObject();
        if (findTestMasterObject.isPresent()) {
            testMaster = findTestMasterObject.get();
            //  System.out.println("testmaster value is" + testMaster);
              String deletedUserName = HttpUtils.getHeader(HttpHeaders.USER_NAME);
            testMaster.setUpdatedBy(deletedUserName);
            testMaster.setActive(obj.get("active").getAsBoolean());
        }
        return patch(testMaster, id.toString());
    }

    /**
     * @param objectToPatch
     * @param id
     * @return
     */
    @Override
    public TestMaster patch(TestMaster objectToPatch, String id) {
        if (objectToPatch == null) {
            throw new DataException("There should exist an object already");
        }
        if (StringUtils.isNoneBlank(id)) {
            try {
                objectToPatch.setId(id);
                return testMasterRepository.save(objectToPatch);
            } catch (Exception e) {
                throw new DataException("persistance error from save", e);
            }
        }
        return null;
    }


    /**
     * @implNote
     * @param testMaster
     * @param testId
     * @return
     */
    @Transactional
    @Override
    public TestMaster update(TestMaster testMaster, String testId) {
        String createdOrUpdatedBy=HttpUtils.getHeader(HttpHeaders.USER_NAME);
        Long testMasterId=Long.parseLong(testId);

        List<TestParameterMapper> testParameterObj = testParameterMapperRepository.findAllByTestMasterId(testMasterId);
        testParameterObj.stream().forEach(testParamSetObject -> {
            testParamSetObject.setActive(false);
            testParamSetObject.setUpdatedBy(createdOrUpdatedBy);
        });
        List<TemplateTestMapper> templateTestMapperObj=templateTestMapperRepository.findAllByTestMasterId(testMasterId);
        templateTestMapperObj.stream().forEach(stremObj->{
            stremObj.setActive(false);
            stremObj.setUpdatedBy(createdOrUpdatedBy);
        });
        testMaster.setId(testId);
        Optional<TestMaster> testMasterOptional = findById(testMasterId);
        if (CollectionUtils.isNotEmpty(testMaster.getTestParameterMappers())) {
            List<TestParameterMapper> testParameterMapper =testMaster.getTestParameterMappers();
            testParameterMapper.stream().forEach(testParameterStreamObj-> {
                testParameterStreamObj.setTestMaster(testMaster);
                testParameterStreamObj.setActive(true);
                testParameterStreamObj.setCreatedBy(createdOrUpdatedBy);
            });
            testMaster.setTestParameterMappers(testParameterMapper);
        }
        if(CollectionUtils.isNotEmpty(testMaster.getTemplateTestMappers())){
            List<TemplateTestMapper> templateTestMapperList=testMaster.getTemplateTestMappers();
            templateTestMapperList.stream().forEach(templateTestMapperStreamObj ->{
                templateTestMapperStreamObj.setActive(true);
                templateTestMapperStreamObj.setUpdatedBy(createdOrUpdatedBy);
                templateTestMapperStreamObj.setTestMaster(testMaster);
            } );
            testMaster.setTemplateTestMappers(templateTestMapperList);
        }
        return testMasterRepository.save(testMaster);
    }

}

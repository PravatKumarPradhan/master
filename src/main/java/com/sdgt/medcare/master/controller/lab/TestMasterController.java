package com.sdgt.medcare.master.controller.lab;

import com.core.base.rest.controller.RestWSController;
import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.sdgt.medcare.master.entity.lab.TestMaster;
import com.sdgt.medcare.master.entity.lab.UnitTestMapper;
import com.sdgt.medcare.master.repository.lab.TestMasterRepository;
import com.sdgt.medcare.master.search.GlobalSearchRequest;
import com.sdgt.medcare.master.search.TestSearchSpecification;
import com.sdgt.medcare.master.service.lab.TestMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;

/**
 * @author Pravat Kumar Pradhan
 * @author Vishrut  Trivedi
 */
@RestController
@CrossOrigin
@RequestMapping ("/Test")
public class TestMasterController extends RestWSController<UnitTestMapper> {

   @Autowired
   private TestMasterRepository testMasterRepository;

   Logger empControllerLogger = LoggerFactory.getLogger(TestMasterController.class);

   @Autowired
   private TestMasterService testMasterService;

   @Autowired
   TestSearchSpecification specification;

   @GetMapping ("/service/{id}")
   public List<TestMaster> getBedByEr(@PathVariable ("id") String id, @QueryParam ("unitId") Long unitId) {
      List<TestMaster> unitTestMappers;
      if (unitId != null) {
         unitTestMappers = null;
         //unitTestMappers = testMasterRepository.findTest(id, unitId);
      } else {
         unitTestMappers = testMasterRepository.findTestByServiceId(id);
      }
      return unitTestMappers;
   }

   /**
    * @param testMaster
    *
    * @return
    */
   @PostMapping ("/save")
   public ResponseEntity<TestMaster> saveTestMaster(@RequestBody TestMaster testMaster) {
      String createdBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
      testMaster.setCreatedBy(createdBy);
      return ResponseEntity.ok(testMasterService.create(testMaster));
   }

   /**
    * @param pageable
    *
    * @return
    */
   @GetMapping ("/getAll")
   public Page<TestMaster> getAllTestMaster(Pageable pageable) {
      return testMasterService.getAllTest(pageable);
   }

   /**
    * @param id
    * @param testMaster
    *
    * @return TestMaster Entity
    */
   @PatchMapping ("/deActiveTest/{id}")
   public TestMaster deActiveTest(@PathVariable Long id, @RequestBody String testMaster) {
      return testMasterService.deActiveTest(id, testMaster);

   }

   /**
    * @param id pass the Id of entity to get the Entity object
    *
    * @return Entity
    */
   @GetMapping ("/getById/{id}")
   public ResponseEntity getById(@PathVariable Long id) {
      return ResponseEntity.ok(testMasterService.findById(id));
   }

   /***
    *
    * @param id
    * @param testMaster
    * @return
    */

   @PutMapping ("/updateTest/{id}")
   public ResponseEntity<TestMaster> updateTestData(@PathVariable Long id, @RequestBody TestMaster testMaster) {
      Optional<TestMaster> testMasterOptional = testMasterService.findById(id);
      if (!testMasterOptional.isPresent()) {
         ResponseEntity.badRequest().build();
      }
      String updatedUserName = HttpUtils.getHeader(HttpHeaders.USER_NAME);
      testMaster.setUpdatedBy(updatedUserName);
      String testId = id.toString();
      TestMaster testMaster1 = testMasterService.update(testMaster, testId);
      return ResponseEntity.ok(testMaster1);

   }


   @PostMapping("/search")
   public Page<TestMaster> search(@RequestBody GlobalSearchRequest filter, Pageable pageable){
      return specification.testMasterSearch(filter,pageable);
   }
}

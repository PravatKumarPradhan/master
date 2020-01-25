package com.sdgt.medcare.master.controller.lab;

import com.core.base.rest.controller.RestWSController;
import com.sdgt.medcare.master.entity.lab.ParameterMaster;
import com.sdgt.medcare.master.search.GlobalSearchRequest;
import com.sdgt.medcare.master.search.ParameterSearchSpecification;
import com.sdgt.medcare.master.service.lab.ParameterMasterService;
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

import java.util.Optional;


/**
 * @author Pravat Kumar Pradhan
 */
@RestController
@CrossOrigin
@RequestMapping ("/parameter")
public class ParameterMasterController extends RestWSController<ParameterMaster> {

 private   ParameterMasterService parameterMasterService;

 @Autowired
 private ParameterSearchSpecification specification;

   @Autowired
   public ParameterMasterController(ParameterMasterService parameterMasterService) {
      super(parameterMasterService);
      this.parameterMasterService = parameterMasterService;
   }

   @PostMapping ("/save")
   public ResponseEntity<ParameterMaster> saveParameterData(@RequestBody ParameterMaster praParameterMaster) {
      return ResponseEntity.ok(parameterMasterService.create(praParameterMaster));
   }

   @GetMapping ("/getAll")
   public Page<ParameterMaster> getAllList(Pageable pageable) {
      return parameterMasterService.getAllParameter(pageable);
   }

   @PatchMapping ("/deActive/{id}")
   public ParameterMaster deActivateParameter(@RequestBody String parameterMaster,@PathVariable Long id) {
      return parameterMasterService.deActiveParameter( parameterMaster,id);
   }

   @GetMapping ("getById/{parameterMasterId}")
   public ResponseEntity getParameterMasterById(@PathVariable Long parameterMasterId) {
      return ResponseEntity.ok(parameterMasterService.findById(parameterMasterId).orElseThrow(() -> new RuntimeException("Parameter Not Found")));
   }

   @PutMapping ( "/update/{id}")
   public ResponseEntity<ParameterMaster> upadateParameterData(@PathVariable Long id ,@RequestBody ParameterMaster parameterMaster) {
      Optional<ParameterMaster> optionalParameterMaster = parameterMasterService.findById(id);
      if (!optionalParameterMaster.isPresent()) {
         ResponseEntity.badRequest().build();
      }
      String idd = id.toString();
      return ResponseEntity.ok(parameterMasterService.update(parameterMaster, idd));
   }

   @PutMapping("/statusChange/{id}")
   public ResponseEntity statusChange(@PathVariable Long id,@RequestBody String type){
      parameterMasterService.updateStatusByType(id,type);
      System.out.println(type);
      return  ResponseEntity.ok(1);
   }

   @PostMapping("/search")
   public Page<ParameterMaster> search(@RequestBody GlobalSearchRequest filter,Pageable pageable){
      return  specification.parameterMastersSearch(filter,pageable);
   }



}

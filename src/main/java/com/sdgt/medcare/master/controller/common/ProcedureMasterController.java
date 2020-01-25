package com.sdgt.medcare.master.controller.common;

import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.sdgt.medcare.master.dto.common.ProcedureDTO;
import com.sdgt.medcare.master.entity.global.ProcedureMaster;
import com.sdgt.medcare.master.exeception.ConflictException;
import com.sdgt.medcare.master.search.GlobalSearchRequest;
import com.sdgt.medcare.master.search.ProcedureSearchSpecification;
import com.sdgt.medcare.master.service.common.ProcedureMasterService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping ("/procedure")
@CrossOrigin
@ControllerAdvice ("ConflictException.class")
public class ProcedureMasterController  //extends RestWSController<ProcedureMaster>
{

   @Autowired
   ProcedureMasterService procedureMasterService;

   @Autowired
   ProcedureSearchSpecification specification;


   @PostMapping (value = {"/save", "/Save"},
         consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
         produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})

   public ResponseEntity<ProcedureDTO> create(@RequestBody ProcedureDTO dto) {
      if (StringUtils.isNotBlank(dto.getProcedureId())) {
         dto.setErrorMessage("Wrong operation. Use PUT method to update procedure.");

         return ResponseEntity.ok(dto);
      }
      ProcedureDTO pp = new ProcedureDTO();
      try {
         pp = procedureMasterService.save(dto);
      } catch (Exception e) {
         throw new ConflictException("Code Already Exist");
      }
      return ResponseEntity.ok(pp);
   }

   @PutMapping (value = {"/save", "/Save"},
         consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
         produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
   public ProcedureDTO update(@RequestBody ProcedureDTO dto) {
      if (StringUtils.isBlank(dto.getProcedureId())) {
         dto.setErrorMessage("Wrong operation. Use POST method to insert procedure.");

         return dto;
      }

      return procedureMasterService.save(dto);
   }

   @PostMapping ("/search")
   public Page<ProcedureMaster> search(@RequestBody GlobalSearchRequest filter, Pageable pageable) {
      return specification.procedureMasterSearch(filter, pageable);
   }


   @PutMapping ("/updateProcedure/{id}")
   public ProcedureMaster updateProcedureProcedure(@PathVariable (value = "id") Long id, @RequestBody ProcedureMaster procedureMaster) {
      return procedureMasterService.update(id, procedureMaster);
   }

   @GetMapping ("/getAll")
   public Page<ProcedureMaster> getAllData(Pageable pageable) {
      return procedureMasterService.getAllPaginated(pageable);
   }

   /**
    * @param id
    * @param procedure
    *
    * @return
    */
   @PatchMapping ("/deActiveProcedure/{id}")
   public ProcedureMaster deActivateEmployee(@PathVariable Long id, @RequestBody String procedure) {
      String deletedUserName = HttpUtils.getHeader(HttpHeaders.USER_NAME);
      return procedureMasterService.deActivateProcedure(id, procedure);
   }

   @GetMapping ("getByUnitService/{id}")
   public long getByUnitServiceMapper(@PathVariable Long id) {
      List<ProcedureMaster> pp = procedureMasterService.findByUnitService(id);
      if (CollectionUtils.isNotEmpty(pp)) {
         long procedureCount = Stream.of(pp).count();
         System.out.println(procedureCount);
         return procedureCount;
      } else
         return 0;
   }


}

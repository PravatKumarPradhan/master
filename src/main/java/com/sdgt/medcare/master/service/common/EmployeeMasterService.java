package com.sdgt.medcare.master.service.common;

import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.core.exceptions.DataException;
import com.core.service.BaseService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sdgt.medcare.master.dto.common.StaffDTO;
import com.sdgt.medcare.master.entity.global.EmployeeContactDetails;
import com.sdgt.medcare.master.entity.global.EmployeeEducationDetails;
import com.sdgt.medcare.master.entity.global.EmployeeProfessionDetails;
import com.sdgt.medcare.master.entity.org.EmployeeTypeMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.DependentDetails;
import com.sdgt.medcare.master.entity.unit.EmployeeDepartmentDetails;
import com.sdgt.medcare.master.entity.unit.EmployeeMasterDetails;
import com.sdgt.medcare.master.repository.common.DependentDetailsRepository;
import com.sdgt.medcare.master.repository.common.EmployeeContactDetailsRepository;
import com.sdgt.medcare.master.repository.common.EmployeeDepartmentDetailsRepository;
import com.sdgt.medcare.master.repository.common.EmployeeEducationRepository;
import com.sdgt.medcare.master.repository.common.EmployeeProffessionalRepository;
import com.sdgt.medcare.master.repository.common.EmployeeRepository;
import com.sdgt.medcare.master.repository.common.EmployeeTypeMasterRepository;
import com.sdgt.medcare.master.repository.common.OTRoleMasterRepository;
import com.sdgt.medcare.master.repository.common.OrganizationMasterRepository;
import com.sdgt.medcare.master.repository.common.UnitRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/***
 * @author Pravat Kumar Pradhan
 */

@Service
public class EmployeeMasterService extends BaseService<EmployeeMasterDetails> {

   Logger doctorEmpStudLogger = LoggerFactory.getLogger(EmployeeMasterService.class);
   private EmployeeRepository employeeRepository;

   @Autowired
   EntityManager entityManager;

   @Autowired
   DependentDetailsRepository dependentDetailsRepository;

   @Autowired
   EmployeeProffessionalRepository employeeProffessionalRepository;

   @Autowired
   EmployeeContactDetailsRepository employeeContactDetailsRepository;
   @Autowired
   EmployeeEducationRepository employeeEducationRepository;

   @Autowired
   EmployeeDepartmentDetailsRepository employeeDepartmentDetailsRepository;

   @Autowired
   UnitRepository unitRepository;

   @Autowired
   OrganizationMasterRepository organizationMasterRepository;


   @Autowired
   private OTRoleMasterRepository otRoleMasterRepository;


   @Autowired
   EmployeeTypeMasterRepository employeeTypeMasterRepository;

   @Autowired
   public void setDao(EmployeeRepository dao) {
      super.setDao(dao);
   }

   public EmployeeMasterService(EmployeeRepository dao) {
      super.setDao(dao);
      employeeRepository = dao;
   }


   public List<EmployeeMasterDetails> getDoctorList() {

      return employeeRepository.findByDoctor();
   }

   public List<EmployeeMasterDetails> getStudList() {
      return employeeRepository.findByStudent();

   }

   public List<EmployeeMasterDetails> getStaffList() {
      return employeeRepository.findByStaff();
   }

   public List<EmployeeMasterDetails> searchAPI(String json) {
      JsonParser parser = new JsonParser();
      JsonElement element = parser.parse(json);
      JsonObject obj = element.getAsJsonObject(); // since you know it's a JsonObject
      Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();// will return members of your object
      String whereCond = "";
      String findByEmployeeName = "";
      int limit = 0, offset = 0;
      for (Map.Entry<String, JsonElement> entry : entries) {
         if (entry.getValue() != null && !entry.getValue().isJsonNull()
               && entry.getValue().toString().length() > 2) {
            if (entry.getKey().equals("name")) {
               findByEmployeeName = entry.getValue().getAsString();
               whereCond = whereCond + " and " + "LOWER(c." + entry.getKey() + ") like CONCAT('%',:findByEmployeeName,'%') ";
            } else if (entry.getKey().equals("limit")) {
               limit = Integer.parseInt(entry.getValue().getAsString());
            } else if (entry.getKey().equals("offset")) {
               offset = Integer.parseInt(entry.getValue().getAsString());
            } else {
               whereCond = whereCond + " and " + "c." + entry.getKey() + "=" + entry.getValue();
            }
         }
      }

      String qu = "";
      if (json != null && json.length() > 0 && json.contains("isDoctor")) {
         qu = "Select distinct  c from EmployeeMasterDetails c join  c.employeeTypeMaster e join c.listunitmaster lum join c.departmentMaster dm left join c.prefixMaster p "
               + " left join p.genderMaster g  where c.active=true " + whereCond + " order by c.name asc ";
         qu = qu.replaceAll("c.isDoctor", "e.isDoctor");
         qu = qu.replaceAll("c.unitMasterid", "lum.id");
         qu = qu.replaceAll("c.unitMastercode", "lum.code");
         qu = qu.replaceAll("c.departmentCode", "dm.code");
         qu = qu.replace("\"", "'");
      } else if (json != null && json.length() > 0 && json.contains("isStudent")) {
         qu = "Select distinct  c from EmployeeMasterDetails c join  c.employeeTypeMaster e left join c.prefixMaster p left join c.unitMaster u"
               + " left join p.genderMaster g  where c.active=true " + whereCond + " order by c.name asc ";
         qu = qu.replaceAll("c.isStudent", "e.isStudent");
         qu = qu.replaceAll("c.unitMasterid", "u.id");
         qu = qu.replaceAll("c.unitMastercode", "u.code");
         qu = qu.replaceAll("c.departmentCode", "dm.code");
         qu = qu.replace("\"", "'");
      } else if (json != null && json.length() > 0 && json.contains("isStaff")) {
         qu = "Select distinct c from EmployeeMasterDetails c join  c.employeeTypeMaster e  left join c.prefixMaster p left join c.unitMaster u"
               + "left join p.genderMaster g  where c.active=true " + whereCond + " order by c.name asc ";
         qu = qu.replaceAll("c.isStaff", "e.isStaff");
         qu = qu.replaceAll("c.unitMasterid", "u.id");
         qu = qu.replaceAll("c.unitMastercode", "u.code");
         qu = qu.replaceAll("c.departmentCode", "dm.code");
         qu = qu.replace("\"", "'");
      }
      qu = qu.replaceAll("c.prefixMasterid", "p.id");
      qu = qu.replaceAll("c.genderMasterid", "g.id");


      if (findByEmployeeName != null && findByEmployeeName.length() > 0) {
         doctorEmpStudLogger.info("Search Query " + qu);
         return entityManager.createQuery(qu)
               .setParameter("findByEmployeeName", findByEmployeeName.toLowerCase())
               .getResultList();
      } else {

         return entityManager.createQuery(qu)
               .getResultList();
      }
   }

   public EmployeeMasterDetails createStaff(StaffDTO staffDTO) {
      EmployeeMasterDetails employeeMasterDetails = new EmployeeMasterDetails();
      /* employeeMasterDetails.setId(staffDTO.getSystemId());*/
      employeeMasterDetails.setCode(staffDTO.getStaffCode());
      /*employeeMasterDetails.setCreatedDate(new Date());*/
      return super.create(employeeMasterDetails);
   }


   /***
    *
    // * @param empDetails
    * @return employee details
    */
   @Override
   @Transactional
   public EmployeeMasterDetails create(EmployeeMasterDetails empDetails) {

      //issue fix for RSD we will have map the master  temporary fix
      //Long employeeType=Long.parseLong(empDetails.getEmployeeTypeMaster().getId());
      /*if(empDetails.getEmployeeTypeMaster()!=null  ){
         Long employeeType=Long.parseLong(empDetails.getEmployeeTypeMaster().getId());
         Long otRole=employeeType;
         OTRoleMaster otRoleMaster=otRoleMasterRepository.getOne(otRole);
         empDetails.setOtRoleMaster(otRoleMaster);
      }*/

    /* if(StringUtils.isNotBlank(empDetails.getOtRoleMaster().getId())){

      }*/
      // empDetails.setCode(String.valueOf(Instant.now().getEpochSecond()));

      ;
     /*if(empDetails.getOrganizationMaster().getId()==null){
         empDetails.setOrganizationMaster(organizationMaster);
     }*/

      //byte[] happy = Base64.getEncoder().encode();

      if(empDetails.getEmpImage()!=null && !empDetails.getEmpImage().toString().isEmpty()) {
         empDetails.setEmpImage(empDetails.getEmpImage());
      }
      //  empDetails.s(addImage(empDetails.getEmpImage()));

     // empDetails.setDesc(empDetails.getName());
      EmployeeMasterDetails employeeMasterDetails = employeeRepository.save(empDetails);
      employeeMasterDetails.setCode(employeeMasterDetails.getId());
      List<DependentDetails> dd = null;
      EmployeeContactDetails empContactDetails = null;
      List<EmployeeProfessionDetails> employeeProfessionDetailsList = null;
      List<EmployeeDepartmentDetails> employeeDepartmentDetails = null;
      List<EmployeeEducationDetails> employeeEducationDetailsList = null;
      if (employeeMasterDetails.getListDependentDetails() != null && !employeeMasterDetails.getListDependentDetails().isEmpty()) {
         for (DependentDetails depDetails : employeeMasterDetails.getListDependentDetails()) {
            depDetails.setEmployeeMasterDetails(employeeMasterDetails);
            depDetails.setGenderMaster(depDetails.getGenderMaster());
            //  depDetails.setCode(depDetails.getId());
         }
      }
      if (employeeMasterDetails.getEmployeeContactDetails() != null) {
         EmployeeContactDetails employeeContactDetails = employeeMasterDetails.getEmployeeContactDetails();
         employeeContactDetails.setEmployeeMasterDetails(employeeMasterDetails);
         employeeContactDetails.setCode(employeeContactDetails.getId());

      }
      if (employeeMasterDetails.getListEmployeeProfessionDetails() != null && !employeeMasterDetails.getListEmployeeProfessionDetails().isEmpty()) {
         for (EmployeeProfessionDetails empProDetails : employeeMasterDetails.getListEmployeeProfessionDetails()) {
            empProDetails.setEmployeeMasterDetails(employeeMasterDetails);
            //  empProDetails.setCode(empProDetails.getId());
         }
      }
      if (employeeMasterDetails.getListEmployeeEducationDetails() != null && !employeeMasterDetails.getListEmployeeEducationDetails().isEmpty())
         for (EmployeeEducationDetails empEduDetails : employeeMasterDetails.getListEmployeeEducationDetails()) {
            empEduDetails.setEmployeeMasterDetails(employeeMasterDetails);
            // empEduDetails.setCode(empEduDetails.getId());
         }
      if (employeeMasterDetails.getEmpDeptDetailsList() != null && !employeeMasterDetails.getEmpDeptDetailsList().isEmpty()) {
         for (EmployeeDepartmentDetails empDeptDetails : employeeMasterDetails.getEmpDeptDetailsList()) {
            empDeptDetails.setEmployeeMasterDetails(employeeMasterDetails);
            empDeptDetails.setDepartmentMaster(empDeptDetails.getDepartmentMaster());
            // empDeptDetails.setCode(empDeptDetails.getId());
         }
      }


/**
 *  rsd issue fixes for employee department   mapping
 */
      UnitMaster uMaster = employeeMasterDetails.getUnitMaster();
      List<UnitMaster> unitList = new ArrayList<>();
      unitList = Arrays.asList(uMaster);
      List<UnitMaster> unitMasterList1 = unitList;

      unitList.stream().forEach(uMList -> {
               uMList.setId(uMList.getId());
               uMList.setId(uMList.getId());
               uMList.setCode(uMList.getCode());
               uMList.setDesc(uMList.getDesc());
               uMList.setCityMaster(uMList.getCityMaster());
               uMList.setContactNo(uMList.getContactNo());
               uMList.setEmailId(uMList.getEmailId());
               uMList.setActive(uMList.getActive());
               uMList.setOrganizationMaster(uMList.getOrganizationMaster());
               uMList.setFaxNo(uMList.getFaxNo());
               uMList.setCountryMaster(uMList.getCountryMaster());
               uMList.setPinCode(uMList.getPinCode());
               uMList.setStateMaster(uMList.getStateMaster());
               uMList.setUnitCode(uMList.getUnitCode());
               //  uMList.setUnitLogo(uMList.getUnitLogo());
               uMList.setOrganizationMaster(uMList.getOrganizationMaster());
            }
      );


      // code for the employee unit department mapping for all need to bind from frontEnd
      if (CollectionUtils.isNotEmpty(employeeMasterDetails.getListunitmaster())) {
         List<UnitMaster> unitMasterList = employeeMasterDetails.getListunitmaster();
         unitMasterList.stream().forEach(unitMaster -> {
            unitMaster.setId(unitMaster.getId());
            unitMaster.setCode(unitMaster.getCode());
            unitMaster.setDesc(unitMaster.getDesc());
            unitMaster.setCityMaster(unitMaster.getCityMaster());
            unitMaster.setContactNo(unitMaster.getContactNo());
            unitMaster.setEmailId(unitMaster.getEmailId());
            unitMaster.setActive(unitMaster.getActive());
            unitMaster.setOrganizationMaster(unitMaster.getOrganizationMaster());
            unitMaster.setFaxNo(unitMaster.getFaxNo());
            unitMaster.setCountryMaster(unitMaster.getCountryMaster());
            unitMaster.setPinCode(unitMaster.getPinCode());
            unitMaster.setStateMaster(unitMaster.getStateMaster());
            unitMaster.setUnitCode(unitMaster.getUnitCode());
            // unitMaster.setUnitLogo(unitMaster.getUnitLogo());
            unitMaster.setOrganizationMaster(unitMaster.getOrganizationMaster());
         });
      }

      employeeMasterDetails.setListEmployeeProfessionDetails(employeeProfessionDetailsList);
      employeeMasterDetails.setListDependentDetails(dd);
      employeeMasterDetails.setListEmployeeEducationDetails(employeeEducationDetailsList);
      employeeMasterDetails.setEmployeeContactDetails(empContactDetails);
      employeeMasterDetails.setEmpDeptDetailsList(employeeDepartmentDetails);

      //issue fix for rsd
      employeeMasterDetails.setListunitmaster(unitMasterList1);
      return employeeMasterDetails;
   }

   /***
    *
    * @param id
    * @param pathcObject
    * @return updated employee
    */
   public EmployeeMasterDetails deActivateEmployee(Long id, String pathcObject) {
      Optional<EmployeeMasterDetails> employee = employeeRepository.findById(id);
      JsonObject obj = new JsonParser().parse(pathcObject).getAsJsonObject();
      EmployeeMasterDetails details = new EmployeeMasterDetails();
      if (employee.isPresent()) {
         details = employee.get();
         String deletedUserName = HttpUtils.getHeader(HttpHeaders.USER_NAME);
         details.setUpdatedBy(deletedUserName);
         details.setActive(obj.get("active").getAsBoolean());
           /* if(obj.get("remarks").)
            details.setRemarks(obj.get("remarks").getAsString());*/
      }
      return patch(details, id.toString());
   }

   @Override
   public EmployeeMasterDetails patch(EmployeeMasterDetails objectTopatch, String id) {
      if (objectTopatch == null)
         throw new DataException("There should exist an object already");
      if (StringUtils.isNotBlank(id)) {
         try {
            objectTopatch.setId(id);
            return employeeRepository.save(objectTopatch);
         } catch (Exception e) {
            throw new DataException("persistance error from save", e);
         }
      }
      return null;
   }

   public Page<EmployeeMasterDetails> getAll(Pageable pageable) {
      return employeeRepository.findAll(pageable);
   }

   public EmployeeMasterDetails updateEmpData(EmployeeMasterDetails updatedData) {
      return null;
   }

   public Optional<EmployeeMasterDetails> findById(Long id) {
      return employeeRepository.findById(id);
   }


   /**
    * @param empData
    * @param id
    *
    * @return
    */
   @Override
   @Transactional
   public EmployeeMasterDetails update(EmployeeMasterDetails empData, String id) {

      String createdOrUpdatedBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
      Long employeeMasterDetails = Long.parseLong(id);
      EmployeeMasterDetails byIdData=employeeRepository.getOne(employeeMasterDetails) ;

     /* if(empData.getEmployeeTypeMaster()!=null  ){
         Long employeeType=Long.parseLong(empData.getEmployeeTypeMaster().getId());
         Long otRole=employeeType;
         OTRoleMaster otRoleMaster=otRoleMasterRepository.getOne(otRole);
         empData.setOtRoleMaster(otRoleMaster);
      }*/
        /*List<DependentDetails> listOfDependedByEmployee=dependentDetailsRepository.findAllByEmployeeMasterDetailsId(employeeMasterDetails);
        System.out.println(listOfDependedByEmployee);
        listOfDependedByEmployee.stream().forEach(dependentStreamObj->{
            dependentStreamObj.setActive(false);
            dependentStreamObj.setUpdatedBy(createdOrUpdatedBy);
        });*/

        /*List<EmployeeProfessionDetails> listOfProffession=employeeProffessionalRepository.findAllByEmployeeMasterDetailsId(employeeMasterDetails);
          listOfProffession.stream().forEach(streamObj->{
              streamObj.setActive(false);
              streamObj.setUpdatedBy(createdOrUpdatedBy);
          });*/
         /*List<EmployeeEducationDetails> listOfEducation=employeeEducationRepository.findAllByEmployeeMasterDetailsId(employeeMasterDetails);
         listOfEducation.stream().forEach(streamEducation->{
             streamEducation.setActive(false);
             streamEducation.setUpdatedBy(createdOrUpdatedBy);
         });*/

        /* List<EmployeeDepartmentDetails>  listOfDept=employeeDepartmentDetailsRepository.findAllByEmployeeMasterDetailsId(employeeMasterDetails); {
             listOfDept.stream().forEach(employeeDepartmentDetails -> {
                 employeeDepartmentDetails.setActive(false);
                 employeeDepartmentDetails.setUpdatedBy(createdOrUpdatedBy);
             });
        }*/
      //  employeeContactDetailsRepository.deleteByEmployeeMasterDetails(employeeMasterDetails);
     /* if (StringUtils.isNotBlank(empData.getName())) {
         empData.setDesc(empData.getName());
      }*/

      empData.setId(employeeMasterDetails);
      if(StringUtils.isEmpty(empData.getCode())){
         empData.setCode(employeeMasterDetails.toString());
      }
      else
         empData.setCode(byIdData.getCode());

      if (CollectionUtils.isNotEmpty(empData.getEmpDeptDetailsList())) {
         List<EmployeeDepartmentDetails> updateEmpDept = empData.getEmpDeptDetailsList();
         updateEmpDept.stream().forEach(employeeDepartmentDetails -> {
            employeeDepartmentDetails.setActive(true);
            employeeDepartmentDetails.setEmployeeMasterDetails(empData);
            employeeDepartmentDetails.setCreatedBy(createdOrUpdatedBy);
         });
         empData.setEmpDeptDetailsList(updateEmpDept);
      }

      if (CollectionUtils.isNotEmpty(empData.getListDependentDetails())) {
         List<DependentDetails> updateEmpDependent = empData.getListDependentDetails();
         updateEmpDependent.stream().forEach(dependentDetails -> {
            //  dependentDetails.setCode(dependentDetails.getId());
            dependentDetails.setActive(true);
            dependentDetails.setEmployeeMasterDetails(empData);
            dependentDetails.setCreatedBy(createdOrUpdatedBy);
         });
         empData.setListDependentDetails(updateEmpDependent);
      }
      if (empData.getEmployeeContactDetails() != null) {
         EmployeeContactDetails upDatedContact = empData.getEmployeeContactDetails();
         upDatedContact.setCode(String.valueOf(Instant.now().toEpochMilli()));
         upDatedContact.setActive(true);
         upDatedContact.setCreatedBy(createdOrUpdatedBy);
         empData.setEmployeeContactDetails(upDatedContact);

      }
      if (CollectionUtils.isNotEmpty(empData.getListEmployeeEducationDetails())) {
         List<EmployeeEducationDetails> updatedEducation = empData.getListEmployeeEducationDetails();
         updatedEducation.stream().forEach(employeeEducationDetails -> {
            // employeeEducationDetails.setCode(String.valueOf(Instant.now().toEpochMilli()));
            employeeEducationDetails.setActive(true);
            employeeEducationDetails.setCreatedBy(createdOrUpdatedBy);
            employeeEducationDetails.setEmployeeMasterDetails(empData);
         });
         empData.setListEmployeeEducationDetails(updatedEducation);
      }
      if (CollectionUtils.isNotEmpty(empData.getListEmployeeProfessionDetails())) {
         List<EmployeeProfessionDetails> updatedEmpProf = empData.getListEmployeeProfessionDetails();
         updatedEmpProf.stream().forEach(employeeProfessionDetails -> {
            employeeProfessionDetails.setActive(true);
            employeeProfessionDetails.setCreatedBy(createdOrUpdatedBy);
            employeeProfessionDetails.setEmployeeMasterDetails(empData);
         });
         empData.setListEmployeeProfessionDetails(updatedEmpProf);
      }

      //update the unit the for the existing data   on date -7-12-2019   for HK as there is only single unit
      UnitMaster uMaster = empData.getUnitMaster();
      List<UnitMaster> unitList = new ArrayList<>();
      unitList = Arrays.asList(uMaster);
      List<UnitMaster> unitMasterList1 = unitList;

      unitList.stream().forEach(uMList -> {
               uMList.setId(uMList.getId());
               uMList.setId(uMList.getId());
               uMList.setCode(uMList.getCode());
               uMList.setDesc(uMList.getDesc());
               uMList.setCityMaster(uMList.getCityMaster());
               uMList.setContactNo(uMList.getContactNo());
               uMList.setEmailId(uMList.getEmailId());
               uMList.setActive(uMList.getActive());
               uMList.setOrganizationMaster(uMList.getOrganizationMaster());
               uMList.setFaxNo(uMList.getFaxNo());
               uMList.setCountryMaster(uMList.getCountryMaster());
               uMList.setPinCode(uMList.getPinCode());
               uMList.setStateMaster(uMList.getStateMaster());
               uMList.setUnitCode(uMList.getUnitCode());
               //  uMList.setUnitLogo(uMList.getUnitLogo());
               uMList.setOrganizationMaster(uMList.getOrganizationMaster());
            }

      );
      empData.setListunitmaster(unitMasterList1);

      return employeeRepository.save(empData);
   }


   public void deleteDependentData(Long id) {
      dependentDetailsRepository.deleteById(id);
   }

   public void deleteEducationData(Long id) {
      employeeEducationRepository.deleteById(id);
   }

   public void deleteProfessionalData(Long id) {
      employeeProffessionalRepository.deleteById(id);
   }

   public void deleteDepartmentData(Long id) {
      employeeDepartmentDetailsRepository.deleteById(id);
   }

   /**
    * Find {@link EmployeeMasterDetails} by given code.
    *
    * @param code given valid master code.
    *
    * @return {@link EmployeeMasterDetails} object.
    */
   public EmployeeMasterDetails findByCode(final String code) {
      if (StringUtils.isBlank(code)) {
         return null;
      }

      return employeeRepository.findByCode(code);
   }

   public List<EmployeeMasterDetails> getByType(Long empType) {

      EmployeeTypeMaster employeeTypeMaster = employeeTypeMasterRepository.getOne(empType);


      return employeeRepository.findByEmployeeTypeMasterAndActive(employeeTypeMaster, true);
   }


   public EmployeeMasterDetails getByName(EmployeeMasterDetails employeeName) {
      String nn = employeeName.getName();
      EmployeeMasterDetails employeeMasterDetails = employeeRepository.findByName(nn);
      System.out.println(employeeMasterDetails);
      String empName = employeeMasterDetails.getName();
      String empDesc = employeeMasterDetails.getDesc();
      System.out.println(empName);
      return employeeMasterDetails;
   }

   public EmployeeMasterDetails saveNam(EmployeeMasterDetails emp){
      EmployeeMasterDetails empSave=employeeRepository.save(emp);
      EmployeeMasterDetails returnDat=empSave;
      return       empSave;
   }

   public byte[] addImage(byte[] emp) {
      byte[] mm = emp;
      // File file=new File("empImage");{
      File file = new File("mm");
      {
         if (file.exists()) {
            try {
               BufferedImage bufferedImage = ImageIO.read(file);
               ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
               ImageIO.write(bufferedImage, "png", byteOutStream);
               return byteOutStream.toByteArray();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
      return null;
   }


}

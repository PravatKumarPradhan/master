package com.sdgt.medcare.master.controller.common;

import com.core.base.rest.controller.RestWSController;
import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.unit.EmployeeMasterDetails;
import com.sdgt.medcare.master.repository.common.EmployeeRepository;
import com.sdgt.medcare.master.repository.common.OrganizationMasterRepository;
import com.sdgt.medcare.master.search.EmployeeSearchSpecification;
import com.sdgt.medcare.master.search.GlobalSearchRequest;
import com.sdgt.medcare.master.service.common.EmployeeMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/***
 * @author Pravat Kumar Pradhan
 */
@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController extends RestWSController<EmployeeMasterDetails> {
	@Autowired
	private EmployeeMasterService employeeMasterService;

	@Autowired
	private EmployeeRepository employeeRepository;



	@Autowired
	OrganizationMasterRepository organizationMasterRepository;



	 @Autowired
	EmployeeSearchSpecification specification;

	Logger empControllerLogger = LoggerFactory.getLogger(EmployeeController.class);

	@RequestMapping(value = { "/list", "/list" }, method = RequestMethod.POST, consumes = {
			MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<EmployeeMasterDetails> getObjectByOneQuery(@RequestBody String json) {
		return employeeMasterService.searchAPI(json);
	}
	@PostMapping  ("/save")
	public EmployeeMasterDetails createEmployee(@RequestBody EmployeeMasterDetails emp){
		String createdBy=HttpUtils.getHeader(HttpHeaders.USER_NAME);
		String orgIdString=HttpUtils.getHeader(HttpHeaders.ORG_ID);
		Long orgId=Long.parseLong(orgIdString);
		OrganizationMaster organizationMaster=organizationMasterRepository.getOne(orgId) ;
		emp.setCreatedBy(createdBy);
		emp.setOrganizationMaster(organizationMaster);
		return employeeMasterService.create(emp);
	}
	/**
	 *
	 * @param id
	 * @param emp
	 * @return   employee details
	 */
	@PatchMapping("/deActiveEmp/{id}")
	public EmployeeMasterDetails deActivateEmployee(@PathVariable Long id, @RequestBody String  emp){
		String deletedUserName=HttpUtils.getHeader(HttpHeaders.USER_NAME);
		return  employeeMasterService.deActivateEmployee(id,emp);
	}
	/**
	 *
	 * @param pageable
	 * @return
	 */
	@GetMapping("/getAll")
	public Page<EmployeeMasterDetails> getAllPatient(Pageable pageable){
		return employeeMasterService.getAll(pageable);
	}

	/**
	 *
	 * @param id
	 * @param details
	 * @return
	 */
	@PutMapping("update/{id}")
	public ResponseEntity <EmployeeMasterDetails> updateEmp(@PathVariable  Long id,@RequestBody EmployeeMasterDetails details) {

		Optional<EmployeeMasterDetails> empData=employeeMasterService.findById(id);
		if(!empData.isPresent()){
			empControllerLogger.error("Employee Data" + empData + " is not existed");
			ResponseEntity.badRequest().build();
		}
		String updatedUserName = HttpUtils.getHeader(HttpHeaders.USER_NAME);
		String orgIdString=HttpUtils.getHeader(HttpHeaders.ORG_ID);
		Long orgId=Long.parseLong(orgIdString);
		OrganizationMaster organizationMaster=organizationMasterRepository.getOne(orgId) ;
		details.setUpdatedBy(updatedUserName);
		details.setOrganizationMaster(organizationMaster);
		return  ResponseEntity.ok(employeeMasterService.update(details,id.toString()));
	}
	/**
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("getById/{id}")
	public ResponseEntity getById(@PathVariable Long id){
		return ResponseEntity.ok(employeeMasterService.findById(id)) ;
	}

	@DeleteMapping("deleteEmpDept/{id}")
	public  void deleteDept(@PathVariable Long id){
		employeeMasterService.deleteDepartmentData(id);
	}

	@DeleteMapping("deleteEmpPro/{id}")
	public void deletePro (@PathVariable  Long id){
		employeeMasterService.deleteProfessionalData(id);
	}

	@DeleteMapping("deleteEdu/{id}")
	public void deleteEdu(@PathVariable Long id){
		employeeMasterService.deleteEducationData(id);
	}

	@DeleteMapping("deleteEmpDependent/{id}")
	public void deleteDependedntData(@PathVariable Long id){

		employeeMasterService.deleteDependentData(id);
	}


	@GetMapping("/getByType")
	public List<EmployeeMasterDetails> getEmpBYType(@RequestParam (value = "empType") Long empType ){
		return   employeeMasterService.getByType(empType);
	}


	//@GetMapping("/search")
	/*@GetMapping
	public List<EmployeeMasterDetails> findAllBySpecification(@RequestParam(value = "search") String search){
		EmployeeSpecificationBuilder builder= new EmployeeSpecificationBuilder();
		String operationSetExper= Joiner.on("|").join(SearchOperationOperators.SIMPLE_SET_OPERTION_SET);
		Pattern pattern=Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),") ;
	//	Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
		Matcher matcher=pattern.matcher(search+",");
		System.out.println(matcher);
		while (matcher.find())
		{
			builder.with(matcher.group(1),matcher.group(2),matcher.group(4),matcher.group(3),matcher.group(5));
		}
		Specification<EmployeeMasterDetails> spec=builder.build();

		return employeeRepository.findAll(spec);
	}*/

	@PostMapping("/search")
	public Page<EmployeeMasterDetails> search(@RequestBody GlobalSearchRequest filter,Pageable pageable){
		return specification.employeeMasterSearch(filter,pageable);
	}

	@PostMapping("/byName")
	public EmployeeMasterDetails getByEmpName(@RequestBody EmployeeMasterDetails empName){

		return employeeMasterService.getByName(empName);
	}

	/**
	 *  This Rest End point will fetch the Employee by its code
	 * @param empCode
	 * @return
	 */
	@GetMapping("/byCode/{empCode}")
	public ResponseEntity<EmployeeMasterDetails> getByEmpCode(@PathVariable String  empCode)
	{
		return ResponseEntity.ok(employeeMasterService.findByCode(empCode))  ;
	}


}

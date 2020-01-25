package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.EmployeeTypeMaster;
import com.sdgt.medcare.master.entity.unit.EmployeeMasterDetails;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends MastersBaseRepository<EmployeeMasterDetails,Long>, JpaSpecificationExecutor<EmployeeMasterDetails> {
	@Query("SELECT t FROM EmployeeMasterDetails t join  t.employeeTypeMaster e  where e.isDoctor = TRUE and t.active = true order by t.desc asc")
	List<EmployeeMasterDetails> findByDoctor();

	@Query("SELECT t FROM EmployeeMasterDetails t join  t.employeeTypeMaster e  where e.isStudent = TRUE and t.active = true order by t.desc asc")
	List<EmployeeMasterDetails> findByStudent();

	@Query("SELECT t FROM EmployeeMasterDetails t join  t.employeeTypeMaster e  where e.isStaff = TRUE and t.active = true order by t.desc asc")
	List<EmployeeMasterDetails> findByStaff();


	/*@Query("SELECT t FROM DoctorEmpStudUnitMapper t join t.DoctorEmpStudMaster dm join t.listUnitMaster um join dm.employeeTypeMaster e  where dm.isDoctor = TRUE and dm.status = 'A' um.UnitMaster.id=:unitId")
	List<DoctorEmpStudMaster> findByDoctor(@Param("unitId") String unitId);*/



	/**
	 * Find {@link EmployeeMasterDetails} by code.
	 *
	 * @param code a valid code.
	 * @return {@link EmployeeMasterDetails} if found. Else null.
	 */
	EmployeeMasterDetails findByCode(final String code);

	List<EmployeeMasterDetails> findByEmployeeTypeMaster(Long empTypeId);

/*	Page<EmployeeMasterDetails> findByActive(Boolean aBoolean);*/
	 List<EmployeeMasterDetails> findByEmployeeTypeMasterAndActive(EmployeeTypeMaster empType, Boolean status);

	@Query("select t.isdoctorshare from EmployeeMasterDetails t  where " +
			" t.id =:doctorId ")
	boolean  findDoctorShareDoctorById(@Param("doctorId") Long servicecode);

	EmployeeMasterDetails findByName(String name);

}

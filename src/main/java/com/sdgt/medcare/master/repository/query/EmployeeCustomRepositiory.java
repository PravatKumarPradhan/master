package com.sdgt.medcare.master.repository.query;

import java.util.List;

public interface EmployeeCustomRepositiory {

   List findByQuery(String empIdNumber,String empName,String empNumber,Long empType);
}

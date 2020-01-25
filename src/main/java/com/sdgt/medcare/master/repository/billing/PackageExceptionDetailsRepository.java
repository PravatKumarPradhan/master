package com.sdgt.medcare.master.repository.billing;

import com.sdgt.medcare.master.entity.billing.packageservice.PackageExceptionDetails;
import com.sdgt.medcare.master.repository.common.MastersBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface PackageExceptionDetailsRepository extends MastersBaseRepository<PackageExceptionDetails, Long> {
}

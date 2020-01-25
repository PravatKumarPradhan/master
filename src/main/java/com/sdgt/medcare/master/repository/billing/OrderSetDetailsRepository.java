package com.sdgt.medcare.master.repository.billing;

import com.sdgt.medcare.master.entity.billing.OrderSetDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface OrderSetDetailsRepository extends JpaRepository<OrderSetDetails, Long> {
}

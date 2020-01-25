package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.bmw.ServiceProviderMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ServiceProviderRepository")
public interface ServiceProviderRepository extends JpaRepository<ServiceProviderMaster, Long> {
}

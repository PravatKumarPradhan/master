package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.CompanyMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.CompanyServiceMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyServiceMasterRepository extends JpaRepository<CompanyServiceMaster,Long> {

    List<CompanyServiceMaster> findByServiceMaster(final ServiceMaster serviceMaster);
    CompanyServiceMaster findByServiceMasterAndCompanyMasterAndUnitMaster(final ServiceMaster  serviceMaster, final  CompanyMaster companyMaster, final UnitMaster unitMaster);
    List<CompanyServiceMaster> findByServiceMasterAndCode(final ServiceMaster serviceMaster,String code);
}

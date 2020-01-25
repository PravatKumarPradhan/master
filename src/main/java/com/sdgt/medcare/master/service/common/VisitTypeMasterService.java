package com.sdgt.medcare.master.service.common;

import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.global.VisitTypeMaster;
import com.sdgt.medcare.master.repository.common.VisitTypeMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Service
public class VisitTypeMasterService extends BaseService<VisitTypeMaster> {
    @Autowired
    private VisitTypeMasterRepository visitTypeMasterRepository;

    /**
     * Gets {@link VisitTypeMaster} object by given visit type code.
     *
     * @param visitTypeCode a valid code from {@link VisitTypeMaster}
     * @return the {@link VisitTypeMaster}
     */
    public VisitTypeMaster findByCode(final String visitTypeCode) {
        return visitTypeMasterRepository.findByCode(visitTypeCode);
    }

    public Optional<VisitTypeMaster> findById(Long id){
        return  visitTypeMasterRepository.findById(id) ;
    }
}

package com.sdgt.medcare.master.service.billing;

import com.sdgt.medcare.master.dto.billing.ContractDTO;
import com.sdgt.medcare.master.entity.billing.contract.ContractMaster;
import com.sdgt.medcare.master.entity.org.CompanyMaster;
import com.sdgt.medcare.master.repository.common.CompanyMasterRepository;
import com.sdgt.medcare.master.repository.billing.ContractMasterRepository;
import com.sdgt.medcare.master.translator.billing.ContractDTOEntityTranslator;
import com.sdgt.medcare.master.util.NumberParseAssistant;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Service
public class ContractService {
    private Logger logger = LoggerFactory.getLogger(ContractService.class);

    @Autowired
    private ContractDTOEntityTranslator contractTranslator;

    @Autowired
    private ContractMasterRepository contractMasterRepository;

    @Autowired
    private CompanyMasterRepository companyMasterRepository;

    /**
     * Find all {@link ContractMaster}
     *
     * @return list of all contracts created.
     */
    public List<ContractDTO> findAll() {
        return contractMasterRepository.findAll().stream().map(contractTranslator::translateContractDTO).collect(Collectors.toList());
    }

    /**
     * Find {@link ContractMaster} by given Id.
     *
     * @param id given valid identifier of {@link ContractMaster}
     * @return {@link ContractMaster} object.
     */
    public ContractDTO findById(final String id) {
        final Optional<ContractMaster> contractMasterOptional = contractMasterRepository.findById(NumberParseAssistant.parseLong(id));

        return contractMasterOptional.map(contractTranslator::translateContractDTO).orElse(null);
    }

    /**
     * Find {@link ContractMaster} by given Code.
     *
     * @param code given valid code of {@link ContractMaster}
     * @return {@link ContractMaster}
     */
    public ContractDTO findByCode(final String code) {
        return contractTranslator.translateContractDTO(contractMasterRepository.findByCode(code));
    }

    /**
     * Find all {@link ContractMaster} by given company.
     *
     * @param companyCode given.
     * @return list of {@link ContractDTO} objects
     */
    public Collection<ContractDTO> findByCompanyCode(final String companyCode) {
        final CompanyMaster companyMaster = companyMasterRepository.findByCode(companyCode);
        final Collection<ContractMaster> contractMasterCollection = contractMasterRepository.findByCompany(companyMaster);

        if(CollectionUtils.isEmpty(contractMasterCollection)) {
            return Collections.emptyList();
        }

        return contractMasterCollection.stream().map(contractTranslator::translateContractDTO).collect(Collectors.toList());
    }

    /**
     * Save {@link ContractDTO}
     * @param contractDTO given.
     */
    @Transactional
    public void save(final ContractDTO contractDTO) {
        contractMasterRepository.save(contractTranslator.translate(contractDTO));
    }
}

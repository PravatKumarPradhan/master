package com.sdgt.medcare.master.controller.billing;

import com.sdgt.medcare.master.dto.billing.ContractDTO;
import com.sdgt.medcare.master.service.billing.ContractService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@CrossOrigin
@RestController
@RequestMapping("/contracts/")
public class ContractController {
    private Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ContractService contractService;

    @GetMapping("/")
    public @ResponseBody
    ResponseEntity<Collection<ContractDTO>> getAllContract() {
        final Collection<ContractDTO> contractDTO = contractService.findAll();

        if (contractDTO == null) {
            logger.warn("getAllContract; No contract records found.");
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(contractDTO);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<ContractDTO> findContractById(final @PathVariable("id") String id) {
        final ContractDTO contractDTO = contractService.findById(id);

        if (contractDTO == null) {
            logger.warn("getContract; id = {}, not found.", id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(contractDTO);
    }

    @GetMapping("/code/{code}")
    public @ResponseBody
    ResponseEntity<ContractDTO> findContractByCode(final @PathVariable("code") String code) {
        final ContractDTO contractDTO = contractService.findByCode(code);

        if (contractDTO == null) {
            logger.warn("getContract; code = {}, not found.", code);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(contractDTO);
    }

    @GetMapping("/company/{companyCode}")
    public @ResponseBody
    ResponseEntity<Collection<ContractDTO>> findContractByCompanyCode(final @PathVariable("companyCode") String companyCode) {
        final Collection<ContractDTO> contractDTOCollection = contractService.findByCompanyCode(companyCode);

        if (CollectionUtils.isEmpty(contractDTOCollection)) {
            logger.warn("getContract; companyCode = {}, not found.", companyCode);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(contractDTOCollection);
    }

    @PostMapping("/save")
    public @ResponseBody
    ResponseEntity<Boolean> saveContract(final @RequestBody ContractDTO dto) {
        contractService.save(dto);

        return ResponseEntity.ok(true);
    }
}

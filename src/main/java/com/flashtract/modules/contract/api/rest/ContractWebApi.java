package com.flashtract.modules.contract.api.rest;

import com.flashtract.crosscutting.domain.ContractDTO;
import com.flashtract.crosscutting.domain.VendorDTO;
import com.flashtract.foundation.framework.exceptions.FlashtractApplicationException;
import com.flashtract.foundation.framework.exceptions.util.ErrorResponseEntityBuilder;
import com.flashtract.modules.contract.usecase.ContractManager;
import java.math.BigInteger;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ContractWebApi
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@Log4j2
@RestController
@RequestMapping(value = "/api/contract")
public class ContractWebApi {

    @Autowired
    private ContractManager manager;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewContract(@RequestBody
    final ContractDTO contract) {
        log.info("web-api: Starting to create contract number: {}",
                contract.getContranctNumber());
        try {
            return ResponseEntity.ok(manager.createContract(contract));
        } catch (FlashtractApplicationException e) {
            log.error("web-api: There was an error creating contract {}",
                    contract.getContranctNumber());
            return ErrorResponseEntityBuilder.builder().withException(e)
                    .build();
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getContracts() {
        log.info("web-api: Searching for all contracts");
        try {
            return ResponseEntity.ok(manager.getAllContracts());
        } catch (FlashtractApplicationException e) {
            log.error("web-api: There was an error searching contracts");
            return ErrorResponseEntityBuilder.builder().withException(e)
                    .build();
        }
    }

    @GetMapping("/{contractNumber}")
    public ResponseEntity<Object> getContract(
            @PathVariable(name = "contractNumber")
            final BigInteger contractNumber) {
        log.info("web-api: Searching for all contracts");
        try {
            return ResponseEntity.ok(manager.getContractById(contractNumber));
        } catch (FlashtractApplicationException e) {
            log.error("web-api: There was an error searching contracts");
            return ErrorResponseEntityBuilder.builder().withException(e)
                    .build();
        }
    }

    @PutMapping("{contractNumber}/update/vendor/")
    public ResponseEntity<Object> updateVendor(
            @PathVariable(name = "contractNumber")
            final BigInteger contractNumber,
            @RequestBody
            final VendorDTO vendor) {
        log.info("web-api: Updating vendor for contract number: {}",
                contractNumber);
        try {
            return ResponseEntity
                    .ok(manager.updateVendor(contractNumber, vendor));
        } catch (FlashtractApplicationException e) {
            log.error("web-api: There was an error searching contracts");
            return ErrorResponseEntityBuilder.builder().withException(e)
                    .build();
        }
    }

}

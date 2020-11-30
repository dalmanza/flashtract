package com.flashtract.modules.contract.dataprovider.jpa;

import com.flashtract.crosscutting.domain.ContractDTO;
import com.flashtract.crosscutting.domain.VendorDTO;
import com.flashtract.crosscutting.patterns.Translator;
import com.flashtract.crosscutting.persistence.entity.Contract;
import com.flashtract.crosscutting.persistence.entity.Vendor;
import com.flashtract.crosscutting.persistence.repository.jpa.ContractRepository;
import com.flashtract.foundation.framework.exceptions.FlashtractSystemException;
import com.flashtract.foundation.framework.exceptions.util.ExceptionBuilder;
import com.flashtract.foundation.framework.stereotypes.DataProvider;
import com.flashtract.modules.contract.dataprovider.ContractDataProvider;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;

/**
 * ContractDataJPAProvider
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@Log4j2
@DataProvider
public class ContractDataJPAProvider implements ContractDataProvider {

    @Autowired
    private ContractRepository repositoryContract;

    @Autowired
    @Qualifier("contractDTOTOContractEntityTranslator")
    Translator<ContractDTO, Contract> contractDTOTOEntity;

    @Autowired
    @Qualifier("contractEntityTOContractDTOTranslator")
    Translator<Contract, ContractDTO> contractEntityTODTO;

    @Autowired
    @Qualifier("vendorDTOToEntityTranslator")
    Translator<VendorDTO, Vendor> vendorTranslator;

    @Override
    public ContractDTO createContract(final ContractDTO contract)
            throws FlashtractSystemException {

        return contractEntityTODTO.translate(repositoryContract
                .save(contractDTOTOEntity.translate(contract)));
    }

    @Override
    public List<ContractDTO> getContracts() throws FlashtractSystemException {
        try {
            return repositoryContract.getAllContracts()
                    .stream()
                    .map(contractEntityTODTO::translate)
                    .collect(Collectors.toList());

        } catch (DataAccessException e) {
            log.error("");
            throw ExceptionBuilder.builder()
                    .withMessage("")
                    .withParentException(e).buildSystemException();
        }
    }

    @Override
    public void updateVendor(final BigInteger contractNumber,
            final VendorDTO vendor) {
        repositoryContract.updateContractVendor(contractNumber,
                vendorTranslator.translate(vendor));
    }

    @Override
    public ContractDTO getContractById(final BigInteger contractNumber) {
        return contractEntityTODTO.translate(
                repositoryContract.getContractByContractNumber(contractNumber));
    }

}

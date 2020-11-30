package com.flashtract.modules.contract.usecase;

import com.flashtract.crosscutting.domain.ContractDTO;
import com.flashtract.crosscutting.domain.VendorDTO;
import com.flashtract.foundation.framework.exceptions.FlashtractApplicationException;
import com.flashtract.foundation.framework.stereotypes.UseCase;
import com.flashtract.modules.contract.dataprovider.ContractDataProvider;
import com.flashtract.modules.contract.usecase.rules.ruleValidator;
import java.math.BigInteger;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ContractManager
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@Log4j2
@UseCase
public class ContractManager {

    @Autowired
    private ruleValidator contractValidator;

    @Autowired
    private ContractDataProvider contractProvider;

    public ContractDTO createContract(final ContractDTO contract)
            throws FlashtractApplicationException {
        log.info("use-case: Creating contract");
        ContractDTO contractValidated = contractValidator
                .approveContract(contract);
        return contractProvider.createContract(contractValidated);
    }

    public List<ContractDTO> getAllContracts()
            throws FlashtractApplicationException {
        log.info("use-case: Searching all contracts");
        return contractProvider.getContracts();
    }

    public ContractDTO getContractById(final BigInteger contractNumber)
            throws FlashtractApplicationException {
        log.info("use-case: Searching all contracts");
        return contractProvider.getContractById(contractNumber);
    }

    public ContractDTO updateVendor(final BigInteger contractNumber,
            final VendorDTO vendor) throws FlashtractApplicationException {
        contractProvider.updateVendor(contractNumber, vendor);
        return contractProvider.getContractById(contractNumber);
    }
    

}

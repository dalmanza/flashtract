package com.flashtract.modules.contract.dataprovider;

import com.flashtract.crosscutting.domain.ContractDTO;
import com.flashtract.crosscutting.domain.VendorDTO;
import com.flashtract.foundation.framework.exceptions.FlashtractSystemException;
import java.math.BigInteger;
import java.util.List;

/**
 * ContractDataProvider
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

public interface ContractDataProvider {

    ContractDTO createContract(final ContractDTO contract)
            throws FlashtractSystemException;

    List<ContractDTO> getContracts() throws FlashtractSystemException;

    void updateVendor(final BigInteger contractNumber, final VendorDTO vendor);

    ContractDTO getContractById(final BigInteger contractNumber);

}

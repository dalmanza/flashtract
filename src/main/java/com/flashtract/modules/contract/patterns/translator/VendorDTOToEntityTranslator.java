package com.flashtract.modules.contract.patterns.translator;

import com.flashtract.crosscutting.domain.VendorDTO;
import com.flashtract.crosscutting.patterns.Translator;
import com.flashtract.crosscutting.persistence.entity.Vendor;
import com.flashtract.foundation.framework.stereotypes.DesignConcept;

/**
 * ContractDTOTOContractEntityTranslator
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@DesignConcept
public class VendorDTOToEntityTranslator
        implements Translator<VendorDTO, Vendor> {

    @Override
    public Vendor translate(final VendorDTO input) {
        return Vendor.builder()
                .companyName(input.getCompanyName())
                .vendorId(input.getVendorId())
                .build();
    }



}

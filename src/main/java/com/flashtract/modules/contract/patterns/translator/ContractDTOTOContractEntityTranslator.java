package com.flashtract.modules.contract.patterns.translator;

import com.flashtract.crosscutting.domain.ContractDTO;
import com.flashtract.crosscutting.domain.InvoiceDTO;
import com.flashtract.crosscutting.patterns.Translator;
import com.flashtract.crosscutting.persistence.entity.Contract;
import com.flashtract.crosscutting.persistence.entity.Invoice;
import com.flashtract.crosscutting.persistence.entity.Vendor;
import com.flashtract.foundation.framework.stereotypes.DesignConcept;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * ContractDTOTOContractEntityTranslator
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@DesignConcept
public class ContractDTOTOContractEntityTranslator
        implements Translator<ContractDTO, Contract> {

    @Autowired
    @Qualifier("invoiceToInvoiceDTOTranslator")
    private Translator<Invoice, InvoiceDTO> invoiceToDto;

    @Override
    public Contract translate(final ContractDTO input) {
        return Contract.builder()
                .contranctNumber(input.getContranctNumber())
                .Status(input.getStatus())
                .toBill(input.getToBill())
                .vendor(getVendorInformation(input))
                .build();
    }

    private Vendor getVendorInformation(final ContractDTO input) {
        if (Objects.nonNull(input.getVendor())) {
            return Vendor.builder()
                    .companyName(input.getVendor().getCompanyName())
                    .vendorId(input.getVendor().getVendorId()).build();
        }
        return null;
    }

}

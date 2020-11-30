package com.flashtract.modules.contract.patterns.translator;

import com.flashtract.crosscutting.domain.ContractDTO;
import com.flashtract.crosscutting.domain.InvoiceDTO;
import com.flashtract.crosscutting.domain.VendorDTO;
import com.flashtract.crosscutting.patterns.Translator;
import com.flashtract.crosscutting.persistence.entity.Contract;
import com.flashtract.crosscutting.persistence.entity.Invoice;
import com.flashtract.foundation.framework.stereotypes.DesignConcept;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
public class ContractEntityTOContractDTOTranslator
        implements Translator<Contract, ContractDTO> {

    @Autowired
    @Qualifier("invoiceToInvoiceDTOTranslator")
    private Translator<Invoice, InvoiceDTO> invoiceToDto;

    @Override
    public ContractDTO translate(final Contract input) {

        if (Objects.nonNull(input)) {
            return ContractDTO.builder()
                    .contranctNumber(input.getContranctNumber())
                    .Status(input.getStatus())
                    .toBill(input.getToBill())
                    .invoice(getInvoices(input.getInvoices()))
                    .vendor(getVendorInformation(input))
                    .contractId(input.getContractId())
                    .build();

        }

        return ContractDTO.builder().build();

    }

    private List<InvoiceDTO> getInvoices(final List<Invoice> invoices) {
        if (Objects.nonNull(invoices)) {
            return invoices.stream()
                    .map(invoiceToDto::translate).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private VendorDTO getVendorInformation(final Contract input) {
        if (Objects.nonNull(input.getVendor())) {
            return VendorDTO.builder()
                    .companyName(input.getVendor().getCompanyName())
                    .vendorId(input.getVendor().getVendorId()).build();
        }
        return null;
    }

}

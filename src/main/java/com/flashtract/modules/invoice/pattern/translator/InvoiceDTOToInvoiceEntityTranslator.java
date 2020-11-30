package com.flashtract.modules.invoice.pattern.translator;

import com.flashtract.crosscutting.domain.InvoiceDTO;
import com.flashtract.crosscutting.patterns.Translator;
import com.flashtract.crosscutting.persistence.entity.Contract;
import com.flashtract.crosscutting.persistence.entity.Invoice;
import com.flashtract.foundation.framework.stereotypes.DesignConcept;

/**
 * ContractDTOTOContractEntityTranslator
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@DesignConcept
public class InvoiceDTOToInvoiceEntityTranslator
        implements Translator<InvoiceDTO, Invoice> {

    @Override
    public Invoice translate(final InvoiceDTO input) {
        return Invoice.builder()
                .invoiceNumber(input.getInvoiceNumber())
                .value(input.getValue())
                .contracts(Contract.builder()
                        .contractId(input.getContract().getContractId())
                        .build())
                .build();
    }

}

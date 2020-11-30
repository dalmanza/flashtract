package com.flashtract.modules.invoice.pattern.translator;

import com.flashtract.crosscutting.domain.InvoiceDTO;
import com.flashtract.crosscutting.patterns.Translator;
import com.flashtract.crosscutting.persistence.entity.Invoice;
import com.flashtract.foundation.framework.stereotypes.DesignConcept;
import java.util.Objects;

/**
 * ContractDTOTOContractEntityTranslator
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@DesignConcept
public class InvoiceToInvoiceDTOTranslator
        implements Translator<Invoice, InvoiceDTO> {

    @Override
    public InvoiceDTO translate(final Invoice input) {
        if (Objects.nonNull(input)) {
            return InvoiceDTO.builder()
                    .invoiceNumber(input.getInvoiceNumber())
                    .value(input.getValue())
                    .build();

        }
        return InvoiceDTO.builder().build();

    }


}

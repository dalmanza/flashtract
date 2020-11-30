package com.flashtract;

import com.flashtract.crosscutting.constants.ContractStatus;
import com.flashtract.crosscutting.domain.ContractDTO;
import com.flashtract.crosscutting.domain.InvoiceDTO;
import com.flashtract.crosscutting.persistence.entity.Invoice;
import java.math.BigInteger;
import java.util.Collections;

/**
 * DataAccrossTestingProject
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 *
 */

public class DataAcrossTestingProject {

    private static final Double INVOICE_VALUE = 500.0;
    private static final Double TO_BILL_VALUE = 1500.0;

    public static InvoiceDTO getInvoiceDTO() {
        return InvoiceDTO.builder()
                .documentation(null)
                .invoiceNumber(BigInteger.ONE)
                .value(INVOICE_VALUE)
                .build();
    }

    public static Invoice getInvoice() {
        return Invoice.builder()
                .invoiceNumber(BigInteger.ONE)
                .value(INVOICE_VALUE)
                .build();
    }

    public static ContractDTO getContractDTO() {
        return ContractDTO.builder()
                .contractId(BigInteger.ONE)
                .contranctNumber(BigInteger.TEN)
                .invoice(Collections.emptyList())
                .Status(ContractStatus.APPROVED)
                .toBill(TO_BILL_VALUE)
                .vendor(null)
                .build();
    }
}

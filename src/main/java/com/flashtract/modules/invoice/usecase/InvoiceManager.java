package com.flashtract.modules.invoice.usecase;

import com.flashtract.crosscutting.domain.ContractDTO;
import com.flashtract.crosscutting.domain.InvoiceDTO;
import com.flashtract.foundation.framework.exceptions.FlashtractApplicationException;
import com.flashtract.foundation.framework.exceptions.FlashtractBusinessException;
import com.flashtract.foundation.framework.exceptions.util.ExceptionBuilder;
import com.flashtract.foundation.framework.stereotypes.UseCase;
import com.flashtract.modules.contract.usecase.ContractManager;
import com.flashtract.modules.invoice.dataprovider.InvoiceDataProvider;
import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * InvoiceManager
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@UseCase
public class InvoiceManager {

    @Autowired
    private InvoiceDataProvider dataProvider;

    @Autowired
    private ContractManager contractManager;

    public InvoiceDTO getInvoiceByInvoiceNumber(
            final BigInteger invoiceNumber)
            throws FlashtractApplicationException {
        try {
            return dataProvider.getInvoiceByInvoiceNumber(invoiceNumber);
        } catch (FlashtractApplicationException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(
                            "use-case: there was an error searching invoice")
                    .buildSystemException();
        }

    }

    public List<InvoiceDTO> getInvoiceByContractNumber(
            final BigInteger contractNumber)
            throws FlashtractApplicationException {
        try {
            return dataProvider
                    .getInvoiceBygetInvoiceByInvoiceNumber(contractNumber);
        } catch (FlashtractApplicationException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(
                            "use-case: there was an error searching invoice")
                    .buildSystemException();
        }
    }

    public InvoiceDTO createInvoice(final InvoiceDTO invoice,
            final BigInteger contranctNumber)
            throws FlashtractApplicationException {

        try {
            final ContractDTO contract = contractManager
                    .getContractById(contranctNumber);
            final double totalContract = contract.getToBill();
            final BigInteger contractId = contract.getContractId();
            double totalSumInvoices = getInvoiceByContractNumber(
                    contranctNumber)
                            .stream().mapToDouble(InvoiceDTO::getValue).sum();

            if ((totalSumInvoices + invoice.getValue()) <= totalContract) {
                return dataProvider.createInvoice(invoice, contractId);
            } else {
                throw ExceptionBuilder.builder().withMessage(
                        "value of the invoice greater than the value of the contract")
                        .buildBusinessException();
            }

        } catch (FlashtractBusinessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(
                            "value of the invoice greater than the value of the contract")
                    .buildBusinessException();
        } catch (FlashtractApplicationException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(
                            "use-case: there was an error creating invoice")
                    .buildSystemException();
        }

    }

}

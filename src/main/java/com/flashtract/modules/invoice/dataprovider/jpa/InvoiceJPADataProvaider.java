package com.flashtract.modules.invoice.dataprovider.jpa;

import com.flashtract.crosscutting.domain.InvoiceDTO;
import com.flashtract.crosscutting.patterns.Translator;
import com.flashtract.crosscutting.persistence.entity.Invoice;
import com.flashtract.crosscutting.persistence.repository.jpa.InvoiceRepository;
import com.flashtract.foundation.framework.exceptions.FlashtractSystemException;
import com.flashtract.foundation.framework.exceptions.util.ExceptionBuilder;
import com.flashtract.foundation.framework.stereotypes.DataProvider;
import com.flashtract.modules.invoice.dataprovider.InvoiceDataProvider;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;

/**
 * InvoiceJPADataProvaider
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@Log4j2
@DataProvider
public class InvoiceJPADataProvaider implements InvoiceDataProvider {

    @Autowired
    private InvoiceRepository invoiceRepo;

    @Autowired
    @Qualifier("invoiceToInvoiceDTOTranslator")
    private Translator<Invoice, InvoiceDTO> invoiceToDto;

    @Autowired
    @Qualifier("invoiceDTOToInvoiceEntityTranslator")
    private Translator<InvoiceDTO, Invoice> dtoToEntity;

    @Override
    public InvoiceDTO getInvoiceByInvoiceNumber(
            final BigInteger invoiceNumber) throws FlashtractSystemException {
        try {
            return invoiceToDto.translate(
                    invoiceRepo.getInvoiceByInvoiceNumber(invoiceNumber));
        } catch (DataAccessException e) {
            String error = "data-provider: There was an error searching for invoice";
            log.error(
                    error);
            throw ExceptionBuilder.builder()
                    .withMessage(error)
                    .buildSystemException();
        }

    }

    @Override
    public List<InvoiceDTO> getInvoiceBygetInvoiceByInvoiceNumber(
            final BigInteger contractNumber) throws FlashtractSystemException {
        try {
            return invoiceRepo.getInvoiceByContractNumber(contractNumber)
                    .stream()
                    .map(invoiceToDto::translate).collect(Collectors.toList());
        } catch (DataAccessException e) {
            String error = "data-provider: There was an error searching for invoice";
            log.error(
                    error);
            throw ExceptionBuilder.builder()
                    .withMessage(error)
                    .buildSystemException();
        }
    }

    @Override
    public InvoiceDTO createInvoice(final InvoiceDTO invoice,
            final BigInteger contractId) throws FlashtractSystemException {
        try {
            return invoiceToDto
                    .translate(
                            invoiceRepo.save(dtoToEntity.translate(invoice)));

        } catch (final DataAccessException e) {
            String error = "data-provider: There was an error creating invoice";
            log.error(
                    error);
            throw ExceptionBuilder.builder()
                    .withMessage(error)
                    .buildSystemException();
        }
    }

}

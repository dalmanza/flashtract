package com.flashtract.modules.invoice.dataprovider.jpa;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.flashtract.DataAcrossTestingProject;
import com.flashtract.crosscutting.domain.InvoiceDTO;
import com.flashtract.crosscutting.patterns.Translator;
import com.flashtract.crosscutting.persistence.entity.Invoice;
import com.flashtract.crosscutting.persistence.repository.jpa.InvoiceRepository;
import com.flashtract.foundation.framework.exceptions.FlashtractApplicationException;
import com.flashtract.foundation.framework.exceptions.FlashtractSystemException;
import java.math.BigInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.QueryTimeoutException;

/**
 * InvoiceJPADataProvaider
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@ExtendWith(MockitoExtension.class)
public class InvoiceJPADataProvaiderTest {

    @Mock
    private InvoiceRepository invoiceRepo;

    @Mock
    @Qualifier("invoiceToInvoiceDTOTranslator")
    private Translator<Invoice, InvoiceDTO> invoiceToDto;

    @Mock
    private Translator<InvoiceDTO, Invoice> dtoToEntity;

    @InjectMocks
    private InvoiceJPADataProvaider provider;

    @Test
    public void getInvoiceByInvoiceNumberTest() {
        try {
            InvoiceDTO expected = DataAcrossTestingProject.getInvoiceDTO();
            when(invoiceToDto.translate(Mockito.any(Invoice.class)))
                    .thenReturn(expected);
            when(invoiceRepo
                    .getInvoiceByInvoiceNumber(Mockito.any(BigInteger.class)))
                            .thenReturn(DataAcrossTestingProject.getInvoice());
            InvoiceDTO response = provider
                    .getInvoiceByInvoiceNumber(BigInteger.ONE);

            assertEquals(expected.getInvoiceNumber(),
                    response.getInvoiceNumber());
            assertEquals(expected.getValue(),
                    response.getValue());
        } catch (FlashtractSystemException e) {
            fail();
        }
    }

    @Test
    public void getInvoiceByInvoiceNumberTestException()
            throws FlashtractSystemException {
        when(invoiceRepo
                .getInvoiceByInvoiceNumber(Mockito.any(BigInteger.class)))
                        .thenThrow(QueryTimeoutException.class);

        Assertions.assertThrows(FlashtractApplicationException.class,
                () -> provider.getInvoiceByInvoiceNumber(BigInteger.ONE));

    }

}

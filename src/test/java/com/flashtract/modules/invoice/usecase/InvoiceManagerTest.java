package com.flashtract.modules.invoice.usecase;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.flashtract.DataAcrossTestingProject;
import com.flashtract.crosscutting.domain.ContractDTO;
import com.flashtract.crosscutting.domain.InvoiceDTO;
import com.flashtract.foundation.framework.exceptions.FlashtractApplicationException;
import com.flashtract.foundation.framework.exceptions.FlashtractBusinessException;
import com.flashtract.modules.contract.usecase.ContractManager;
import com.flashtract.modules.invoice.dataprovider.InvoiceDataProvider;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * InvoiceManager
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@ExtendWith(MockitoExtension.class)
public class InvoiceManagerTest {

    @Mock
    private InvoiceDataProvider dataProvider;

    @Mock
    private ContractManager contractManager;

    @InjectMocks
    private InvoiceManager manager;

    private static final Double INVOICE_VALUE = 500.0;

    @Test
    public void createInvoiceTest()
            throws FlashtractApplicationException {

        ContractDTO expected = DataAcrossTestingProject.getContractDTO();
        List<InvoiceDTO> expectedInvoice = Arrays
                .asList(DataAcrossTestingProject.getInvoiceDTO());

        when(contractManager.getContractById(Mockito.any(BigInteger.class)))
                .thenReturn(expected);
        when(dataProvider
                .getInvoiceBygetInvoiceByInvoiceNumber(
                        Mockito.any(BigInteger.class)))
                                .thenReturn(expectedInvoice);
        when(dataProvider.createInvoice(Mockito.any(InvoiceDTO.class),
                Mockito.any(BigInteger.class)))
                        .thenReturn(DataAcrossTestingProject.getInvoiceDTO());
        InvoiceDTO response = manager.createInvoice(
                DataAcrossTestingProject.getInvoiceDTO(),
                BigInteger.ONE);
        assertEquals(BigInteger.ONE, response.getInvoiceNumber());
        assertEquals(INVOICE_VALUE, response.getValue());

    }

    @Test
    public void createInvoiceTestExceptionBusiness() {

        ContractDTO expected = DataAcrossTestingProject.getContractDTO();
        List<InvoiceDTO> expectedInvoice = Arrays
                .asList(DataAcrossTestingProject.getInvoiceDTO());

        try {
            when(contractManager.getContractById(Mockito.any(BigInteger.class)))
                    .thenReturn(expected);

            when(dataProvider
                    .getInvoiceBygetInvoiceByInvoiceNumber(
                            Mockito.any(BigInteger.class)))
                                    .thenReturn(expectedInvoice);
            manager.createInvoice(
                    InvoiceDTO.builder().value(INVOICE_VALUE * 10).build(),
                    BigInteger.ONE);

            Assertions.assertThrows(FlashtractBusinessException.class,
                    () -> manager.createInvoice(
                            InvoiceDTO.builder().value(INVOICE_VALUE * 10)
                                    .build(),
                            BigInteger.ONE));

        } catch (FlashtractBusinessException e) {
            assertTrue(true);
        } catch (FlashtractApplicationException e) {
            fail();
        }
    }

}

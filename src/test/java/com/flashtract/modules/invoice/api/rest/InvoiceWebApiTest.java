package com.flashtract.modules.invoice.api.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import com.flashtract.DataAcrossTestingProject;
import com.flashtract.crosscutting.domain.InvoiceDTO;
import com.flashtract.foundation.framework.exceptions.FlashtractApplicationException;
import com.flashtract.modules.invoice.usecase.InvoiceManager;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * ContractWebApi
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@ExtendWith(MockitoExtension.class)
public class InvoiceWebApiTest {

    @Mock
    private InvoiceManager manager;

    @InjectMocks
    private InvoiceWebApi api;

    @Test
    public void getInvoiceByInvoiceNumberTest() {

        InvoiceDTO expected = DataAcrossTestingProject.getInvoiceDTO();
        try {
            when(manager
                    .getInvoiceByInvoiceNumber(Mockito.any(BigInteger.class)))
                            .thenReturn(expected);
            ResponseEntity<Object> response = api
                    .getInvoiceByInvoiceNumber(BigInteger.TEN);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        } catch (FlashtractApplicationException e) {
            fail();
        }
    }

    @Test
    public void getInvoiceByInvoiceNumberTestException() {

        try {
            when(manager
                    .getInvoiceByInvoiceNumber(Mockito.any(BigInteger.class)))
                            .thenThrow(FlashtractApplicationException.class);
            ResponseEntity<Object> response = api
                    .getInvoiceByInvoiceNumber(BigInteger.TEN);
            assertEquals(HttpStatus.BAD_REQUEST,
                    response.getStatusCode());
        } catch (FlashtractApplicationException e) {
            fail();
        }
    }

}

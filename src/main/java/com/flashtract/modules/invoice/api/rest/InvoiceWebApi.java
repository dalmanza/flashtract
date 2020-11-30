package com.flashtract.modules.invoice.api.rest;

import com.flashtract.crosscutting.domain.InvoiceDTO;
import com.flashtract.foundation.framework.exceptions.FlashtractApplicationException;
import com.flashtract.foundation.framework.exceptions.util.ErrorResponseEntityBuilder;
import com.flashtract.modules.invoice.usecase.InvoiceManager;
import java.math.BigInteger;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ContractWebApi
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@Log4j2
@RestController
@RequestMapping(value = "/api/invoice")
public class InvoiceWebApi {

    @Autowired
    private InvoiceManager manager;

    @GetMapping("/{invoiceNumber}")
    public ResponseEntity<Object> getInvoiceByInvoiceNumber(
            @PathVariable("invoiceNumber")
            final BigInteger invoiceNumber) {
        log.info("web-api: Searching for all contracts");
        try {
            return ResponseEntity
                    .ok(manager.getInvoiceByInvoiceNumber(invoiceNumber));
        } catch (FlashtractApplicationException e) {
            log.error("web-api: There was an error searching contracts");
            return ErrorResponseEntityBuilder.builder().withException(e)
                    .build();
        }
    }

    @GetMapping("contractNumber/{contractNumber}")
    public ResponseEntity<Object> getInvoiceByContractNumber(
            @PathVariable(name = "contractNumber")
            final BigInteger contractNumber) {
        log.info(
                "web-api: Searching for invoices that belong to contract number: {}",
                contractNumber);
        try {
            return ResponseEntity.ok(manager
                    .getInvoiceByContractNumber(contractNumber));
        } catch (FlashtractApplicationException e) {
            log.error("web-api: There was an error searching invoices");
            return ErrorResponseEntityBuilder.builder().withException(e)
                    .build();
        }
    }

    @PostMapping("contractNumber/{contractNumber}")
    public ResponseEntity<Object> createInvoice(@RequestBody
    final InvoiceDTO invoice, @PathVariable("contractNumber")
    final BigInteger contractNumber) {
        log.info("web-api: Creating a new invoice");
        try {
            return ResponseEntity.ok(manager
                    .createInvoice(invoice, contractNumber));
        } catch (FlashtractApplicationException e) {
            log.error("web-api: There was an error creating invoice");
            return ErrorResponseEntityBuilder.builder().withException(e)
                    .build();
        }
    }

}

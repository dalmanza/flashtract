/*
 ****************************************************************
 *
 * EQ RAISE - Commercial Underwriting Portal.
 *
 * © 2019, Equitable Bank All rights reserved.
 *
 ****************************************************************
 */

package com.flashtract.modules.invoice.dataprovider;

import com.flashtract.crosscutting.domain.InvoiceDTO;
import com.flashtract.foundation.framework.exceptions.FlashtractSystemException;
import java.math.BigInteger;
import java.util.List;

/**
 * InvoiceDataProvider
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

public interface InvoiceDataProvider {

    public InvoiceDTO getInvoiceByInvoiceNumber(
            final BigInteger invoiceNumber) throws FlashtractSystemException;

    public List<InvoiceDTO> getInvoiceBygetInvoiceByInvoiceNumber(
            BigInteger contractNumber) throws FlashtractSystemException;

    public InvoiceDTO createInvoice(InvoiceDTO invoice, BigInteger contractId)
            throws FlashtractSystemException;

}

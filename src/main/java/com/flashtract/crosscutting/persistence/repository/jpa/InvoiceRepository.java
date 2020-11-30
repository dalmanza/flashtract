package com.flashtract.crosscutting.persistence.repository.jpa;

import com.flashtract.crosscutting.persistence.entity.Invoice;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * ContractRepository
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@Repository
public interface InvoiceRepository
        extends JpaRepository<Invoice, BigInteger> {

    @Query(" SELECT i FROM Invoice i WHERE i.invoiceNumber = :invoiceNumber")
    Invoice getInvoiceByInvoiceNumber(
            @Param("invoiceNumber") BigInteger invoiceNumber);

    @Query(" SELECT i FROM Invoice i WHERE i.contracts.contranctNumber = :contranctNumber")
    List<Invoice> getInvoiceByContractNumber(
            @Param("contranctNumber") BigInteger contranctNumber);

}

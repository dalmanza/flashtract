package com.flashtract.crosscutting.persistence.entity;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Invoice
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 *
 */

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INVOICES")
public class Invoice {

    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger invoiceId;

    @Column(name = "invoice_number")
    private BigInteger invoiceNumber;

    @Column(name = "value")
    private Double value;

    // bi-directional many-to-one association to DocumentationTrackingControl
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", referencedColumnName = "contract_id",
            nullable = false)
    private Contract contracts;

}

package com.flashtract.crosscutting.persistence.entity;

import com.flashtract.crosscutting.constants.ContractStatus;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contract
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
@Table(name = "CONTRACTS")
public class Contract {

    @Id
    @Column(name = "contract_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger contractId;

    @Column(name = "contract_number")
    private BigInteger contranctNumber;

    @Column(name = "to_bill")
    private Double toBill;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ContractStatus Status;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @OneToMany(mappedBy = "contracts")
    private List<Invoice> invoices;

}

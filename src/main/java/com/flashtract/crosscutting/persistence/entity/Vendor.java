package com.flashtract.crosscutting.persistence.entity;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Vendor
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
@Table(name = "VENDOR")
public class Vendor {

    @Id
    @Column(name = "vendor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger vendorId;

    @Column(name = "company_name")
    private String companyName;

}

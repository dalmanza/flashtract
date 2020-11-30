package com.flashtract.crosscutting.domain;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {

    private BigInteger vendorId;
    private String companyName;

}

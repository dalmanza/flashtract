package com.flashtract.crosscutting.domain;

import com.flashtract.crosscutting.constants.ContractStatus;
import java.math.BigInteger;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO {

    private BigInteger contranctNumber;
    private Double toBill;
    private ContractStatus Status;
    private VendorDTO vendor;
    private List<InvoiceDTO> invoice;
    private BigInteger contractId;
}

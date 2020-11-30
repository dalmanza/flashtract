package com.flashtract.crosscutting.domain;

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
public class InvoiceDTO {

    private BigInteger invoiceNumber;
    private Double value;
    private List<DocumentationDTO> documentation;
    private ContractDTO contract;

}

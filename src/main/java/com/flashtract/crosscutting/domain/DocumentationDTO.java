package com.flashtract.crosscutting.domain;

import com.flashtract.crosscutting.constants.DocumentType;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentationDTO {

    private BigInteger documentId;
    private DocumentType documentType;
}

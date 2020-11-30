package com.flashtract.modules.contract.usecase.rules.impl;

import com.flashtract.crosscutting.constants.ContractStatus;
import com.flashtract.crosscutting.domain.ContractDTO;
import com.flashtract.foundation.framework.stereotypes.DesignConcept;
import com.flashtract.modules.contract.usecase.rules.ruleValidator;

/**
 * ContractApproverByBill
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@DesignConcept
public class ContractApproverByBill implements ruleValidator {

    @Override
    public ContractDTO approveContract(final ContractDTO contract) {
        return ContractDTO.builder()
                .contranctNumber(contract.getContranctNumber())
                .Status(contract.getToBill() > 0 ? ContractStatus.APPROVED
                        : ContractStatus.DECLINED)
                .toBill(contract.getToBill())
                .invoice(contract.getInvoice())
                .vendor(contract.getVendor())
                .build();
    }

}

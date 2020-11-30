package com.flashtract.modules.contract.usecase.rules;

import com.flashtract.crosscutting.domain.ContractDTO;

/**
 * ruleValidator
 *
 * @author BlancLabs
 * @version 1.0
 * @since 2020-11-28
 */

@FunctionalInterface
public interface ruleValidator {
    ContractDTO approveContract(final ContractDTO contract);

}

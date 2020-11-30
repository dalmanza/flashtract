package com.flashtract.crosscutting.persistence.repository.jpa;

import com.flashtract.crosscutting.persistence.entity.Contract;
import com.flashtract.crosscutting.persistence.entity.Vendor;
import java.math.BigInteger;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
public interface ContractRepository
        extends JpaRepository<Contract, BigInteger> {

    @Query(" SELECT c FROM Contract c")
    List<Contract> getAllContracts();

    @Modifying
    @Transactional
    @Query(" UPDATE Contract c SET c.vendor = :#{#vendor}"
            + " WHERE c.contranctNumber = :contractNumber")
    void updateContractVendor(
            @Param("contractNumber") BigInteger contractNumber,
            @Param("vendor") Vendor vendor);

    @Query(" SELECT c FROM Contract c WHERE c.contranctNumber = :contractNumber")
    Contract getContractByContractNumber(
            @Param("contractNumber") BigInteger contractNumber);

}

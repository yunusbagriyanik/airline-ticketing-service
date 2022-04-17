package com.airlinesticketingbackend.repository.cust;

import com.airlinesticketingbackend.entity.cust.CustEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustEntityRepository extends JpaRepository<CustEntity, Long> {
    List<CustEntity> findAllByIsActv(Integer isActv);

    CustEntity findByCustCode(String custCode);

    CustEntity findByEmail(String email);
}

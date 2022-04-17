package com.airlinesticketingbackend.repository.general;

import com.airlinesticketingbackend.entity.general.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatusEntityRepository extends JpaRepository<StatusEntity, Long> {
    StatusEntity findByStatusIdAndIsActv(Long statusId, Integer isActv);

    StatusEntity findByShortCodeAndEntCodeNameAndIsActv(String shrtCode, String enitityCodeName, Integer isActv);

    @Query("select gs.statusId from StatusEntity gs " +
            "where gs.isActv = :isActv " +
            "and gs.shortCode = :shrtCode")
    Long findByShortCodeAndIsActv(@Param("shrtCode") String shrtCode, @Param("isActv") Integer isActv);
}

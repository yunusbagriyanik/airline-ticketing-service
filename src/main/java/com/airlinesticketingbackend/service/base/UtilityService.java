package com.airlinesticketingbackend.service.base;


import com.airlinesticketingbackend.dto.general.Status;

public interface UtilityService {
    String findStatusShortCode(Long stId);

    Long findGeneralStatusId(String shrtCode, String entityCodeName, Integer isActv);

    Status findGeneralStatus(String shrtCode, String entityCodeName, Integer isActv);

    Status findGeneralStatusById(Long gnlStId);
}

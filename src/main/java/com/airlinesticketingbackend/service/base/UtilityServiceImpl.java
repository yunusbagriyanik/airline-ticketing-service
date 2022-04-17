package com.airlinesticketingbackend.service.base;

import com.airlinesticketingbackend.base.Constants;
import com.airlinesticketingbackend.base.exception.ProvideExceptionHandler;
import com.airlinesticketingbackend.base.mapper.DozerMapper;
import com.airlinesticketingbackend.dto.general.Status;
import com.airlinesticketingbackend.entity.general.StatusEntity;
import com.airlinesticketingbackend.repository.general.StatusEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class UtilityServiceImpl extends AbstractService implements UtilityService {

    private final StatusEntityRepository gnlStEntityRepository;
    private final DozerMapper dozerMapper;

    @Autowired
    public UtilityServiceImpl(StatusEntityRepository gnlStEntityRepository, DozerMapper dozerMapper) {
        this.gnlStEntityRepository = gnlStEntityRepository;
        this.dozerMapper = dozerMapper;
    }

    @Override
    public String findStatusShortCode(Long stId) {
        StatusEntity gnlStEntity = gnlStEntityRepository.findByStatusIdAndIsActv(stId, Constants.TRUE_FLAG);
        Status generalStatus = dozerMapper.map(gnlStEntity, Status.class, "GnlStEntity_GeneralStatus");
        return generalStatus.getShortCode();
    }

    @Override
    public Long findGeneralStatusId(String shrtCode, String entityCodeName, Integer isActv) {
        final Status generalStatus = findGeneralStatus(shrtCode, entityCodeName, isActv);
        return ofNullable(generalStatus)
                .map(Status::getId)
                .orElseThrow(() -> new ProvideExceptionHandler("General status id not found. "));
    }

    @Override
    public Status findGeneralStatus(String shrtCode, String entityCodeName, Integer isActv) {
        StatusEntity gnlStEntity = gnlStEntityRepository.findByShortCodeAndEntCodeNameAndIsActv(shrtCode, entityCodeName, isActv);
        return dozerMapper.map(gnlStEntity, Status.class, "GnlStEntity_GeneralStatus");
    }

    @Override
    public Status findGeneralStatusById(Long gnlStId) {
        return Optional.ofNullable(this.gnlStEntityRepository.findByStatusIdAndIsActv(gnlStId, Constants.TRUE_FLAG))
                .map(stEntity -> this.dozerMapper.map(stEntity, Status.class, "GnlStEntity_GeneralStatus"))
                .orElseThrow(() -> new ProvideExceptionHandler("General status not found!"));
    }
}

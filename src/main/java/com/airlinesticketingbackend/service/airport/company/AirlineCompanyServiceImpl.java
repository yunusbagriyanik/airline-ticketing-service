package com.airlinesticketingbackend.service.airport.company;

import com.airlinesticketingbackend.base.exception.ProvideExceptionHandler;
import com.airlinesticketingbackend.base.mapper.DozerMapper;
import com.airlinesticketingbackend.dto.SearchRequest;
import com.airlinesticketingbackend.dto.airport.AirlineCompany;
import com.airlinesticketingbackend.dto.common.ProcessResult;
import com.airlinesticketingbackend.entity.airport.AirlineCompanyEntity;
import com.airlinesticketingbackend.repository.airline.AirlineCompanyEntityRepository;
import com.airlinesticketingbackend.service.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AirlineCompanyServiceImpl extends AbstractService implements IAirlineCompanyService {

    private final AirlineCompanyEntityRepository airlineCompanyRepository;
    private final DozerMapper dozerMapper;

    @Autowired
    public AirlineCompanyServiceImpl(AirlineCompanyEntityRepository airlineCompanyRepository, DozerMapper dozerMapper) {
        this.airlineCompanyRepository = airlineCompanyRepository;
        this.dozerMapper = dozerMapper;
    }

    @Override
    @Transactional
    public ProcessResult createAirlineCompany(AirlineCompany request) {
        this.validateAirlineCompanyRequest(request);
        this.checkIfExistingAirlineCompany(request.getAirlineCompanyName());

        Optional.ofNullable(dozerMapper.map(request, AirlineCompanyEntity.class, "AirlineCompany_AirlineCompanyEntity"))
                .map(airlineCompanyRepository::save)
                .orElseThrow(() -> new ProvideExceptionHandler("An error was received while registering the airline company."));

        return ProcessResult.success("Create Airline Company API");
    }

    private void validateAirlineCompanyRequest(AirlineCompany request) {
        if (request.getAirlineCompanyName() == null)
            throw new ProvideExceptionHandler("AIRPORT_NAME_CANNOT_BE_NULL.");
    }

    private void checkIfExistingAirlineCompany(String name) {
        boolean isPresent = Optional.ofNullable(this.airlineCompanyRepository.findByName(name)).isPresent();

        if (isPresent)
            throw new ProvideExceptionHandler("There is already a airline company with this name: {}", name);
    }

    @Override
    @Transactional
    public AirlineCompany searchAirlineCompany(SearchRequest request) {
        return Optional.ofNullable(this.airlineCompanyRepository.findByName(request.getFieldName()))
                .map(company -> this.dozerMapper.map(company, AirlineCompany.class, "AirlineCompanyEntity_AirlineCompany"))
                .orElseThrow(() -> new ProvideExceptionHandler("This company not found. " + request.getFieldName()));
    }
}

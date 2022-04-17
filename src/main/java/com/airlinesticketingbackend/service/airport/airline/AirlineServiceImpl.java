package com.airlinesticketingbackend.service.airport.airline;

import com.airlinesticketingbackend.base.exception.ProcessResultException;
import com.airlinesticketingbackend.base.mapper.DozerMapper;
import com.airlinesticketingbackend.dto.SearchRequest;
import com.airlinesticketingbackend.dto.address.Address;
import com.airlinesticketingbackend.dto.airport.CreateAirlineRequest;
import com.airlinesticketingbackend.dto.airport.Route;
import com.airlinesticketingbackend.dto.airport.SearchAirlineResponse;
import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.common.ProcessResult;
import com.airlinesticketingbackend.entity.address.AddressEntity;
import com.airlinesticketingbackend.entity.airport.AirportEntity;
import com.airlinesticketingbackend.entity.flight.RouteEntity;
import com.airlinesticketingbackend.repository.address.AddressEntityRepository;
import com.airlinesticketingbackend.repository.airline.AirportEntityRepository;
import com.airlinesticketingbackend.repository.airline.RouteEntityRepository;
import com.airlinesticketingbackend.service.base.AbstractService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AirlineServiceImpl extends AbstractService implements AirlineService {

    private final AirportEntityRepository airportRepository;
    private final AddressEntityRepository addressEntityRepository;
    private final RouteEntityRepository routeRepository;
    private final DozerMapper dozerMapper;

    public AirlineServiceImpl(AirportEntityRepository airportRepository, AddressEntityRepository addressEntityRepository, RouteEntityRepository routeRepository, DozerMapper dozerMapper) {
        this.airportRepository = airportRepository;
        this.addressEntityRepository = addressEntityRepository;
        this.routeRepository = routeRepository;
        this.dozerMapper = dozerMapper;
    }

    @Override
    @Transactional
    public GenericResult<CreateAirlineRequest> createAirport(CreateAirlineRequest request) {
        this.validateBuildAirportRequest(request);
        GenericResult<CreateAirlineRequest> response = new GenericResult<>();
        try {
            AirportEntity airportEntity = new AirportEntity();

            Address addr = this.validateAddressRequest(request.getAddress());
            if (addr == null) {
                AddressEntity address = this.buildAirportAddress(request.getAddress());
                airportEntity.setName(request.getAirportName());
                airportEntity.setAddrId(address.getAddrId());

                this.airportRepository.save(airportEntity);
            } else {
                airportEntity.setName(request.getAirportName());
                airportEntity.setAddrId(addr.getAddressId());
                this.airportRepository.save(airportEntity);
            }

            response.setProcessResult(ProcessResult.success("Create Airport API"));
            response.setResult(request);

            return response;
        } catch (Exception e) {
            ProcessResult errorResult = ProcessResult.internalServerError("An error occurred while persist the airport. " + e.getMessage());
            response.setProcessResult(errorResult);

            return response;
        }
    }

    @Override
    @Transactional
    public SearchAirlineResponse createAirport(SearchRequest request) {
        return Optional.ofNullable(this.airportRepository.findByName(request.getFieldName()))
                .map(airportEntity -> this.dozerMapper.map(airportEntity, SearchAirlineResponse.class, "AirportEntity_SearchAirlineResponse"))
                .orElseThrow(() -> new ProcessResultException(
                        ProcessResult
                                .noContent("AIRPORT_NOT_FOUND"), false));
    }

    @Override
    public ProcessResult createRoute(Route request) {
        Optional.ofNullable(this.dozerMapper.map(request, RouteEntity.class, "Route_RouteEntity"))
                .map(this.routeRepository::save)
                .map(routeEntity -> this.dozerMapper.map(routeEntity, Route.class, "RouteEntity_Route"))
                .orElseThrow(() -> new ProcessResultException(
                        ProcessResult
                                .internalServerError("GET_ERROR_WHILE_PERSIST_ROUTE"), false));

        return ProcessResult.success("Create Route API");
    }

    @Override
    public Route inquireRouteByRouteId(Long routeId) {
        return Optional.ofNullable(this.routeRepository.findByRouteId(routeId))
                .map(route -> this.dozerMapper.map(route, Route.class, "RouteEntity_Route"))
                .orElseThrow(() -> new ProcessResultException(
                        ProcessResult
                                .noContent("ROUTE_NOT_FOUND"), false));
    }

    private AddressEntity buildAirportAddress(Address request) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCountry(request.getCountryName());
        addressEntity.setProvince(request.getProvinceName());

        return this.addressEntityRepository.save(addressEntity);
    }

    private void validateBuildAirportRequest(CreateAirlineRequest request) {
        if (StringUtils.isBlank(request.getAirportName()))
            throw new ProcessResultException(ProcessResult.badRequest("AIRPORT_NAME_CANNOT_BE_EMPTY" + request.getAirportName()), true);
        if (request.getAirportName() == null)
            throw new ProcessResultException(ProcessResult.badRequest("AIRPORT_NAME_CANNOT_BE_NULL" + request.getAirportName()), true);
        if (this.checkIfExistingAirport(request.getAirportName()) != null)
            throw new ProcessResultException(ProcessResult.badRequest("There is already a airport with this name. " + request.getAirportName()), true);
        if (request.getAddress().getCountryName() == null)
            throw new ProcessResultException(ProcessResult.badRequest("COUNTRY_NAME_CANNOT_BE_NULL" + request.getAddress().getCountryName()), true);
        if (request.getAddress().getProvinceName() == null)
            throw new ProcessResultException(ProcessResult.badRequest("PROVINCE_NAME_CANNOT_BE_NULL" + request.getAddress().getProvinceName()), true);
    }

    private AirportEntity checkIfExistingAirport(String airportName) {
        AirportEntity airportEntity = this.airportRepository.findByName(airportName);
        if (airportEntity != null) {
            return airportEntity;
        }

        return null;
    }

    private Address validateAddressRequest(Address request) {
        return Optional.ofNullable(this.addressEntityRepository.findByCountryOrProvince(request.getCountryName(), request.getProvinceName()))
                .map(ae -> this.dozerMapper.map(ae, Address.class, "AddressEntity_Address"))
                .orElse(null);
    }
}

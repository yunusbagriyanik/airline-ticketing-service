package com.airlinesticketingbackend.entity.airport;

import com.airlinesticketingbackend.base.Constants;
import com.airlinesticketingbackend.entity.address.AddressEntity;
import com.airlinesticketingbackend.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "airport")
public class AirportEntity extends BaseEntity {
    private Long airportId;
    private Long addrId;
    private String name;
    private Integer isActv;
    private AddressEntity addressEntity;

    @PrePersist
    public void onPrePersist() {
        this.isActv = Constants.TRUE_FLAG;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id")
    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    @Basic
    @Column(name = "addr_id")
    public Long getAddrId() {
        return addrId;
    }

    public void setAddrId(Long addrId) {
        this.addrId = addrId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "is_actv")
    public Integer getIsActv() {
        return isActv;
    }

    public void setIsActv(Integer isActv) {
        this.isActv = isActv;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addr_id", insertable = false, updatable = false)
    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirportEntity that = (AirportEntity) o;
        return Objects.equals(airportId, that.airportId) &&
                Objects.equals(addrId, that.addrId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(isActv, that.isActv) &&
                Objects.equals(addressEntity, that.addressEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airportId, addrId, name, isActv, addressEntity);
    }
}

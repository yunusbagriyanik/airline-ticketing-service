package com.airlinesticketingbackend.entity.address;

import com.airlinesticketingbackend.base.Constants;
import com.airlinesticketingbackend.entity.airport.AirportEntity;
import com.airlinesticketingbackend.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "addr")
public class AddressEntity extends BaseEntity {
    private Long addrId;
    private String country;
    private String province;
    private Integer isActv;
    private List<AirportEntity> airportEntityList;

    @PrePersist
    public void onPrePersist() {
        this.isActv = Constants.TRUE_FLAG;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addr_id")
    public Long getAddrId() {
        return addrId;
    }

    public void setAddrId(Long addrId) {
        this.addrId = addrId;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "is_actv")
    public Integer getIsActv() {
        return isActv;
    }

    public void setIsActv(Integer isActv) {
        this.isActv = isActv;
    }

    @OneToMany(mappedBy = "addressEntity", cascade = CascadeType.ALL)
    public List<AirportEntity> getAirportEntityList() {
        return airportEntityList;
    }

    public void setAirportEntityList(List<AirportEntity> airportEntityList) {
        this.airportEntityList = airportEntityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(addrId, that.addrId) &&
                Objects.equals(country, that.country) &&
                Objects.equals(province, that.province) &&
                Objects.equals(isActv, that.isActv) &&
                Objects.equals(airportEntityList, that.airportEntityList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addrId, country, province, isActv, airportEntityList);
    }
}

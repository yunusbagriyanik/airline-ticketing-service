package com.airlinesticketingbackend.entity.airport;

import com.airlinesticketingbackend.base.Constants;
import com.airlinesticketingbackend.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "airline_company")
public class AirlineCompanyEntity extends BaseEntity {
    private Long airlineCompanyId;
    private String name;
    private Integer isActv;

    @PrePersist
    public void onPrePersist() {
        this.isActv = Constants.TRUE_FLAG;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_comp_id")
    public Long getAirlineCompanyId() {
        return airlineCompanyId;
    }

    public void setAirlineCompanyId(Long airlineCompanyId) {
        this.airlineCompanyId = airlineCompanyId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirlineCompanyEntity that = (AirlineCompanyEntity) o;
        return Objects.equals(airlineCompanyId, that.airlineCompanyId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(isActv, that.isActv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airlineCompanyId, name, isActv);
    }
}

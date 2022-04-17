package com.airlinesticketingbackend.entity.cust;

import com.airlinesticketingbackend.base.Constants;
import com.airlinesticketingbackend.entity.base.BaseEntity;
import com.airlinesticketingbackend.entity.general.StatusEntity;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cust")
public class CustEntity extends BaseEntity {
    private Long custId;
    private String custCode;
    private Long statusId;
    private Integer isActv;
    private String username;
    private String firstName;
    private String lastName;
    private StatusEntity gnlStEntity;
    private String fullName;
    private String email;

    @PrePersist
    public void onPrePersist() {
        this.isActv = Constants.TRUE_FLAG;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    @Basic
    @Column(name = "cust_code")
    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    @Basic
    @Column(name = "cust_st_id")
    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "is_actv")
    public Integer getIsActv() {
        return isActv;
    }

    public void setIsActv(Integer isActv) {
        this.isActv = isActv;
    }

    @Basic
    @Column(name = "uname")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToOne(targetEntity = StatusEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cust_st_id", referencedColumnName = "gnl_st_id", insertable = false, updatable = false)
    public StatusEntity getGnlStEntity() {
        return gnlStEntity;
    }

    public void setGnlStEntity(StatusEntity gnlStEntity) {
        this.gnlStEntity = gnlStEntity;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(this.firstName)) {
            sb.append(this.firstName);
            sb.append(" ");
        }
        if (!StringUtils.isEmpty(this.lastName)) {
            sb.append(this.lastName);
        }

        this.fullName = sb.toString().trim();

        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustEntity that = (CustEntity) o;
        return Objects.equals(custId, that.custId) &&
                Objects.equals(custCode, that.custCode) &&
                Objects.equals(statusId, that.statusId) &&
                Objects.equals(isActv, that.isActv) &&
                Objects.equals(username, that.username) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(gnlStEntity, that.gnlStEntity) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(custId, custCode, statusId, isActv, username, firstName, lastName, gnlStEntity, fullName, email);
    }
}

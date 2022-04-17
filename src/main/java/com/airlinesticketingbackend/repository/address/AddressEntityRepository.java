package com.airlinesticketingbackend.repository.address;

import com.airlinesticketingbackend.entity.address.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressEntityRepository extends JpaRepository<AddressEntity, Long> {

    @Query("select ae from AddressEntity ae " +
            "where ae.country = :countryName " +
            "and ae.province = :provinceName ")
    AddressEntity findByCountryOrProvince(String countryName, String provinceName);
}

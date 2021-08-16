package com.ewhaenonymous.ttockclinic.repository;

import com.ewhaenonymous.ttockclinic.domain.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface ClinicRepository extends CrudRepository<Clinic, Long> {
    Optional<Clinic> findByLatitudeAndLongitude(String latitude, String longitude);
}

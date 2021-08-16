package com.ewhaenonymous.ttockclinic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    List<Clinic> findByCnameAndLoc(String cname, String loc);

    @Transactional
    @Modifying
    @Query("UPDATE Clinic c SET c.waitings = :to")
    int updateWaitings(int to);
}

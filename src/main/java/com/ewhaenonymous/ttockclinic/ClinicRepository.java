package com.ewhaenonymous.ttockclinic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    List<Clinic> findByNameAndLoc(String cname, String loc);

    @Transactional
    @Modifying
    @Query("UPDATE Clinic c SET c.waitings = :to WHERE c.waitings = :from")
    int updateWaitings(int from, int to);
}

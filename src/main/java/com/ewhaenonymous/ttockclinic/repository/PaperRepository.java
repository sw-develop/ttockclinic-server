package com.ewhaenonymous.ttockclinic.repository;

import com.ewhaenonymous.ttockclinic.domain.Paper;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PaperRepository extends CrudRepository<Paper, Long> {

    Optional<Paper> findByPhoneAndName(String phone, String name);
    Optional<Paper> findByPhone(String phone);
}

package com.ewhaenonymous.ttockclinic.repository;

import com.ewhaenonymous.ttockclinic.domain.Paper;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Stack;

public interface PaperRepository extends CrudRepository<Paper, Long> {
    Optional<Paper> findByPhoneAndNameAndDeleted(String phone, String name, String deleted);
    Optional<Paper> findByPhoneAndDate(String phone, LocalDate date);
    Optional<Paper> findByIdAndDeleted(Long id, String deleted);
    Stack<Paper> findByDeleted(String deleted);
}

package com.mark.projectmng.repository;

import com.mark.projectmng.domain.Emp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Emp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmpRepository extends JpaRepository<Emp, Long> {}

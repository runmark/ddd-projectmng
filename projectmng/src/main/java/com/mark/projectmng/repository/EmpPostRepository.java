package com.mark.projectmng.repository;

import com.mark.projectmng.domain.EmpPost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the EmpPost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmpPostRepository extends JpaRepository<EmpPost, EmpPostId> {}

package com.mark.projectmng.repository;

import com.mark.projectmng.domain.OrgType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OrgType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrgTypeRepository extends JpaRepository<OrgType, Long> {}
